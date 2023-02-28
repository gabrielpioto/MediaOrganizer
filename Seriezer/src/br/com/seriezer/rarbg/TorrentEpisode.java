package br.com.seriezer.rarbg;

public class TorrentEpisode {
	
	private String imdbId;
	private int season = -1;
	private int episode = -1;
	private String magnet;
	private String quality;
	private String resolution;
	private int seeders = -1;
	private int leechers = -1;
	private String uploader;
	private String size; //TODO: change to double
	private boolean hd;	
	
	public TorrentEpisode(String imdbId, int season, int episode, String magnet,
			String quality, String resolution, int seeders, int leechers,
			String uploader, String size, boolean hd) {
		super();
		this.imdbId = imdbId;
		this.season = season;
		this.episode = episode;
		this.magnet = magnet;
		this.quality = quality;
		this.resolution = resolution;
		this.seeders = seeders;
		this.leechers = leechers;
		this.uploader = uploader;
		this.size = size;
		this.hd = hd;
	}
	public boolean isHd() {
		return hd;
	}
	public void setHd(boolean hd) {
		this.hd = hd;
	}
	public String getImdbId() {
		return imdbId;
	}
	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}
	public int getSeason() {
		return season;
	}
	public void setSeason(int season) {
		this.season = season;
	}
	public int getEpisode() {
		return episode;
	}
	public void setEpisode(int episode) {
		this.episode = episode;
	}
	public String getMagnet() {
		return magnet;
	}
	public void setMagnet(String magnet) {
		this.magnet = magnet;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public int getSeeders() {
		return seeders;
	}
	public void setSeeders(int seeders) {
		this.seeders = seeders;
	}
	public int getLeechers() {
		return leechers;
	}
	public void setLeechers(int leechers) {
		this.leechers = leechers;
	}
	public String getUploader() {
		return uploader;
	}
	public void setUploader(String uploader) {
		this.uploader = uploader;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	@Override
	public String toString() {
		return "Episode [imdbId=" + imdbId + ", season=" + season
				+ ", episode=" + episode + ", magnet=" + magnet + ", quality="
				+ quality + ", resolution=" + resolution + ", seeders="
				+ seeders + ", leechers=" + leechers + ", uploader=" + uploader
				+ ", size=" + size + "]";
	}
	
	
	
	

}
