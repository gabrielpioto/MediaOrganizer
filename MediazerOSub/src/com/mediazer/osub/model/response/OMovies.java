package com.mediazer.osub.model.response;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.mediazer.osub.model.OMovie;
import com.mediazer.osub.model.base.OData;

public class OMovies extends OData<Map<String, OMovie>> {
	private Object[] not_processed;
	
	public OMovies(Map<String, ?> map) {
		super(map);
	}

	public Object[] getNotProcessed() {
		return not_processed;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void fromMap(Map<String, ?> map) {
		super.fromMap(map);
		Object obj;
		Map<String, OMovie> ndata = null;
		if ((obj = map.get("data")) instanceof Map) {
			Map<String, ?> data = (Map<String, ?>) obj;
			ndata = new HashMap<>();
			for (Entry<String, ?> entry : data.entrySet()) {
				OMovie movie = null;
				if((obj = entry.getValue()) instanceof Map){
					Map<String, String> movieMap = (Map<String, String>) obj;
					movie = new OMovie(movieMap);
				}
				ndata.put(entry.getKey(), movie);
			}
		} else {
			System.out.println("nada encontrado");
			//TODO: Log proper
		}
		
		setData(ndata);
	}

}
