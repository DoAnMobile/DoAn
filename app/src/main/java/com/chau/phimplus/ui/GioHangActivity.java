package com.chau.phimplus.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.chau.phimplus.R;

public class GioHangActivity extends AppCompatActivity {

    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giohang);

        AnhXa();

        XuLy();

    }

    private void XuLy() {

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });

    }

    private void AnhXa() {

        btnBack = (ImageButton) findViewById(R.id.btn_giohang_back);

    }
}