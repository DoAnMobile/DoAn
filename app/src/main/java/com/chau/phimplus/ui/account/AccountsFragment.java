package com.chau.phimplus.ui.account;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.chau.phimplus.ui.Account;
import com.chau.phimplus.Data.LocalData;
import com.chau.phimplus.ui.GioHangActivity;
import com.chau.phimplus.ui.LoginActivity;
import com.chau.phimplus.MainActivity;
import com.chau.phimplus.R;

import java.util.ArrayList;

public class AccountsFragment extends Fragment {

    ListView listView;

    Button btnLogin;

    TextView txtAccountName;

    LocalData localData;

    ArrayList<String> chucNang = new ArrayList<String>();

    ArrayList<Integer> iconRow = new ArrayList<Integer>();

    String curTaiKhoan;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_account, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AnhXa(view);

        // Thêm chức năng vào ListView
        AddListView();

        // Click ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

                String text = chucNang.get(position);

                Mess(text);

                if (text.equalsIgnoreCase("Cài đặt tài khoản"))
                {
                    Intent intent = new Intent(getContext(), Account.class);
                    startActivity(intent);
                }

                if (text.equalsIgnoreCase("Giỏ hàng"))
                {
                    Intent intent = new Intent(getContext(), GioHangActivity.class);
                    startActivity(intent);
                }

                if (text.equalsIgnoreCase("Đăng xuất"))
                {
                    Logout(curTaiKhoan);
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    startActivity(intent);
                }

            }

        });

        CreateLocalData();

        CreateFirstLogin();

        // Check đã đăng nhập chưa
        CheckLogin();

        // Nhấn nút ĐĂNG NHẬP
        btnLogin.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), LoginActivity.class);

                startActivity(intent);

            }

        });

    }

    // Hàm thêm chức năng vào ListView
    private void AddListView()
    {

        chucNang.add("Vé đã mua");
        chucNang.add("Giỏ hàng");
        chucNang.add("Yêu thích");
        chucNang.add("Cài đặt tài khoản");
        chucNang.add("Cài đặt ứng dụng");

        iconRow.add(R.drawable.ic_tickets);
        iconRow.add(R.drawable.ic_cart);
        iconRow.add(R.drawable.ic_heart);
        iconRow.add(R.drawable.ic_account_setting_02);
        iconRow.add(R.drawable.ic_app_setting);

        myAdapter adapter = new myAdapter(getContext(), chucNang, iconRow);
        listView.setAdapter(adapter);

    }

    private void AnhXa(View view)
    {

        listView = (ListView) view.findViewById(R.id.listview_account);
        btnLogin = (Button) view.findViewById(R.id.btnLogin);
        txtAccountName = (TextView) view.findViewById(R.id.txt_account_name);
        localData = new LocalData(getContext(),"sttAccount", null, 1);

    }

    // Adapter -> Custom ListView
    class myAdapter extends ArrayAdapter<String>
    {

        Context context;
        ArrayList<String> chucNang = new ArrayList<String>();
        ArrayList<Integer> iconNe = new ArrayList<Integer>();

        myAdapter (Context c, ArrayList<String> chucNang, ArrayList<Integer> icon)
        {
            super(c, R.layout.activity_row_list_account, R.id.row_txt_account, chucNang);
            this.context = c;
            this.chucNang = chucNang;
            this.iconNe = icon;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.activity_row_list_account, parent, false);
            ImageView icon = (ImageView) row.findViewById(R.id.row_image_icon);
            TextView text = (TextView) row.findViewById(R.id.row_txt_account);

            icon.setImageResource(iconRow.get(position));
            text.setText(chucNang.get(position));

            return row;

        }

    }

    // Tạo Local data
    public void CreateLocalData()
    {
        // Tao bang tai khoan
        String queryTaoBang = "Create Table if not exists TaiKhoan (ID Integer Primary Key AutoIncrement, Name VARCHAR(100), Phone VARCHAR(50), Pass VARCHAR(50), TrangThai VARCHAR(10))";
        localData.AddData(queryTaoBang);

        // Tao bang kiem tra lan khoi dong
        String queryTaoBangCheck = "Create Table if not exists CheckLogin (ID Integer Primary Key, Stt VARCHAR(10))";
        localData.AddData(queryTaoBangCheck);
    }

    // Hàm đăng xuất -> thay đổi trang thái tài khoản đang đăng nhập
    private void Logout(String curTaiKhoan) {

        String queryLogout = "Update TaiKhoan Set TrangThai = 'false' Where Phone = '" + curTaiKhoan + "'";
        localData.AddData(queryLogout);

    }

    // Hàm lấy dữ liệu từ Local Data
    public Cursor GetLocalData(String queryLayTaiKhoan)
    {

        Cursor cursor = localData.GetData(queryLayTaiKhoan);
        return cursor;

    }

    // Check đăng nhập (lấy từ local data)
    public void CheckLogin()
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
                    // Nếu stt của tài khoản là true thì chạy
                    if (cursor.getString(4).trim().equalsIgnoreCase("true"))
                    {
                        // Lấy tên tài khoản
                        curTaiKhoan = cursor.getString(2);

                        // Nếu đã đăng nhập thì tắt nút ĐĂNG NHẬP
                        String nameAcc = cursor.getString(1);
                        btnLogin.setVisibility(View.INVISIBLE);
                        txtAccountName.setText(nameAcc);
                        txtAccountName.setVisibility(View.VISIBLE);

                        // Nếu đã đăng nhập thì hiện chức năng ĐĂNG XUẤT
                        chucNang.add("Đăng xuất");
                        iconRow.add(R.drawable.ic_logout_02);
                    }
                }
            }
        }

    }

    // Lần đầu đăng nhập -> thêm vào Local Data
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

    // Hàm Toast nhanh
    public void Mess(String text)
    {
        Toast.makeText(getContext(),text,Toast.LENGTH_SHORT).show();
    }

}