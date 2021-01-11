package com.chau.phimplus.Server;

import com.chau.phimplus.Models.TaiKhoan;
import com.chau.phimplus.ui.movie_detail.comment.models.Comment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Dataserver {

	//CALL API Login.php
    @GET("get_account.php")
    Call<List<TaiKhoan>> getTaiKhoan();

    @POST("register.php")
    @FormUrlEncoded
    Call<List<TaiKhoan>> savePost(
            @Field("Phone") String Phone,
            @Field("Password") String Password
    );


    @POST("get-comment.php")
    @FormUrlEncoded
    Call<ArrayList<Comment>> getComment(
            @Field("key") String movieid
    );



}
