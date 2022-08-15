package com.imman.iava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.imman.iava.Home.HomeCardPagerAdapter;

public class HomeActivity extends AppCompatActivity {
    private ViewPager2 viewPager2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        viewPager2 = findViewById(R.id.home_card_viewpager);

        HomeCardPagerAdapter viewPager2Adapter = new HomeCardPagerAdapter();
        viewPager2.setAdapter(viewPager2Adapter);

    }
}