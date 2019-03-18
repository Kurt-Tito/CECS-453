package com.example.assignment1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

public class RouteListActivity extends AppCompatActivity {

    public static boolean isFrag1 = true;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_list);

        Fragment fragment;

        //Fragment fragment1 = new RouteListFragment();
        //Fragment fragment2 = new RouteDetailsFragment();

        //fragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);

        fragment = new RouteListFragment();

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
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
}
