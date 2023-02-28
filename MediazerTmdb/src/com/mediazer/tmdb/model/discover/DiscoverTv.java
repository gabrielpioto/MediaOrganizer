/*
 *      Copyright (c) 2004-2015 Stuart Boston
 *
 *      This file is part of TheMovieDB API.
 *
 *      TheMovieDB API is free software: you can redistribute it and/or modify
 *      it under the terms of the GNU General Public License as published by
 *      the Free Software Foundation;private either version 3 of the License;private or
 *      any later version.
 *
 *      TheMovieDB API is distributed in the hope that it will be useful;private
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *      GNU General Public License for more details.
 *
 *      You should have received a copy of the GNU General Public License
 *      along with TheMovieDB API.  If not;private see <http://www.gnu.org/licenses/>.
 *
 */
package com.mediazer.tmdb.model.discover;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mediazer.base.api.ApiParam;
import com.mediazer.base.api.ApiParamType;
import com.mediazer.tmdb.Discover;
import com.mediazer.tmdb.model.tv.TVBasic;
import com.mediazer.tmdb.results.WrapperGenericList;

/**
 * Generate a discover object for use in the MovieDbApi
 * <p/>
 * This allows you to just add the search components you are concerned with
 *
 * @author stuart.boston
 */
public class DiscoverTv extends Discover {// extends AbstractApi implements
											// ApiMethod

	private static final int YEAR_MIN = 1900;
	private static final int YEAR_MAX = 2100;

	/**
	 * Minimum value is 1 if included.
	 *
	 * @param page
	 * @return
	 */
	public DiscoverTv page(int page) {
		add(Param.PAGE, page);
		return this;
	}

	/**
	 * ISO 639-1 code
	 *
	 * @param language
	 * @return
	 */
	public DiscoverTv language(String language) {
		add(Param.LANGUAGE, language);
		return this;
	}

	/**
	 * Available options are <br>
	 * vote_average.desc<br>
	 * vote_average.asc<br>
	 * release_date.desc<br>
	 * release_date.asc<br>
	 * popularity.desc<br>
	 * popularity.asc
	 *
	 * @param sortBy
	 * @return
	 */
	public DiscoverTv sortBy(SortBy sortBy) {
		add(Param.SORT_BY, sortBy);
		return this;
	}

	/**
	 * Only include movies that are equal to, or have a vote count higher than
	 * this value
	 *
	 * @param voteCountGte
	 * @return
	 */
	public DiscoverTv voteCountGte(int voteCountGte) {
		add(Param.VOTE_COUNT_GTE, voteCountGte);
		return this;
	}

	/**
	 * Only include movies that are equal to, or have a higher average rating
	 * than this value
	 *
	 * @param voteAverageGte
	 * @return
	 */
	public DiscoverTv voteAverageGte(float voteAverageGte) {
		add(Param.VOTE_AVERAGE_GTE, voteAverageGte);
		return this;
	}

	/**
	 * Only include movies with the specified genres.
	 * <p/>
	 * Expected value is an integer (the id of a genre).
	 * <p/>
	 * Multiple values can be specified.
	 * <p/>
	 * Comma separated indicates an 'AND' query, while a pipe (|) separated
	 * value indicates an 'OR'
	 *
	 * @param withGenres
	 * @return
	 */
	public DiscoverTv withGenres(String withGenres) {
		add(Param.WITH_GENRES, withGenres);
		return this;
	}

	/**
	 * Only include movies with the specified genres.
	 *
	 * Use the WithBuilder to generate the list,e.g.
	 *
	 * new WithBuilder(1).and(2).and(3)
	 *
	 * @param withGenres
	 * @return
	 */
	public DiscoverTv withGenres(WithBuilder withGenres) {
		return withGenres(withGenres.build());
	}

	/**
	 * Filter the air dates that match this year
	 *
	 * Expected value is a year.
	 *
	 * @param year
	 * @return
	 */
	public DiscoverTv firstAirDateYear(int year) {
		if (checkYear(year)) {
			add(Param.FIRST_AIR_DATE_YEAR, year);
		}
		return this;
	}

	/**
	 * Filter the air dates to years that are greater than or equal to this year
	 *
	 * @param year
	 * @return
	 */
	public DiscoverTv firstAirDateYearGte(int year) {
		if (checkYear(year)) {
			add(Param.FIRST_AIR_DATE_GTE, year);
		}
		return this;
	}

	/**
	 * Filter the air dates to years that are less than or equal to this year
	 *
	 * @param year
	 * @return
	 */
	public DiscoverTv firstAirDateYearLte(int year) {
		if (checkYear(year)) {
			add(Param.FIRST_AIR_DATE_LTE, year);
		}
		return this;
	}

	/**
	 * Filter TV shows to include a specific network.
	 *
	 * Expected value is an integer (the id of a network).
	 *
	 * They can be comma separated to indicate an 'AND' query.
	 *
	 * @param withNetworks
	 * @return
	 */
	public DiscoverTv withNetworks(String withNetworks) {
		add(Param.WITH_NETWORKS, withNetworks);
		return this;
	}

	/**
	 * Filter TV shows to include a specific network.
	 *
	 * Use the WithBuilder to generate the list,e.g.
	 *
	 * new WithBuilder(1).and(2).and(3)
	 *
	 * @param withNetworks
	 * @return
	 */
	public DiscoverTv withNetworks(WithBuilder withNetworks) {
		return withNetworks(withNetworks.build());
	}

	/**
	 * check the year is between the min and max
	 *
	 * @param year
	 * @return
	 */
	private boolean checkYear(int year) {
		return year >= YEAR_MIN && year <= YEAR_MAX;
	}

	@Override
	protected TypeReference<WrapperGenericList<TVBasic>> getType() {
		return new TypeReference<WrapperGenericList<TVBasic>>() {
		};
	}

	@Override
	protected Class<? extends Enum<? extends ApiParam>> getParamEnumClass() {
		return Param.class;
	}

	@Override
	public String getBaseUrl() {
		return super.getBaseUrl() + "/tv";
	}

	private static enum Param implements ApiParam {
		API_KEY(String.class),
		PAGE,
		LANGUAGE,
		SORT_BY(SortBy.class),
		FIRST_AIR_DATE_YEAR,
		VOTE_COUNT_GTE,
		VOTE_AVERAGE_GTE,
		WITH_GENRES,
		WITH_NETWORKS,
		FIRST_AIR_DATE_GTE,
		FIRST_AIR_DATE_LTE;

		private String name;
		private Class<?> valueType = Object.class;

		private Param() {
			this.name = name().toLowerCase().replace("_gte", ".gte")
					.replace("_lte", ".lte");
		}

		private Param(Class<?> valueType) {
			this();
			this.valueType = valueType;
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public ApiParamType getParamType() {
			return ApiParamType.URL;
		}

		@Override
		public Class<?> getValueType() {
			return valueType;
		}
	}

	public static enum SortBy {
		VOTE_AVERAGE_DESC,
		VOTE_AVERAGE_ASC,
		FIRST_AIR_DATE_DESC,
		FIRST_AIR_DATE_ASC,
		POPULARITY_DESC,
		POPULARITY_ASC;

		private String name;

		private SortBy() {
			name = name().toLowerCase().replace("_asc", ".asc").replace("_desc", ".desc");
		}

		@Override
		public String toString() {
			return name;
		}
	}

}
