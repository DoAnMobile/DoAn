package com.chau.phimplus.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.chau.phimplus.Data.LocalData;
import com.chau.phimplus.Models.TaiKhoan;
import com.chau.phimplus.R;
import com.chau.phimplus.Server.APIserver;
import com.chau.phimplus.Server.Dataserver;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Account extends AppCompatActivity {

    ImageButton btnBack;

    Button btnUpdate;

    LocalData localData;

    TextView txtSdt;

    EditText edName, edPass, edRePass;

    String curSdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        AnhXa();

        CreateLocalData();

        XuLy();
    }

    private void XuLy() {

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });

        LoadDataFromLocal();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String FullName     = edName.getText().toString();
                String Pass         = edPass.getText().toString();
                String reMatKhau    = edRePass.getText().toString();

                if (Pass.equalsIgnoreCase(reMatKhau) == false)
                {
                    Mess("Mật khẩu nhập lại không đúng");
                }
                else
                if (Pass.length() < 6)
                {
                    Mess("Mật khẩu phải hơn 6 ký tự");
                }
                else if (FullName.length() < 1)
                {
                    Mess("Tên không được để trống");
                }
                else {

                    // INSERT DATA
                    UpdateData(curSdt, Pass, FullName);

                    // UPDATE LOCAL DATA
                    UpdateLocalData(Pass, FullName);

                }

            }
        });

    }

    private void AnhXa() {

        btnBack     = (ImageButton) findViewById(R.id.btn_account_back);
        txtSdt      = (TextView) findViewById(R.id.txtSdt);
        edName      = (EditText) findViewById(R.id.hoten);
        edPass      = (EditText) findViewById(R.id.matkhau);
        edRePass    = (EditText) findViewById(R.id.rematkhau);
        btnUpdate   = (Button) findViewById(R.id.btnUpdate);

        localData   = new LocalData(getApplicationContext(),"sttAccount", null, 1);

    }

    public void LoadDataFromLocal()
    {

        String getID = "Select MAX(ID) from CheckLogin";
        Cursor curID = GetLocalData(getID);

        while (curID.moveToNext())
        {
            if (curID.getInt(0) == 1)
            {
                String queryLayTaiKhoan = "Select * From TaiKhoan";
                Cursor cursor = GetLocalData(queryLayTaiKhoan);
                while (cursor.moveToNext())
                {
                    if (cursor.getString(4).trim().equalsIgnoreCase("true"))
                    {
                        // Lấy thông tin tài khoản ra
                        String sdt = cursor.getString(1).toString();
                        curSdt = cursor.getString(1).toString();
                        String pass = cursor.getString(2).toString();
                        String name = cursor.getString(3).toString();

                        // Gán thông tin đã lấy lên giao diện
                        txtSdt.setText(sdt);
                        edPass.setText(pass);
                        edRePass.setText("");
                        edName.setText(name);

                        Mess(curSdt);

                    }
                }
            }
        }

    }

    public void UpdateData(String Phone, String Pass, String FullName)
    {

        Dataserver dataserver = APIserver.getServer();
        Call<List<TaiKhoan>> callback ;

        callback = dataserver.saveAccount(Phone, Pass, FullName);

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

//    Không cập nhật được password local
    public void UpdateLocalData(String ahi, String FullName)
    {
//        String query = "UPDATE TaiKhoan SET FullName = '" + FullName + "', Pass = '" + ahi + "' WHERE TrangThai = 'true'";
        String query = "UPDATE TaiKhoan SET FullName = '" + FullName + "' WHERE TrangThai = 'true'";
        localData.AddData(query);
    }

    public void CreateLocalData()
    {

        // Tạo bản tài khoản
        String queryTaoBang = "Create Table if not exists TaiKhoan (ID Integer Primary Key AutoIncrement, Phone VARCHAR(50), Pass VARCHAR(50), FullName VARCHAR(100), TrangThai VARCHAR(10))";
        localData.AddData(queryTaoBang);

        // Tạo bảng kiểm tra lần đầu khởi động
        String queryTaoBangCheck = "Create Table if not exists CheckLogin (ID Integer Primary Key, Stt VARCHAR(10))";
        localData.AddData(queryTaoBangCheck);

    }

    // Hàm lấy dữ liệu từ Local Data
    public Cursor GetLocalData(String queryLayTaiKhoan)
    {

        Cursor cursor = localData.GetData(queryLayTaiKhoan);
        return cursor;

    }

    // Hàm Toast nhanh
    public void Mess(String text)
    {
        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
    }

}