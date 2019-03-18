package com.example.assignment1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RouteDetailsFragment_land extends Fragment {

    TextView name, details;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.route_details_fragment, container, false);
        name = (TextView)view.findViewById(R.id.Name);
        details = (TextView)view.findViewById(R.id.Location);

        return view;
    }

    public void change(String inName, String inLocation) {
        name.setText(inName);
        details.setText(inLocation);
    }
}
