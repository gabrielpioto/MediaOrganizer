package com.mediazer.osub.conversor;

import java.util.Map;

import com.mediazer.osub.model.OMovie;
import com.mediazer.osub.model.OSubInfo;
import com.mediazer.osub.model.OSubtitle;
import com.mediazer.osub.model.base.DataArrayConversor;
import com.mediazer.osub.model.base.DataMapConversor;
import com.mediazer.osub.model.base.OBase;
import com.mediazer.osub.model.response.OMovies;
import com.mediazer.osub.model.response.OSubInfos;
import com.mediazer.osub.model.response.OSubtitles;

public class Conversors {
	
	private static final DefaultConversor<? extends OBase> DEFAULT = new DefaultConversor<>();	
	
	public static final DefaultConversor<OMovies> MOVIES = new DataMapConversor<OMovies, OMovie>(OMovie.class);
	public static final DefaultConversor<OSubInfos> SUBINFOS = new DataArrayConversor<OSubInfos, OSubInfo>(OSubInfo.class);
	public static final DefaultConversor<OSubtitles> SUBTITLES = new DataArrayConversor<OSubtitles, OSubtitle>(OSubtitle.class);
	
	
	@SuppressWarnings("unchecked")
	public static <T> T convertDefault(Class<T> klass, Map<String, ?> map){
		return ((DefaultConversor<T>) DEFAULT).convert(klass, map);
	}

}
