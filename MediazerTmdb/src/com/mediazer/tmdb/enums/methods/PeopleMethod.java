package com.mediazer.tmdb.enums.methods;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mediazer.base.api.ApiMethod;
import com.mediazer.base.api.ApiParam;
import com.mediazer.tmdb.enums.Param;
import com.mediazer.tmdb.interfaces.AppendToResponseMethod;
import com.mediazer.tmdb.util.MethodUtil;

public enum PeopleMethod implements ApiMethod, AppendToResponseMethod {
	MOVIE_CREDITS(Param.LANGUAGE, Param.APPEND_TO_RESPONSE),
	TV_CREDITS(Param.LANGUAGE, Param.APPEND_TO_RESPONSE),
	COMBINED_CREDITS(Param.LANGUAGE, Param.APPEND_TO_RESPONSE),
	EXTERNAL_IDS,
	IMAGES,
	TAGGED_IMAGES(Param.PAGE, Param.LANGUAGE),
	CHANGES(Param.START_DATE, Param.END_DATE);

	private int maxParams;
	private String baseUrl;
	private ApiParam[] params;
	private String name;

	private PeopleMethod(ApiParam... params) {
		this.name = name().toLowerCase();
		baseUrl = BaseMethod.PERSON.getBaseUrl() + "/" + name;
		this.params = MethodUtil.addApiKey(params);
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
		return 1;
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

	@Override
	public String getPropertyString() {
		return name;
	}

}
