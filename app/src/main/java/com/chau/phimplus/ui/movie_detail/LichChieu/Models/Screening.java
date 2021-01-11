package com.chau.phimplus.ui.movie_detail.LichChieu.Models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Screening {

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("Time")
    @Expose
    private String time;

    public Screening(String id, String time) {
        this.id = id;
        this.time = time;
    }
    public Screening() {    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}