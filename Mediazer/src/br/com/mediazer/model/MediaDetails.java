package br.com.mediazer.model;

import java.time.LocalDate;
import java.util.List;

import com.mediazer.osub.model.OMovie;

public class MediaDetails {

	private String id;
	protected String title;
	protected LocalDate releaseDate;
	protected List<String> genres;
	protected MediaType type;

	protected MediaDetails(OMovie movieOs) {
		this.id = movieOs.getMovieImdbID();
		this.title = movieOs.getMovieName();
		int year = Integer.parseInt(movieOs.getMovieYear());
		this.releaseDate = LocalDate.ofYearDay(year, 1);
	}
	
	protected MediaDetails(){
		
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public List<String> getGenres() {
		return genres;
	}

	public MediaType getType() {
		return type;
	}

	public static enum MediaType {
		EPISODE,
		FILM;
	}

}
