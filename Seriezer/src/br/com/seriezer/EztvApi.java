package br.com.seriezer;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import br.com.seriezer.model.Episode;
import br.com.seriezer.model.Serie;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class EztvApi {
	
	
	private static final String BASE_URL = "http://eztvapi.re/show/";
	private ObjectMapper mapper;
	
	public EztvApi() {
		mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}
	
	public Serie getSerie(String imdbId) throws JsonParseException, JsonMappingException, MalformedURLException, IOException{
		return mapper.readValue(new URL(BASE_URL+imdbId), Serie.class);
	}
	
	public List<Episode> getEpisodesBySeason(String imdbId, int season){
		List<Episode> episodes = new ArrayList<>();
		Serie serie = null;
		try {
			serie = getSerie(imdbId);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		for(Episode e : serie.getEpisodes()){
			if(e.getSeason() == season)
				episodes.add(e);
		}
		return episodes;		
	}
	
	
	
}
