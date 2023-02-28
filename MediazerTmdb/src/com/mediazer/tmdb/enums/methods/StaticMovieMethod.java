package com.mediazer.tmdb.enums.methods;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mediazer.base.api.ApiMethod;
import com.mediazer.base.api.ApiParam;
import com.mediazer.tmdb.enums.Param;
import com.mediazer.tmdb.util.MethodUtil;

public enum StaticMovieMethod implements ApiMethod {
	LATEST,
	UPCOMING(Param.PAGE, Param.LANGUAGE),
	NOW_PLAYING(Param.PAGE, Param.LANGUAGE),
	POPULAR(Param.PAGE, Param.LANGUAGE),
	TOP_RATED(Param.PAGE, Param.LANGUAGE);

	private String baseUrl;
	private ApiParam[] params;
	private int maxParam;

	private StaticMovieMethod(ApiParam... params) {
		baseUrl = "/movie/" + name().toLowerCase();
		this.params = MethodUtil.addApiKey(params);
		maxParam = getStartAt() + this.params.length;
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
		return maxParam;
	}

	@Override
	public int getMinParams() {
		return 1;
	}

	@Override
	public <T> T parse(InputStream stream) throws JsonParseException,
			JsonMappingException, IOException {
		//return MethodUtil.MAPPER.readValue(stream, TYPE_MAP.get(this));
		return null;
	}

}
