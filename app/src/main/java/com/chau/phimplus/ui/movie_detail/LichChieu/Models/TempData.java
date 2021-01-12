package com.chau.phimplus.ui.movie_detail.LichChieu.Models;

import java.util.ArrayList;

public class TempData {
    private String sreeningId;
    private String branchAddress;
    private String cinemaId;
    private String seatId;

    private ArrayList<Screening> listScreening;
    private ArrayList<Branch> listBranch;
    private ArrayList<Cinema> listCinema;
    private ArrayList<Seat> listSeat;

    public ArrayList<Seat> getListSeat() {
        return listSeat;
    }

    public void setListSeat(ArrayList<Seat> listSeat) {
        this.listSeat = listSeat;
    }

    public TempData() {}

    public String getSreeningId() {
        return sreeningId;
    }

    public void setSreeningId(String sreeningId) {
        this.sreeningId = sreeningId;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    public String getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(String cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getSeatId() {
        return seatId;
    }

    public void setSeatId(String seatId) {
        this.seatId = seatId;
    }

    public ArrayList<Screening> getListScreening() {
        return listScreening;
    }

    public void setListScreening(ArrayList<Screening> listScreening) {
        this.listScreening = listScreening;
    }

    public ArrayList<Branch> getListBranch() {
        return listBranch;
    }

    public void setListBranch(ArrayList<Branch> listBranch) {
        this.listBranch = listBranch;
    }

    public ArrayList<Cinema> getListCinema() {
        return listCinema;
    }

    public void setListCinema(ArrayList<Cinema> listCinema) {
        this.listCinema = listCinema;
    }
}
