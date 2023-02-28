package com.mediazer.imdb.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Runtime extends AbstractJsonMapping {

    @JsonProperty("country")
    private String country;
    @JsonProperty("time")
    private long runtime = -1;
    @JsonProperty("attr")
    private String attribute;

    public String getCountry() {
        return country;
    }

    public long getRuntime() {
        return runtime;
    }

    public String getAttribute() {
        return attribute;
    }

}
