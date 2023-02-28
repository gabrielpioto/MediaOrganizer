package com.mediazer.imdb.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Certificate extends AbstractJsonMapping {
	@JsonProperty("certificate")
	private String certificate;
	@JsonProperty("attr")
	private String attr;
	@JsonProperty("country")
	private String country;

	public String getCertificate() {
		return certificate;
	}

	public String getAttr() {
		return attr;
	}

	public String getCountry() {
		return country;
	}

}
