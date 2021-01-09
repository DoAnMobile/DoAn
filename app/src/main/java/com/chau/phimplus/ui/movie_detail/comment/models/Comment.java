package com.chau.phimplus.ui.movie_detail.comment.models;

public class Comment {


    private int _userId;
    private int _movieId;
    private String _content;
    private int _rating;

    public int get_rating() {
        return _rating;
    }

    public void set_rating(int _rating) {
        this._rating = _rating;
    }

    public Comment() {
    }

    public Comment(int _userId, int _movieId, String _content,int _rating) {
        this._userId = _userId;
        this._movieId = _movieId;
        this._content = _content;
        this._rating = _rating;
    }

    public int get_userId() {
        return _userId;
    }

    public int get_movieId() {
        return _movieId;
    }

    public String get_content() {
        return _content;
    }

    public void set_userId(int _userId) {
        this._userId = _userId;
    }

    public void set_movieId(int _movieId) {
        this._movieId = _movieId;
    }

    public void set_content(String _content) {
        this._content = _content;
    }
}
