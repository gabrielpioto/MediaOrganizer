package br.com.moviezer.opensubtitle;

public enum Method {
	LOGIN("LogIn"), LOGOUT("LogOut"), SEARCH("SearchSubtitles"), DOWNLOAD(
			"DownloadSubtitles"), MOVIE("CheckMovieHash");

	private String name;

	private Method(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
