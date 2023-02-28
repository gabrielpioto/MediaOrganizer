package br.com.moviezer.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class HttpUtil {

	public static String generateAndRequest(String mainUrl, String... params) {
		String url = genURL(mainUrl, params);
		//System.out.println("URL gerada: " + url);
		String result = "";
		try {
			result = requestGet(url);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public static String requestGet(String strUrl) throws IOException {
		URL url = new URL(strUrl);

		BufferedReader in = new BufferedReader(new InputStreamReader(
				url.openStream(), "UTF-8"));
		String inputLine;
		StringBuilder builder = new StringBuilder();
		while ((inputLine = in.readLine()) != null){
			//System.out.println(inputLine);
			builder.append(inputLine);
		}
		in.close();
		return builder.toString();
	}

	public static String genURL(String mainUrl, String... params) {
		if (params.length < 2 || params.length % 2 != 0)
			return null;
		StringBuilder builder = new StringBuilder(mainUrl).append("?");
		builder.append(params[0]).append("=").append(params[1]);
		for (int i = 2; i < params.length; i += 2) {
			builder.append("&").append(params[i]).append("=")
					.append(params[i + 1]);
		}
		return builder.toString();
	}

}
