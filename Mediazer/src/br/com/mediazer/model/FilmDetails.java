package br.com.mediazer.model;

import java.time.LocalDate;
import java.util.stream.Collectors;

import com.mediazer.osub.model.OMovie;
import com.mediazer.tmdb.model.Genre;
import com.mediazer.tmdb.model.collection.Collection;
import com.mediazer.tmdb.model.movie.MovieInfo;

public class FilmDetails extends MediaDetails{
	
	private String collection;
	private double vote;
	
	public FilmDetails(MovieInfo info) {
		Collection c = info.getBelongsToCollection();
		collection = (c==null)?null:c.getTitle();
		vote = info.getVoteAverage();
		type = MediaType.FILM;
		genres = info.getGenres().stream().map(Genre::getName).collect(Collectors.toList());
		releaseDate = LocalDate.parse(info.getReleaseDate());
		title = info.getTitle();
	}
	
	public FilmDetails(OMovie movieOs) {
		super(movieOs);
	}

	public String getCollection() {
		return collection;
	}

	public double getVote() {
		return vote;
	}	

}
