package com.ibrhmdurna.idgames;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ibrhmdurna.idgames.fragments.CategoriesFragment;
import com.ibrhmdurna.idgames.fragments.MainFragment;


public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private MainFragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolsManagement();

        mainFragment = new MainFragment();
        showFragment(mainFragment, "MainFragment");
    }

    private void bottomViewItemSelected(){

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home_item:
                        showFragment(mainFragment, "MainFragment");
                        break;
                    case R.id.categories_item:
                        showFragment(new CategoriesFragment(), "CategoriesFragment");
                        break;
                }
                return true;
            }
        });

        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {

            }
        });
    }

    private void showFragment(Fragment fragment, String tag){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.mainFrame, fragment);
        transaction.addToBackStack(tag);
        transaction.commit();
    }

    private void toolsManagement(){
        buildView();
        bottomViewItemSelected();
    }

    private void buildView(){
        bottomNavigationView = findViewById(R.id.main_bottom_view);
    }
}
