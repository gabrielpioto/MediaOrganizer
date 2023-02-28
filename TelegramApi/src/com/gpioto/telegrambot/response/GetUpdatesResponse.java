package com.gpioto.telegrambot.response;

import java.util.List;

import com.gpioto.telegrambot.model.Update;

/**
 * stas
 * 8/11/15.
 */
public class GetUpdatesResponse {

    private boolean ok;
    private List<Update> result;

    GetUpdatesResponse() {
    }

    public boolean isOk() {
        return ok;
    }

    public List<Update> updates() {
        return result;
    }

    @Override
    public String toString() {
        return "GetUpdatesResponse{" +
                "ok=" + ok +
                ", result=" + result +
                '}';
    }
}