package com.chau.phimplus.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.chau.phimplus.R;

public class ChiTietphim extends AppCompatActivity {

    ImageView imageView;
    TextView txtTitle, txtDetail, txtTheLoai;
    Button btnMuaVe;
    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tietphim);

        AnhXa();

        GanGiaTri();

        XuLy();

    }

    private void GanGiaTri() {

        String title = getIntent().getExtras().getString("title");
        String detail = getIntent().getExtras().getString("detail");
        String theloai = getIntent().getExtras().getString("theloai");
        int img = getIntent().getExtras().getInt("imgURL");

        Glide.with(this).load(img).into(imageView);
        imageView.setImageResource(img);

        txtTitle.setText(title);
        txtDetail.setText(detail);
        txtTheLoai.setText(theloai);

    }

    private void AnhXa() {

        imageView = findViewById(R.id.img_chi_tiet_phim);
        txtTitle = (TextView) findViewById(R.id.txt_title);
        txtDetail = findViewById(R.id.detail_movie);
        txtTheLoai = (TextView) findViewById(R.id.txtTheLoai);
        btnMuaVe = (Button) findViewById(R.id.btnMuaVe);
        btnBack = (ImageButton) findViewById(R.id.btn_chitiet_back);

    }

    private void XuLy() {

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}