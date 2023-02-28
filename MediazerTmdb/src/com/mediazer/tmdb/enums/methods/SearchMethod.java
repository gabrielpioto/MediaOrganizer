package com.mediazer.tmdb.enums.methods;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mediazer.base.api.ApiMethod;
import com.mediazer.base.api.ApiParam;
import com.mediazer.tmdb.enums.Param;
import com.mediazer.tmdb.util.MethodUtil;

public enum SearchMethod implements ApiMethod {
	COMPANY,
	COLLECTION(Param.LANGUAGE),
	KEYWORD,
	LIST(Param.INCLUDE_ADULT),
	MOVIE(Param.LANGUAGE, Param.INCLUDE_ADULT, Param.YEAR, Param.PRIMARY_RELEASE_YEAR, Param.SEARCH_TYPE),
	MULTI(Param.LANGUAGE, Param.INCLUDE_ADULT),
	PERSON(Param.INCLUDE_ADULT, Param.SEARCH_TYPE),
	TV(Param.LANGUAGE, Param.FIRST_AIR_DATE_YEAR, Param.SEARCH_TYPE);

	private int maxParams;
	private String baseUrl;
	private ApiParam[] params;

	private SearchMethod(ApiParam... params) {
		baseUrl = "/search/" + name().toLowerCase();
		this.params = MethodUtil.prepend(params, Param.API_KEY, Param.QUERY, Param.PAGE);
		maxParams = getStartAt() + this.params.length;
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
		return 2;
	}

	@Override
	public <T> T parse(InputStream stream) throws JsonParseException,
			JsonMappingException, IOException {
		//return MethodUtil.MAPPER.readValue(stream, TYPE_MAP.get(this));
		return null;
	}

}
