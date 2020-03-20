package com.ibrhmdurna.idgames.util;

import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

public class ShadowTransformer implements ViewPager.OnPageChangeListener, ViewPager.PageTransformer {

    private ViewPager mViewPager;
    private CardAdapter mAdapter;
    private float mLastOffset;
    private boolean mScalingEnabled = true;

    public ShadowTransformer(ViewPager viewPager, CardAdapter adapter) {
        mViewPager = viewPager;
        viewPager.addOnPageChangeListener(this);
        mAdapter = adapter;
    }

    public void enableScaling(boolean enable) {
        if (mScalingEnabled && !enable) {
            // shrink main card
            RelativeLayout currentCard = mAdapter.getRootLayout(mViewPager.getCurrentItem());
            if (currentCard != null) {
                currentCard.animate().scaleY(1);
                currentCard.animate().scaleX(1);
            }
        }else if(!mScalingEnabled && enable){
            // grow main card
            RelativeLayout currentCard = mAdapter.getRootLayout(mViewPager.getCurrentItem());
            if (currentCard != null) {
                currentCard.animate().scaleY(1.1f);
                currentCard.animate().scaleX(1.1f);
            }
        }

        mScalingEnabled = enable;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int realCurrentPosition;
        int nextPosition;
        float baseElevation = mAdapter.getBaseElevation();
        float realOffset;
        boolean goingLeft = mLastOffset > positionOffset;

        // If we're going backwards, onPageScrolled receives the last position
        // instead of the current one
        if (goingLeft) {
            realCurrentPosition = position + 1;
            nextPosition = position;
            realOffset = 1 - positionOffset;
        } else {
            nextPosition = position + 1;
            realCurrentPosition = position;
            realOffset = positionOffset;
        }

        // Avoid crash on overscroll
        if (nextPosition > mAdapter.getCount() - 1
                || realCurrentPosition > mAdapter.getCount() - 1) {
            return;
        }

        RelativeLayout relativeLayout =  mAdapter.getRootLayout(realCurrentPosition);

        // This might be null if a fragment is being used
        // and the views weren't created yet
        if (relativeLayout != null) {
            if (mScalingEnabled) {
                relativeLayout.setScaleX((float) (1 + 0.1 * (1 - realOffset)));
                relativeLayout.setScaleY((float) (1 + 0.1 * (1 - realOffset)));
            }
            /*relativeLayout.setCardElevation((baseElevation + baseElevation
             * (CardAdapter.MAX_ELEVATION_FACTOR - 1) * (1 - realOffset)));*/
        }

        RelativeLayout nextLayout = mAdapter.getRootLayout(nextPosition);

        // We might be scrolling fast enough so that the next (or previous) card
        // was already destroyed or a fragment might not have been created yet
        if (nextLayout != null) {
            if (mScalingEnabled) {
                nextLayout.setScaleX((float) (1 + 0.1 * (realOffset)));
                nextLayout.setScaleY((float) (1 + 0.1 * (realOffset)));
            }
                /*
                nextCard.setCardElevation((baseElevation + baseElevation
                        * (CardAdapter.MAX_ELEVATION_FACTOR - 1) * (realOffset)));*/
        }

        mLastOffset = positionOffset;
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void transformPage(@NonNull View page, float position) {

    }
}
