package com.mediazer.osub.conversor;

public interface Conversor<T,U>{
	
	T convert(Class<T> klass, U u);
}
