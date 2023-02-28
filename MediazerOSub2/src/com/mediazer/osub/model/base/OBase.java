package com.mediazer.osub.model.base;

import java.util.Map;

public abstract class OBase extends OObject{
	private Double seconds;
	private String status;
	
	public OBase(Map<String,?> map) {
		super(map);
	}
	
	public Double getSeconds() {
		return seconds;
	}
	
	public String getStatus() {
		return status;
	}

}
