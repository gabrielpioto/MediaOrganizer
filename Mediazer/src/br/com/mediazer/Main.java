package br.com.mediazer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import br.com.mediazer.model.EpisodeDetails;
import br.com.mediazer.model.FilmDetails;
import br.com.mediazer.model.MediaDetails;
import br.com.mediazer.model.Movie;
import br.com.mediazer.pathbuilder.PathBuilder;
import br.com.mediazer.pathbuilder.PathBuilders;
import br.com.mediazer.util.Finder;
import br.com.mediazer.util.ObjectUtil;

import com.mediazer.imdb.ImdbApi;
import com.mediazer.osub.OSubApi;
import com.mediazer.osub.SearchBuilder;
import com.mediazer.osub.SearchBuilder.SearchTerm;
import com.mediazer.osub.model.SubtitleOs;
import com.mediazer.osub.util.OpenSubtitlesHasher;
import com.mediazer.subdb.TheSubDbApi;
import com.mediazer.tmdb.TmdbApi;
import com.mediazer.tmdb.model.movie.MovieInfo;

public class Main {

	private TmdbApi tmdb;
	private OSubApi osapi;
	private ImdbApi imdb;
	private TheSubDbApi tsdb;

	private Map<String, Movie> movieMap;
	private Map<String, String> imdbMap;

	private boolean isDetailsLoaded;
	private boolean isSubtitleLoaded;

	private int n;
	private Mode mode;
	// private static final String SOURCE_DIR =
	// "E:\\Media\\Series\\Game of Thrones";

	private static final String SOURCE_DIR = "/home/gabriel/HD/Media/Series/Game of Thrones/Season 5";

	public static void main(String[] args) throws Exception {
		Finder finder = new Finder("*.{mp4,mkv,wmv,avi}");

		Files.walkFileTree(Paths.get(SOURCE_DIR), finder);
		List<Path> files = finder.getMatchedFiles();
		System.out.println("Size: " + files.size());
		Main main = new Main(files, Mode.ONLY_SUBTITLES);

//		main.move("/home/gabriel/HD/", PathBuilders.DEFAULT);
//		main.rename("%EpisodeNumber% - %Title%");
		main.downloadSubtitles2("pt");	

	}

	public Main(Collection<Path> files, Mode mode) {
		this.mode = mode;
		Function<Path, String> hasher = null;
		movieMap = new HashMap<>();

		switch (mode) {
		case FULL_INFO:
			tmdb = new TmdbApi("fa972b7ad92b3145d8babab8f95abd7c");
			osapi = new OSubApi("Hardsoft v1");
			imdb = new ImdbApi();
			imdbMap = new HashMap<>(files.size());
			hasher = OpenSubtitlesHasher::computeHash;
			break;
		case ONLY_SUBTITLES:
			tsdb = new TheSubDbApi("Subdb", "1.0.1", "http://github.com/arshad/subdb-cli");
			hasher = TheSubDbApi.Hasher::computeHash;
			break;
		}

		for (Path f : files) {
			String hash = hasher.apply(f);
			// System.out.println(hash);
			Movie d = movieMap.put(hash, new Movie(f));
			if (d != null) {
				throw new IllegalArgumentException("duplicate file: " + f + " & "
						+ d.getFile());
			}
		}

		isDetailsLoaded = false;
		isSubtitleLoaded = false;
	}

	private void loadDetails() {
		if (mode != Mode.FULL_INFO)
			throw new IllegalStateException("mode does not match");
		List<String> hashes = new ArrayList<>(movieMap.keySet());
		ObjectUtil.split(hashes, OSubApi.MOVIE_LIMIT, this::loadDetails);

		isDetailsLoaded = true;
	}

	private void loadDetails(Collection<String> hashes) {

		osapi.checkMovieHash(
				hashes,
				movieOs -> {
					String newHash = movieOs.getMovieHash();
					String imdbId = movieOs.getMovieImdbID();
					Movie m = movieMap.get(newHash);
					if (StringUtils.isBlank(imdbId)) {
						// TODO: proper log (severe)
						System.out.println(m + " -------NAO ENCONTRADOOO-------");
						movieMap.remove(newHash);
					} else {
						String oldHash = imdbMap.put(imdbId, newHash);
						if (oldHash != null) {
							System.out.println("DUPLICATE IMDB ID: " + m + " "
									+ movieMap.get(oldHash));
							movieMap.remove(newHash);
							movieMap.remove(oldHash);
						} else {
							MediaDetails details = m.getDetails();

							if (movieOs.getMovieKind().equals("episode")) {
								details = new EpisodeDetails(movieOs);
							} else {
								MovieInfo info = tmdb.getMovieInfo(imdbId, "pt", null);
								details = new FilmDetails(info);
								// details = new FilmDetails(movieOs);
							}
							m.setDetails(details);
						}
					}
				});
	}

	private void loadSubtitles() {
		if (mode != Mode.FULL_INFO)
			throw new IllegalStateException("mode does not match");
		List<SearchBuilder> searches = movieMap.keySet().stream()
				.filter(h -> StringUtils.isNotBlank(h))
				.map(h -> new SearchBuilder(h, "pob", SearchTerm.MOVIE_HASH))
				.collect(Collectors.toList());
		ObjectUtil.split(searches, 250, this::loadSubtitles); // TODO: 70
																// hardcoded
		isSubtitleLoaded = true;
	}

	private void loadSubtitles(Collection<SearchBuilder> builders) {
		n = 0;
		System.out.println("Total searches: " + builders.size());
		osapi.searchSubtitles(builders, sub -> {
			movieMap.get(sub.getMovieHash()).getSubtitles().add(sub);
			n++;
		});
		System.out.println("Total subs: " + n);
	}

	public void move(String baseDir, PathBuilder<MediaDetails> pb) {
		if (!isDetailsLoaded)
			loadDetails();
		try {
			for (Movie m : movieMap.values()) {
				String strDestPath = pb.build(m.getDetails());
				strDestPath += m.getFile().getFileName();
				strDestPath = baseDir + strDestPath;
				Path destPath = Paths.get(strDestPath);
				if (!m.getFile().equals(destPath)) {
					System.out.println(destPath);
					Files.createDirectories(destPath.getParent());
					Files.move(m.getFile(), destPath);
					m.setFile(destPath);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void rename(String code) {
		if (!isDetailsLoaded)
			loadDetails();
		try {
			for (Movie m : movieMap.values()) {
				String oldName = m.getFile().toString();
				String newName = ObjectUtil.invokeFromCode(code, m.getDetails())
						.replace(":", " -").replace(File.separator, " - ");
				if (newName == null)
					throw new IllegalStateException("Error ocurred");
				newName += oldName.substring(oldName.lastIndexOf('.'));

				Path destPath = m.getFile().resolveSibling(newName);
				if (!m.getFile().equals(destPath)) {
					System.out.println(oldName + " --> " + destPath);
					Files.move(m.getFile(), destPath);
					m.setFile(destPath);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void downloadSubtitles() {
		if (!isSubtitleLoaded)
			loadSubtitles();
		List<SubtitleOs> subs = movieMap.values().stream()
				.map(Movie::getMostDownloadedSubtitle).filter(s -> s != null).distinct()
				.collect(Collectors.toList());
		System.out.println("Total: " + subs.size());
		ObjectUtil.split(subs, 100, this::downloadSubtitles);

	}

	private void downloadSubtitles(Collection<SubtitleOs> subs) {
		System.out.println("total subs: " + subs.size());
		osapi.downloadSubtitles(subs, (sub, data) -> {
			Path file = movieMap.get(sub.getMovieHash()).getFile();

			String filename = file.getFileName().toString();
			filename = filename.substring(0, filename.lastIndexOf('.'));
			filename += "." + sub.getSubFormat();
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

	public void downloadSubtitles2(String language) {
		if(mode != Mode.ONLY_SUBTITLES) throw new IllegalStateException("mode does not match");
		for (Entry<String, Movie> entry : movieMap.entrySet()) {
			Path file = entry.getValue().getFile();
			String parent = file.getParent().toString();
			String fileName = File.separator + file.getFileName().toString();
			fileName = fileName.substring(0, fileName.lastIndexOf('.')) + ".srt";
			try {
				FileOutputStream fos = new FileOutputStream(parent + fileName);
				if(!tsdb.downloadSubtitle(entry.getKey(), language, fos)){
					System.out.println("sub for "+file+" in lang "+language+" not found :(");
				} else {
					System.out.println("sub for "+file+" in lang "+language+" found :)");
				}
				
			} catch (IOException | URISyntaxException e) {
				e.printStackTrace();
			}
		}

	}

	private enum Mode {
		ONLY_SUBTITLES,
		FULL_INFO
	}

}
