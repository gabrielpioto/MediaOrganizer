package br.com.mediazer.model;

import com.mediazer.osub.model.OMovie;

public class EpisodeDetails extends MediaDetails{
	
	private String serieTitle;
	private int seasonNumber;
	private int episodeNumber;
	
	public EpisodeDetails(OMovie movieOs) {
		super(movieOs);
		//TODO: fix title and serieTitle
		title = movieOs.getMovieName();
		serieTitle = movieOs.getMovieName();
		seasonNumber = Integer.parseInt(movieOs.getSeriesSeason());
		episodeNumber = Integer.parseInt(movieOs.getSeriesEpisode());
		type = MediaType.EPISODE;
	}

	public String getSerieTitle() {
		return serieTitle;
	}

	public int getSeasonNumber() {
		return seasonNumber;
	}

	public int getEpisodeNumber() {
		return episodeNumber;
	}
	
	

}
