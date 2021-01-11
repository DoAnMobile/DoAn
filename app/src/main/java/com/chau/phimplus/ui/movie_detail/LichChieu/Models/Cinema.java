package com.chau.phimplus.ui.movie_detail.LichChieu.Models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cinema {

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("SeatQuantity")
    @Expose
    private String seatQuantity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeatQuantity() {
        return seatQuantity;
    }

    public void setSeatQuantity(String seatQuantity) {
        this.seatQuantity = seatQuantity;
    }

}