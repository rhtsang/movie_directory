package com.example.mymoviedirectory.mymoviedirectory.Model;

import java.io.Serializable;

/**
 * Created by RaymondTsang on 12/28/17.
 */

public class Movie implements Serializable {

    //RecyclerView info
    private String posterURL;
    private String title;
    private String releaseDate;
    private String type;

    private String imdbId;

    //Movie Details info
    private String category;
    private String rating;
    private String runtime;
    private String directors;
    private String actors;
    private String writers;
    private String plot;
    private String boxOffice;

    public Movie() {
    }

    public String getPosterURL() {
        return posterURL;
    }

    public void setPosterURL(String posterURL) {
        this.posterURL = posterURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public  String getCategory() {
        return category;
    }

    public void   setCategory(String category) {
        this.category = category;
    }

    public String getRating() {
        return rating;
    }

    public void   setRating(String rating) {
        this.rating = rating;
    }

    public String getRuntime() {
        return runtime;
    }

    public void   setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getDirectors() {
        return directors;
    }

    public void   setDirectors(String directors) {
        this.directors = directors;
    }

    public String getActors() {
        return actors;
    }

    public void   setActors(String actors) {
        this.actors = actors;
    }

    public String getWriters() {
        return writers;
    }

    public void   setWriters(String writers) {
        this.writers = writers;
    }

    public String getPlot() {
        return plot;
    }

    public void   setPlot(String plot) {
        this.plot = plot;
    }

    public String getBoxOffice() {
        return boxOffice;
    }

    public void   setBoxOffice(String boxOffice) {
        this.boxOffice = boxOffice;
    }
}
