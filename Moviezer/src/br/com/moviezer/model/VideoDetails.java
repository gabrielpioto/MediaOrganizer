package br.com.moviezer.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.moviezer.base.MediaDetails;

public class VideoDetails extends MediaDetails{
	
	private List<String> genres;
	private String sequel;
	private String overview;
	private String posterURL;
	private double vote;
	
	public VideoDetails(String id, String title, LocalDate releaseDate) {
		super(id, title, releaseDate);
		genres = new ArrayList<String>();
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public String getPosterURL() {
		return posterURL;
	}

	public void setPosterURL(String posterURL) {
		this.posterURL = posterURL;
	}

	public double getVote() {
		return vote;
	}

	public void setVote(double vote) {
		this.vote = vote;
	}
	
	public void setSequel(String sequel) {
		this.sequel = sequel;
	}

	public String getSequel() {
		return sequel;
	}

	public String getGenre(int i){
		return genres.get(i);
	}
	
	public String getGenre1(){
		return genres.get(0);
	}
	
	public String getGenre2(){
		return genres.get(1);
	}
	
	public int getNumGenres(){
		return genres.size();
	}
	
	public void addGenre(String genre){
		genres.add(genre);
	}	
	
}
