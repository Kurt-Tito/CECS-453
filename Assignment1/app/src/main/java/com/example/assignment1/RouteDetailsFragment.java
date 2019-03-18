package com.example.assignment1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RouteDetailsFragment extends Fragment {

    TextView name, details;

    String[] details_s = new String[] {"CSULB \n"
            +"AM or PM   AM \n"
            +"Start      8:00 \n"
            +"End        9:30",
            "Carson \n"
                    +"AM or PM   PM \n"
                    +"Start      12:00 \n"
                    +"End        1:15 \n",
            "Los Angels \n"
                    +"AM or PM   PM \n"
                    +"Start      3:45 \n"
                    +"End        5:00 \n",
            "Bakersfield \n"
                    +"AM or PM   AM \n"
                    +"Start      8:00 \n"
                    +"End        8:45 \n",
            "Cerritos \n"
                    +"AM or PM  PM \n"
                    +"Start     6:00 \n"
                    +"End       9:00 \n"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.route_details_fragment, container, false);
        name = (TextView)view.findViewById(R.id.Name);
        details = (TextView)view.findViewById(R.id.Location);

        switch (RouteListFragment.pos)
        {
            case 0:
                change("Name:" + "Route 1", "Location: " + details_s[0]);
                break;
            case 1:
                change("Name:" + "Route 2", "Location: " + details_s[1]);
                break;
            case 2:
                change("Name:" + "Route 3", "Location: " + details_s[2]);
                break;
            case 3:
                change("Name:" + "Route 4", "Location: " + details_s[3]);
                break;
            case 4:
                change("Name:" + "Route 5", "Location: " + details_s[4]);
                break;
        }

        return view;
    }

    public void change(String inName, String inLocation) {
        name.setText(inName);
        details.setText(inLocation);
    }
}
