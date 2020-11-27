package com.chau.phimplus.ui.movie_detail.comment;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.chau.phimplus.R;
import com.chau.phimplus.ui.movie_detail.comment.models.Comment;

public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


    public ImageView imgUser;
    public TextView txt_userName,txt_content,txt_more;
    public RatingBar ratingBar;
    public RecyclerViewHolder(View itemView){
        super(itemView);
        //
        init(itemView);
        //fullComment(itemView);

    }


    @Override
    public void onClick(View v) {

    }
    public void setOnItemClickListener(ClickListener clickListener) {
        CommentAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
    }
    // add
    private void fullComment(View view) {

        Log.d("fff","Maxline " + Float.toString(txt_content.getMaxLines()));

        if(txt_content.getText().length()<1){

            ((ViewGroup) txt_more.getParent()).removeView(txt_more);
            ((ViewGroup) txt_content.getParent()).removeView(txt_content);

        }else if(txt_content.getMaxLines()<100){
            ((ViewGroup) txt_more.getParent()).removeView(txt_more);
        }

    }

    private void init(View view) {
        txt_userName = (TextView) view.findViewById(R.id.cmt_username);
        txt_content = (TextView) view.findViewById(R.id.cmt_content);
        txt_more = (TextView) view.findViewById(R.id.cmt_more);
        imgUser = (ImageView) view.findViewById(R.id.cmt_img_user);
        ratingBar = (RatingBar) view.findViewById(R.id.cmt_rating);

    }
    //end -add
}
