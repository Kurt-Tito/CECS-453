package com.example.assignment1;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RouteListActivity_land extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_list_land);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Intent i = new Intent(RouteListActivity_land.this, RouteListActivity.class);
            startActivity(i);
            finish();
        }
    }

    @Override
    public void onBackPressed()
    {
        finish();
    }
}
