package fr.eni.android.ch10_viewpagerexemple;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchChart extends chart {


    public static Date date;

    public SearchChart() {
        // Required empty public constructor
    }

   /* public Fragment newInstance(Date fecha){

    }*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_chart, container, false);
    }

}
