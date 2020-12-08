package com.chau.phimplus.ui.movie_detail;

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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.chau.phimplus.Models.Phim;
import com.chau.phimplus.R;
import com.chau.phimplus.ui.home.HomeFragment;

public class FragmentThongTin extends Fragment {

    private View mRootView;
    Button btn_trailer;
    ImageView imageView;
    TextView txtTitle, txtDetail, txtTheLoai;
    Button btnMuaVe;
    ImageButton btnBack;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //


        //
        mRootView =inflater.inflate(R.layout.fragment_thongtin,container,false);
        //
        //xuLy();\
        btn_trailer = (Button)mRootView.findViewById(R.id.btn_test);

//        btn_trailer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), com.example.movie_detail.FragmentBinhLuan.class);
//                startActivity(intent);
//            }
//        });

        AnhXa();


        GanGiaTri();

       // XuLy();

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


        Log.d("fff",Integer.toString(img));
        Log.d("fff",title);
        Log.d("fff",detail);

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
        //btnBack = (ImageButton)  mRootView.findViewById(R.id.btn_chitiet_back);

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
