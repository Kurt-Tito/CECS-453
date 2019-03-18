package com.example.assignment1;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class RouteListFragment extends ListFragment {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    public static RouteDetailsFragment fragment = new RouteDetailsFragment();

    public static int pos;

    String[] routes = new String[] {"Route 1", "Route 2", "Route 3", "Route 4", "Route 5"};

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
        //fragment.change("Name:" + routes[position], "Location: " + details[position]);

        //Toast.makeText(getActivity(), "Route " +(position+1) +" selected", Toast.LENGTH_SHORT).show();
        //fragment.change("Name:" + routes[position], "Location: " + details[position]);

        pos = position;

        fragmentManager = getActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.add(R.id.fragmentContainer, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        //fragment.change("Name:" + routes[position], "Location: " + details[position]);

        //RouteDetailsFragment deets_frag = (RouteDetailsFragment)getFragmentManager().findFragmentById(R.id.fragment2);
        //deets_frag.change("Name:" + routes[position], "Location: " + details[position]);
        //getListView().setSelector(android.R.color.holo_blue_dark);
    }
}
