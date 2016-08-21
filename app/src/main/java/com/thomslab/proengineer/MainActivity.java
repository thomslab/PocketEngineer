package com.thomslab.proengineer;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by mitohida on 8/7/2016.
 */

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // inisialisasi tab
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        // penambahan tabs dan teks tab
        tabLayout.addTab(tabLayout.newTab().setText("Electrical"));
        tabLayout.addTab(tabLayout.newTab().setText("Instrument"));
        tabLayout.addTab(tabLayout.newTab().setText("Mechanical"));

        // inisialisasi pager
        viewPager = (ViewPager) findViewById(R.id.pager);

        // membuat pager adapter
        pager pagerAdapter = new pager(getSupportFragmentManager(), tabLayout.getTabCount());

        // menambahkan adapter ke pager
        viewPager.setAdapter(pagerAdapter);

        // menambahkan Ontabselectedlistener untuk swipe views
        tabLayout.addOnTabSelectedListener(this);
        tabLayout.setSmoothScrollingEnabled(true);
        tabLayout.setHorizontalScrollBarEnabled(true);

        //deteksi on page selected
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {

                //actionBar.setSelectedNavigationItem(postion);
                tabLayout.setScrollPosition(position,0,true);
                tabLayout.setSelected(true);
            }
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }
            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });

    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
