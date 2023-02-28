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
import com.mediazer.tmdb.model.movie.MovieBasic;
import com.mediazer.tmdb.results.WrapperGenericList;

/**
 * Generate a discover object for use in the MovieDbApi
 * <p/>
 * This allows you to just add the search components you are concerned with
 *
 * @author stuart.boston
 */
public class DiscoverMovie extends Discover {

	private static final int YEAR_MIN = 1900;
	private static final int YEAR_MAX = 2100;

	/**
	 * Minimum value is 1 if included.
	 *
	 * @param page
	 * @return
	 */
	public DiscoverMovie page(int page) {
		add(Param.PAGE, page);
		return this;
	}

	/**
	 * ISO 639-1 code
	 *
	 * @param language
	 * @return
	 */
	public DiscoverMovie language(String language) {
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
	public DiscoverMovie sortBy(SortBy sortBy) {
		add(Param.SORT_BY, sortBy);
		return this;
	}

	/**
	 * Toggle the inclusion of adult titles
	 *
	 * @param includeAdult
	 * @return
	 */
	public DiscoverMovie includeAdult(boolean includeAdult) {
		add(Param.INCLUDE_ADULT, includeAdult);
		return this;
	}

	/**
	 * Toggle the inclusion of items marked as a video.
	 *
	 * Expected value is a boolean, true or false. Default is true.
	 *
	 * @param includeVideo
	 * @return
	 */
	public DiscoverMovie includeVideo(boolean includeVideo) {
		add(Param.INCLUDE_VIDEO, includeVideo);
		return this;
	}

	/**
	 * Filter the results release dates to matches that include this value.
	 *
	 * @param year
	 * @return
	 */
	public DiscoverMovie year(int year) {
		if (checkYear(year)) {
			add(Param.YEAR, year);
		}
		return this;
	}

	/**
	 * Filter the results so that only the primary release date year has this
	 * value
	 *
	 * @param primaryReleaseYear
	 * @return
	 */
	public DiscoverMovie primaryReleaseYear(int primaryReleaseYear) {
		if (checkYear(primaryReleaseYear)) {
			add(Param.PRIMARY_RELEASE_YEAR, primaryReleaseYear);
		}
		return this;
	}

	/**
	 * Only include movies that are equal to, or have a vote count higher than
	 * this value
	 *
	 * @param voteCountGte
	 * @return
	 */
	public DiscoverMovie voteCountGte(int voteCountGte) {
		add(Param.VOTE_COUNT_GTE, voteCountGte);
		return this;
	}

	/**
	 * Only include movies that are equal to, or have a vote count higher than
	 * this value
	 *
	 * @param voteCountLte
	 * @return
	 */
	public DiscoverMovie voteCountLte(int voteCountLte) {
		add(Param.VOTE_COUNT_LTE, voteCountLte);
		return this;
	}

	/**
	 * Only include movies that are equal to, or have a higher average rating
	 * than this value
	 *
	 * @param voteAverageGte
	 * @return
	 */
	public DiscoverMovie voteAverageGte(float voteAverageGte) {
		add(Param.VOTE_AVERAGE_GTE, voteAverageGte);
		return this;
	}

	/**
	 * Only include movies that are less than or equal to this value
	 *
	 * @param voteAverageLte
	 * @return
	 */
	public DiscoverMovie voteAverageLte(float voteAverageLte) {
		add(Param.VOTE_AVERAGE_LTE, voteAverageLte);
		return this;
	}

	/**
	 * The minimum release to include.
	 * <p/>
	 * Expected format is YYYY-MM-DD.
	 *
	 * @param releaseDateGte
	 * @return
	 */
	public DiscoverMovie releaseDateGte(String releaseDateGte) {
		add(Param.RELEASE_DATE_GTE, releaseDateGte);
		return this;
	}

	/**
	 * The maximum release to include.
	 * <p/>
	 * Expected format is YYYY-MM-DD.
	 *
	 * @param releaseDateLte
	 * @return
	 */
	public DiscoverMovie releaseDateLte(String releaseDateLte) {
		add(Param.RELEASE_DATE_LTE, releaseDateLte);
		return this;
	}

	/**
	 * Only include movies with certifications for a specific country.
	 * <p/>
	 * When this value is specified, 'certificationLte' is required.
	 * <p/>
	 * A ISO 3166-1 is expected
	 *
	 * @param certificationCountry
	 * @return
	 */
	public DiscoverMovie certificationCountry(String certificationCountry) {
		add(Param.CERTIFICATION_COUNTRY, certificationCountry);
		return this;
	}

	/**
	 * Only include movies with this certification and lower.
	 * <p/>
	 * Expected value is a valid certification for the specified
	 * 'certificationCountry'.
	 *
	 * @param certificationLte
	 * @return
	 */
	public DiscoverMovie certificationLte(String certificationLte) {
		add(Param.CERTIFICATION_LTE, certificationLte);
		return this;
	}

	/**
	 * Only include movies with this certification.
	 *
	 * Expected value is a valid certification for the specified
	 * 'certificationCountry'.
	 *
	 * @param certification
	 * @return
	 */
	public DiscoverMovie certification(String certification) {
		add(Param.CERTIFICATION, certification);
		return this;
	}

	/**
	 * Only include movies that have this person id added as a cast member.
	 *
	 * Expected value is an integer (the id of a person).
	 *
	 * Comma separated indicates an 'AND' query, while a pipe (|) separated
	 * value indicates an 'OR'
	 *
	 * @param withCast
	 * @return
	 */
	public DiscoverMovie withCast(String withCast) {
		add(Param.WITH_CAST, withCast);
		return this;
	}

	/**
	 * Only include media that have these person IDs added as cast members.
	 *
	 * Use the WithBuilder to generate the list,e.g.
	 *
	 * new WithBuilder(1).and(2).and(3)
	 *
	 * @param withCast
	 * @return
	 */
	public DiscoverMovie withCast(WithBuilder withCast) {
		return withCast(withCast.build());
	}

	/**
	 * Only include movies that have this person id added as a crew member.
	 *
	 * Expected value is an integer (the id of a person).
	 *
	 * Comma separated indicates an 'AND' query, while a pipe (|) separated
	 * value indicates an 'OR'.
	 *
	 * @param withCrew
	 * @return
	 */
	public DiscoverMovie withCrew(String withCrew) {
		add(Param.WITH_CREW, withCrew);
		return this;
	}

	/**
	 * Only include media that have these person IDs added as crew members.
	 *
	 * Use the WithBuilder to generate the list,e.g.
	 *
	 * new WithBuilder(1).and(2).and(3)
	 *
	 * @param withCrew
	 * @return
	 */
	public DiscoverMovie withCrew(WithBuilder withCrew) {
		return withCrew(withCrew.build());
	}

	/**
	 * Filter movies to include a specific company.
	 *
	 * Expected value is an integer (the id of a company).
	 *
	 * Comma separated indicates an 'AND' query, while a pipe (|) separated
	 * value indicates an 'OR'
	 *
	 * @param withCompanies
	 * @return
	 */
	public DiscoverMovie withCompanies(String withCompanies) {
		add(Param.WITH_COMPANIES, withCompanies);
		return this;
	}

	/**
	 * Filter movies to include a specific company.
	 *
	 * Use the WithBuilder to generate the list,e.g.
	 *
	 * new WithBuilder(1).and(2).and(3)
	 *
	 * @param withCompanies
	 * @return
	 */
	public DiscoverMovie withCompanies(WithBuilder withCompanies) {
		return withCompanies(withCompanies.build());
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
	public DiscoverMovie withGenres(String withGenres) {
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
	public DiscoverMovie withGenres(WithBuilder withGenres) {
		return withGenres(withGenres.build());
	}

	/**
	 * Only include movies with the specified genres.
	 *
	 * Expected value is an integer (the id of a genre).
	 *
	 * Comma separated indicates an 'AND' query, while a pipe (|) separated
	 * value indicates an 'OR'.
	 *
	 * @param withKeywords
	 * @return
	 */
	public DiscoverMovie withKeywords(String withKeywords) {
		add(Param.WITH_KEYWORDS, withKeywords);
		return this;
	}

	/**
	 * Only include movies with the specified genres.
	 *
	 * Use the WithBuilder to generate the list,e.g.
	 *
	 * new WithBuilder(1).and(2).and(3)
	 *
	 * @param withKeywords
	 * @return
	 */
	public DiscoverMovie withKeywords(WithBuilder withKeywords) {
		return withKeywords(withKeywords.build());
	}

	/**
	 * Only include movies that have these person id's added as a cast or crew
	 * member.
	 *
	 * Expected value is an integer (the id or ids of a person).
	 *
	 * Comma separated indicates an 'AND' query, while a pipe (|) separated
	 * value indicates an 'OR'.
	 *
	 * @param withPeople
	 * @return
	 */
	public DiscoverMovie withPeople(String withPeople) {
		add(Param.WITH_PEOPLE, withPeople);
		return this;
	}

	/**
	 * Only include movies that have these person id's added as a cast or crew
	 * member.
	 *
	 * Use the WithBuilder to generate the list,e.g.
	 *
	 * new WithBuilder(1).and(2).and(3)
	 *
	 * @param withPeople
	 * @return
	 */
	public DiscoverMovie withPeople(WithBuilder withPeople) {
		return withPeople(withPeople.build());
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
	protected TypeReference<WrapperGenericList<MovieBasic>> getType() {
		return new TypeReference<WrapperGenericList<MovieBasic>>() {
		};
	}

	@Override
	protected Class<? extends Enum<? extends ApiParam>> getParamEnumClass() {
		return Param.class;
	}

	@Override
	public String getBaseUrl() {
		return super.getBaseUrl() + "/movie";
	}

	private enum Param implements ApiParam {
		API_KEY(String.class),
		CERTIFICATION_COUNTRY,
		CERTIFICATION,
		CERTIFICATION_LTE,
		INCLUDE_ADULT,
		INCLUDE_VIDEO,
		LANGUAGE,
		PAGE,
		PRIMARY_RELEASE_YEAR,
		PRIMARY_RELEASE_DATE_GTE,
		PRIMARY_RELEASE_DATE_LTE,
		RELEASE_DATE_GTE,
		RELEASE_DATE_LTE,
		SORT_BY(SortBy.class),
		VOTE_COUNT_GTE,
		VOTE_COUNT_LTE,
		VOTE_AVERAGE_GTE,
		VOTE_AVERAGE_LTE,
		WITH_CAST,
		WITH_CREW,
		WITH_COMPANIES,
		WITH_GENRES,
		WITH_KEYWORDS,
		WITH_PEOPLE,
		YEAR;

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
		POPULARITY_ASC,
		POPULARITY_DESC,
		RELEASE_DATE_ASC,
		RELEASE_DATE_DESC,
		REVENUE_ASC,
		REVENUE_DESC,
		PRIMARY_RELEASE_DATE_ASC,
		PRIMARY_RELEASE_DATE_DESC,
		ORIGINAL_TITLE_ASC,
		ORIGINAL_TITLE_DESC,
		VOTE_AVERAGE_ASC,
		VOTE_AVERAGE_DESC,
		VOTE_COUNT_ASC,
		VOTE_COUNT_DESC;

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
