package br.com.moviezer.opensubtitle;

public class Subtitle {
	private String matchedBy;
	private String idFile;
	private String id;
	private String filename;
	private String languageId;
	private String languageName;
	private String format;
	private String movieHash;
	private String subHash;
	private long downloadCnt;
	private double rating;
	private int movieImdbId;
	
	public Subtitle(String movieHash, String idFile) {
		this.movieHash = movieHash;
		this.idFile = idFile;
	}

	public String getMatchedBy() {
		return matchedBy;
	}

	public String getIdFile() {
		return idFile;
	}

	public String getId() {
		return id;
	}

	public String getFilename() {
		return filename;
	}

	public String getLanguageId() {
		return languageId;
	}

	public String getLanguageName() {
		return languageName;
	}

	public String getFormat() {
		return format;
	}

	public String getMovieHash() {
		return movieHash;
	}

	public String getSubHash() {
		return subHash;
	}

	public long getDownloadCnt() {
		return downloadCnt;
	}

	public double getRating() {
		return rating;
	}

	public int getMovieImdbId() {
		return movieImdbId;
	}

	void setMatchedBy(String matchedBy) {
		this.matchedBy = matchedBy;
	}

	void setIdFile(String idFile) {
		this.idFile = idFile;
	}

	void setId(String id) {
		this.id = id;
	}

	void setFilename(String filename) {
		this.filename = filename;
	}

	void setLanguageId(String languageId) {
		this.languageId = languageId;
	}

	void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	void setFormat(String format) {
		this.format = format;
	}

	void setMovieHash(String movieHash) {
		this.movieHash = movieHash;
	}

	void setSubHash(String subHash) {
		this.subHash = subHash;
	}

	void setDownloadCnt(long downloadCnt) {
		this.downloadCnt = downloadCnt;
	}

	void setRating(double rating) {
		this.rating = rating;
	}

	void setMovieImdbId(int movieImdbId) {
		this.movieImdbId = movieImdbId;
	}	
	
}