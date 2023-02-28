package com.mediazer.tmdb.enums.methods;

import java.io.IOException;
import java.io.InputStream;
import java.util.EnumMap;
import java.util.EnumSet;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mediazer.base.api.ApiMethod;
import com.mediazer.base.api.ApiParam;
import com.mediazer.tmdb.enums.Param;
import com.mediazer.tmdb.interfaces.AppendToResponseMethod;
import com.mediazer.tmdb.util.MethodUtil;

/**
 * List of all "Append To Response" movie methods
 *
 * @author Stuart
 */
public enum MovieMethod implements ApiMethod, AppendToResponseMethod {

	ACCOUNT_STATES(3, Param.SESSION_ID),

	IMAGES(Param.LANGUAGE, Param.APPEND_TO_RESPONSE, Param.INCLUDE_IMAGE_LANGUAGE),
	SIMILAR(Param.PAGE, Param.LANGUAGE, Param.APPEND_TO_RESPONSE),
	REVIEWS(Param.PAGE, Param.LANGUAGE, Param.APPEND_TO_RESPONSE),
	LISTS(Param.PAGE, Param.LANGUAGE, Param.APPEND_TO_RESPONSE),
	ALTERNATIVE_TITLES(Param.COUNTRY, Param.APPEND_TO_RESPONSE),
	VIDEOS(Param.LANGUAGE, Param.APPEND_TO_RESPONSE),
	CHANGES(Param.START_DATE, Param.END_DATE),
	TRANSLATIONS(Param.APPEND_TO_RESPONSE),
	KEYWORDS(Param.APPEND_TO_RESPONSE),
	RELEASES(Param.APPEND_TO_RESPONSE),
	CREDITS(Param.APPEND_TO_RESPONSE),

	RATING_SESSION(4, "/movie/%s/rating", Param.SESSION_ID, Param.VALUE),
	RATING_GUEST(4, "/movie/%s/rating", Param.GUEST_SESSION_ID, Param.VALUE);

	private String name;
	private int minParams;
	private int maxParams;
	private String baseUrl;
	private ApiParam[] params;

	private static final EnumSet<MovieMethod> ATR = EnumSet.of(ALTERNATIVE_TITLES,
			CHANGES, CREDITS, IMAGES, KEYWORDS, LISTS, RELEASES, REVIEWS, SIMILAR,
			TRANSLATIONS, VIDEOS);
	private static final EnumMap<MovieMethod, TypeReference<?>> TYPE_MAP = new EnumMap<>(
			MovieMethod.class);

	static {

	}

	private MovieMethod(ApiParam... params) {
		this(2, params);
	}

	private MovieMethod(int minParam, ApiParam... params) {
		this(minParam, null, params);
		this.baseUrl = BaseMethod.MOVIE.getBaseUrl() + "/" + name;
	}

	private MovieMethod(int minParam, String baseUrl, ApiParam... params) {
		this.params = MethodUtil.addApiKey(params);
		this.minParams = minParam;
		this.baseUrl = baseUrl;
		this.maxParams = getStartAt() + this.params.length;
		this.name = name().toLowerCase();
	}

	/**
	 * Get the string to use in the URL
	 *
	 * @return
	 */
	@Override
	public String getPropertyString() {
		if (!ATR.contains(this))
			throw new UnsupportedOperationException();
		return name;
	}

	@Override
	public String getBaseUrl() {
		return baseUrl;
	}

	@Override
	public ApiParam getParam(int i) {
		return params[i];
	}

	@Override
	public int getStartAt() {
		return 1;
	}

	@Override
	public int getMaxParams() {
		return maxParams;
	}

	@Override
	public int getMinParams() {
		return minParams;
	}

	@Override
	public <T> T parse(InputStream stream) throws JsonParseException,
			JsonMappingException, IOException {
		return MethodUtil.MAPPER.readValue(stream, TYPE_MAP.get(this));
	}

	/**
	 * Convert a string into an Enum type
	 *
	 * @param method
	 * @return
	 * @throws IllegalArgumentException
	 *             If type is not recognised
	 *
	 */
	public static MovieMethod fromString(String method) {
		if (StringUtils.isNotBlank(method)) {
			try {
				return MovieMethod.valueOf(method.trim().toUpperCase());
			} catch (IllegalArgumentException ex) {
				throw new IllegalArgumentException("Method " + method
						+ " does not exist.", ex);
			}
		}
		throw new IllegalArgumentException("Method must not be null");
	}

}
