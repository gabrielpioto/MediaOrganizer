package com.mediazer.osub.model.base;

import java.util.Map;

import org.apache.commons.lang3.builder.RecursiveToStringStyle;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.mediazer.osub.util.Convert;

public abstract class OObject implements Convertible{

	
	public OObject(Map<String,?> map) {
		fromMap(map);
	}
	
	@Override
	public void fromMap(Map<String, ?> map) {
		Convert.fromMap(this, map);
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, RecursiveToStringStyle.SHORT_PREFIX_STYLE);
	}
}
