package br.com.moviezer.api;

import java.time.LocalDate;
import java.util.Locale;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import br.com.moviezer.model.EpisodeDetails;
import br.com.moviezer.util.HttpUtil;

public class ImdbApi {

	private static final String BASE_URL = "http://app.imdb.com/";
	private static final String API_VERSION = "v1";
	private static final String APP_ID = "iphone1";
	private static final String SIG = "app1";

	private static JSONObject getVideoDetails(String imdbID, String language) {		
		String result = HttpUtil.generateAndRequest(BASE_URL + "title/maindetails", 
				"api", API_VERSION, "appid", APP_ID,
				"locale", Locale.forLanguageTag(language).toString(),
				//"timestamp", String.valueOf(System.currentTimeMillis() / 1000),
				"tconst", imdbID, "sig", SIG);
		return (JSONObject) JSONValue.parse(result);
	}

	public static void getMovieDetails(String imdbID) {
		//TODO
		getVideoDetails(imdbID, "pt");
	}
	
	public static EpisodeDetails getEpisodeDetails(String imdbID, String language){
		JSONObject obj = getVideoDetails(imdbID, language);
		obj = (JSONObject) obj.get("data");
		JSONObject tmp = (JSONObject) obj.get("release_date");
		LocalDate date = LocalDate.parse((String) tmp.get("normal"));
		String title = (String) obj.get("title");
		tmp = (JSONObject) obj.get("series");
		String serieName = (String) tmp.get("title");
		tmp = (JSONObject) obj.get("image");
		String imgUrl = tmp!=null?(String) tmp.get("url"):null; // tmp can be null here
		tmp = (JSONObject) obj.get("best_plot");
		String overview = (String) tmp.get("outline");
		Number rating = (Number) obj.get("rating"); 
		
		EpisodeDetails ed = new EpisodeDetails(imdbID, title, date);
		ed.setSerieName(serieName);
		ed.setPosterURL(imgUrl);
		ed.setOverview(overview);
		ed.setVote(rating.doubleValue());
		
		JSONArray genres = (JSONArray) obj.get("genres");
		for(Object genre : genres){
			ed.addGenre((String) genre);
		}
		return ed;
		
	}

	

}
