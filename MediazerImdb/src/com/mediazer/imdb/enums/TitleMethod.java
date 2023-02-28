package com.mediazer.imdb.enums;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mediazer.base.api.ApiMethod;
import com.mediazer.base.api.ApiParam;
import com.mediazer.imdb.model.TitleDetails;
import com.mediazer.imdb.util.MethodUtil;
import com.mediazer.imdb.wrappers.WrapperData;

public enum TitleMethod implements ApiMethod {
	MAINDETAILS(new TypeReference<WrapperData<TitleDetails>>() {});
	;
	private static final ApiParam[] params = { Param.TCONST, Param.LOCALE };

	private String baseUrl;
	private TypeReference<?> type;

	private TitleMethod(TypeReference<?> type) {
		baseUrl = "/title/" + name().toLowerCase();
		this.type = type;
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
		return 2;
	}

	@Override
	public int getMinParams() {
		return 1;
	}

	@Override
	public <T> T parse(InputStream stream) throws IOException {
		return MethodUtil.MAPPER.readValue(stream, type);
	}

}
