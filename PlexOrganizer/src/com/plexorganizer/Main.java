package com.plexorganizer;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

import com.mediazer.osub.model.OMovie;
import com.plexorganizer.util.Finder;

public class Main {

	private static final String SOURCE_DIR = "/home/gabriel/HD/Downloads/Torrents";

	public static void main(String[] args) throws Exception {
		Finder finder = new Finder("*.{mp4,mkv,wmv,avi,m4v}");

		Files.walkFileTree(Paths.get(SOURCE_DIR), finder);
		Collection<Path> files = finder.getMatchedFiles();
		System.out.println(files.size());
		rename(files);
		// downSub(files);

	}

	public static void downSub(Collection<Path> files) {
		TheSubDb tsdb = new TheSubDb(files);
		tsdb.downloadSubtitles("pt");
	}

	private static String getMoviePath(OMovie movie) {
		String newName = movie.getMovieName() + " (" + movie.getMovieYear() + ")";
		newName = newName.replace(":", " -").replace(File.separator, " - ");
		newName += File.separator + newName;
		return (File.separator + "Movies" + File.separator + newName);
	}

	private static String getEpisodePath(OMovie movie) {
		String showName = movie.getMovieName();
		showName = showName.substring(showName.indexOf('"') + 1);
		showName = showName.substring(0, showName.indexOf('"'));
		String episodeName = movie.getMovieName().replace('"' + showName + '"', "")
				.trim();
		String season = movie.getSeriesSeason();
		String episode = movie.getSeriesEpisode();
		season = (season.length() < 2) ? "0" + season : season;
		episode = (episode.length() < 2) ? "0" + episode : episode;
		String filename = File.separator + showName + " - s" + season + "e" + episode
				+ " - " + episodeName;
		return File.separator + "TV Shows" + File.separator + showName + File.separator
				+ "Season " + season + filename;

	}

	public static void rename(Collection<Path> files) {
		OpenSubtitle osub = new OpenSubtitle(files);
		String destination = "/home/gabriel/HD/Media";

		Collection<Path> notFound = osub.checkMovieHash((file, movie) -> {
			String newName = "";
			String oldName = file.toString();
			String ext = oldName.substring(oldName.lastIndexOf('.'));
			// System.out.println(movie.getMovieKind());
			switch (movie.getMovieKind()) {
			case "episode":
				newName = getEpisodePath(movie);
				break;
			case "movie":
				newName = getMoviePath(movie);
				break;
			}
			System.out.println(newName);

			newName = destination + newName + ext;

			// Path destPath = Paths.get(newName);
			// try {
			// Files.createDirectories(destPath.getParent());
			// Files.move(file, destPath);
			// } catch (IOException e) {
			// e.printStackTrace();
			// }
		});
		osub.done();
		if (!notFound.isEmpty()) {
			System.out.println("---Não encontrados---");
			//notFound.forEach(System.out::println);
		}
		System.out.println("Found: " + (files.size() - notFound.size()));
		System.out.println("Not Found: " + notFound.size());

	}

}
