package com.mediazer.osub.model.base;

import org.apache.commons.lang3.builder.RecursiveToStringStyle;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public abstract class OObject {
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, RecursiveToStringStyle.SHORT_PREFIX_STYLE);
	}
}
