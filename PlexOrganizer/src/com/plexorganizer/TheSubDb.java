package com.plexorganizer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.mediazer.subdb.TheSubDbApi;

public class TheSubDb {
	
	private Collection<Path> files;
	private boolean isLoaded = false;
	private Map<String,Path> mapHashFile;
	private TheSubDbApi tsdb;
	
	public TheSubDb(Collection<Path> files) {
		this.files = files;
		this.mapHashFile = new HashMap<>();
		this.tsdb = new TheSubDbApi("Subdb", "1.0.1", "http://github.com/arshad/subdb-cli");
	}
	
	private void load(){
		if(isLoaded) return;
		for(Path file : files){
			String hash = TheSubDbApi.Hasher.computeHash(file);
			Path other = mapHashFile.put(hash, file);
			if(other != null){
				System.out.println("Duplicate files");
				System.exit(0);
			}			
		}
		isLoaded = true;
	}
	
	public void downloadSubtitles(String language){
		load();
		for (Entry<String, Path> entry : mapHashFile.entrySet()) {
			Path file = entry.getValue();
			String parent = file.getParent().toString();
			String fileName = File.separator + file.getFileName().toString();
			fileName = fileName.substring(0, fileName.lastIndexOf('.')) + "."+language+".srt";
			try {
				FileOutputStream fos = new FileOutputStream(parent + fileName);
				if(!tsdb.downloadSubtitle(entry.getKey(), language, fos)){
					System.out.println("sub for "+file+" in lang "+language+" not found :(");
				} else {
					System.out.println("sub for "+file+" in lang "+language+" found :)");
				}
				
			} catch (IOException | URISyntaxException e) {
				e.printStackTrace();
			}
		}
		
	}

}
