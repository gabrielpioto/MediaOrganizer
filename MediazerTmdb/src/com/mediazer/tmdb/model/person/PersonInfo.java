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
package com.mediazer.tmdb.model.person;

import java.io.Serializable;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.mediazer.tmdb.enums.methods.PeopleMethod;
import com.mediazer.tmdb.interfaces.AppendToResponse;
import com.mediazer.tmdb.model.artwork.Artwork;
import com.mediazer.tmdb.model.artwork.ArtworkMedia;
import com.mediazer.tmdb.model.change.ChangeKeyItem;
import com.mediazer.tmdb.model.credits.CreditMovieBasic;
import com.mediazer.tmdb.model.credits.CreditTVBasic;
import com.mediazer.tmdb.results.WrapperChanges;
import com.mediazer.tmdb.results.WrapperGenericList;
import com.mediazer.tmdb.results.WrapperImages;

/**
 * @author stuart.boston
 */
public class PersonInfo extends PersonBasic implements Serializable, AppendToResponse<PeopleMethod> {

    private static final long serialVersionUID = 4L;

    @JsonProperty("adult")
    private boolean adult;
    @JsonProperty("also_known_as")
    private List<String> alsoKnownAs;
    @JsonProperty("biography")
    private String biography;
    @JsonProperty("birthday")
    private String birthday;
    @JsonProperty("deathday")
    private String deathday;
    @JsonProperty("homepage")
    private String homepage;
    @JsonProperty("imdb_id")
    private String imdbId;
    @JsonProperty("place_of_birth")
    private String placeOfBirth;
    @JsonProperty("popularity")
    private float popularity;
    // AppendToResponse
    private final Set<PeopleMethod> methods = EnumSet.noneOf(PeopleMethod.class);
    // AppendToResponse Properties
    private List<ChangeKeyItem> changes = Collections.emptyList();
    // TODO: Add COMBINED_CREDITS
    private ExternalID externalIDs = new ExternalID();
    private List<Artwork> images = Collections.emptyList();
    private PersonCreditList<CreditMovieBasic> movieCredits = new PersonCreditList<CreditMovieBasic>();
    private List<ArtworkMedia> taggedImages = Collections.emptyList();
    private PersonCreditList<CreditTVBasic> tvCredits = new PersonCreditList<CreditTVBasic>();

    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public List<String> getAlsoKnownAs() {
        return alsoKnownAs;
    }

    public void setAlsoKnownAs(List<String> alsoKnownAs) {
        this.alsoKnownAs = alsoKnownAs;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDeathday() {
        return deathday;
    }

    public void setDeathday(String deathday) {
        this.deathday = deathday;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }
    //</editor-fold>

    private void addMethod(PeopleMethod method) {
        methods.add(method);
    }

    @Override
    public boolean hasMethod(PeopleMethod method) {
        return methods.contains(method);
    }

    //<editor-fold defaultstate="collapsed" desc="AppendToResponse Setters">
    @JsonSetter("changes")
    public void setChanges(WrapperChanges changes) {
        this.changes = changes.getChangedItems();
        addMethod(PeopleMethod.CHANGES);
    }

    @JsonSetter("external_ids")
    public void setExternalIDs(ExternalID externalIDs) {
        this.externalIDs = externalIDs;
        addMethod(PeopleMethod.EXTERNAL_IDS);
    }

    @JsonSetter("images")
    public void setImages(WrapperImages images) {
        this.images = images.getAll();
        addMethod(PeopleMethod.IMAGES);
    }

    @JsonSetter("movie_credits")
    public void setMovieCredits(PersonCreditList<CreditMovieBasic> movieCredits) {
        this.movieCredits = movieCredits;
        addMethod(PeopleMethod.MOVIE_CREDITS);
    }

    @JsonSetter("tagged_images")
    public void setTaggedImages(WrapperGenericList<ArtworkMedia> taggedImages) {
        this.taggedImages = taggedImages.getResults();
        addMethod(PeopleMethod.TAGGED_IMAGES);
    }

    @JsonSetter("tv_credits")
    public void setTvCredits(PersonCreditList<CreditTVBasic> tvCredits) {
        this.tvCredits = tvCredits;
        addMethod(PeopleMethod.TV_CREDITS);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="AppendToResponse Getters">
    public List<ChangeKeyItem> getChanges() {
        return changes;
    }

    public ExternalID getExternalIDs() {
        return externalIDs;
    }

    public List<Artwork> getImages() {
        return images;
    }

    public PersonCreditList<CreditMovieBasic> getMovieCredits() {
        return movieCredits;
    }

    public List<ArtworkMedia> getTaggedImages() {
        return taggedImages;
    }

    public PersonCreditList<CreditTVBasic> getTvCredits() {
        return tvCredits;
    }
    //</editor-fold>

}
