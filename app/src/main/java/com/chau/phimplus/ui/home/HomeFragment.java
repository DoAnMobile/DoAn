package com.chau.phimplus.ui.home;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.chau.phimplus.ui.ChiTietphim;
import com.chau.phimplus.Models.ItemClick;
import com.chau.phimplus.Models.MovieAdapter;
import com.chau.phimplus.Models.Phim;
import com.chau.phimplus.R;
import com.chau.phimplus.ui.movie_detail.FragmentBinhLuan;
import com.chau.phimplus.ui.movie_detail.FragmentThongTin;
import com.chau.phimplus.ui.movie_detail.MainActivity_MovieDetail;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements ItemClick {

    RecyclerView MoviesSapChieuRV, MoviesDangChieuRV;


    ViewFlipper slideVF;

    String ctTiecTrangMau = "Chuyển thể từ kịch bản nước ngoài, Tiệc Trăng Máu quy tụ dàn diễn viên bạc tỷ - Thái Hòa, Đức Thịnh, Kiều Minh Tuấn, Hứa Vỹ Văn, Hồng Ánh, Thu Trang và Kaity Nguyễn. Ngay từ khi những thông tin về phim được công bố, Tiệc Trăng Máu đã ngay lập tức cho thấy tham vọng tạo nên sự đột phá cho điện ảnh Việt. Nhà sản xuất đã công bố thông tin chi tiết về ngày ra mắt chính thức và giới thiệu dàn diễn viên hùng hậu. Trong một buổi tiệc họp mặt bạn bè vào đêm trăng máu, nhân vật do nữ diễn viên Hồng Ánh thể hiện đã đề nghị một trò chơi với tên gọi “công khai bí mật từ điện thoại”. Cuộc tụ tập của nhóm bạn đã trở thành một thử thách căng thẳng với luật chơi tưởng như đơn giản nhẹ nhàng. Những tin nhắn, cuộc gọi bất ngờ hé lộ bí mật sâu kín họ muốn chôn giấu. Tình bạn mấy chục năm có nguy cơ tan vỡ...";
    String ctDaiDichXacSong = "Cuộc sống yên bình của cậu thanh niên Aidan bỗng bị đảo lộn khi những người hàng xóm bị mắc virus lạ và trở thành zombie. Bị cô lập trong căn hộ, thức ăn ngày càng vơi dần, Aidan phải chọn lựa giữa cố thủ ở nhà rồi chết đói hoặc ra ngoài tìm người giúp giữa vòng vây lũ quái vật điên cuồng.";
    String ctQuaiVat = "Một phi hành gia bị tấn công bởi sinh vật bí ẩn ngoài vũ trụ. Để bảo toàn mạng sống, nhà khoa hoc Tatyana được cử tới với nhiệm vụ cứu chữa và giải đáp sự thật kinh hoàng đang bị che giấu. ";
    String ctCucNo = "Từ kẻ đòi nợ, hai người đàn ông bỗng trở thành gà trống nuôi con sau khi bắt cóc cô con gái của một phụ nữ nhập cư trái phép. Liệu những tình huống nào sẽ xảy đến với gia đình đặc biệt này?";
    String ctTiHon = "Hai trăm năm trước, những yêu tinh vùng Cologne từng giúp đỡ các người thợ trong vùng vào ban đêm. Thế nhưng, sau khi bị một thợ cắt tóc phản bội, họ đã biến mất. Giờ đây, huyền thoại năm xưa xuất hiện trở lại tại một tiệm bánh.";
    String ctChong = "Chồng Người Ta là một bộ phim tâm lý tình cảm có yếu tố bất ngờ, phim khai thác câu chuyện tình yêu đầy thù hận xoay quanh ba nhân vật Hà,Cường và Trung. Tưởng rằng quá khứ đã chôn vùi, Cường cùng vợ con xây đắp tổ ấm nhỏ hạnh phúc. Nào ngờ, sự xuất hiện của Thắng - người nắm giữbí mật động trời của Cường. Liệu Hà, Cường và Trung có vượt qua được nỗiđau của tình yêu và hận thù?";


    ImageButton btnAcc, btnDangNhap, btnGioHang;

    private HomeViewModel homeViewModel;
    public static Phim phimClicked = new Phim();
    View mRootView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //setHasOptionsMenu(true);
        mRootView = inflater.inflate(R.layout.fragment_home, container, false);



        return mRootView;
    }




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AnhXa(view);

        int images[] = {R.drawable.slide_1, R.drawable.slide_2, R.drawable.slide_3};

        for (int image: images)
        {
            SlideShow(image);
        }

        DsPhimDangChieu();

        DsPhimSapChieu();

    }

    private void AnhXa(View view)
    {

        slideVF = (ViewFlipper) view.findViewById(R.id.slideShow);
        MoviesDangChieuRV = view.findViewById(R.id.RvPhimDangChieu);
        MoviesSapChieuRV = view.findViewById(R.id.RvPhimSapChieu);
    }

    private void SlideShow(int image)
    {

        ImageView imageView = new ImageView(getContext());
        imageView.setBackgroundResource(image);

        slideVF.addView(imageView);
        slideVF.setFlipInterval(4000);
        slideVF.setAutoStart(true);

        slideVF.setInAnimation(getContext(), R.anim.slide_in);
        slideVF.setOutAnimation(getContext(), R.anim.slide_out);

    }

    public void DsPhimDangChieu()
    {

        List<Phim> dsPhim = new ArrayList<>();
        dsPhim.add(new Phim("Tiệc Trăng Máu", "Kinh dị", R.drawable.tiec_trang_mau, ctTiecTrangMau));
        dsPhim.add(new Phim("Đại Dịch Xác Sống", "Kinh dị", R.drawable.dai_dich_xac_song, ctDaiDichXacSong));
        dsPhim.add(new Phim("Quái Vật Săn Đêm", "Kinh dị", R.drawable.quai_vat_san_dem, ctQuaiVat));
        dsPhim.add(new Phim("Cục Nợ Hóa Cục Vàng", "Tình cảm", R.drawable.cu_no_hoa_cuc_cung, ctCucNo));
        dsPhim.add(new Phim("Tí Hon Hậu Đậu", "Hoạt hình", R.drawable.ti_hon_hau_dau, ctTiHon));
        dsPhim.add(new Phim("Chồng người ta", "Tâm Lý, 18+", R.drawable.chong_nguoi_ta, ctChong));

        MovieAdapter movieAdapter = new MovieAdapter(getContext(), dsPhim, this);
        MoviesDangChieuRV.setAdapter(movieAdapter);
        MoviesDangChieuRV.setLayoutManager(new GridLayoutManager(getContext(), 3));

    }

    public void DsPhimSapChieu()
    {

        List<Phim> dsPhim = new ArrayList<>();
        dsPhim.add(new Phim("Chồng người ta", "Tâm Lý, 18+", R.drawable.chong_nguoi_ta, ctChong));
        dsPhim.add(new Phim("Cục Nợ Hóa Cục Vàng", "Tình cảm", R.drawable.cu_no_hoa_cuc_cung, ctCucNo));
        dsPhim.add(new Phim("Tí Hon Hậu Đậu", "Hoạt hình, 18+", R.drawable.ti_hon_hau_dau, ctTiHon));
        dsPhim.add(new Phim("Đại Dịch Xác Sống", "Kinh dị", R.drawable.dai_dich_xac_song, ctDaiDichXacSong));
        dsPhim.add(new Phim("Tiệc Trăng Máu", "Kinh dị", R.drawable.tiec_trang_mau, ctTiecTrangMau));
        dsPhim.add(new Phim("Quái Vật Săn Đêm", "Kinh dị", R.drawable.quai_vat_san_dem, ctQuaiVat));

        MovieAdapter movieAdapter = new MovieAdapter(getContext(), dsPhim, this);
        MoviesSapChieuRV.setAdapter(movieAdapter);
        MoviesSapChieuRV.setLayoutManager(new GridLayoutManager(getContext(), 3));

    }

    @Override
    public void onPhimClick(Phim phim, ImageView phimImageView)
    {

//        Intent intent = new Intent(getActivity(), MainActivity_MovieDetail.class);

        phimClicked.setTenphim(phim.getTenphim());
        phimClicked.setHinhanh(phim.getHinhanh());
        phimClicked.setChitiet(phim.getChitiet());
        phimClicked.setTheloai(phim.getTheloai());

        //
//        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(), phimImageView, "sharedName");
//        FragmentThongTin fragmentThongTin = new FragmentThongTin();
//        FragmentBinhLuan fragmentBinhLuan = new FragmentBinhLuan();
//        FragmentManager fragmentManager = getParentFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.nav_host_fragment,fragmentThongTin);
//
//        fragmentTransaction.commit();
        //startActivity(intent);

        Intent intent= new Intent(getActivity(), MainActivity_MovieDetail.class);
        startActivity(intent);


    }
}