package com.chau.phimplus.ui.movie_detail.comment;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.chau.phimplus.R;
import com.chau.phimplus.ui.movie_detail.comment.models.Comment;

public class RecyclerViewHolder extends RecyclerView.ViewHolder{


    public ImageView imgUser;
    public TextView txt_userName,txt_content;
    public RatingBar ratingBar;
    public RecyclerViewHolder(View itemView){
        super(itemView);
        //
        imgUser = (ImageView)itemView.findViewById(R.id.cmt_img_user);
        ratingBar = (RatingBar)itemView.findViewById(R.id.cmt_rating);
        txt_userName = (TextView)itemView.findViewById(R.id.cmt_username);
        txt_content = (TextView)itemView.findViewById(R.id.cmt_content);


    }


}
