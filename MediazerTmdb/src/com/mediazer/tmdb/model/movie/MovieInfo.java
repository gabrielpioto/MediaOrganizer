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
package com.mediazer.tmdb.model.movie;

import java.io.Serializable;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.mediazer.tmdb.enums.methods.MovieMethod;
import com.mediazer.tmdb.interfaces.AppendToResponse;
import com.mediazer.tmdb.interfaces.Identification;
import com.mediazer.tmdb.model.Genre;
import com.mediazer.tmdb.model.Language;
import com.mediazer.tmdb.model.artwork.Artwork;
import com.mediazer.tmdb.model.change.ChangeKeyItem;
import com.mediazer.tmdb.model.collection.Collection;
import com.mediazer.tmdb.model.credits.MediaCreditCast;
import com.mediazer.tmdb.model.credits.MediaCreditCrew;
import com.mediazer.tmdb.model.keyword.Keyword;
import com.mediazer.tmdb.model.list.UserList;
import com.mediazer.tmdb.model.media.AlternativeTitle;
import com.mediazer.tmdb.model.media.MediaCreditList;
import com.mediazer.tmdb.model.media.Translation;
import com.mediazer.tmdb.model.media.Video;
import com.mediazer.tmdb.model.review.Review;
import com.mediazer.tmdb.results.WrapperAlternativeTitles;
import com.mediazer.tmdb.results.WrapperChanges;
import com.mediazer.tmdb.results.WrapperGenericList;
import com.mediazer.tmdb.results.WrapperImages;
import com.mediazer.tmdb.results.WrapperMovieKeywords;
import com.mediazer.tmdb.results.WrapperReleaseInfo;
import com.mediazer.tmdb.results.WrapperTranslations;
import com.mediazer.tmdb.results.WrapperVideos;

/**
 * Movie Bean
 *
 * @author stuart.boston
 */
public class MovieInfo extends MovieBasic implements Serializable, Identification, AppendToResponse<MovieMethod> {

    private static final long serialVersionUID = 4L;

    @JsonProperty("belongs_to_collection")
    private Collection belongsToCollection;
    @JsonProperty("budget")
    private long budget;
    @JsonProperty("genres")
    private List<Genre> genres;
    @JsonProperty("homepage")
    private String homepage;
    @JsonProperty("imdb_id")
    private String imdbID;
    @JsonProperty("overview")
    private String overview;
    @JsonProperty("production_companies")
    private List<ProductionCompany> productionCompanies = Collections.emptyList();
    @JsonProperty("production_countries")
    private List<ProductionCountry> productionCountries = Collections.emptyList();
    @JsonProperty("revenue")
    private long revenue;
    @JsonProperty("runtime")
    private int runtime;
    @JsonProperty("original_language")
    private String originalLanguage;
    @JsonProperty("spoken_languages")
    private List<Language> spokenLanguages = Collections.emptyList();
    @JsonProperty("tagline")
    private String tagline;
    @JsonProperty("status")
    private String status;
    // AppendToResponse
    private final Set<MovieMethod> methods = EnumSet.noneOf(MovieMethod.class);
    // AppendToResponse Properties
    private List<AlternativeTitle> alternativeTitles = Collections.emptyList();
    private MediaCreditList credits = new MediaCreditList();
    private List<Artwork> images = Collections.emptyList();
    private List<Keyword> keywords = Collections.emptyList();
    private List<ReleaseInfo> releases = Collections.emptyList();
    private List<Video> videos = Collections.emptyList();
    private List<Translation> translations = Collections.emptyList();
    private List<MovieInfo> similarMovies = Collections.emptyList();
    private List<Review> reviews = Collections.emptyList();
    private List<UserList> lists = Collections.emptyList();
    private List<ChangeKeyItem> changes = Collections.emptyList();

    // <editor-fold defaultstate="collapsed" desc="Getter methods">
    public Collection getBelongsToCollection() {
        return belongsToCollection;
    }

    public long getBudget() {
        return budget;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getOverview() {
        return overview;
    }

    public List<ProductionCompany> getProductionCompanies() {
        return productionCompanies;
    }

    public List<ProductionCountry> getProductionCountries() {
        return productionCountries;
    }

    public long getRevenue() {
        return revenue;
    }

    public int getRuntime() {
        return runtime;
    }

    public List<Language> getSpokenLanguages() {
        return spokenLanguages;
    }

    public String getTagline() {
        return tagline;
    }

    public String getStatus() {
        return status;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Setter methods">
    public void setBelongsToCollection(Collection belongsToCollection) {
        this.belongsToCollection = belongsToCollection;
    }

    public void setBudget(long budget) {
        this.budget = budget;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setProductionCompanies(List<ProductionCompany> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public void setProductionCountries(List<ProductionCountry> productionCountries) {
        this.productionCountries = productionCountries;
    }

    public void setRevenue(long revenue) {
        this.revenue = revenue;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public void setSpokenLanguages(List<Language> spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }
    // </editor-fold>

    //<editor-fold defaultstate="collapsed" desc="AppendToResponse Getters">
    public List<AlternativeTitle> getAlternativeTitles() {
        return alternativeTitles;
    }

    public List<MediaCreditCast> getCast() {
        return credits.getCast();
    }

    public List<MediaCreditCrew> getCrew() {
        return credits.getCrew();
    }

    public List<Artwork> getImages() {
        return images;
    }

    public List<Keyword> getKeywords() {
        return keywords;
    }

    public List<ReleaseInfo> getReleases() {
        return releases;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public List<Translation> getTranslations() {
        return translations;
    }

    public List<MovieInfo> getSimilarMovies() {
        return similarMovies;
    }

    public List<UserList> getLists() {
        return lists;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public List<ChangeKeyItem> getChanges() {
        return changes;
    }
    // </editor-fold>

    //<editor-fold defaultstate="collapsed" desc="AppendToResponse Setters">
    @JsonSetter("alternative_titles")
    public void setAlternativeTitles(WrapperAlternativeTitles alternativeTitles) {
        this.alternativeTitles = alternativeTitles.getTitles();
        addMethod(MovieMethod.ALTERNATIVE_TITLES);
    }

    @JsonSetter("credits")
    public void setCredits(MediaCreditList credits) {
        this.credits = credits;
        addMethod(MovieMethod.CREDITS);
    }

    @JsonSetter("images")
    public void setImages(WrapperImages images) {
        this.images = images.getAll();
        addMethod(MovieMethod.IMAGES);
    }

    @JsonSetter("keywords")
    public void setKeywords(WrapperMovieKeywords keywords) {
        this.keywords = keywords.getKeywords();
        addMethod(MovieMethod.KEYWORDS);
    }

    @JsonSetter("releases")
    public void setReleases(WrapperReleaseInfo releases) {
        this.releases = releases.getCountries();
        addMethod(MovieMethod.RELEASES);
    }

    @JsonSetter("videos")
    public void setVideos(WrapperVideos trailers) {
        this.videos = trailers.getVideos();
        addMethod(MovieMethod.VIDEOS);
    }

    @JsonSetter("translations")
    public void setTranslations(WrapperTranslations translations) {
        this.translations = translations.getTranslations();
        addMethod(MovieMethod.TRANSLATIONS);
    }

    @JsonSetter("similar")
    public void setSimilarMovies(WrapperGenericList<MovieInfo> similarMovies) {
        this.similarMovies = similarMovies.getResults();
        addMethod(MovieMethod.SIMILAR);
    }

    @JsonSetter("lists")
    public void setLists(WrapperGenericList<UserList> lists) {
        this.lists = lists.getResults();
        addMethod(MovieMethod.LISTS);
    }

    @JsonSetter("reviews")
    public void setReviews(WrapperGenericList<Review> reviews) {
        this.reviews = reviews.getResults();
        addMethod(MovieMethod.REVIEWS);
    }

    @JsonSetter("changes")
    public void setChanges(WrapperChanges changes) {
        this.changes = changes.getChangedItems();
    }
    // </editor-fold>

    private void addMethod(MovieMethod method) {
        methods.add(method);
    }

    @Override
    public boolean hasMethod(MovieMethod method) {
        return methods.contains(method);
    }
}
