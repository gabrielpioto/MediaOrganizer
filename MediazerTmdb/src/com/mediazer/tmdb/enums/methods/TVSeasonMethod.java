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

public enum TVSeasonMethod implements ApiMethod, AppendToResponseMethod {
	CHANGES(1,2,"/tv/season/%s",Param.START_DATE, Param.END_DATE),
	ACCOUNT_STATES(4,Param.SESSION_ID),
	CREDITS,
	EXTERNAL_IDS(Param.LANGUAGE),
	IMAGES(Param.LANGUAGE, Param.INCLUDE_IMAGE_LANGUAGE),
	VIDEOS(Param.LANGUAGE);

	private int startAt;
	private int minParams;
	private int maxParams;
	private String name;
	private String baseUrl;
	private ApiParam[] params;

	private TVSeasonMethod(ApiParam... params) {
		this(3, params);
	}

	private TVSeasonMethod(int minParams, ApiParam... params) {
		this(2, minParams, BaseMethod.TV.getBaseUrl(), params);
	}

	private TVSeasonMethod(int startAt, int minParams, String parentUrl,
			ApiParam... params) {
		this.params = MethodUtil.addApiKey(params);
		this.startAt = startAt;
		this.minParams = minParams;
		this.maxParams = startAt + this.params.length;
		this.name = name().toLowerCase();
		this.baseUrl = parentUrl + "/" + name;
	}

	@Override
	public String getPropertyString() {
		return name;
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
	public <T> T parse(InputStream stream) throws JsonParseException,
			JsonMappingException, IOException {
		//return MethodUtil.MAPPER.readValue(stream, TYPE_MAP.get(this));
		return null;
	}

}
