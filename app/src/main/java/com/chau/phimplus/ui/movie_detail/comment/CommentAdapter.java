package com.chau.phimplus.ui.movie_detail.comment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chau.phimplus.MainActivity;
import com.chau.phimplus.R;
import com.chau.phimplus.ui.movie_detail.comment.models.Comment;

import java.util.ArrayList;

public class CommentAdapter
        extends RecyclerView.Adapter<CommentAdapter.RecyclerViewHolder> {

    ArrayList<Comment> mListCommemt;
    private Context context;
    private OnCommentListener mOnCommentListener;
    TextView txt_content;

    public CommentAdapter(Context context, ArrayList<Comment> mListCommemt, OnCommentListener onCommentListener) {
        this.mListCommemt = mListCommemt;
        this.context =context;
        this.mOnCommentListener = onCommentListener;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View commentView = inflater.inflate(R.layout.activity_item_comment,parent,false);

        // Return a new holder instance
        return  new RecyclerViewHolder(commentView,mOnCommentListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        // Get the data model based on position
        Comment comment = mListCommemt.get(position);

        // Set item views based on your views and data model

        String username = mListCommemt.get(position).getFisrtName()+" "+mListCommemt.get(position).getLastName();
        holder.txt_userName.setText(username);

        String content = mListCommemt.get(position).getContent();
        holder.txt_content.setText(comment.getContent());
        //holder.imgUser.;
        holder.ratingBar.setRating(comment.getRate());

        int username = mListCommemt.get(position).get_userId();
        holder.txt_userName.setText(Integer.toString(username));

        String content = mListCommemt.get(position).get_content();
        holder.txt_content.setText(comment.get_content());
        //holder.imgUser.;
        holder.ratingBar.setRating(comment.get_rating());


        // Init
        init(holder);

        try{

            if(holder.txt_content.getText().toString().equals("")){
                LinearLayout.LayoutParams  size = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,1);
                holder.txt_content.setLayoutParams(size);
            }else if(holder.txt_content.getLineCount()<3){
                LinearLayout.LayoutParams  size = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,100);
                holder.txt_content.setLayoutParams(size);
            }
        }catch (Exception e){}

    }

    private void init(RecyclerViewHolder recyclerViewHolder) {
        txt_content =  (TextView) recyclerViewHolder.txt_content;
    }
    @Override
    public int getItemCount() {
        return mListCommemt.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        OnCommentListener onCommentListener;

        public ImageView imgUser;
        public TextView txt_userName,txt_content;
        public RatingBar ratingBar;
        public RecyclerViewHolder(final View itemView, OnCommentListener onCommentListener){
            super(itemView);

            imgUser = (ImageView)itemView.findViewById(R.id.cmt_img_user);
            ratingBar = (RatingBar)itemView.findViewById(R.id.cmt_rating);
            txt_userName = (TextView)itemView.findViewById(R.id.cmt_username);
            txt_content = (TextView)itemView.findViewById(R.id.cmt_content);
            this.onCommentListener = onCommentListener;

            //Click
            txt_content.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onCommentListener.OnCommentClick(getAdapterPosition());
            RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(v,onCommentListener);

            if(recyclerViewHolder.txt_content.getHeight()>100){
                Log.d("fff", ">100: ");
                LinearLayout.LayoutParams  size = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,100);
                recyclerViewHolder.txt_content.setLayoutParams(size);
            }else if(recyclerViewHolder.txt_content.getLineCount()>1 && recyclerViewHolder.txt_content.getHeight()!=ViewGroup.LayoutParams.WRAP_CONTENT){

                Log.d("fff", "onClick: "+ Integer.toString(recyclerViewHolder.txt_content.getHeight()));
                LinearLayout.LayoutParams  size = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                recyclerViewHolder.txt_content.setLayoutParams(size);
            }


        }

    }

    public interface OnCommentListener{
        void OnCommentClick(int position);
    }


}
