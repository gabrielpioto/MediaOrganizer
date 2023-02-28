package br.com.moviezer;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.com.moviezer.model.Config;
import br.com.moviezer.pathbuilder.MyPathBuilder;
import br.com.moviezer.util.Finder;

public class Main {

	

	//private static final String SOURCE_DIR = "/mnt/HD-Windows8/Users/Gabriel/Videos/Series/Friends";
	private static final String SOURCE_DIR = "E:\\Series\\Sherlock\\Sherlock.S02.720p.BluRay.x264.ShAaNiG";
	//private static final String SOURCE_DIR = "E:\\Series\\Friends";

	public static void main(String... args) {

		// ImdbApi.getMovieDetails("tt0499549");

		// Config config = loadConfig(SOURCE_DIR,DEST_DIR,"-l","en","-m","01");
		// System.out.println(config);

		Finder finder = new Finder("*.{mp4,mkv,wmv,avi}");

		try {
			Files.walkFileTree(Paths.get(SOURCE_DIR), finder);
			//List<Path> files = finder.getMatchedFiles();
			List<Path> files = new ArrayList<>();
			files.add(Paths.get("/media/gabriel/HD-E1/Video/Series/Sherlock/03 - His Last Vow.mkv"));
//			files.add(Paths.get("/media/gabriel/HD-E1/Filmes/22 Jump Street (2014).mkv"));
//			files.add(Paths.get("/media/gabriel/HD-E1/Video/Series/Breaking Bad/Season 3/03 - I.F.T..mkv"));
//			files.add(Paths.get("/media/gabriel/HD-E1/Series/Friends/2x17 - The One Where Eddie Moves In.mkv"));
//			files.add(Paths.get("/media/gabriel/HD-E1/Series/Friends/1x06 - The One With The Butt.mkv"));
			OpenSubController osub = new OpenSubController(files);
			MyPathBuilder mpb = new MyPathBuilder();
//			mpb = mpb.add("Season %PrettySeasonNumber%").add("%SerieName%");
			mpb.If("%SeasonNumber%").add("Season %SeasonNumber%")
			.add("%SerieName%").add("Series").Else().If("%Sequel%").add("%Sequel%").add("Colecoes")
			.Else().add("%Genre1%").endElse().add("Filmes").endElse();
			//osub.move("E:\\Video", mpb);
			//osub.move("E:\\Video\\", mpb);
			//osub.rename("%PrettyEpisodeNumber% - %Title%");
			
			//osub.move("/mnt/HD-Windows8/Users/Gabriel/Videos/", mpb);
			
			
			osub.downloadBestsSubtitles("pob", false);
//			
			//osub.printPaths("E:\\MyVideos");		
//			osub.rename("%Title% (%ReleaseDate.Year%)");
//			osub.downloadBestsSubtitles("pob");
			
			//System.out.println(TheMovieApi.getMovieId("1951265"));
			
			//osub.downloadBestsSubtitles();
			
			// List<Path> files = new ArrayList<Path>();
			
			// OpenSubtitlesAPI osa = new OpenSubtitlesAPI("", "", "pob",
			// movies);
			
			
			// int n = osa.getSubtitles();
			// System.out.println(n+" subtitles found!");
			//List<Subtitle> subs = movies.get(0).getSubtitles();
			// osa.downloadAllSubtitles();
			// for(Subtitle sub : subs){
			// System.out.println(sub.getEncoded()+"\t"+sub.getFilename());
			// }

			// osa.downloadAllSubtitles();

			// osa.downloadSubtitles(allsubs);

			// for(int i=0;i<subs.size();i++){
			// String name = p.getFileName().toString();
			// String filename = name.substring(0, name.lastIndexOf('.'));
			// filename += "_"+((i<10)?"0"+(i+1):(i+1));
			// filename +="."+subs.get(i).getFormat();
			// String dir = p.getParent().toString();
			// FileOutputStream fos = new
			// FileOutputStream(dir+File.separator+filename);
			// fos.write(subs.get(i).getData());
			// fos.close();
			// }

			// osa.getImdbIds();

			// List<Movie> movies = OpenSubtitlesApi.getMovies(files, "pob");
			// for(Movie movie : movies){
			// System.out.println(movie.toString());
			// System.out.println();
			// }
			// Map<Integer, byte[]> legendas =
			// OpenSubtitlesApi.downloadSubtitles(1954396067, 1954386878,
			// 1954397519, 1954388236, 1954387912, 1954404391);
			//
			// FileOutputStream fos = new
			// FileOutputStream(SOURCE_DIR+File.separator+"legenda.srt");
			// fos.write(legendas.get(1954388236));
			// fos.close();
			// for(Path file : files){
			// movies.add(new Movie(file, config));
			// }
			//
			// List<Movie> errors = api.setImdbIds(movies);
			// Map<String,Integer> map = new HashMap<String, Integer>();
			// for(Movie movie: movies){
			// movie.moviezer();
			// String sequel = movie.getSequel();
			// if(sequel!=null){
			// int nint;
			// if(map.containsKey(sequel)){
			// nint = map.get(sequel)+1;
			// } else {
			// nint=1;
			// }
			// map.put(sequel, nint);
			// }
			//
			// }
			// for(Movie movie : movies){
			// String sequel = movie.getSequel();
			// if(sequel!=null && map.get(sequel)<=1){
			// System.out.println("precisa mudar: "+movie.getTitle());
			// movie.getConfig().setSequelMode(SequelMode.NONE);
			// movie.moviezer();
			// }
			// }
			// for(Movie errorMovie : errors){
			// System.out.println(errorMovie);
			// }
			// for(Map.Entry<String, Integer> entry : map.entrySet()){
			// System.out.println(entry.getKey()+"\t"+entry.getValue());
			// }

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	// public static List<Movie> getMovies(List<Path> paths, String language){
	// List<String> hashes = new ArrayList<String>(paths.size());
	// List<Movie> movies = new ArrayList<Movie>(paths.size());
	// List<Movie> errors = new ArrayList<Movie>();
	// for(Path path : paths){
	// Movie movie = new Movie(path);
	// movies.add(movie);
	// hashes.add(movie.getHash());
	// }
	// Map<String,String> imdbs = OpenSubtitlesApi.getImdbIds(hashes, language);
	// for(Movie movie : movies){
	// String imdb = imdbs.get(movie.getHash());
	// if(imdb == null || imdb.trim().isEmpty()){
	// System.out.println("Movie: "+movie.getPath().getFileName().toString()+" not Found: ignored");
	// errors.add(movie);
	// }
	// else
	// movie.setMovieDetails(new MovieDetails(imdb, language));
	// }
	// movies.removeAll(errors);
	// return movies;
	// }

	public static void usage() {
		System.out.println("usage");
		System.exit(-1);
	}

	public static Config loadConfig(String... args) {
		if (args.length < 2 || args.length > 8 || args.length % 2 == 1)
			usage();

		Config config = new Config(args[0], args[1]);

		for (int i = 2; i < args.length; i += 2) {
			if("-p".equals(args[i]))
				config.setMatchFilePattern(args[i+1]);
			else if("-l".equals(args[i]))
				config.setLocale(Locale.forLanguageTag(args[i+1]));
			//TODO rename and move mode
			else usage();
		}
		return config;
	}
}
