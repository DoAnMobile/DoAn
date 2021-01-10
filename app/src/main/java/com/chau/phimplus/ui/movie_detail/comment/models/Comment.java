package com.chau.phimplus.ui.movie_detail.comment.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comment {

    @SerializedName("MovieId")
    @Expose
    private String movieId;
    @SerializedName("Rate")
    @Expose
    private int rate;
    @SerializedName("Content")
    @Expose
    private String content;
    @SerializedName("Status")
    @Expose
    private Object status;
    @SerializedName("FisrtName")
    @Expose
    private Object fisrtName;
    @SerializedName("LastName")
    @Expose
    private String lastName;

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public Object getFisrtName() {
        return fisrtName;
    }

    public void setFisrtName(Object fisrtName) {
        this.fisrtName = fisrtName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
