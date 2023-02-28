package com.plexorganizer;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.function.BiConsumer;

import com.mediazer.osub.OSearch;
import com.mediazer.osub.OSearch.SearchTerm;
import com.mediazer.osub.OpenSub;
import com.mediazer.osub.OpenSubApi;
import com.mediazer.osub.model.OMovie;
import com.mediazer.osub.model.response.OLogin;
import com.mediazer.osub.model.response.OMovies;
import com.mediazer.osub.util.OpenSubtitlesHasher;

public class OpenSubtitle {

	private boolean isLoaded = false;

	private Collection<Path> files;
	private Collection<OSearch> searches;
	private Map<String, Path> mapHashFile;
	private OLogin login;
	private OpenSub opensub;

	public OpenSubtitle(Collection<Path> files) {
		this.files = files;
		this.mapHashFile = new HashMap<>();
		this.searches = new ArrayList<>();
		this.opensub = new OpenSubApi();
	}

	private void load() {
		if (isLoaded)
			return;
		login = opensub.login("", "", "pt", "Hardsoft v1");
		for (Path file : files) {
			String hash = OpenSubtitlesHasher.computeHash(file);
			Path previous = mapHashFile.put(hash, file);
			if (previous != null) {
				System.out
						.println("CRITICAL: duplicate files: " + file + " & " + previous);
				System.exit(0);
			}
			searches.add(new OSearch(hash, "pob", SearchTerm.MOVIE_HASH));
		}
		isLoaded = true;
	}

	public void done() {
		opensub.logout(login.getToken());
		isLoaded = false;
	}

	public Collection<Path> checkMovieHash(BiConsumer<Path, OMovie> consumer) {
		load();
		
		OMovies omovies;
		Object[] notProcessed;
		Map<String, OMovie> data;
		Collection<Path> notFound = new HashSet<Path>();
		Collection<?> hashes = mapHashFile.keySet();

		do {
			omovies = opensub.checkMovieHash(login.getToken(), hashes);
			notProcessed = omovies.getNotProcessed();
			data = omovies.getData();
			data.forEach((hash, movie) -> {
				Path file = mapHashFile.get(hash);
				if (movie == null)
					notFound.add(file);
				else
					consumer.accept(file, movie);
			});
			hashes = Arrays.asList(notProcessed);

		} while (notProcessed.length > 0);

		return notFound;
	}

}
