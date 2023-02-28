package com.mediazer.osub;

import java.util.Collection;

import com.mediazer.osub.model.response.OLogin;
import com.mediazer.osub.model.response.OLogout;
import com.mediazer.osub.model.response.OMovies;
import com.mediazer.osub.model.response.OSubInfos;
import com.mediazer.osub.model.response.OSubtitles;

public interface OpenSub {
	OLogin login(String userName, String password, String language, String userAgent);
	OLogout logout(String token);
	OMovies checkMovieHash(String token, Collection<?> hashes);
	OSubInfos searchSubtitles(String token, Collection<OSearch> searches);
	OSubtitles downloadSubtitles(String token, Collection<String> idsSubtitleFile);
}
