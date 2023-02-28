package com.mediazer.base.api;

public interface ApiParam {
	
	public String getName();

	public ApiParamType getParamType();
	
	public Class<?> getValueType();
}
