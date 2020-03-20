package com.ibrhmdurna.idgames.fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.appbar.AppBarLayout;
import com.ibrhmdurna.idgames.R;
import com.ibrhmdurna.idgames.cards.StoryCardFragment;
import com.ibrhmdurna.idgames.util.Graphics;
import com.ibrhmdurna.idgames.util.ShadowTransformer;
import com.ibrhmdurna.idgames.util.adapter.CardFragment;
import com.ibrhmdurna.idgames.util.adapter.SliderMediumAdapter;
import com.ibrhmdurna.idgames.util.adapter.SliderPagerAdapter;
import com.ibrhmdurna.idgames.util.adapter.SliderStandardAdapter;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private View view;
    private ViewPager storyPager;
    private RecyclerView recommendView;
    private RecyclerView mightLikeView;
    private RecyclerView newGamesView;
    private RecyclerView saleView;

    private AppBarLayout appBarLayout;
    private NestedScrollView mainScrollView;

    private ArrayList<CardFragment> fragments;

    private static Timer timer;
    private static int currentItemIndex = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);

        timer = new Timer();
        fragments = new ArrayList<>();
        toolsManagement();

        return view;
    }

    private void setSalePagerHandler(){
        saleView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        SliderStandardAdapter sliderAdapter = new SliderStandardAdapter();
        saleView.setAdapter(sliderAdapter);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(saleView);
    }

    private void setNewGamesPagerHandler(){
        newGamesView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        SliderStandardAdapter sliderAdapter = new SliderStandardAdapter();
        newGamesView.setAdapter(sliderAdapter);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(newGamesView);
    }

    private void setMightLikePagerHandler(){
        mightLikeView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        SliderMediumAdapter sliderAdapter = new SliderMediumAdapter();
        mightLikeView.setAdapter(sliderAdapter);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(mightLikeView);
    }

    private void setRecommendedPagerHandler(){

        recommendView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        SliderStandardAdapter sliderAdapter = new SliderStandardAdapter();
        recommendView.setAdapter(sliderAdapter);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recommendView);
    }

    private void setStoryPagerHandler(){
        fragments.clear();

        for(int i = 0; i < 15; i++){
            fragments.add(new StoryCardFragment());
        }

        final SliderPagerAdapter sliderAdapter = new SliderPagerAdapter(getChildFragmentManager(), fragments, Graphics.dpToPixels(2, Objects.requireNonNull(getContext())));
        storyPager.setAdapter(sliderAdapter);
        storyPager.setCurrentItem(currentItemIndex);

        storyPager.setPageTransformer(true, new ShadowTransformer(storyPager, sliderAdapter));

        storyPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentItemIndex = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(storyPager.getCurrentItem() < fragments.size() - 1){
                            storyPager.setCurrentItem(storyPager.getCurrentItem() + 1);
                        }else{
                            storyPager.setCurrentItem(0);
                        }
                    }
                });
            }
        }, 4000 ,6000);
    }

    private void scrollListener(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mainScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    if (scrollY > 0){
                        appBarLayout.setElevation(15f);
                    }else{
                        appBarLayout.setElevation(0f);
                    }
                }
            });
        }
    }

    private void toolsManagement(){
        buildView();
        scrollListener();
        setStoryPagerHandler();
        setRecommendedPagerHandler();
        setMightLikePagerHandler();
        setNewGamesPagerHandler();
        setSalePagerHandler();
    }

    private void buildView(){
        appBarLayout = view.findViewById(R.id.appbar);
        mainScrollView = view.findViewById(R.id.main_scroll_view);
        storyPager = view.findViewById(R.id.story_pager);
        recommendView = view.findViewById(R.id.recommend_container);
        mightLikeView = view.findViewById(R.id.games_you_like_container);
        newGamesView = view.findViewById(R.id.new_games_container);
        saleView = view.findViewById(R.id.sale_container);
    }
}
