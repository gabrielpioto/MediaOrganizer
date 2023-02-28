package br.com.moviezer;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import br.com.moviezer.api.ImdbApi;
import br.com.moviezer.api.TheMovieApi;
import br.com.moviezer.model.EpisodeDetails;
import br.com.moviezer.model.Video;
import br.com.moviezer.model.VideoDetails;
import br.com.moviezer.opensubtitle.AsyncOpenSubAPI;
import br.com.moviezer.opensubtitle.Movie;
import br.com.moviezer.opensubtitle.Subtitle;
import br.com.moviezer.pathbuilder.MyPathBuilder;

public class OpenSubController {

	public static final int SIZE_LIMIT = 200;

	private Map<String, Video> videoMap;
	private Map<Integer, Movie> movieMap;
	private AsyncOpenSubAPI aosa;
	private boolean isSubtitleLoaded;
	private boolean isDetailsLoaded;
	private String language;

	public OpenSubController(Collection<Path> paths) {
		language = "en";
		aosa = new AsyncOpenSubAPI("", "");
		videoMap = new HashMap<>();
		movieMap = new HashMap<>();
		aosa.calculateHashes(paths, (path, hash) -> {
			System.out.println(hash+" - "+path.getFileName().toString());
			if (videoMap.put(hash, new Video(path)) != null) {
				
				System.out.println("BOOOM!");
				System.exit(-1);
			}
		});
	}
	
	private void loadDetails() {
		int i,j;
		List<String> hashes = new ArrayList<>(videoMap.keySet());
		int splitSize = videoMap.size()/AsyncOpenSubAPI.MOVIE_LIMIT + 1;
		int factor = videoMap.size() / splitSize + 1;
		int mod = videoMap.size() % splitSize;
		
		for(i = 1, j = 0;i<splitSize;i++, j+=factor){
			loadDetails(hashes.subList(j, factor+j));
		}
		loadDetails(hashes.subList(j, factor+j-splitSize+mod));
		
		isDetailsLoaded = true;
		System.out.println("movies: " + movieMap.size() + "  Videos: "
				+ videoMap.size());
	}
	
	private void loadDetails(Collection<String> c){
		aosa.getMovieInfo(c, (movie) -> {
			int imdbId = Integer.parseInt(movie.getImdbId());
			Movie m = movieMap.put(imdbId, movie);
			if (m != null) {
				System.out.println("ERROR... same imdbId for "
						+ m.getMovieHash() + " and "
						+ movie.getMovieHash());
				movieMap.remove(imdbId);
			} else {
				switch (movie.getMovieKind()) {
				case "movie":
					VideoDetails md = TheMovieApi.getMovieDetails(
							movie.getImdbID(), language);
					videoMap.get(movie.getMovieHash()).setDetails(md);
					break;
				case "episode":
					EpisodeDetails ed = ImdbApi.getEpisodeDetails(
							movie.getImdbID(), language);
					// System.out.println(movie.getImdbID());
					ed.setEpisodeNumber(movie.getSeriesEpisode());
					ed.setSeasonNumber(movie.getSeriesSeason());
					videoMap.get(movie.getMovieHash()).setDetails(ed);
				}
			}
		});
	}

	private void loadSubtitles(String subLanguage) {
		if (!isDetailsLoaded) {
			aosa.getMovieInfo(videoMap.keySet(), (movie) -> {
				System.out.println(movie.getImdbId());
				int imdbId = Integer.parseInt(movie.getImdbId());
				movieMap.put(imdbId, movie);
			});
		}

		aosa.getSubtitles(movieMap.values(), subLanguage, "hash",
				(subtitle) -> {
					String hash = subtitle.getMovieHash();
					videoMap.get(hash).addSubtitle(subtitle);
				});
		isSubtitleLoaded = true;
	}

	public void rename(String code) {
		if (!isDetailsLoaded)
			loadDetails();
		try {
			for (Movie m : movieMap.values()) {
				Video v = videoMap.get(m.getMovieHash());
				v.rename(code);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void move(String baseDir, MyPathBuilder pb) {

		if (!isDetailsLoaded)
			loadDetails();
		try {
			for (Movie m : movieMap.values()) {
				Video v = videoMap.get(m.getMovieHash());
				v.move(baseDir, pb);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void downloadBestsSubtitles(String subLanguage, boolean byImdb) {
		if (!isSubtitleLoaded)
			loadSubtitles(subLanguage);

		List<Subtitle> subs = new ArrayList<>(videoMap.size());
		List<Movie> subsNotFound = new ArrayList<>();

		for (Movie m : movieMap.values()) {
			Video v = videoMap.get(m.getMovieHash());
			Subtitle sub = v.getSubtitle(0);
			if (sub != null)
				subs.add(sub);
			else
				subsNotFound.add(m);
		}

		if (subsNotFound.size() != 0 && byImdb) {
			aosa.getSubtitles(subsNotFound, subLanguage, "imdbId",
					(subtitle) -> {
						Movie m = movieMap.get(subtitle.getMovieImdbId());
						Video v = videoMap.get(m.getMovieHash());
						v.addSubtitle(subtitle);
					});

			for (Movie m : subsNotFound) {
				Video v = videoMap.get(m.getMovieHash());
				Subtitle sub = v.getSubtitle(0);
				if (sub != null)
					subs.add(sub);
				else
					System.out.println("SubReallyNotFound: "
							+ v.getFile().toString());
			}
		}

		aosa.downloadSubtitles(subs, (subtitle, data) -> {

			Movie m = movieMap.get(subtitle.getMovieImdbId());
			Path file = videoMap.get(m.getMovieHash()).getFile();

			String filename = file.getFileName().toString();
			filename = filename.substring(0, filename.lastIndexOf('.'));
			filename += "." + subtitle.getFormat();
			String dir = file.getParent().toString();
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(dir + File.separator + filename);
				fos.write(data);
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				try {
					fos.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

	}
}
