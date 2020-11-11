package com.chau.phimplus.ui;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.chau.phimplus.Data.LocalData;
import com.chau.phimplus.MainActivity;
import com.chau.phimplus.R;

public class LoginActivity extends AppCompatActivity {

    LocalData localData;

    EditText dienthoai, matkhau;

    Button btnDK1, btnDN1;

    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        AnhXa();

        XuLy();

    }

    private void XuLy() {
        btnDN1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String taikhoan = dienthoai.getText().toString();
                String matkhau1  = matkhau.getText().toString();
                String fullname = "Nguyen Phuc Bao Chau";

                InsertAccountLocal(taikhoan, matkhau1, fullname, "true");

                CreateFirstLogin();

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                startActivity(intent);

            }
        });

        btnDK1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, DangKy.class);
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

        btnDK1 = (Button) findViewById(R.id.btn_dangky_login);
        btnDN1 = (Button) findViewById(R.id.btn_dangNhap_Login);
        dienthoai = (EditText) findViewById(R.id.edtDienThoai);
        matkhau = (EditText) findViewById(R.id.edt_login_pass);
        btnBack = (ImageButton) findViewById(R.id.btn_login_back);

        localData = new LocalData(LoginActivity.this,"sttAccount", null, 1);

    }

    // Local data

    public void CreateLocalData()
    {
        // Tao bang tai khoan
        String queryTaoBang = "Create Table if not exists TaiKhoan (ID Integer Primary Key AutoIncrement, TaiKhoan VARCHAR(50), MatKhau VARCHAR(50), FullName VARCHAR(100), TrangThai VARCHAR(10))";
        localData.AddData(queryTaoBang);

        // Tao bang kiem tra lan khoi dong
        String queryTaoBangCheck = "Create Table if not exists CheckLogin (ID Integer Primary Key, Stt VARCHAR(10))";
        localData.AddData(queryTaoBangCheck);
    }

    private void Logout(String curTaiKhoan) {

        String queryLogout = "Update TaiKhoan Set TrangThai = 'false' Where TaiKhoan = '" + curTaiKhoan + "'";
        localData.AddData(queryLogout);

    }

    public Cursor GetLocalData(String queryLayTaiKhoan)
    {
        Cursor cursor = localData.GetData(queryLayTaiKhoan);
        return cursor;
    }

    public void InsertAccountLocal(String taikhoan, String matkhau, String fullname, String stt)
    {
        String queryTaoTaiKhoan = "Insert Into TaiKhoan values (null, '" + taikhoan + "',  '" + matkhau + "', '" + fullname + "', '" + stt + "')";
        localData.AddData(queryTaoTaiKhoan);
    }

    public void CreateFirstLogin()
    {
        String queryCheck = "Insert Into CheckLogin values (1, 'true')";

        String getID = "Select MAX(ID) from CheckLogin";
        Cursor curID = GetLocalData(getID);

        while (curID.moveToNext())
        {
            if (curID.getInt(0) != 1)
            {
                localData.AddData(queryCheck);
            }
        }
    }
}