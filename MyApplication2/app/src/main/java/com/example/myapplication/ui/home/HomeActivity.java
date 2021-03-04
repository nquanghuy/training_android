package com.example.myapplication.ui.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.interface_app.OnClickItemListener;
import com.example.myapplication.interface_app.setTextChange;
import com.example.myapplication.ui.home.cart.CartFragment;
import com.example.myapplication.ui.home.detail_product.DetailProductFragment;
import com.example.myapplication.ui.home.shop.ShopFragment;
import com.example.myapplication.ui.home.view_other.ViewOtherFragment;
import com.example.myapplication.ui.login.LoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class HomeActivity extends AppCompatActivity implements setTextChange{

    LinearLayout layoutBottomSheet;
    BottomNavigationView bottomNavigationView;
    private ActionBar toolbar;
    BottomSheetBehavior bottomSheetBehavior;
    DetailProductFragment detailProductFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNavigationView = findViewById(R.id.navigationHome);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        toolbar = getSupportActionBar();

        LinearLayout bottomSheet = findViewById ( R.id.bottom_sheet_behavior_id);
        bottomSheetBehavior =  BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(View view, int i) {
                // do something when state changes
            }

            @Override
            public void onSlide(View view, float v) {
                // do something when slide happens
            }
        });
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);


        Fragment fragment = new ShopFragment();
        loadFragment(fragment);
        SharedPreferences sharedPref = getSharedPreferences("StatusLogin", Context.MODE_PRIVATE);
        boolean active = sharedPref.getBoolean("status", false);
        Toast.makeText(this, "Gia tri SharePreferences" + active, Toast.LENGTH_SHORT).show();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_shop:
                    fragment = new ShopFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_search:
                    fragment = new SearchFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_profile:
//                    SharedPreferences sharedPref = getSharedPreferences("StatusLogin", Context.MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedPref.edit();
//                    editor.putBoolean("status", false);
//                    editor.apply();
//                    boolean active = sharedPref.getBoolean("status", false);
//                    Toast.makeText(HomeActivity.this, "Gia tri moi cua shered "+ active, Toast.LENGTH_SHORT).show();
//                    finish();
//                    startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                                        if (bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                                            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    } else {
                                            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    }

                    return true;
                case R.id.navigation_hot:
                    fragment = new ViewOtherFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_cart:
                    //fragment = new CartFragment();
                    fragment = new DemoUIFragment();
                    loadFragment(fragment);
                    return true;
//                case R.id.navigation_show_bottom_sheet:
//                    if (sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
//                        sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//                    } else {
//                        sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
//                    }
//                    return true;
            }
            return true;
        }
    };

    public void loadFragment(Fragment fragment) {
        // load fragment when change option
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onClickSetText(String str) {
        SubFragment subFragment = (SubFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentSub);
        if (subFragment != null || subFragment.isInLayout()) {
            subFragment.updateText(str);
            Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Khong tim thay, hoac fragment khong hien", Toast.LENGTH_SHORT).show();
        }
    }
}