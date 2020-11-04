package com.chau.doan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.chau.doan.R;

public class MainActivity1 extends AppCompatActivity {

    Button btnDK1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        AnhXa();
        XuLy();
    }

    private void XuLy() {
        btnDK1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity1.this,MainActivity2.class);
                startActivity(intent);
            }
        });

    }

    private void AnhXa() {
        btnDK1 = (Button) findViewById(R.id.btnDK);
    }
}