package com.gpioto.telegrambot;

import com.gpioto.telegrambot.impl.BotApi;

import retrofit2.Retrofit;

/**
 * stas 8/4/15.
 */
public class TelegramBotAdapter {

	public static final String API_URL = "https://api.telegram.org/bot";

	public static BotApi newBotApi(String botToken) {
		return prepare(botToken).build().create(BotApi.class);
	}

	// public static TelegramBot build(String botToken) {
	// Retrofit retrofit = prepare(botToken).build();
	// BotApi botApi = retrofit.create(BotApi.class);
	// FileApi fileApi = new FileApi(botToken);
	// return new TelegramBot(botApi, fileApi);
	// }

	// //TODO: find logLevel replacement
	// @Deprecated
	// public static TelegramBot buildDebug(String botToken) {
	// return build(botToken);
	// }

	public static Retrofit.Builder prepare(String botToken) {
		return new Retrofit.Builder().baseUrl(API_URL + botToken);
	}
}
