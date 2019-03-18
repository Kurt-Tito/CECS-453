package com.example.assignment1;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

public class RouteListActivity extends AppCompatActivity {

    public static boolean isFrag1 = true;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    //Fragment fragment1 = new RouteListFragment();
    //Fragment fragment2 = new RouteDetailsFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_list);

        Fragment fragment;

        //fragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);

        fragment = new RouteListFragment();

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment);

        //fragmentTransaction.replace(R.id.fragmentContainer1, fragment1);
        //fragmentTransaction.replace(R.id.fragmentContainer2, fragment2);

        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        /*
        RouteListFragment routeListFragment = new RouteListFragment();
        //RouteDetailsFragment routeDetailsFragment = new RouteDetailsFragment();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.add(R.id.fragment1, routeListFragment);
        ft.commit();
        */

    }

    @Override
    public void onBackPressed()
    {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragmentContainer);

        if (fragmentManager.getBackStackEntryCount() == 1)
        {
            this.finish();
        }
        else
        {
            fragmentManager.popBackStack();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Intent i = new Intent (RouteListActivity.this, RouteListActivity_land.class);
            startActivity(i);
            finish();
        }
    }
}
