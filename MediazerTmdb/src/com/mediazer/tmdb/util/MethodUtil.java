package com.mediazer.tmdb.util;

import java.util.Arrays;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mediazer.base.api.ApiParam;
import com.mediazer.tmdb.enums.Param;

public class MethodUtil {
	
	public static final ObjectMapper MAPPER = new ObjectMapper();
	
	public static ApiParam[] addApiKey(ApiParam[] params) {
		return prepend(params, Param.API_KEY);
	}

	@SafeVarargs
	public static <T> T[] prepend(T[] target, T... values) {
		T[] aux = Arrays.copyOf(values, values.length + target.length);
		System.arraycopy(target, 0, aux, values.length, target.length);
		return aux;
	}

}
