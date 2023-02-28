package com.mediazer.osub.model.base;

public abstract class OData<T> extends OBase{
	private T data;
	
	public T getData(){
		return data;
	}
	
	protected void setData(T data){
		this.data = data;
	}
	
}
