package com.example.watchman;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    Fragment fragment;
    FragmentManager fm;
    FragmentTransaction ft;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_tracking:
                    fragment = new fragMain();
                    fm = getFragmentManager();
                    ft = fm.beginTransaction();
                    ft.replace(R.id.fragment_place, fragment);
                    ft.commit();
                    return true;
                case R.id.navigation_lastroute:
                    fragment = new fragLastRoute();
                    fm = getFragmentManager();
                    ft = fm.beginTransaction();
                    ft.replace(R.id.fragment_place, fragment);
                    ft.commit();                    return true;
                case R.id.navigation_settings:
                    fragment = new fragSettings();
                    fm = getFragmentManager();
                    ft = fm.beginTransaction();
                    ft.replace(R.id.fragment_place, fragment);
                    ft.commit();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment = new fragMain();
        fm = getFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.fragment_place, fragment);
        ft.commit();

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
//hey ben
}//hi
//hi again
