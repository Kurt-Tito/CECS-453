package com.example.assignment1;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class RouteListFragment extends ListFragment {

    String[] routes = new String[] {"Route 1", "Route 2", "Route 3", "Route 4", "Route 5"};
    String[] details = new String[] {"California State University Long Beach \n"
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
        RouteDetailsFragment deets_frag = (RouteDetailsFragment)getFragmentManager().findFragmentById(R.id.fragment2);
        deets_frag.change("Name:" + routes[position], "Location: " + details[position]);
        getListView().setSelector(android.R.color.holo_blue_dark);
    }
}
