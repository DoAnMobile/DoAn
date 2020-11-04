package com.chau.doan;

public class Phim {

    private String tenphim;
    private  String theloai;
    private  int hinhanh;
    private String chitiet;


    public void setChitiet(String chitiet) {
        this.chitiet = chitiet;
    }

    public String getChitiet() {
        return chitiet;
    }

    public Phim(String tenphim, int hinhanh) {
        this.tenphim = tenphim;
        this.hinhanh = hinhanh;
    }

    public Phim(String tenphim, String theloai, int hinhanh, String chitiet) {
        this.tenphim = tenphim;
        this.theloai = theloai;
        this.hinhanh = hinhanh;
        this.chitiet = chitiet;
    }

    public String getTenphim() {
        return tenphim;
    }

    public String getTheloai() {
        return theloai;
    }

    public int getHinhanh() {
        return hinhanh;
    }

    public void setTenphim(String tenphim) {
        this.tenphim = tenphim;
    }

    public void setTheloai(String theloai) {
        this.theloai = theloai;
    }

    public void setHinhanh(int hinhanh) {
        this.hinhanh = hinhanh;
    }
}
