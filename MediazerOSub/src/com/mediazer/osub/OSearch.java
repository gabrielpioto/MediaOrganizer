package com.mediazer.osub;

import java.util.HashMap;
import java.util.Map;

public class OSearch {
	private SearchTerm searchBy;
	private String term;

	private String subLanguageId = "all";
	private long movieSize = -1;
	private String imdbId = "";
	private String query = "";
	private int season = -1;
	private int episode = -1;

	public OSearch(String term, SearchTerm searchBy) {
		switch (searchBy) {
		case SUB_LANGUAGE_ID:
		case MOVIE_SIZE:
		case SEASON:
		case EPISODE:
			throw new IllegalArgumentException();
		default:
			break;
		}

		this.searchBy = searchBy;
		this.term = term;
	}

	public OSearch(String term, String subLanguageId, SearchTerm searchBy) {
		this(term, searchBy);
		setSubLanguageId(subLanguageId);
	}

	public OSearch setSubLanguageId(String subLanguageId) {
		this.subLanguageId = subLanguageId;
		return this;
	}

	public OSearch setSeason(int season) {
		if (searchBy == SearchTerm.QUERY || searchBy == SearchTerm.IMDB_ID) {
			this.season = season;
			return this;
		}
		return null;
	}

	public OSearch setEpisode(int episode) {
		if (searchBy == SearchTerm.QUERY || searchBy == SearchTerm.IMDB_ID) {
			this.episode = episode;
			return this;
		}
		return null;
	}

	public OSearch setMovieSize(long movieSize) {
		if (searchBy == SearchTerm.MOVIE_HASH) {
			this.movieSize = movieSize;
			return this;
		}
		return null;
	}

	public OSearch setQuery(String query) {
		if (searchBy == SearchTerm.TAG) {
			this.query = query;
			return this;
		}
		return null;
	}

	public OSearch setImdbId(String imdbId) {
		if (searchBy == SearchTerm.TAG) {
			this.imdbId = imdbId;
			return this;
		}
		return null;
	}

	Map<String, Object> build() {
		Map<String, Object> search = new HashMap<>();
		search.put(SearchTerm.SUB_LANGUAGE_ID.name, subLanguageId);
		search.put(searchBy.name, term);
		switch (searchBy) {
		case MOVIE_HASH:
			if (movieSize > 0)
				search.put(SearchTerm.MOVIE_SIZE.name, movieSize);
			break;
		case IMDB_ID:
		case QUERY:
			if (season >= 0)
				search.put(SearchTerm.SEASON.name, season);
			if (episode >= 0)
				search.put(SearchTerm.EPISODE.name, episode);
			break;
		case TAG:
			if (!"".equals(query))
				search.put(SearchTerm.QUERY.name, query);
			if (!"".equals(imdbId))
				search.put(SearchTerm.IMDB_ID.name, imdbId);
			break;
		default:
			break;
		}

		return search;
	}

	public static enum SearchTerm {
		SUB_LANGUAGE_ID("sublanguageid"),
		MOVIE_HASH("moviehash"),
		MOVIE_SIZE("moviebytesize"),
		IMDB_ID("imdbid"),
		QUERY("query"),
		SEASON("season"),
		EPISODE("episode"),
		TAG("tag");

		private String name;

		private SearchTerm(String name) {
			this.name = name;
		}

	}

}
