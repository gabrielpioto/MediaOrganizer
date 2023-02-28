package com.mediazer.tmdb.enums.methods;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mediazer.base.api.ApiMethod;
import com.mediazer.base.api.ApiParam;
import com.mediazer.tmdb.util.MethodUtil;

public enum StaticMethod implements ApiMethod {
	CONFIGURATION,
	CERTIFICATION_MOVIE_LIST,
	CERTIFICATION_TV_LIST,
	MOVIE_CHANGES,
	PERSON_CHANGES,
	TV_CHANGES,
	GENRE_MOVIE_LIST,
	GENRE_TV_LIST,
	JOB_LIST,
	PERSON_POPULAR,
	PERSON_LATEST,
	TIMEZONES_LIST;

	private int minParams;
	private int maxParams;

	private String baseUrl;
	private ApiParam[] params;

	private StaticMethod(ApiParam... params) {
		this(1,params);		
	}
	
	private StaticMethod(int minParams, ApiParam...params){
		this(minParams, null, params);
		this.baseUrl = "/" + name().toLowerCase().replace("_", "/");
	}

	private StaticMethod(int minParams, String baseUrl, ApiParam... params) {
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
		return 0;
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
	public <T> T parse(InputStream stream) throws JsonParseException,
			JsonMappingException, IOException {
		//return MethodUtil.MAPPER.readValue(stream, TYPE_MAP.get(this));
		return null;
	}

}
