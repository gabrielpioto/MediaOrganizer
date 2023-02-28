package com.mediazer.tvdb.model.wrappers;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mediazer.tvdb.model.Language;

@XmlRootElement(name="Languages")
public class Languages {

	@XmlElement(name="Language")
	private List<Language> languages;
	public List<Language> getLanguages() {
		return languages;
	}
	
	

}
