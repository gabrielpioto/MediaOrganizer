package com.mediazer.base.api;

import java.io.IOException;
import java.io.InputStream;

public interface ApiMethod {

	public String getBaseUrl();

	public ApiParam getParam(int i);

	public int getStartAt();

	public int getMaxParams();

	public int getMinParams();

	//public TypeReference<?> getType();
	
	<T> T parse(InputStream stream) throws IOException;

}
