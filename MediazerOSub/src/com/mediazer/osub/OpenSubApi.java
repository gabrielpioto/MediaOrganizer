package com.mediazer.osub;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import com.mediazer.osub.conversor.Conversors;
import com.mediazer.osub.model.response.OLogin;
import com.mediazer.osub.model.response.OLogout;
import com.mediazer.osub.model.response.OMovies;
import com.mediazer.osub.model.response.OSubInfos;
import com.mediazer.osub.model.response.OSubtitles;

public class OpenSubApi implements OpenSub {

	private static final String BASE_URL = "http://api.opensubtitles.org/xml-rpc";
	private XmlRpcClient client;

	public OpenSubApi() {
		client = new XmlRpcClient();
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		try {
			config.setServerURL(new URL(BASE_URL));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		client.setConfig(config);
	}

	@Override
	public OLogin login(String userName, String password, String language,
			String userAgent) {
		Map<String, ?> r = execute(Method.LOGIN, userName, password, language, userAgent);
		return Conversors.<OLogin> convertDefault(OLogin.class, r);
	}

	@Override
	public OLogout logout(String token) {
		Map<String, ?> r = execute(Method.LOGOUT, token);
		return Conversors.<OLogout> convertDefault(OLogout.class, r);
	}

	@Override
	public OMovies checkMovieHash(String token, Collection<?> hashes) {
		Map<String, ?> r = execute(Method.MOVIE, token, hashes.toArray());
		return Conversors.MOVIES.convert(OMovies.class, r);
	}

	@Override
	public OSubInfos searchSubtitles(String token, Collection<OSearch> searches) {
		List<Map<String,Object>> searchList = new ArrayList<>(searches.size());
		for(OSearch search : searches){
			searchList.add(search.build());
		}		
		Map<String, ?> r = execute(Method.SEARCH, token, searchList.toArray());
		return Conversors.SUBINFOS.convert(OSubInfos.class, r);
	}

	@Override
	public OSubtitles downloadSubtitles(String token, Collection<String> idsSubtitleFile) {
		Map<String, ?> r = execute(Method.DOWNLOAD, token, idsSubtitleFile.toArray());
		return Conversors.SUBTITLES.convert(OSubtitles.class, r);
	}

	
	@SuppressWarnings("unchecked")
	private Map<String, ?> execute(Method method, Object... params) {
		Map<String, ?> result = null;
		try {
			System.out.println("Executando metodo: " + method.name);

			result = (Map<String, ?>) client.execute(method.name, params);

			System.out.println(method.name + " executado");
//			if(method == Method.MOVIE){
//				Object obj = result.get("not_processed");
//				if(obj instanceof Object[]){
//					System.out.println(Arrays.toString((Object[])obj));
//				}
//				
//			}
			//System.out.println(result.getClass());
		} catch (XmlRpcException e) {
			e.printStackTrace();
		}
		return result;
	}

	private enum Method {
		LOGIN("LogIn"),
		LOGOUT("LogOut"),
		SEARCH("SearchSubtitles"),
		DOWNLOAD("DownloadSubtitles"),
		MOVIE("CheckMovieHash");

		private String name;

		private Method(String name) {
			this.name = name;
		}
	}

}
