package br.com.moviezer.base;

import java.nio.file.Path;

public class Media {
	private MediaDetails details;
	private Path file;
	
	public Media(Path file) {
		super();
		this.file = file;
	}
	
	public MediaDetails getDetails() {
		return details;
	}
	public void setDetails(MediaDetails details) {
		this.details = details;
	}
	public Path getFile() {
		return file;
	}
	public void setFile(Path file) {
		this.file = file;
	}
	
	

}
