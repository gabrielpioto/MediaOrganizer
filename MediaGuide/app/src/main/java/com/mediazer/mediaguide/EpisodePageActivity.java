package com.mediazer.mediaguide;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ScrollView;

import com.mediazer.mediaguide.views.PinnedParallaxScrollView;
import com.viewpagerindicator.TitlePageIndicator;


public class EpisodePageActivity extends AppCompatActivity{

    private Drawable abBackground;
    private int indicatorHeight;

    private PinnedParallaxScrollView.OnScrollListener mOnScrollChangedListener = new PinnedParallaxScrollView.OnScrollListener(){
        public void onScroll(ScrollView who, int scrollY) {
            int headerHeight = who.findViewById(R.id.image_header).getHeight() - indicatorHeight;
            final float ratio = (float) Math.min(Math.max(scrollY, 0), headerHeight) / headerHeight;
            final int newAlpha = (int) (ratio * 255);
            abBackground.setAlpha(newAlpha);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TitlePageIndicator indicator = (TitlePageIndicator) findViewById(R.id.indicator);

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        indicatorHeight = getResources().getDimensionPixelSize(R.dimen.indicator_height);

        abBackground = new ColorDrawable(getResources().getColor(android.R.color.holo_orange_dark));
        //abBackground.setAlpha(0);
        indicator.setBackground(abBackground);

        pager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(final int position) {
                return EpisodePageFragment.newInstance(position, mOnScrollChangedListener);
            }

            @Override
            public int getCount() {
                return 10;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return "Titulo " + (position + 1);
            }
        });

        indicator.setViewPager(pager);
        int hl = getResources().getDimensionPixelOffset(R.dimen.default_title_indicator_footer_line_height);
        int hi = getResources().getDimensionPixelOffset(R.dimen.default_title_indicator_footer_indicator_height);
        float ht = indicator.getTextSize() / 2.0f;
        float hb = indicatorHeight / 2.0f;
        indicator.setTopPadding(hb - ht - hi - hl);
        //indicator.setFooterLineHeight(0);
        //indicator.setFooterIndicatorHeight(0);

    }

}
