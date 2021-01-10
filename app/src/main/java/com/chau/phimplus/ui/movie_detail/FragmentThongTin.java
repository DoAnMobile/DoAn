package com.chau.phimplus.ui.movie_detail;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.chau.phimplus.Models.Phim;
import com.chau.phimplus.R;
import com.chau.phimplus.ui.home.HomeFragment;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class FragmentThongTin extends Fragment {

    private View mRootView;
    ImageView imageView,img_trailer;
    TextView txtTitle, txtDetail, txtTheLoai;
    Button btnMuaVe;
    ImageButton btnBack;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView =inflater.inflate(R.layout.fragment_thongtin,container,false);

        AnhXa();

        GanGiaTri();

        img_trailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                // Create and show the dialog.
                Dialog_trailer newFragment = new Dialog_trailer ();
                newFragment.show(ft, "dialog");
            }
        });

        return mRootView;
    }
    private void GanGiaTri() {

        Phim thisMovie = new HomeFragment().phimClicked;

        String title,detail,theloai;
        int img ;

        title = thisMovie.getTenphim();
        detail = thisMovie.getChitiet();
        theloai = thisMovie.getTheloai();
        img = thisMovie.getHinhanh();

        Glide.with(this).load(img).into(imageView);
        imageView.setImageResource(img);
        txtTitle.setText(title);
        txtDetail.setText(detail);
        txtTheLoai.setText(theloai);


    }



    private void AnhXa() {

        imageView =  mRootView.findViewById(R.id.movie_detail_img);
        txtTitle = (TextView)  mRootView.findViewById(R.id.movie_detail_txt_title);
        txtDetail =  mRootView.findViewById(R.id.movie_detail_content);
        txtTheLoai = (TextView) mRootView.findViewById(R.id.txtTheLoai);
        btnMuaVe = (Button)  mRootView.findViewById(R.id.btnMuaVe);
        img_trailer = (ImageView) mRootView.findViewById(R.id.movie_trailer_img);

    }

    private void XuLy() {

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finish();
            }
        });

    }




}
