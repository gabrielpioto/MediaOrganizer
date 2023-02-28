package com.gpioto.telegrambot.impl;

import com.gpioto.telegrambot.model.request.ChatAction;
import com.gpioto.telegrambot.model.request.Keyboard;
import com.gpioto.telegrambot.model.request.ParseMode;
import com.gpioto.telegrambot.response.GetFileResponse;
import com.gpioto.telegrambot.response.GetMeResponse;
import com.gpioto.telegrambot.response.GetUpdatesResponse;
import com.gpioto.telegrambot.response.GetUserProfilePhotosResponse;
import com.gpioto.telegrambot.response.OkResponse;
import com.gpioto.telegrambot.response.SendChatActionResponse;
import com.gpioto.telegrambot.response.SendResponse;
import com.gpioto.telegrambot.response.SetWebhookResponse;

import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * stas 8/4/15.
 */
public interface BotApi {

	@GET("/getMe")
	GetMeResponse getMe();

	@POST("/sendMessage")
	@FormUrlEncoded
	SendResponse sendMessage(@Field("chat_id") String chatId, @Field("text") String text,
			@Field("parse_mode") ParseMode parse_mode, @Field("disable_web_page_preview") Boolean disableWebPagePreview,
			@Field("reply_to_message_id") Integer replyToMessageId, @Field("reply_markup") Keyboard replyMarkup);

	@POST("/forwardMessage")
	@FormUrlEncoded
	SendResponse forwardMessage(@Field("chat_id") String chatId, @Field("from_chat_id") String fromChatId,
			@Field("message_id") Integer messageId);

	@Multipart
	@POST("/sendPhoto")
	SendResponse sendPhoto(@Part("chat_id") String chatId, @Part("photo") String photo, @Part("caption") String caption,
			@Part("reply_to_message_id") Integer replyToMessageId, @Part("reply_markup") Keyboard replyMarkup);

	@Multipart
	@POST("/sendPhoto")
	SendResponse sendPhoto(@Part("chat_id") String chatId, @Part("photo") RequestBody photo,
			@Part("caption") String caption, @Part("reply_to_message_id") Integer replyToMessageId,
			@Part("reply_markup") Keyboard replyMarkup);

	@Multipart
	@POST("/sendAudio")
	SendResponse sendAudio(@Part("chat_id") String chatId, @Part("audio") String audio,
			@Part("duration") Integer duration, @Part("performer") String performer, @Part("title") String title,
			@Part("reply_to_message_id") Integer replyToMessageId, @Part("reply_markup") Keyboard replyMarkup);

	@Multipart
	@POST("/sendAudio")
	SendResponse sendAudio(@Part("chat_id") String chatId, @Part("audio") RequestBody audio,
			@Part("duration") Integer duration, @Part("performer") String performer, @Part("title") String title,
			@Part("reply_to_message_id") Integer replyToMessageId, @Part("reply_markup") Keyboard replyMarkup);

	@Multipart
	@POST("/sendDocument")
	SendResponse sendDocument(@Part("chat_id") String chatId, @Part("document") String document,
			@Part("reply_to_message_id") Integer replyToMessageId, @Part("reply_markup") Keyboard replyMarkup);

	@Multipart
	@POST("/sendDocument")
	SendResponse sendDocument(@Part("chat_id") String chatId, @Part("document") RequestBody document,
			@Part("reply_to_message_id") Integer replyToMessageId, @Part("reply_markup") Keyboard replyMarkup);

	@Multipart
	@POST("/sendSticker")
	SendResponse sendSticker(@Part("chat_id") String chatId, @Part("sticker") String sticker,
			@Part("reply_to_message_id") Integer replyToMessageId, @Part("reply_markup") Keyboard replyMarkup);

	@Multipart
	@POST("/sendSticker")
	SendResponse sendSticker(@Part("chat_id") String chatId, @Part("sticker") RequestBody sticker,
			@Part("reply_to_message_id") Integer replyToMessageId, @Part("reply_markup") Keyboard replyMarkup);

	@Multipart
	@POST("/sendVideo")
	SendResponse sendVideo(@Part("chat_id") String chatId, @Part("video") String video,
			@Part("duration") Integer duration, @Part("caption") String caption,
			@Part("reply_to_message_id") Integer replyToMessageId, @Part("reply_markup") Keyboard replyMarkup);

	@Multipart
	@POST("/sendVideo")
	SendResponse sendVideo(@Part("chat_id") String chatId, @Part("video") RequestBody video,
			@Part("duration") Integer duration, @Part("caption") String caption,
			@Part("reply_to_message_id") Integer replyToMessageId, @Part("reply_markup") Keyboard replyMarkup);

	@Multipart
	@POST("/sendVoice")
	SendResponse sendVoice(@Part("chat_id") String chatId, @Part("voice") String voice,
			@Part("duration") Integer duration, @Part("reply_to_message_id") Integer replyToMessageId,
			@Part("reply_markup") Keyboard replyMarkup);

	@Multipart
	@POST("/sendVoice")
	SendResponse sendVoice(@Part("chat_id") String chatId, @Part("voice") RequestBody voice,
			@Part("duration") Integer duration, @Part("reply_to_message_id") Integer replyToMessageId,
			@Part("reply_markup") Keyboard replyMarkup);

	@FormUrlEncoded
	@POST("/sendLocation")
	SendResponse sendLocation(@Field("chat_id") String chatId, @Field("latitude") Float latitude,
			@Field("longitude") Float longitude, @Field("reply_to_message_id") Integer replyToMessageId,
			@Field("reply_markup") Keyboard replyMarkup);

	@FormUrlEncoded
	@POST("/sendChatAction")
	SendChatActionResponse sendChatAction(@Field("chat_id") String chatId, @Field("action") ChatAction action);

	@GET("/getUserProfilePhotos")
	GetUserProfilePhotosResponse getUserProfilePhotos(@Query("user_id") Integer userId, @Query("offset") Integer offset,
			@Query("limit") Integer limit);

	@GET("/getUpdates")
	GetUpdatesResponse getUpdates(@Query("offset") Integer offset, @Query("limit") Integer limit,
			@Query("timeout") Integer timeout);

	@FormUrlEncoded
	@POST("/setWebhook")
	SetWebhookResponse setWebhook(@Field("url") String url);

	@Multipart
	@POST("/setWebhook")
	SetWebhookResponse setWebhook(@Part("url") String url, @Part("certificate") RequestBody certificate);

	@GET("/getFile")
	GetFileResponse getFile(@Query("file_id") String fileId);

	@FormUrlEncoded
	@POST("/answerInlineQuery")
	OkResponse answerInlineQuery(@Field("inline_query_id") String inlineQueryId, @Field("results") String results,
			@Field("cache_time") Integer cacheTime, @Field("is_personal") Boolean isPersonal,
			@Field("next_offset") String nextOffset);
}