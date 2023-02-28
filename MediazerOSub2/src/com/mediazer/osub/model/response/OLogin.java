package com.mediazer.osub.model.response;

import java.util.Map;

import com.mediazer.osub.model.base.OBase;

public class OLogin extends OBase{

	private String token;
	
	public OLogin(Map<String, ?> map) {
		super(map);
	}
	
	public String getToken(){
		return token;
	}	
	
}
