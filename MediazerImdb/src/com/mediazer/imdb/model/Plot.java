package com.mediazer.imdb.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Plot extends AbstractJsonMapping {

	@JsonProperty("outline")
	private String outline;
	@JsonProperty("summary")
	private String summary = "";
	@JsonProperty("total_summaries")
	private int totalSummaries = -1;
	@JsonProperty("more")
	private int more = -1;

	public String getOutline() {
		return outline;
	}

	public String getSummary() {
		return summary;
	}

	public int getTotalSummaries() {
		return totalSummaries;
	}

	public int getMore() {
		return more;
	}

}
