package com.mediazer.osub.model.base;

import java.util.Map;

public abstract class OData<T> extends OBase{
	public T data;
	
	public OData(Map<String, ?> map) {
		super(map);
	}
	
	public T getData(){
		return data;
	}
	
	protected void setData(T data){
		this.data = data;
	}
	
}
