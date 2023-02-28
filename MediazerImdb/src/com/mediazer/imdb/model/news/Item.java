package com.mediazer.imdb.model.news;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {
	@JsonProperty("source")
	private String source;
	@JsonProperty("head")
	private String head;
	@JsonProperty("id")
	private String id;
	private DateTime dateTime;

	public String getSource() {
		return source;
	}

	public String getHead() {
		return head;
	}

	public String getId() {
		return id;
	}

	public DateTime getDateTime() {
		return dateTime;
	}

	@JsonProperty("datetime")
	private void setDateTime(String dateTime) {
		this.dateTime = DateTime.parse(dateTime);
	}

}
