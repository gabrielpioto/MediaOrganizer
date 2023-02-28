package com.mediazer.tmdb;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mediazer.base.api.ApiMethod;
import com.mediazer.base.api.ApiParam;
import com.mediazer.tmdb.util.MethodUtil;

public abstract class Discover implements ApiMethod{
	
	private Object[] values;
	
	public Discover() {
		values = new Object[getMaxParams()];
	}
	
	protected void add(Enum<? extends ApiParam> param, Object value){
		values[param.ordinal()] = value;		
	}
	
	@Override
	public int getMaxParams() {
		return getParamEnumClass().getEnumConstants().length;
	}
	
	@Override
	public int getMinParams() {
		return 1;
	}
	
	@Override
	public int getStartAt() {
		return 0;
	}
	
	@Override
	public ApiParam getParam(int i) {
		return (ApiParam) getParamEnumClass().getEnumConstants()[i];
	}
	
	@Override
	public String getBaseUrl() {
		return "/discover";
	}
	
	Object[] getValues(){
		return values;
	}
	
	void setApiKey(String apiKey){
		values[0] = apiKey;
	}
	
	@Override
	public <T> T parse(InputStream stream) throws JsonParseException,
			JsonMappingException, IOException {
		return MethodUtil.MAPPER.readValue(stream, getType());
	}
	
	protected abstract TypeReference<?> getType();
	
	protected abstract Class<? extends Enum<? extends ApiParam>> getParamEnumClass();
			
			

}
