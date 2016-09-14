package com.nathaniel.searchbox.view;

import android.os.Bundle;

import com.nathaniel.searchbox.R;
import com.nathaniel.searchbox.view.base.BaseActivity;
import com.nathaniel.searchbox.view.searchbox.SearchBoxFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().add(
                R.id.container, new SearchBoxFragment(), SearchBoxFragment.class.getSimpleName()).commit();
    }
}