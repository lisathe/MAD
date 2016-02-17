package com.example.lisa.animalviewer;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends AppCompatActivity {

    private LinearLayout viewpageIndicatorContainer;
    private List<ImageView> indicatorList;
    private MyPageAdapter pageAdapter;
    private ViewPager mpager;
    private int[] imageIds = {R.drawable.image1, R.drawable.image2, R.drawable.image3};
    private String[] animalNames;
    private String[] descriptionStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        indicatorList = new ArrayList<>();

        animalNames = getResources().getStringArray(R.array.image_title);
        descriptionStrings = getResources().getStringArray(R.array.description_tekst);

        viewpageIndicatorContainer = (LinearLayout) findViewById(R.id.page_indicator_container);
        final TextView TITLE = (TextView) findViewById(R.id.image_title);
        final TextView DESCRIPTION = (TextView) findViewById(R.id.description_text);

        TITLE.setText(animalNames[0]);
        DESCRIPTION.setText(descriptionStrings[0]);

        int viewPagerLength = viewpageIndicatorContainer.getChildCount();

        for (int i = 0; i < viewPagerLength; i++) {
            indicatorList.add((ImageView) viewpageIndicatorContainer.getChildAt(i));
        }

        mpager = (ViewPager) findViewById(R.id.viewpager);
        pageAdapter = new MyPageAdapter(getSupportFragmentManager());
        mpager.setAdapter(pageAdapter);

        mpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int indicatorLength = indicatorList.size();
                for (int i = 0; i < indicatorLength; i++) {
                    if (i == position) {
                        indicatorList.get(i).setImageResource(R.drawable.circle_white);
                    } else {
                        indicatorList.get(i).setImageResource(R.drawable.circle_grey);
                    }
                }
                TITLE.setText(animalNames[position]);
                if (position < indicatorList.size() - 1) {
                    if (DESCRIPTION.getVisibility() != View.VISIBLE) {
                        DESCRIPTION.setVisibility(View.VISIBLE);
                    }
                    DESCRIPTION.setText(descriptionStrings[position]);
                } else {
                    DESCRIPTION.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    public void scrollToPage(int pageId) {
        mpager.setCurrentItem(pageId, true);
    }


    private class MyPageAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments;

        public MyPageAdapter(FragmentManager fm) {
            super(fm);
            fragments = new ArrayList<>();
            fragments.add(AnimalFragment.newInstance(imageIds[0]));
            fragments.add(AnimalFragment.newInstance(imageIds[1]));
            fragments.add(AnimalFragment.newInstance(imageIds[2]));
            fragments.add(AnimalListFragment.newInstance(imageIds, animalNames));
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return this.fragments.size();
        }
    }
}
