package com.ibrhmdurna.idgames.util.adapter;

import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ibrhmdurna.idgames.util.CardAdapter;

import java.util.ArrayList;

public class SliderPagerAdapter extends FragmentPagerAdapter implements CardAdapter {

    private ArrayList<CardFragment> mFragments;
    private float mBaseElevation;

    public SliderPagerAdapter(@NonNull FragmentManager fm, ArrayList<CardFragment> fragments, float baseElevation) {
        super(fm);
        mFragments = fragments;
        mBaseElevation = baseElevation;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public RelativeLayout getRootLayout(int position) {
        return mFragments.get(position).getRootLayout();
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
