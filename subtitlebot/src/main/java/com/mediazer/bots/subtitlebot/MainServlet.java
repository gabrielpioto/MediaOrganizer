/**
 * Copyright 2015 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mediazer.bots.subtitlebot;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.gpioto.telegrambot.TelegramBotAdapter;
import com.gpioto.telegrambot.impl.BotApi;
import com.gpioto.telegrambot.model.Message;
import com.gpioto.telegrambot.model.Update;
import com.mediazer.osub.OSearch;
import com.mediazer.osub.OSearch.Term;
import com.mediazer.osub.OpenSubApi;
import com.mediazer.osub.model.OSubInfo;
import com.mediazer.osub.model.OSubtitle;
import com.mediazer.osub.model.response.OLogin;
import com.mediazer.osub.model.response.OSubInfos;
import com.mediazer.osub.model.response.OSubtitles;

@SuppressWarnings("serial")
public class MainServlet extends HttpServlet {


	private static final String BOT_TOKEN = "163665313:AAHZ-hMLqeGJ-iaAK9JZkPhi1pNxVTvi3II";
	private static final String seriesMatch = "/(.*)[Ss](\\d+)[Ee](\\d+)/g";
	private static final Gson gson = new Gson();

	private OpenSubApi openSubApi = new OpenSubApi();
	private OLogin login;
	private BotApi telegramApi = TelegramBotAdapter.newBotApi(BOT_TOKEN);

	public MainServlet() {
		login = openSubApi.login("", "", "pt", "VLSub 0.9");
		
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Enumeration e = req.getParameterNames();
		PrintWriter out = resp.getWriter();

		while (e.hasMoreElements()) {
			String s = e.nextElement().toString();
			out.println(s + ": " + req.getParameter(s));
		}
		// out.println(req.getAttribute("teste"));

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
		
		Update update = gson.fromJson(req.getReader(), Update.class);
		
		if (Datastore.UPDATES.add(update)) {
			// System.out.println(update);

			Message message = update.message();
			long chatId = message.chat().id();
			
			Matcher matcher = Pattern.compile(seriesMatch).matcher(message.text());
			Collection<OSearch> searches = new ArrayList<>();
			OSearch search = new OSearch("pob");
			if (matcher.matches()) {
				search.set(Term.QUERY, matcher.group(1).trim());
				int season = Integer.parseInt(matcher.group(2));
				int episode = Integer.parseInt(matcher.group(3));
				search.set(Term.SEASON, season).set(Term.EPISODE, episode);
			} else {
				search.set(Term.QUERY, message.getText());
			}

			searches.add(search);
			Map<String, OSubInfo> subs = new HashMap<>();
			OSubInfos infos = openSubApi.searchSubtitles(login.getToken(), searches);
			for(OSubInfo info : infos.getData()){
				subs.put(info.getIDSubtitleFile(), info);
			}
			
			OSubtitles subtitles = openSubApi.downloadSubtitles(login.getToken(), subs.keySet());
			
			for(OSubtitle subtitle : subtitles.getData()){
				
				
				
				
				MultipartUtility multipart = new SubtitleMultipart(docUrl, subtitle);
				multipart.addFormField(PARAM_CHAT_ID, String.valueOf(chatId));
				multipart.addFilePart(PARAM_DOCUMENT, subs.get(subtitle.getIdsubtitlefile()).getSubFileName());
				List<String> ss = multipart.finish();
				for(String s : ss) System.out.println(s);				
			}
			
		}
		
		

	}
}
