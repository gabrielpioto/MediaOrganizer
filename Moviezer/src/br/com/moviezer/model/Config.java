package br.com.moviezer.model;

import java.util.Locale;

public class Config {
	
	private String sourceDir;
	private String destDir;

	private Locale locale;
	private String matchFilePattern;
	
	public Config(String sourceDir, String destFolder) {
		this.sourceDir = sourceDir;
		this.destDir = destFolder;
//		this.genreMode = GenreMode.TREE_GENRE;
//		this.sequelMode = SequelMode.MIDDLESIDE;
		this.locale = Locale.getDefault();
		this.matchFilePattern = "*.{mp4,mkv}"; 
	}

	public String getMatchFilePattern() {
		return matchFilePattern;
	}

	public void setMatchFilePattern(String matchFilePattern) {
		this.matchFilePattern = matchFilePattern;
	}

	public String getSourceDir() {
		return sourceDir;
	}

	public String getDestDir() {
		return destDir;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	
	
	
	
//	@Override
//	public String toString() {
//		return "Config [sourceDir=" + sourceDir + ", destDir=" + destDir
//				+ ", genreMode=" + genreMode + ", sequelMode=" + sequelMode
//				+ ", locale=" + locale + ", matchFilePattern="
//				+ matchFilePattern + "]";
//	}



	
	
	
	
}
