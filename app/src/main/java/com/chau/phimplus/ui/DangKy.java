package com.chau.phimplus.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.chau.phimplus.R;

public class DangKy extends AppCompatActivity {

    Button btnDN1;
    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);

        AnhXa();

        XuLy();

    }

    private void XuLy() {
        btnDN1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(DangKy.this, LoginActivity.class);
                startActivity(intent);

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });
    }

    private void AnhXa() {

        btnDN1 = (Button) findViewById(R.id.btn_backToDangNhap);
        btnBack = (ImageButton) findViewById(R.id.btn_account_back);

    }
}