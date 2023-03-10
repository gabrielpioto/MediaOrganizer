package com.gpioto.telegrambot.response;

import com.gpioto.telegrambot.model.User;

/**
 * stas
 * 8/4/15.
 */
public class GetMeResponse {

    private boolean ok;
    private User result;

    GetMeResponse() {
    }

    public boolean isOk() {
        return ok;
    }

    public User user() {
        return result;
    }

    @Override
    public String toString() {
        return "GetMeResponse{" +
                "ok=" + ok +
                ", result=" + result +
                '}';
    }
}
