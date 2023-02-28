package com.mediazer.osub;

import java.nio.file.Paths;
import java.util.Collections;

import com.mediazer.osub.model.response.OLogin;
import com.mediazer.osub.model.response.OMovies;
import com.mediazer.osub.util.OpenSubtitlesHasher;

public class Main {

	public static void main(String[] args) throws Exception{
		OpenSubApi api = new OpenSubApi();
		OLogin login = api.login("", "", "pt", "Hardsoft v1");
		//System.out.println(login);
		String hash = OpenSubtitlesHasher.computeHash(Paths.get("/home/gabriel/HD/Media/Filmes/Aventura/Guerra dos Mundos (2005).mp4"));
		
		OMovies movies = api.checkMovieHash(login.getToken(), Collections.singleton(hash));
		System.out.println(movies);
//		OSearch search = new OSearch(hash, "pob", SearchTerm.MOVIE_HASH);
//		OSubInfos infos = api.searchSubtitles(login.getToken(), Collections.singleton(search));
//		OSubtitles subs = api.downloadSubtitles(login.getToken(), Collections.singleton(infos.getData()[0].getIDSubtitleFile()));
//		System.out.println(subs);
		
	}

}
