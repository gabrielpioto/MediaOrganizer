package com.mediazer.osub;

import java.util.HashMap;
import java.util.Map;

public class OSearch {

	private Map<String,Object> map;
	
	public OSearch() {
		this("all");
		
	}
	
	public OSearch(String subLanguageId){
		this(Term.SUB_LANGUAGE_ID, subLanguageId);
	}
	
	public OSearch(Term term, Object value){
		map = new HashMap<>();
		set(term, value);
	}
	
	public OSearch set(Term term, Object value){
		map.put(term.name, value);
		return this;
	}
	
	Map<String, Object> build(){
		return map;
	}	
	
	public static enum Term{
		SUB_LANGUAGE_ID("sublanguageid"),
		MOVIE_HASH("moviehash"),
		MOVIE_SIZE("moviebytesize"),
		IMDB_ID("imdbid"),
		QUERY("query"),
		SEASON("season"),
		EPISODE("episode"),
		TAG("tag");

		private String name;

		private Term(String name) {
			this.name = name;			
		}
	}
}
