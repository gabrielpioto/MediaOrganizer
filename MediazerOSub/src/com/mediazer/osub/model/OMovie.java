package com.mediazer.osub.model;

import com.mediazer.osub.model.base.OObject;

public class OMovie extends OObject{
	private String MovieHash, MovieImdbID, MovieName, MovieYear, MovieKind, SeriesSeason,
	SeriesEpisode, SeenCount, SubCount;

	public String getMovieHash() {
		return MovieHash;
	}

	public String getMovieImdbID() {
		return MovieImdbID;
	}

	public String getMovieName() {
		return MovieName;
	}

	public String getMovieYear() {
		return MovieYear;
	}

	public String getMovieKind() {
		return MovieKind;
	}

	public String getSeriesSeason() {
		return SeriesSeason;
	}

	public String getSeriesEpisode() {
		return SeriesEpisode;
	}

	public String getSeenCount() {
		return SeenCount;
	}

	public String getSubCount() {
		return SubCount;
	}
	
}
