package com.mediazer.osub.model;

import java.util.Map;

import com.mediazer.osub.model.base.OObject;

public class OSubtitle extends OObject{
	private String idsubtitlefile, data;
	
	public OSubtitle(Map<String,?> map) {
		super(map);
	}
	
	public String getData() {
		return data;
	}
	
	public String getIdsubtitlefile() {
		return idsubtitlefile;
	}

}
