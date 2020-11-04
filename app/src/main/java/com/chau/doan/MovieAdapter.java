package com.chau.doan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    Context context;
    List<Phim> mData;
    ItemClick itemClick;

    public MovieAdapter(Context context, List<Phim> mData, ItemClick click)
    {
        this.context = context;
        this.mData = mData;
        itemClick = click;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.title.setText(mData.get(position).getTenphim());
        holder.imageView.setImageResource(mData.get(position).getHinhanh());
        holder.theloai.setText(mData.get(position).getTheloai());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView title, theloai;
        private ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.item_movie_title);
            theloai = (TextView) itemView.findViewById(R.id.item_movie_theloai) ;
            imageView = (ImageView) itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClick.onPhimClick(mData.get(getAdapterPosition()), imageView);
                }
            });
        }
    }
}
