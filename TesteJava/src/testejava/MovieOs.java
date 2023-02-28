package testejava;

public class MovieOs {

	private String MovieHash, MovieImdbID, MovieName, MovieYear, MovieKind, SeriesSeason,
			SeriesEpisode, SeenCount, SubCount;

	public MovieOs(String hash) {
		MovieHash = hash;
	}

	public String getMovieHash() {
		return MovieHash;
	}

	public String getMovieImdbID() {
		return MovieImdbID;
	}

	public String getMovieName() {
		return MovieName;
	}

	public String getMovieYear() {
		return MovieYear;
	}

	public String getMovieKind() {
		return MovieKind;
	}

	public String getSeriesSeason() {
		return SeriesSeason;
	}

	public String getSeriesEpisode() {
		return SeriesEpisode;
	}

	public String getSeenCount() {
		return SeenCount;
	}

	public String getSubCount() {
		return SubCount;
	}

	@Override
	public String toString() {
		return "MovieOs [MovieHash=" + MovieHash + ", MovieImdbID=" + MovieImdbID
				+ ", MovieName=" + MovieName + ", MovieYear=" + MovieYear
				+ ", MovieKind=" + MovieKind + ", SeriesSeason=" + SeriesSeason
				+ ", SeriesEpisode=" + SeriesEpisode + ", SeenCount=" + SeenCount
				+ ", SubCount=" + SubCount + "]";
	}

}
