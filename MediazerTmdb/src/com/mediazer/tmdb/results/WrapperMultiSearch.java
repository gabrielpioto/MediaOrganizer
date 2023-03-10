/*
 *      Copyright (c) 2004-2015 Stuart Boston
 *
 *      This file is part of TheMovieDB API.
 *
 *      TheMovieDB API is free software: you can redistribute it and/or modify
 *      it under the terms of the GNU General Public License as published by
 *      the Free Software Foundation, either version 3 of the License, or
 *      any later version.
 *
 *      TheMovieDB API is distributed in the hope that it will be useful,
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *      GNU General Public License for more details.
 *
 *      You should have received a copy of the GNU General Public License
 *      along with TheMovieDB API.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.mediazer.tmdb.results;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.mediazer.tmdb.model.media.MediaBasic;
import com.mediazer.tmdb.model.movie.MovieBasic;
import com.mediazer.tmdb.model.tv.TVBasic;
import com.mediazer.tmdb.model.tv.TVEpisodeBasic;

import java.util.List;

/**
 *
 * @author stuart.boston
 */
public class WrapperMultiSearch extends AbstractWrapperAll {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<? extends MediaBasic> results;

    @SuppressWarnings("unchecked")
	public List<MediaBasic> getResults() {
        return (List<MediaBasic>) results;
    }

    @JsonTypeInfo(
            use = JsonTypeInfo.Id.NAME,
            include = JsonTypeInfo.As.PROPERTY,
            property = "media_type",
            defaultImpl = MediaBasic.class
    )
    @JsonSubTypes({
        @JsonSubTypes.Type(value = MovieBasic.class, name = "movie"),
        @JsonSubTypes.Type(value = TVBasic.class, name = "tv"),
        @JsonSubTypes.Type(value = TVEpisodeBasic.class, name = "episode")
    })
    @JsonSetter("results")
    public void setResults(List<? extends MediaBasic> results) {
        this.results = results;
    }

}
