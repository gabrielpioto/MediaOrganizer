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

public enum TVEpisodeMethod implements ApiMethod, AppendToResponseMethod {
	CHANGES(1,2,"/tv/episode/%s",Param.START_DATE, Param.END_DATE),
	ACCOUNT_STATES(5,Param.SESSION_ID),
	CREDITS,
	EXTERNAL_IDS(Param.LANGUAGE),
	IMAGES,
	RATING(6,Param.SESSION_ID,Param.VALUE),
	RATING_GUEST(6,RATING.getPropertyString(),Param.GUEST_SESSION_ID, Param.VALUE),
	VIDEOS(Param.LANGUAGE), ;

	private int startAt;
	private int minParams;
	private int maxParams;
	private String baseUrl;
	private String name;
	private ApiParam[] params;

	private TVEpisodeMethod(ApiParam... params) {
		this(4, params);
	}

	private TVEpisodeMethod(int minParams, ApiParam... params) {
		this(3, minParams, BaseMethod.TV_SEASON_EPISODE.getBaseUrl(), params);
	}

	private TVEpisodeMethod(int minParams, String name, ApiParam... params) {
		this(minParams, params);
		this.name = name;
		this.baseUrl = BaseMethod.TV_SEASON_EPISODE.getBaseUrl() + "/" + name;
	}

	private TVEpisodeMethod(int startAt, int minParams, String parentUrl,
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
