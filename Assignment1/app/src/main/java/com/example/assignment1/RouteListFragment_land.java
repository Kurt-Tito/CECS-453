package com.example.assignment1;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class RouteListFragment_land extends ListFragment {

    String[] routes = new String[] {"Route 1", "Route 2", "Route 3", "Route 4", "Route 5"};
    String[] details = new String[] {"CSULB \n"
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

        View view = inflater.inflate(R.layout.route_list_fragment, container, false);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, routes);
        setListAdapter(adapter);
        return view;
    }

    @Override
    public void onListItemClick(ListView I, View v, int position, long id)
    {
        Toast.makeText(getActivity(), "Route " +(position+1) +" selected", Toast.LENGTH_SHORT).show();

        RouteDetailsFragment_land txt = (RouteDetailsFragment_land) getFragmentManager().findFragmentById(R.id.fragment2);
        txt.change("Name: "+ routes[position],"Location : "+ details[position]);
        getListView().setSelector(android.R.color.holo_blue_dark);
    }
}
