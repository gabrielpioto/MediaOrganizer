package br.com.moviezer.opensubtitle;

public class Movie {
	private String movieHash;
	private String imdbId;
	private String movieName;
	private int movieYear;
	private String movieKind;
	private int seriesSeason;
	private int seriesEpisode;
	private long seenCount;
	private long subCount;
	
	public Movie(String movieHash, String imdbId){
		setMovieHash(movieHash);
		setImdbId(imdbId);
	}

	public String getMovieHash() {
		return movieHash;
	}

	public String getImdbID() {
		return "tt"+imdbId;
	}
	
	public String getImdbId(){
		return imdbId;
	}

	public String getMovieName() {
		return movieName;
	}

	public int getMovieYear() {
		return movieYear;
	}

	public String getMovieKind() {
		return movieKind;
	}

	public int getSeriesSeason() {
		return seriesSeason;
	}

	public int getSeriesEpisode() {
		return seriesEpisode;
	}

	public long getSeenCount() {
		return seenCount;
	}

	public long getSubCount() {
		return subCount;
	}

	void setMovieHash(String movieHash) {
		this.movieHash = movieHash;
	}

	void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}

	void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	void setMovieYear(int movieYear) {
		this.movieYear = movieYear;
	}

	void setMovieKind(String movieKind) {
		this.movieKind = movieKind;
	}

	void setSeriesSeason(int seriesSeason) {
		this.seriesSeason = seriesSeason;
	}

	void setSeriesEpisode(int seriesEpisode) {
		this.seriesEpisode = seriesEpisode;
	}

	void setSeenCount(long seenCount) {
		this.seenCount = seenCount;
	}

	void setSubCount(long subCount) {
		this.subCount = subCount;
	}
	
}
