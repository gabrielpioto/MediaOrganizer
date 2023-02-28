package com.mediazer.tmdb.enums.methods;

import java.io.IOException;
import java.io.InputStream;
import java.util.EnumMap;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mediazer.base.api.ApiMethod;
import com.mediazer.base.api.ApiParam;
import com.mediazer.tmdb.enums.Param;
import com.mediazer.tmdb.model.FindResults;
import com.mediazer.tmdb.model.movie.MovieInfo;
import com.mediazer.tmdb.model.tv.TVEpisodeInfo;
import com.mediazer.tmdb.util.MethodUtil;

public enum BaseMethod implements ApiMethod {
	ACCOUNT(0), // sem
	COLLECTION,
	COLLECTION_IMAGES(1), // sem
	COMPANY,
	COMPANY_MOVIES(1), // sem
	CREDIT,
	FIND(1, 3, Param.EXTERNAL_SOURCE, Param.LANGUAGE),
	GENRE_MOVIES(1), // sem
	GUEST_RATED(1, 2, "/guest_session/%s/rated_movies"),
	KEYWORD,
	KEYWORD_MOVIES(1), // sem
	LIST, // GET & DELETE
	MOVIE(Param.LANGUAGE, Param.APPEND_TO_RESPONSE),
	NETWORK,
	PERSON,
	REVIEW,
	TV,
	TV_SEASON(2, 3),
	TV_SEASON_EPISODE(3, 4, Param.LANGUAGE, Param.APPEND_TO_RESPONSE), ;

	private int startAt;
	private int minParams;
	private int maxParams;
	private String baseUrl;
	private ApiParam[] params;
	
	private static final EnumMap<BaseMethod,TypeReference<?>> TYPE_MAP = new EnumMap<>(BaseMethod.class);
	
	static{
		TYPE_MAP.put(MOVIE, new TypeReference<MovieInfo>(){});
		TYPE_MAP.put(FIND, new TypeReference<FindResults>(){});
		TYPE_MAP.put(TV_SEASON_EPISODE, new TypeReference<TVEpisodeInfo>() {});
	}

	private BaseMethod(ApiParam... params) {
		this(1, 2, params);
	}

	private BaseMethod(int startAt, int minParams, ApiParam... params) {
		this(startAt, minParams, null, params);
		this.baseUrl = "/" + name().toLowerCase().replace("_", "/%s/") + "/%s";
	}

	// anormal
	private BaseMethod(int startAt, ApiParam... params) {
		this(startAt, 2, params);
		this.baseUrl = "/" + name().toLowerCase().replace("_", "/%s/");
	}

	private BaseMethod(int startAt, int minParams, String baseUrl, ApiParam... params) {
		this.startAt = startAt;
		this.minParams = minParams;
		this.baseUrl = baseUrl;
		this.params = MethodUtil.addApiKey(params);
		this.maxParams = getStartAt() + this.params.length;
	}

	@Override
	public String getBaseUrl() {
		return baseUrl;
	}

	@Override
	public ApiParam getParam(int i) {
		return params[i];
	}

	@Override
	public int getStartAt() {
		return startAt;
	}

	@Override
	public int getMaxParams() {
		return maxParams;
	}

	@Override
	public int getMinParams() {
		return minParams;
	}

	@Override
	public <T> T parse(InputStream stream) throws JsonParseException, JsonMappingException, IOException {
		return MethodUtil.MAPPER.readValue(stream, TYPE_MAP.get(this));
	}
	

}
