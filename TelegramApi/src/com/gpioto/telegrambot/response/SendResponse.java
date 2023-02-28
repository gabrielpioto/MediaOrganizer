package com.gpioto.telegrambot.response;

import com.gpioto.telegrambot.model.Message;

/**
 * stas
 * 8/5/15.
 */
public class SendResponse {

    private boolean ok;
    private Message result;

    SendResponse() {
    }

    public boolean isOk() {
        return ok;
    }

    public Message message() {
        return result;
    }

    @Override
    public String toString() {
        return "SendResponse{" +
                "ok=" + ok +
                ", result=" + result +
                '}';
    }
}
