package fr.eni.android.ch10_viewpagerexemple;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchChart extends Chart {


    public static Date date;
    public static String fechaString ="fecha";


    public SearchChart() {
        // Required empty public constructor
    }

    public static Fragment newInstance(Date fecha){
        SearchChart chart = new SearchChart();
        Bundle bdl = new Bundle(1);
        bdl.putLong(fechaString,fecha.getTime());
        chart.setArguments(bdl);
        return chart;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Long DateLong = getArguments().getLong(fechaString); //recuperamos la fecha de busqueda para la grafica
            date = new Date(DateLong);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_search_chart, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //recuperamos lineChart del view
        lineChart = view.findViewById(R.id.searchChart);


        setChart(date); //generamos la gráfica

    }
}
