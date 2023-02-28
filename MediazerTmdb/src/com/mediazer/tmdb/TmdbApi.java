package com.mediazer.tmdb;

import java.io.IOException;
import java.util.List;

import com.mediazer.base.api.AbstractApi;
import com.mediazer.tmdb.enums.ExternalSource;
import com.mediazer.tmdb.enums.methods.BaseMethod;
import com.mediazer.tmdb.model.FindResults;
import com.mediazer.tmdb.model.discover.DiscoverMovie;
import com.mediazer.tmdb.model.discover.DiscoverTv;
import com.mediazer.tmdb.model.movie.MovieBasic;
import com.mediazer.tmdb.model.movie.MovieInfo;
import com.mediazer.tmdb.model.tv.TVBasic;
import com.mediazer.tmdb.model.tv.TVEpisodeInfo;
import com.mediazer.tmdb.results.WrapperGenericList;

public class TmdbApi extends AbstractApi {

	private String apiKey;

	public TmdbApi(String apiKey) {
		super("https://api.themoviedb.org/3");
		this.apiKey = apiKey;
	}

	public MovieInfo getMovieInfo(String imdbId, String language,
			AppendToResponseBuilder appendToResponse) {
		MovieInfo movie = null;
		try {
			movie = (MovieInfo) get(BaseMethod.MOVIE, imdbId, apiKey, language,
					appendToResponse);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return movie;

	}

	public List<MovieBasic> getDiscoverMovies(DiscoverMovie discoverMovie) {
		Discover discover = (Discover) discoverMovie;
		discover.setApiKey(apiKey);
		WrapperGenericList<MovieBasic> wrapper = null;
		try {
			wrapper = get(discover, discover.getValues());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return wrapper.getResults();
	}

	public List<TVBasic> getDiscoverTV(DiscoverTv discoverTv) {
		Discover discover = (Discover) discoverTv;
		discover.setApiKey(apiKey);
		WrapperGenericList<TVBasic> wrapper = null;
		try {
			wrapper = get(discover, discover.getValues());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return wrapper.getResults();
	}

	public FindResults find(String id, ExternalSource source, String language) {
		FindResults results = null;	
		
		try {
			results = this.<FindResults>get(BaseMethod.FIND, id, apiKey, source, language);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return results;
	}

	public TVEpisodeInfo getEpisodeInfo(int tvID, int seasonNumber, int episodeNumber,
			String language, AppendToResponseBuilder appendToResponse) {
		TVEpisodeInfo episode = null;
		try {
			episode = (TVEpisodeInfo) get(BaseMethod.TV_SEASON_EPISODE, tvID,
					seasonNumber, episodeNumber, apiKey, language, appendToResponse);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return episode;
	}
}
