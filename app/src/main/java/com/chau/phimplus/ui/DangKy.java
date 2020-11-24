package com.chau.phimplus.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.chau.phimplus.Models.TaiKhoan;
import com.chau.phimplus.R;
import com.chau.phimplus.Server.APIserver;
import com.chau.phimplus.Server.Dataserver;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangKy extends AppCompatActivity {

    Button btnDN1, btnDK;
    ImageButton btnBack;
    EditText edPhone, edPass, edRePass, edFullName, edNgaySinh;

    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

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

        Register();
    }

    private void AnhXa() {

        btnDN1      = (Button) findViewById(R.id.btn_backToDangNhap);
        btnDK       = (Button) findViewById(R.id.btnDangKy1);
        btnBack     = (ImageButton) findViewById(R.id.btn_account_back);
        edPass      = (EditText) findViewById(R.id.edtPassword1);
        edPhone     = (EditText) findViewById(R.id.edtSDT);
        edRePass    = (EditText) findViewById(R.id.edtPasswordAgain);
        edFullName  = (EditText) findViewById(R.id.edtHoTen);
        edNgaySinh  = (EditText) findViewById(R.id.edNgaySinh_DangKy);

    }

    // Hàm khi nhấn nút đăng ký
    public void Register()
    {

        Calendar calendar = Calendar.getInstance();
        edNgaySinh.setText(format.format(calendar.getTime()));

        edNgaySinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Picker();

            }
        });

        btnDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Phone        = edPhone.getText().toString();
                String Pass         = edPass.getText().toString();
                String reMatKhau    = edRePass.getText().toString();
                String FullName     = edFullName.getText().toString();
                String BirthDay     = edNgaySinh.getText().toString();

                if (Phone.length() > 10)
                {
                    Mess("Số điện thoại quá dài");
                }
                else if (Phone.length() < 10)
                {
                    Mess("Số điện thoại không đúng");
                }
                else if (Pass.equalsIgnoreCase(reMatKhau) == false)
                {
                    Mess("Mật khẩu nhập lại không đúng");
                }
                else if (Pass.length() < 6)
                {
                    Mess("Mật khẩu phải hơn 6 ký tự");
                }
                else if (FullName.length() < 1)
                {
                    Mess("Tên không được để trống");
                }
                else {

                    // INSERT DATA
                    InsertData(Phone, Pass, FullName, BirthDay);

                    finish();

                }

            }
        });

    }

    // Đẩy Data lên Server
    public void InsertData(String Phone, String Pass, String FullName, String BirthDay){

        Dataserver dataserver = APIserver.getServer();
        Call<List<TaiKhoan>> callback ;

        callback = dataserver.savePost(Phone, Pass, FullName, BirthDay);

        callback.enqueue(new Callback<List<TaiKhoan>>() {
            @Override
            public void onResponse(Call<List<TaiKhoan>> call, Response<List<TaiKhoan>> response) {

                Mess("Thành công rồi!");

            }

            @Override
            public void onFailure(Call<List<TaiKhoan>> call, Throwable t) {

                Mess("Thành công rồi!");

            }
        });
    }

    // Hàm Toast nhanh
    public void Mess(String text)
    {
        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
    }

    // DatePicker
    public void Picker()
    {
        final Calendar calendar = Calendar.getInstance();
        final Calendar Now = Calendar.getInstance();
        int Date = calendar.get(Calendar.DATE);
        int Month = calendar.get(Calendar.MONTH);
        int Year = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                calendar.set(year, month, dayOfMonth);
                edNgaySinh.setText(format.format(calendar.getTime()));

            }
        }, Year, Month, Date);

        datePickerDialog.show();
    }

}