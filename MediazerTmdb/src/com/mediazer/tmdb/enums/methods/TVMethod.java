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

public enum TVMethod implements ApiMethod, AppendToResponseMethod {
	ACCOUNT_STATES(3, Param.SESSION_ID),
	ALTERNATIVE_TITLES,
	CHANGES(Param.START_DATE, Param.END_DATE),
	CONTENT_RATINGS,
	CREDITS(Param.LANGUAGE, Param.APPEND_TO_RESPONSE),
	EXTERNAL_IDS(Param.LANGUAGE),
	IMAGES(Param.LANGUAGE, Param.INCLUDE_IMAGE_LANGUAGE),
	KEYWORDS(Param.APPEND_TO_RESPONSE),
	RATING(4, Param.SESSION_ID, Param.VALUE),
	RATING_GUEST(4, RATING.getPropertyString(), Param.GUEST_SESSION_ID, Param.VALUE),
	SIMILAR(Param.PAGE, Param.LANGUAGE, Param.APPEND_TO_RESPONSE),
	TRANSLATIONS,
	VIDEOS(Param.LANGUAGE), ;

	private int minParams;
	private int maxParams;
	private String baseUrl;
	private String name;
	private ApiParam[] params;

	private TVMethod(ApiParam... params) {
		this(2, params);
	}

	private TVMethod(int minParams, ApiParam... params) {
		this.minParams = minParams;
		this.params = MethodUtil.addApiKey(params);
		this.name = name().toLowerCase();
		this.baseUrl = BaseMethod.TV.getBaseUrl() + "/" + name;
		this.maxParams = getStartAt() + this.params.length;
	}

	private TVMethod(int minParams, String name, ApiParam... params) {
		this.minParams = minParams;
		this.params = MethodUtil.addApiKey(params);
		this.name = name;
		this.baseUrl = BaseMethod.TV.getBaseUrl() + "/" + name;
		this.maxParams = getStartAt() + this.params.length;
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
		return 1;
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
