package com.example.jewelryecommerceapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.jewelryecommerceapp.R;

import java.util.ArrayList;

public class SearchFragment extends Fragment {
    private View view;
    private ImageView btnFilter;
    private FrameLayout filterFrame;
    private Fragment filterFragment, categoryFragment,a;
    public static FrameLayout searchFrame;
    private FragmentTransaction transaction;
    public static EditText etSearch;


    public static int selectedPrice = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_search, container, false);

        // use for filter

        //add category fragment
        transaction = getChildFragmentManager().beginTransaction();
        searchFrame = view.findViewById(R.id.search_frame);
        categoryFragment = new CategoryFragment();
        transaction.add(R.id.search_frame, categoryFragment).commit();

        reference();
        getEvent();

        return view;
    }

    private void getEvent() {

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

                filterFragment = getChildFragmentManager().findFragmentByTag(FilterFragment.TAG);

                if (filterFragment != null) {
                    if (filterFragment.isVisible()) {
                        transaction.replace(R.id.filter_frame, new Fragment()).addToBackStack(FilterFragment.TAG);
                        transaction.commit();
                        searchFrame.setClickable(true);
                    } else {
                        getChildFragmentManager().popBackStack();
                        searchFrame.setClickable(false);
                    }
                } else {
                    filterFragment = new FilterFragment();
                    transaction.replace(R.id.filter_frame, filterFragment, FilterFragment.TAG);
                    transaction.commit();
                    searchFrame.setClickable(false);
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                filterFragment = getChildFragmentManager().findFragmentByTag(FilterFragment.TAG);
                if (filterFragment != null) {
                    if (filterFragment.isVisible()) {
                        transaction.replace(R.id.filter_frame, new Fragment()).addToBackStack(FilterFragment.TAG);
                        transaction.commit();
                        searchFrame.setClickable(true);
                    }
                }
            }
        });
    }

    private void reference() {
        btnFilter = view.findViewById(R.id.btnFilter);
        filterFrame = view.findViewById(R.id.filter_frame);
        etSearch = view.findViewById(R.id.searchFragment_etSearch);
    }
}
