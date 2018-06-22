package com.example.vartikajain.moviesearch.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.vartikajain.moviesearch.Api.ApiService;
import com.example.vartikajain.moviesearch.BuildConfig;
import com.example.vartikajain.moviesearch.R;
import com.example.vartikajain.moviesearch.interfaces.ActivityConsts;
import com.example.vartikajain.moviesearch.models.MovieResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverFragment extends Fragment {


    public DiscoverFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_discover, container, false);
        Button btnSubmit= (Button) rootView.findViewById(R.id.btnSubmit);
        final Spinner spinnerYear= (Spinner) rootView.findViewById(R.id.spinnerYear);
        final Spinner spinnerGenre= (Spinner) rootView.findViewById(R.id.spinnerGenre);
        final Spinner spinnerSortBy= (Spinner) rootView.findViewById(R.id.spinnerSortBy);
        final Spinner spinnerAdult= (Spinner) rootView.findViewById(R.id.spinnerAdult);

        ArrayAdapter<CharSequence> yearAdapter=ArrayAdapter.createFromResource
                (getActivity(),R.array.year,android.R.layout.simple_spinner_item);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerYear.setAdapter(yearAdapter);

        ArrayAdapter<CharSequence> genreAdapter=ArrayAdapter.createFromResource
                (getActivity(),R.array.genre,android.R.layout.simple_spinner_item);
        genreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGenre.setAdapter(genreAdapter);

        ArrayAdapter<CharSequence> sortbyAdapter=ArrayAdapter.createFromResource
                (getActivity(),R.array.sort_by,android.R.layout.simple_spinner_item);
        sortbyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSortBy.setAdapter(sortbyAdapter);

        ArrayAdapter<CharSequence> adultAdapter=ArrayAdapter.createFromResource
                (getActivity(),R.array.adult,android.R.layout.simple_spinner_item);
        adultAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAdult.setAdapter(adultAdapter);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String year=spinnerYear.getSelectedItem().toString();
                final Integer primary_release_year= !year.equals("Year")?Integer.parseInt(year) : 0;
                String genre=spinnerGenre.getSelectedItem().toString();
                String sort_by=spinnerSortBy.getSelectedItem().toString();
                String adult=spinnerAdult.getSelectedItem().toString();
                final Boolean include_adult=adult.equals("true");

                Bundle bundle=new Bundle();
                searchFragment searchFragment=new searchFragment();
                FragmentManager fragmentManager=getFragmentManager();
                bundle.putString("sort_by",sort_by);
                bundle.putString("genre",genre);
                bundle.putInt("primary_release_year",primary_release_year);
                bundle.putBoolean("include_adult",include_adult);
                bundle.putString("Calling_Activity", String.valueOf(ActivityConsts.DiscoverFragment));
                searchFragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.flFragContainer,searchFragment)
                        .commit();
            }
        });
        return rootView;
    }
}
