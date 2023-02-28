package com.mediazer.tvdb;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import com.mediazer.base.api.AbstractApi;
import com.mediazer.tvdb.enums.StaticMethod;
import com.mediazer.tvdb.model.Language;
import com.mediazer.tvdb.model.wrappers.Languages;

public class TvdbApi extends AbstractApi {

	private Set<Locale> locales = new HashSet<Locale>();

	public TvdbApi(String apiKey) {
		super("http://thetvdb.com/api");
		List<Language> langs;
		try {
			langs = ((Languages) get(StaticMethod.LANGUAGES, apiKey)).getLanguages();
			for(Language l : langs){
				locales.add(Locale.forLanguageTag(l.getAbbreviation()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
