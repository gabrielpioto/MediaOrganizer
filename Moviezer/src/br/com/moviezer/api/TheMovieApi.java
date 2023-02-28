package br.com.moviezer.api;

import java.time.LocalDate;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import br.com.moviezer.model.VideoDetails;
import br.com.moviezer.util.HttpUtil;

public class TheMovieApi {

	private static final String BASE_URL = "https://api.themoviedb.org/3";
	private static final String API_KEY = "fa972b7ad92b3145d8babab8f95abd7c";
	private static final String BASE_IMG_URL = "http://image.tmdb.org/t/p/original";

	// FIND
	public static long getMovieId(String imdbID) {
		String temp = BASE_URL + "/find/" + imdbID;
		temp = HttpUtil.generateAndRequest(temp, "api_key", API_KEY,
				"external_source", "imdb_id");
		JSONObject obj = (JSONObject) JSONValue.parse(temp);
		JSONArray array = (JSONArray) obj.get("movie_results");
		obj = (JSONObject) array.get(0);
		return (long) obj.get("id");
	}

	// MOVIE
	public static JSONObject getMovie(long movieId, String language) {
		String tmp = BASE_URL + "/movie/" + movieId;
		tmp = HttpUtil.generateAndRequest(tmp, "api_key", API_KEY, "language", language);
		return (JSONObject) JSONValue.parse(tmp);
	}

	public static VideoDetails getMovieDetails(String imdbID, String language) {
		long movieId = getMovieId(imdbID);
		JSONObject movie = getMovie(movieId, language);

		String title = ((String)movie.get("title")).replaceAll(":", " -");
		LocalDate releaseDate = LocalDate
				.parse((String)movie.get("release_date"));
		VideoDetails details = new VideoDetails(imdbID, title, releaseDate);

		JSONArray jsonGenres = (JSONArray) movie.get("genres");
		for (int i = 0; i < jsonGenres.size(); i++) {
			JSONObject genre = (JSONObject) jsonGenres.get(i);
			details.addGenre((String)genre.get("name"));
		}

		Object jsonCollection = movie.get("belongs_to_collection");
		
		if (null!=jsonCollection) {
			String sequel = (String)((JSONObject) jsonCollection).get("name");
			sequel = sequel.replaceAll(":", " -");
			details.setSequel(sequel);
		}

		details.setOverview((String)movie.get("overview"));
		details.setPosterURL(BASE_IMG_URL + (String)movie.get("poster_path"));
		details.setVote((double) movie.get("vote_average"));
		return details;
	}

}
