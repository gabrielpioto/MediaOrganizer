package br.com.moviezer.opensubtitle;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.zip.GZIPInputStream;

import org.apache.ws.commons.util.Base64;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

public class AsyncOpenSubAPI {

	private String token;
	private XmlRpcClient client;

	private static final String BASE_URL = "http://api.opensubtitles.org/xml-rpc";
	private static final String USER_AGENT = "Hardsoft v1";
	public static final int MOVIE_LIMIT = 200;
	

	public AsyncOpenSubAPI(String user, String password) {

		client = new XmlRpcClient();
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		try {
			config.setServerURL(new URL(BASE_URL));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		client.setConfig(config);
		token = (String) execute(Method.LOGIN, user, password, "en", USER_AGENT)
				.get("token");
	}

	public void calculateHashes(Collection<Path> paths,
			BiConsumer<Path, String> consumer) {
		for (Path path : paths) {
			String hash = OpenSubtitlesHasher.getHash(path);
			consumer.accept(path, hash);
		}
	}

	public void getSubtitles(Collection<Movie> movies, String subLanguage,
			String searchBy, Consumer<Subtitle> consumer) {
		// max 500 ou 100 sei la
		Map<String, String>[] searches = null;
		switch (searchBy) {
		case "hash":
			searches = getSearchesByHash(movies, subLanguage);
			break;
		case "imdbId":
			searches = getSearchesByImdbId(movies, subLanguage);
			break;
		}

		Object subData = execute(Method.SEARCH, token, searches).get("data");
		if (!(subData instanceof Object[])) {
			subData = new Object[0];
		}

		Object[] subs = (Object[]) subData;
		for (Object sub : subs) {
			@SuppressWarnings("unchecked")
			Subtitle subtitle = convertToSubtitle((Map<String, String>) sub);
			consumer.accept(subtitle);
		}
	}

	@SuppressWarnings("unchecked")
	public void downloadSubtitles(Iterable<Subtitle> subs,
			BiConsumer<Subtitle, byte[]> consumer) {

		Map<String, Subtitle> subMap = new HashMap<String, Subtitle>();
		for (Subtitle sub : subs) {
			subMap.put(sub.getIdFile(), sub);
		}
		

		Map<String, Object> temp = execute(Method.DOWNLOAD, token, subMap
				.keySet().toArray());

		Object[] data = (Object[]) temp.get("data");
		try {
			for (Object o : data) {

				temp = (Map<String, Object>) o;
				byte[] decodedBytes = Base64.decode((String) temp.get("data"));
				byte[] subtitle = gunzip(decodedBytes);
				Subtitle sub = subMap.get((String) temp.get("idsubtitlefile"));
				consumer.accept(sub, subtitle);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// hash -> imdbId
	@SuppressWarnings("unchecked")
	public void getMovieInfo(Collection<String> hashes, Consumer<Movie> consumer) {
		if(hashes.size()>MOVIE_LIMIT) throw new IllegalArgumentException("Too Many Videos, limit is "
				+ MOVIE_LIMIT);
		Map<String, Object> movieData = execute(Method.MOVIE, token,
				hashes.toArray());
		
		movieData = (Map<String, Object>) movieData.get("data");
		for (String hash : hashes) {
			Object movie = movieData.get(hash);
			if (movie instanceof Map) {
				Map<String,String> movieMap = (Map<String, String>) movie;
				System.out.println(movieMap);
				consumer.accept(convertToMovie(movieMap));				
			}
		}
	}

	

	private Map<String, String>[] getSearchesByHash(Collection<Movie> movies,
			String subLanguage) {
		@SuppressWarnings("unchecked")
		Map<String, String>[] searches = new Map[movies.size()];
		int i = 0;

		for (Movie movie : movies) {
			searches[i] = new HashMap<String, String>();
			searches[i].put("moviehash", movie.getMovieHash());
			searches[i].put("sublanguageid", subLanguage);
			i++;
		}
		return searches;
	}

	private Map<String, String>[] getSearchesByImdbId(Collection<Movie> movies,
			String subLanguage) {
		@SuppressWarnings("unchecked")
		Map<String, String>[] searches = new Map[movies.size()];
		int i = 0;

		for (Movie movie : movies) {
			searches[i] = new HashMap<String, String>();
			searches[i].put("imdbid", movie.getImdbId());
			searches[i].put("sublanguageid", subLanguage);
			i++;
		}
		return searches;
	}

	private Subtitle convertToSubtitle(Map<String, String> subMap) {
		Subtitle sub = new Subtitle(subMap.get("MovieHash"),
				subMap.get("IDSubtitleFile"));
		sub.setId(subMap.get("IDSubtitle"));
		sub.setFormat(subMap.get("SubFormat"));
		sub.setMatchedBy(subMap.get("MatchedBy"));
		sub.setFilename(subMap.get("SubFileName"));
		sub.setLanguageId(subMap.get("SubLanguageID"));
		sub.setLanguageName(subMap.get("LanguageName"));
		sub.setRating(Double.parseDouble(subMap.get("SubRating")));
		sub.setMovieImdbId(Integer.parseInt(subMap.get("IDMovieImdb")));
		sub.setDownloadCnt(Long.parseLong(subMap.get("SubDownloadsCnt")));
		

		return sub;
	}

	private Movie convertToMovie(Map<String, String> movieMap) {
		Movie m = new Movie(movieMap.get("MovieHash"),
				movieMap.get("MovieImdbID"));
		m.setMovieName(movieMap.get("MovieName"));
		m.setMovieKind(movieMap.get("MovieKind"));
		m.setSubCount(Long.parseLong(movieMap.get("SubCount")));
		m.setSeenCount(Long.parseLong(movieMap.get("SeenCount")));
		m.setMovieYear(Integer.parseInt(movieMap.get("MovieYear")));
		m.setSeriesSeason(Integer.parseInt(movieMap.get("SeriesSeason")));
		m.setSeriesEpisode(Integer.parseInt(movieMap.get("SeriesEpisode")));

		return m;
	}
	
	private byte[] gunzip(byte[] bytes) throws IOException {
		GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(
				bytes));
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		OutputStream out = new BufferedOutputStream(baos);
		byte[] buffer = new byte[1024];
		while (true) {
			synchronized (buffer) {
				int amountRead = gis.read(buffer);
				if (amountRead == -1) {
					break;
				}
				out.write(buffer, 0, amountRead);
			}
		}
		out.flush();
		out.close();
		return baos.toByteArray();
	}

	@SuppressWarnings("unchecked")
	private Map<String, Object> execute(Method method, Object... params) {
		Map<String, Object> result = null;
		try {
			System.out.println("Executando metodo: " + method.getName());

			result = (Map<String, Object>) client.execute(method.getName(),
					params);

			System.out.println(method.getName() + " executado");
		} catch (XmlRpcException e) {
			e.printStackTrace();
		}
		return result;
	}

}
