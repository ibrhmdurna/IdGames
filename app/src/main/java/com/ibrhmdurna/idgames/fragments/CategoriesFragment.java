package com.ibrhmdurna.idgames.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ibrhmdurna.idgames.R;
import com.ibrhmdurna.idgames.models.Category;
import com.ibrhmdurna.idgames.util.adapter.CategoriesAdapter;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriesFragment extends Fragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_categories, container, false);

        toolsManagement();

        return view;
    }

    private void toolsManagement() {
        buildView();
    }

    private void buildView(){

    }
}
