package com.chau.phimplus.ui.movie_detail.comment;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chau.phimplus.R;
import com.chau.phimplus.ui.movie_detail.comment.models.Comment;

import java.util.ArrayList;
import java.util.List;

public class CommentAdapter
        extends RecyclerView.Adapter<RecyclerViewHolder> {

    ArrayList<Comment> mListCommemt;
    private Context context;
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View commentView = inflater.inflate(R.layout.activity_item_comment,parent,false);

        // Return a new holder instance

        commentView.setOnClickListener();

        return  new RecyclerViewHolder(commentView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        // Get the data model based on position
        Comment comment = mListCommemt.get(position);

        // Set item views based on your views and data model
        int username = mListCommemt.get(position).get_userId();
        holder.txt_userName.setText(Integer.toString(username));

        String content = mListCommemt.get(position).get_content();
        holder.txt_content.setText(comment.get_content());
        //holder.imgUser.;
        holder.ratingBar.setRating(comment.get_rating());




    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
    //    @Override
//    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
//
//        // Get the data model based on position
//        Comment comment = mListCommemt.get(position);
//
//        // Set item views based on your views and data model
//        TextView userName = holder.txt_userName;
//        TextView content = holder.txt_content;
//        ImageView img_user = holder.imgUser;
//        RatingBar  ratingBar = holder.ratingBar;
//    }

    @Override
    public int getItemCount() {
        return mListCommemt.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public ImageView imgUser;
        public TextView txt_userName,txt_content;
        public RatingBar ratingBar;
        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public myViewHolder (View view){
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(view);

            imgUser = (ImageView)view.findViewById(R.id.cmt_img_user);
            ratingBar = (RatingBar)view.findViewById(R.id.cmt_rating);
            txt_userName = (TextView)view.findViewById(R.id.cmt_username);
            txt_content = (TextView)view.findViewById(R.id.cmt_content);
        }

    }

    public CommentAdapter(Context context,ArrayList<Comment> mListCommemt) {
        this.mListCommemt = mListCommemt;
        this.context =context;
    }
}
