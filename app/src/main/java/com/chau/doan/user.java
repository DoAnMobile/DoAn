package com.chau.doan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class user extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

            anhxa();
            hienthiTab();

    }

    public void anhxa()
    {
        viewPager = (ViewPager)findViewById(R.id.ViewPagerUser);
    }

    public void hienthiTab()
    {
        DieuChinhFrg adapter = new DieuChinhFrg(getSupportFragmentManager());
        adapter.addTab(new ThongTin(), "Thông Tin");
        adapter.addTab(new GiaoDich(), "Giao Dịch");
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout)this.findViewById(R.id.tablayoutUser);
        tabLayout.setupWithViewPager(viewPager);
    }
}