package com.ibrhmdurna.idgames.cards;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.ibrhmdurna.idgames.R;
import com.ibrhmdurna.idgames.util.adapter.CardFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoryCardFragment extends CardFragment {

    private RelativeLayout rootLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_story_card, container, false);
        rootLayout = view.findViewById(R.id.root_layout);
        return view;
    }

    @Override
    public RelativeLayout getRootLayout() {
        return rootLayout;
    }
}
