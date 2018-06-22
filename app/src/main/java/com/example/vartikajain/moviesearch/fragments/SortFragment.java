package com.example.vartikajain.moviesearch.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vartikajain.moviesearch.R;
import com.example.vartikajain.moviesearch.interfaces.ActivityConsts;

/**
 * A simple {@link Fragment} subclass.
 */
public class SortFragment extends Fragment implements View.OnClickListener{


    public SortFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_sort, container, false);
        TextView tvPopA= (TextView) rootView.findViewById(R.id.tvPopA);
        TextView tvPopD= (TextView) rootView.findViewById(R.id.tvPopD);
        TextView tvDateA= (TextView) rootView.findViewById(R.id.tvDateA);
        TextView tvDateD= (TextView) rootView.findViewById(R.id.tvDateD);
        TextView tvVoteA= (TextView) rootView.findViewById(R.id.tvVoteA);
        TextView tvVoteD= (TextView) rootView.findViewById(R.id.tvVoteD);
        TextView tvRevenueA= (TextView) rootView.findViewById(R.id.tvRevenueA);
        TextView tvRevenueD= (TextView) rootView.findViewById(R.id.tvRevenueD);
        tvPopA.setOnClickListener(this);
        tvPopD.setOnClickListener(this);
        tvDateA.setOnClickListener(this);
        tvDateD.setOnClickListener(this);
        tvVoteA.setOnClickListener(this);
        tvVoteD.setOnClickListener(this);
        tvRevenueA.setOnClickListener(this);
        tvRevenueD.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        Bundle bundle=new Bundle();
        searchFragment searchFragment=new searchFragment();
        FragmentManager fragmentManager=getFragmentManager();
        switch (v.getId()){
            case R.id.tvPopA:
                bundle.putString("sort_by","popularity.asc");
                bundle.putString("Calling_Activity", String.valueOf(ActivityConsts.SortFragment));
                searchFragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.flFragContainer,searchFragment)
                        .commit();
                break;
            case R.id.tvPopD:
                bundle.putString("sort_by","popularity.desc");
                bundle.putString("Calling_Activity", String.valueOf(ActivityConsts.SortFragment));
                searchFragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.flFragContainer,searchFragment)
                        .commit();
                break;
            case R.id.tvDateA:
                bundle.putString("sort_by","release_date.asc");
                bundle.putString("Calling_Activity", String.valueOf(ActivityConsts.SortFragment));
                searchFragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.flFragContainer,searchFragment)
                        .commit();
                break;
            case R.id.tvDateD:
                bundle.putString("sort_by","release_date.desc");
                bundle.putString("Calling_Activity", String.valueOf(ActivityConsts.SortFragment));
                searchFragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.flFragContainer,searchFragment)
                        .commit();
                break;
            case R.id.tvVoteA:
                bundle.putString("sort_by","vote_average.asc");
                bundle.putString("Calling_Activity", String.valueOf(ActivityConsts.SortFragment));
                searchFragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.flFragContainer,searchFragment)
                        .commit();
                break;
            case R.id.tvVoteD:
                bundle.putString("sort_by","vote_average.desc");
                bundle.putString("Calling_Activity", String.valueOf(ActivityConsts.SortFragment));
                searchFragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.flFragContainer,searchFragment)
                        .commit();
                break;
            case R.id.tvRevenueA:
                bundle.putString("sort_by","revenue.asc");
                bundle.putString("Calling_Activity", String.valueOf(ActivityConsts.SortFragment));
                searchFragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.flFragContainer,searchFragment)
                        .commit();
                break;
            case R.id.tvRevenueD:
                bundle.putString("sort_by","revenue.desc");
                bundle.putString("Calling_Activity", String.valueOf(ActivityConsts.SortFragment));
                searchFragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.flFragContainer,searchFragment)
                        .commit();
                break;
        }
    }
}
