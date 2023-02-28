package testejava.duplicate;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindDuplicates {
	
	public static final String KEEP_DIR = "C:\\Users\\Gabriel\\OneDrive\\Documentos";
	//public static final String KEEP_DIR = "F:\\Escola";
	public static final String DISP_DIR = "E:\\Lubuntu\\Downloads\\docs";
	
	
	public static void findNbProjects(String dir) throws Exception{
		Finder finder = new Finder("*.*");
		Path source = Paths.get(dir);
		Files.walkFileTree(source, finder);
		List<Path> files = finder.getMatchedFiles();
		for(Path file : files){
			if(file.getFileName().toString().equals("build.xml")){
				System.out.println(source.relativize(file));
			}
		}	
		
	}
	
	
	
	public static void main(String[] args) throws Exception{
		//findNbProjects("C:\\Users\\Gabriel\\OneDrive\\Escola\\IFSP\\2011\\2 Semestre\\Java");
		//System.exit(0);
		Finder finder = new Finder("*.*");
		Path ksource = Paths.get(KEEP_DIR);
		Path dsource = Paths.get(DISP_DIR);
		Files.walkFileTree(ksource, finder);
		List<Path> files = finder.getMatchedFiles();
		Map<String,Path> map = new HashMap<>();
		
		
		for(Path file : files){
			String hash = OpenSubtitlesHasher.computeHash(file);
			Path other = map.put(hash, file);
//			if(other != null){
//				System.out.println("D: "+ksource.relativize(file)+" = K:"+ksource.relativize(other));
//			}
					
		}
		finder = new Finder("*.*");
		Files.walkFileTree(dsource, finder);
		List<Path> dispFiles = finder.getMatchedFiles();

		for(Path file : dispFiles){
			String hash = OpenSubtitlesHasher.computeHash(file);
			Path other = map.put(hash, file);
			if(other != null){
				System.out.println("D: "+dsource.relativize(file)+" = K:"+ksource.relativize(other));
			}			
		}
//		
		
		
		

	}

}

