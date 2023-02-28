package com.mediazer.imdb.enums;

import java.util.Locale;

import com.mediazer.base.api.ApiParam;
import com.mediazer.base.api.ApiParamType;

public enum Param implements ApiParam{
	TCONST(String.class),
	NCONST(String.class),
	LOCALE(Locale.class);

	private Class<?> type;
	private String name;
	
	private Param(Class<?> type) {
		this.type = type;
		this.name = name().toLowerCase();
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
		return type;
	}

}
