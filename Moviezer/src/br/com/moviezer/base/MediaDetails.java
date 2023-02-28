package br.com.moviezer.base;

import java.time.LocalDate;

public class MediaDetails implements Cloneable{

	private String id;
	private String title;
	private LocalDate releaseDate;
	
	public MediaDetails(String id, String title, LocalDate releaseDate) {
		super();
		this.id = id;
		this.title = title;
		this.releaseDate = releaseDate;
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
	
	@Override
	protected Object clone() {
		return new MediaDetails(id, title, releaseDate);
	}
	

}
