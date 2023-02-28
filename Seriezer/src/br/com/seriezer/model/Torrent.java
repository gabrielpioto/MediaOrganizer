package br.com.seriezer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Torrent {
	@JsonProperty("url")
	private String magnetUrl;
	@JsonProperty("seeds")
	private long seeds;
	@JsonProperty("peers")
	private long peers;
	public String getMagnetUrl() {
		return magnetUrl;
	}
	public long getSeeds() {
		return seeds;
	}
	public long getPeers() {
		return peers;
	}
	
	
}
