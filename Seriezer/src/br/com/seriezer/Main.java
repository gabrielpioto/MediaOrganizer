package br.com.seriezer;

import java.awt.Desktop;
import java.net.URI;
import java.util.Map;

import br.com.seriezer.model.Episode;
import br.com.seriezer.model.Serie;
import br.com.seriezer.model.Torrent;


public class Main {

	public static void main(String[] args) throws Exception{
		EztvApi api = new EztvApi();
		Serie serie = api.getSerie("tt3475734");
		for(Episode ep : serie.getEpisodes()){
			System.out.println("S"+ep.getSeason()+"E"+ep.getEpisode()+": "+ep.getTitle());
			String magnet = getBestMagnet(ep);
			Desktop.getDesktop().browse(new URI(magnet));		
		}
		
	}
	
	private static String getBestMagnet(Episode episode){
		return getBestMagnet(episode.getTorrents(),"1080p","720p","480p","0");
	}
	
	private static String getBestMagnet(Map<String,Torrent> map, String...strings){
		for(String s : strings){
			if(map.containsKey(s))
				return map.get(s).getMagnetUrl();
			System.out.println("nao tem: "+s);
		}
		return null;
		
	}

	
	
	
}
