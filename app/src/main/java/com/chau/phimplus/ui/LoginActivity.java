package com.chau.phimplus.ui;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.chau.phimplus.Data.LocalData;
import com.chau.phimplus.MainActivity;
import com.chau.phimplus.Models.TaiKhoan;
import com.chau.phimplus.R;
import com.chau.phimplus.Server.APIserver;
import com.chau.phimplus.Server.Dataserver;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    private void GetData() {

        Dataserver dataserver = APIserver.getServer();
        Call<List<TaiKhoan>> callback = dataserver.getTaiKhoan();

        callback.enqueue(new Callback<List<TaiKhoan>>() {
            @Override
            public void onResponse(Call<List<TaiKhoan>> call, Response<List<TaiKhoan>> response) {
                ArrayList<TaiKhoan> taikhoans = (ArrayList<TaiKhoan>) response.body();

                Integer dem = 0;

                for(int i = 0; i < taikhoans.size(); i++)
                {
                    if(dienthoai.getText().toString().equalsIgnoreCase(taikhoans.get(i).getPhone().toString()) == true
                            && matkhau.getText().toString().equalsIgnoreCase(taikhoans.get(i).getPassword().toString()) == true)
                    {
                        dem++;

                        String phone = taikhoans.get(i).getPhone().toString();
                        String password  = taikhoans.get(i).getPassword().toString();
                        String name = taikhoans.get(i).getFullName().toString();
                        String stt = taikhoans.get(i).getStatus().toString();

                        InsertAccountLocal(phone, password, name, "true");

                        CreateFirstLogin();

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                        startActivity(intent);
                    }
                }

                if (dem == 0)
                {
                    Mess("Sai gì đó rồi");
                }
            }

            @Override
            public void onFailure(Call<List<TaiKhoan>> call, Throwable t) {

            }

        });

    }

    private void XuLy() {
        btnDN1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GetData();

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
        // Tạo bản tài khoản
        String queryTaoBang = "Create Table if not exists TaiKhoan (ID Integer Primary Key AutoIncrement, Phone VARCHAR(50), Pass VARCHAR(50), FullName VARCHAR(100), TrangThai VARCHAR(10))";
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

    public void InsertAccountLocal(String phone, String pass, String name, String stt)
    {
        String queryTaoTaiKhoan = "Insert Into TaiKhoan values (null, '" + phone + "',  '" + pass + "', '" + name + "','" + stt + "')";
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

    public void Mess(String text)
    {
        Toast.makeText(getApplicationContext(), text ,Toast.LENGTH_LONG).show();
    }
}