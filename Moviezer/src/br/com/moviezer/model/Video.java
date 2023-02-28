package br.com.moviezer.model;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import br.com.moviezer.base.Media;
import br.com.moviezer.opensubtitle.Subtitle;
import br.com.moviezer.pathbuilder.MyPathBuilder;
import br.com.moviezer.util.StringUtils;
import br.com.moviezer.util.StringUtils.NullResultException;

public class Video extends Media {

	private ArrayList<Subtitle> subtitles;

	public Video(Path file) {
		super(file);
		this.subtitles = new ArrayList<>();
	}

	public void addSubtitle(Subtitle s) {
		subtitles.add(s);
	}

	public Subtitle getSubtitle(int n) {
		return subtitles.get(n);
	}

	public void printSubtitles() {
		for (Subtitle s : subtitles) {
			System.out.println(s.getDownloadCnt() + "\t" + s.getFilename());
		}
	}

	public void rename(String code) throws IOException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, NullResultException {
		String oldName = getFile().toString();
		String newName = StringUtils.invokeFromCode(code, getDetails())
				.replaceAll(":", " -");
		// System.out.println(newName == null);
		newName += oldName.substring(oldName.lastIndexOf('.'));
		System.out.println(newName);
		Path target = getFile().resolveSibling(newName);
		
		Files.move(getFile(), target);
		setFile(target);
	}

	public void move(String baseDir, MyPathBuilder pb) throws IOException {
		String strDestPath = pb.build(getDetails());
		strDestPath += getFile().getFileName().toString();
		strDestPath = baseDir + strDestPath;
		Path destPath = Paths.get(strDestPath);
		Files.createDirectories(destPath.getParent());
		Files.move(getFile(), destPath);
		setFile(destPath);
	}

	@Override
	public String toString() {
		return "Movie [subtitleIds=" + subtitles.toString() + "]";
	}

}
