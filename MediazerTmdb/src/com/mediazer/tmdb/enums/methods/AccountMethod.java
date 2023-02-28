package com.mediazer.tmdb.enums.methods;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mediazer.base.api.ApiMethod;
import com.mediazer.base.api.ApiParam;

public enum AccountMethod implements ApiMethod{
	LISTS, 
	FAVORITE_MOVIES,
	FAVORITE_TV, 
	FAVORITE,
	RATED_MOVIES, 
	RATED_TV,
	RATED_TV_EPISODES,
	WATCHLIST_MOVIES,
	WATCHLIST_TV,
	WATCHLIST,
	
	;

	@Override
	public String getBaseUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiParam getParam(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getStartAt() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxParams() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMinParams() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T> T parse(InputStream stream) throws JsonParseException,
			JsonMappingException, IOException {
		//return MethodUtil.MAPPER.readValue(stream, TYPE_MAP.get(this));
		return null;
	}

}
