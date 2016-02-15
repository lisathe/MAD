package com.example.lisa.animalviewer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AnimalListFragment extends Fragment {

    private static final String IMAGES = "images";
    private static final String NAMES = "names";

    private int[] imageIds;
    private String[] animalNames;

    private ArrayAdapter adapter;
    private ListView listView;
    private ListModel listModels;

    public AnimalListFragment newInstance(int[] imageIds, String[] nameStrings) {
        Bundle args = new Bundle();
        args.putIntArray(IMAGES, imageIds);
        args.putStringArray(NAMES, nameStrings);
        AnimalListFragment fragment = new AnimalListFragment();
        fragment.setArguments(args);
        return fragment;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_animal_list, container, false);
        List<ListModel> listModels = new ArrayList<>();

        for (int i = 0; i < imageIds.length; i++) {
            listModels.add(new ListModel(imageIds[i], animalNames[i]));
        }
        listView = (ListView) view.findViewById(R.id.listview);
        adapter = new ListAdapter(getActivity(), R.layout.animal_list_item, listModels);
        listView.setAdapter(adapter);

        return view;

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            imageIds = getArguments().getIntArray(IMAGES);
            animalNames = getArguments().getStringArray(NAMES);



        }
    }
}
