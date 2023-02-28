package com.mediazer.imdb.wrappers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({"exp","@meta","copyright"})
public class WrapperData <T> {

	@JsonProperty("data")
	private T data;
	
	public T getData(){
		return data;
	}
}
