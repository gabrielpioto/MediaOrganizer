package br.com.seriezer.rarbg;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.client.ClientProtocolException;

import com.jaunt.Document;
import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.NotFound;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;
import com.jaunt.component.Form;

public class Rarbg {

	private static final String POPULATE_TV_URL = "https://rarbg.com/tv.php?ajax=1&tvepisode=";
	private static final UserAgent userAgent = new UserAgent();
	private static final Pattern ES_PATTERN = Pattern.compile("S(\\d+)E(\\d+)");

	public static void main(String[] args) throws NotFound, ResponseException,
			ClientProtocolException, IOException, URISyntaxException {
		List<TorrentEpisode> eps = getAllEpisodes("tt2364582");
//		Collections.sort(eps, new Comparator<TorrentEpisode>() {
//			@Override
//			public int compare(TorrentEpisode o1, TorrentEpisode o2) {
//				return o2.getSeeders() - o1.getSeeders();
//			}
//		});
		TreeSet<TorrentEpisode> set = new TreeSet<>(new Comparator<TorrentEpisode>() {
			@Override
			public int compare(TorrentEpisode o1, TorrentEpisode o2) {
				String s1 = o1.getSeason() + "" + o1.getEpisode();
				String s2 = o2.getSeason() + "" + o2.getEpisode();
				return Integer.valueOf(s1).compareTo(Integer.valueOf(s2));
			}
		});

		for (TorrentEpisode ep : eps) {
			if (ep.isHd()){
				if (!set.add(ep)) {
					TorrentEpisode epSet = set.tailSet(ep).first();
					if (ep.getSeeders() > epSet.getSeeders()) {
						set.remove(epSet);
						if (!set.add(ep))
							System.out.println("PAM!");
					}
				}
			}
		}

		set.forEach(System.out::println);

	}

	public static void getDocument(Document doc) throws NotFound,
			ClientProtocolException, IOException, URISyntaxException, ResponseException {
		Form f = doc.getForm(0);
		String imgUrl = f.getElement().getElement(0).getAt("src");
		String line = MainOcr.ocr(imgUrl);
		System.out.println("------------" + line + "------------");
		f.set("solve_string", line);
		f.submit();
	}

	public static List<TorrentEpisode> getAllEpisodes(String imdbId)
			throws ResponseException, NotFound, ClientProtocolException, IOException,
			URISyntaxException {
		List<TorrentEpisode> allEpisodes = new ArrayList<>();
		Document doc;
		Elements ols;
		do {
			doc = userAgent.visit("http://rarbg.com/tv/" + imdbId + "/");
			ols = doc.findEvery("<div class=\"tvdivhidden\"");
			if (ols.size() == 0)
				getDocument(doc);
		} while (ols.size() == 0);

		System.out.println("Found " + ols.size() + " OLs:");
		for (Element ol : ols) { // iterate through Results
			String strEpId = ol.getParent().getElement(1).getAt("id")
					.replace("tvcontent_", "");
			List<TorrentEpisode> eps = getEpisodes(strEpId);
			allEpisodes.addAll(eps);
		}

		System.out.println("terminado");
		return allEpisodes;

	}

	public static List<TorrentEpisode> getEpisodes(String id) throws ResponseException,
			NotFound, ClientProtocolException, IOException, URISyntaxException {
		System.out.println(POPULATE_TV_URL + id);

		Document doc = userAgent.visit(POPULATE_TV_URL + id);
		Elements rows = doc.findEach("<tr class=\"lista2\">");
		while (rows.size() == 0) {
			getDocument(doc);
			doc = userAgent.visit(POPULATE_TV_URL + id);
			rows = doc.findEach("<tr class=\"lista2\">");
		}

		System.out.println("ep found: " + rows.size());
		List<TorrentEpisode> episodes = new ArrayList<>(rows.size());
		for (Element row : rows) {
			TorrentEpisode e = getEpisode(row);
			System.out.println(e);
			episodes.add(e);
		}
		return episodes;
	}

	public static TorrentEpisode getEpisode(Element row) throws NotFound {
		String imageLink = row.getElement(EpData.IMAGE.ordinal()).getElement(0)
				.getAt("href");
		boolean hd = imageLink.contains("41");

		Element link = row.getElement(EpData.FILE.ordinal()).getElement(0);
		String title = link.getAt("title");

		String res = getProperty(title, "1080p", "720p");
		String quality = getProperty(title, "HDTV", "WEB-DL", "WEBRip");

		Matcher esMatcher = ES_PATTERN.matcher(title);
		esMatcher.find();
		int season = Integer.parseInt(esMatcher.group(1));
		int episode = Integer.parseInt(esMatcher.group(2));

		String torrentId = link.getAt("href");
		torrentId = torrentId.substring(torrentId.lastIndexOf('/') + 1);

		String size = row.getElement(EpData.SIZE.ordinal()).getText();

		String strLeechers = row.getElement(EpData.LEECHERS.ordinal()).getText();
		int leechers = strLeechers.isEmpty() ? 0 : Integer.parseInt(strLeechers);

		String strSeeders = row.getElement(EpData.SEEDERS.ordinal()).getElement(0)
				.getText();
		int seeders = strSeeders.isEmpty() ? 0 : Integer.parseInt(strSeeders);

		String uploader = row.getElement(EpData.UPLOADER.ordinal()).getText();
		// System.out.println(hd);
		// System.exit(0);
		return new TorrentEpisode("", season, episode, torrentId, quality, res, seeders,
				leechers, uploader, size, hd);

	}

	private static String getProperty(String title, String... properties) {
		for (String property : properties) {
			if (title.contains(property)) {
				return property;
			}
		}
		return "";
	}

	private static enum EpData {
		IMAGE,
		FILE,
		ADDED,
		SIZE,
		SEEDERS,
		LEECHERS,
		COMMENTS,
		RATING,
		UPLOADER;
	}

}
