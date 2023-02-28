/*
 *      Copyright (c) 2004-2015 Stuart Boston
 *
 *      This file is part of TheMovieDB API.
 *
 *      TheMovieDB API is free software: you can redistribute it and/or modify
 *      it under the terms of the GNU General Public License as published by
 *      the Free Software Foundation, either version 3 of the License, or
 *      any later version.
 *
 *      TheMovieDB API is distributed in the hope that it will be useful,
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *      GNU General Public License for more details.
 *
 *      You should have received a copy of the GNU General Public License
 *      along with TheMovieDB API.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.mediazer.tmdb.enums;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDate;

import com.mediazer.base.api.ApiParam;
import com.mediazer.base.api.ApiParamType;
import com.mediazer.tmdb.AppendToResponseBuilder;

/**
 * Parameters for use in the URL
 *
 * @author Stuart
 */
public enum Param implements ApiParam{

    API_KEY(String.class),
    LANGUAGE(String.class), //TODO: change to language
    APPEND_TO_RESPONSE(AppendToResponseBuilder.class),
    SESSION_ID(String.class),
    COUNTRY(String.class), //TODO: change to country
    PAGE(Integer.class),
    START_DATE(LocalDate.class),
	END_DATE(LocalDate.class), 
    GUEST_SESSION_ID(String.class),
    INCLUDE_IMAGE_LANGUAGE(String.class), //TODO: change to language
    INCLUDE_ADULT(Boolean.class),
    QUERY(String.class),
    YEAR(Integer.class),
    PRIMARY_RELEASE_YEAR(Integer.class),
    SEARCH_TYPE(SearchType.class),
    FIRST_AIR_DATE_YEAR(Integer.class),
    TIMEZONE(String.class), //TODO: change to timezone
    EXTERNAL_SOURCE(ExternalSource.class),
    
    VALUE(String.class) //TODO: change to jsonElement acho

    ;
    private String name;
    private Class<?> valueType;
    private ApiParamType paramType;

    private Param(Class<?> valueType){
    	this(valueType,ApiParamType.URL);
    }
    
    private Param(Class<?> valueType, ApiParamType paramType){
    	this(null,valueType,paramType);
    	this.name = name().toLowerCase();
    }

	private Param(String name, Class<?> valueType, ApiParamType paramType) {
		this.name = name;
		this.valueType = valueType;
		this.paramType = paramType;
	}

	@Override
	public ApiParamType getParamType() {
		return paramType;
	}

	@Override
	public Class<?> getValueType() {
		return valueType;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	/**
     * Convert a string into an Enum type
     *
     * @param value
     * @return
     */
    public static Param fromString(String value) {
    	if (StringUtils.isNotBlank(value)) {
			try {
				return Param.valueOf(value.trim().toUpperCase());
			} catch (IllegalArgumentException ex) {
				throw new IllegalArgumentException("Param " + value
						+ " does not exist.", ex);
			}
		}
		throw new IllegalArgumentException("Param must not be null");
    }

}
