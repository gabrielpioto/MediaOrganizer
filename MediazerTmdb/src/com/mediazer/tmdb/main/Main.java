package com.mediazer.tmdb.main;

import com.mediazer.tmdb.AppendToResponseBuilder;
import com.mediazer.tmdb.TmdbApi;
import com.mediazer.tmdb.enums.ExternalSource;
import com.mediazer.tmdb.enums.methods.TVEpisodeMethod;
import com.mediazer.tmdb.model.artwork.Artwork;
import com.mediazer.tmdb.model.tv.TVEpisodeBasic;
import com.mediazer.tmdb.model.tv.TVEpisodeInfo;

public class Main {

	public static void main(String[] args) {
		
		TmdbApi api = new TmdbApi("a2038ba65a6e1cffb5e59cee83bd1bcd");
		TVEpisodeBasic e = api.find("tt2301455", ExternalSource.IMDB_ID, "en").getTvEpisodeResults().get(0);
		int id = Integer.parseInt(e.getShowId());
		TVEpisodeInfo ep = api.getEpisodeInfo(id, e.getSeasonNumber(), e.getEpisodeNumber(), null, new AppendToResponseBuilder(TVEpisodeMethod.IMAGES));
		for(Artwork a : ep.getImages())
			System.out.println(a);
		System.out.println(e.getPosterPath());

	}

}
