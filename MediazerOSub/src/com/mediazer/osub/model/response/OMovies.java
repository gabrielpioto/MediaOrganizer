package com.mediazer.osub.model.response;

import java.util.Map;

import com.mediazer.osub.model.OMovie;
import com.mediazer.osub.model.base.OData;

public class OMovies extends OData<Map<String, OMovie>> {
	private Object[] not_processed;

	public Object[] getNotProcessed() {
		return not_processed;
	}
	
}
