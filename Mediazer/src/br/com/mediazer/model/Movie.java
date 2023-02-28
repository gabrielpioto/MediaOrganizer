package br.com.mediazer.model;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.mediazer.osub.model.OSubInfo;

public class Movie extends Media {

	private Path file;
	private List<OSubInfo> subtitles;

	public Movie(Path file) {
		this.file = file;
		subtitles = new ArrayList<>();
	}

	public Path getFile() {
		return file;
	}

	public void setFile(Path file) {
		this.file = file;
	}

	public List<OSubInfo> getSubtitles() {
		return subtitles;
	}

	public OSubInfo getMostDownloadedSubtitle() {
		if (subtitles.size() == 0)
			return null;
		return Collections.max(subtitles, (s, t) -> {
			int x = Integer.parseInt(s.getSubDownloadsCnt());
			int y = Integer.parseInt(t.getSubDownloadsCnt());
			return Integer.compare(x, y);
		});

	}

	public OSubInfo getTopRatedSubtitle() {
		if (subtitles.size() == 0)
			return null;
		return Collections.max(subtitles,(s, t) -> {
			double x = Double.parseDouble(s.getSubRating());
			double y = Double.parseDouble(t.getSubRating());
			return Double.compare(x, y);
		});
	}

	@Override
	public String toString() {
		return getFile().toString();
	}

}
