package com.mediazer.tvdb.model;

import javax.xml.bind.annotation.XmlElement;

public class Language {

	@XmlElement
	private String name;
	@XmlElement
	private String abbreviation;
	@XmlElement
	private int id;

	public String getName() {
		return name;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Language [name=" + name + ", abbreviation=" + abbreviation + ", id=" + id
				+ "]";
	}

}
