package com.mediazer.osub;

import java.util.ArrayList;
import java.util.Collection;

import com.mediazer.osub.OSearch.Term;
import com.mediazer.osub.model.OSubInfo;
import com.mediazer.osub.model.response.OLogin;
import com.mediazer.osub.model.response.OSubInfos;

public class Main {

	public static void main(String[] args) throws Exception{
		OpenSubApi api = new OpenSubApi();
		OLogin login = api.login("", "", "pt", "Hardsoft v1");
		//System.out.println(login);
		//String hash = OpenSubtitlesHasher.computeHash(Paths.get("/media/lubuntu/HD-E1/Media/Movies LowRes/The.Martian.2015.HD-TS.XVID.AC3.HQ.Hive-CM8/sample.avi"));
		
		//OMovies movies = api.checkMovieHash(login.getToken(), Collections.singleton(hash));
		
		//System.out.println(movies);
		//OSearch search = new OSearch("pob").set(Term.MOVIE_HASH, hash);		
		OSubInfos infos = api.searchSubtitles(login.getToken(), funcao());
		
		//OSubtitles subs = api.downloadSubtitles(login.getToken(), Collections.singleton(infos.getData()[0].getIDSubtitleFile()));
		//OSubInfo[] infoss = ;
		int i = 0;
		for(OSubInfo info : infos.getData()){
			System.out.println(info);
			i++;
		}
		System.out.println(i);
		//System.out.println(infos);
		
	}
	
	public static Collection<OSearch> funcao(){
		
		Collection<OSearch> searches = new ArrayList<>();
		for(int s = 6;s<=10;s++){
			for(int e=1;e<=24;e++){
				OSearch search = new OSearch("pob").set(Term.QUERY, "Friends psychd");
				search.set(Term.SEASON, s).set(Term.EPISODE, e);
				searches.add(search);
			}
			
		}
		return searches;
		
		
		
	}

}
