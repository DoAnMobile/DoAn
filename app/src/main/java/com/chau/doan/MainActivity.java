package com.chau.doan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemClick{

    private RecyclerView MoviesSapChieuRV, MoviesDangChieuRV;

    ViewFlipper slideVF;

    String ctTiecTrangMau = "Chuyển thể từ kịch bản nước ngoài, Tiệc Trăng Máu quy tụ dàn diễn viên bạc tỷ - Thái Hòa, Đức Thịnh, Kiều Minh Tuấn, Hứa Vỹ Văn, Hồng Ánh, Thu Trang và Kaity Nguyễn. Ngay từ khi những thông tin về phim được công bố, Tiệc Trăng Máu đã ngay lập tức cho thấy tham vọng tạo nên sự đột phá cho điện ảnh Việt. Nhà sản xuất đã công bố thông tin chi tiết về ngày ra mắt chính thức và giới thiệu dàn diễn viên hùng hậu. Trong một buổi tiệc họp mặt bạn bè vào đêm trăng máu, nhân vật do nữ diễn viên Hồng Ánh thể hiện đã đề nghị một trò chơi với tên gọi “công khai bí mật từ điện thoại”. Cuộc tụ tập của nhóm bạn đã trở thành một thử thách căng thẳng với luật chơi tưởng như đơn giản nhẹ nhàng. Những tin nhắn, cuộc gọi bất ngờ hé lộ bí mật sâu kín họ muốn chôn giấu. Tình bạn mấy chục năm có nguy cơ tan vỡ...";
    String ctDaiDichXacSong = "Cuộc sống yên bình của cậu thanh niên Aidan bỗng bị đảo lộn khi những người hàng xóm bị mắc virus lạ và trở thành zombie. Bị cô lập trong căn hộ, thức ăn ngày càng vơi dần, Aidan phải chọn lựa giữa cố thủ ở nhà rồi chết đói hoặc ra ngoài tìm người giúp giữa vòng vây lũ quái vật điên cuồng.";
    String ctQuaiVat = "Một phi hành gia bị tấn công bởi sinh vật bí ẩn ngoài vũ trụ. Để bảo toàn mạng sống, nhà khoa hoc Tatyana được cử tới với nhiệm vụ cứu chữa và giải đáp sự thật kinh hoàng đang bị che giấu. ";
    String ctCucNo = "Từ kẻ đòi nợ, hai người đàn ông bỗng trở thành gà trống nuôi con sau khi bắt cóc cô con gái của một phụ nữ nhập cư trái phép. Liệu những tình huống nào sẽ xảy đến với gia đình đặc biệt này?";
    String ctTiHon = "Hai trăm năm trước, những yêu tinh vùng Cologne từng giúp đỡ các người thợ trong vùng vào ban đêm. Thế nhưng, sau khi bị một thợ cắt tóc phản bội, họ đã biến mất. Giờ đây, huyền thoại năm xưa xuất hiện trở lại tại một tiệm bánh.";

    ImageButton btnAcc, btnDangNhap, btnGioHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        slideVF = (ViewFlipper) findViewById(R.id.slideShow);
        btnAcc = (ImageButton) findViewById(R.id.btnAcc);
        btnDangNhap = (ImageButton) findViewById(R.id.btnDangNhapNe);
        btnGioHang = (ImageButton) findViewById(R.id.btnGio);


        int images[] = {R.drawable.slide_1, R.drawable.slide_2, R.drawable.slide_3};

        for (int image: images)
        {
            SlideShow(image);
        }

        DsPhimDangChieu();

        DsPhimSapChieu();

        btnAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, user.class);

                startActivity(intent);

            }
        });

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, MainActivity1.class);
                startActivity(intent);

            }
        });

        btnGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity3.class);

                startActivity(intent);
            }
        });

    }

    private void SlideShow(int image) {


        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        slideVF.addView(imageView);
        slideVF.setFlipInterval(4000);
        slideVF.setAutoStart(true);

        slideVF.setInAnimation(this, R.anim.slide_in);
        slideVF.setOutAnimation(this, R.anim.slide_out);

    }

    @Override
    public void onPhimClick(Phim phim, ImageView phimImageView) {
        Intent intent = new Intent(this, ChiTietphim.class);
        intent.putExtra("title", phim.getTenphim());
        intent.putExtra("imgURL", phim.getHinhanh());
        intent.putExtra("detail", phim.getChitiet());
        intent.putExtra("theloai", phim.getTheloai());
        //
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, phimImageView, "sharedName");
        startActivity(intent, options.toBundle());
    }

    public void Mess(String text)
    {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    public void DsPhimDangChieu()
    {
        MoviesDangChieuRV = findViewById(R.id.RvPhimDangChieu);

        List<Phim> dsPhim = new ArrayList<>();
        dsPhim.add(new Phim("Tiệc Trăng Máu", "Kinh dị", R.drawable.tiec_trang_mau, ctTiecTrangMau));
        dsPhim.add(new Phim("Đại Dịch Xác Sống", "Kinh dị", R.drawable.dai_dich_xac_song, ctDaiDichXacSong));
        dsPhim.add(new Phim("Quái Vật Săn Đêm", "Kinh dị", R.drawable.quai_vat_san_dem, ctQuaiVat));
        dsPhim.add(new Phim("Cục Nợ Hóa Cục Vàng", "Tình cảm", R.drawable.cu_no_hoa_cuc_cung, ctCucNo));
        dsPhim.add(new Phim("Tí Hon Hậu Đậu", "Hoạt hình", R.drawable.ti_hon_hau_dau, ctTiHon));

        MovieAdapter movieAdapter = new MovieAdapter(this, dsPhim, this);
        MoviesDangChieuRV.setAdapter(movieAdapter);
        MoviesDangChieuRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    public void DsPhimSapChieu()
    {
        MoviesSapChieuRV = findViewById(R.id.RvPhimSapChieu);

        List<Phim> dsPhim = new ArrayList<>();
        dsPhim.add(new Phim("Cục Nợ Hóa Cục Vàng", "Tình cảm", R.drawable.cu_no_hoa_cuc_cung, ctCucNo));
        dsPhim.add(new Phim("Tí Hon Hậu Đậu", "Hoạt hình, 18+", R.drawable.ti_hon_hau_dau, ctTiHon));
        dsPhim.add(new Phim("Đại Dịch Xác Sống", "Kinh dị", R.drawable.dai_dich_xac_song, ctDaiDichXacSong));
        dsPhim.add(new Phim("Tiệc Trăng Máu", "Kinh dị", R.drawable.tiec_trang_mau, ctTiecTrangMau));
        dsPhim.add(new Phim("Quái Vật Săn Đêm", "Kinh dị", R.drawable.quai_vat_san_dem, ctQuaiVat));

        MovieAdapter movieAdapter = new MovieAdapter(this, dsPhim, this);
        MoviesSapChieuRV.setAdapter(movieAdapter);
        MoviesSapChieuRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }
}