package fr.eni.android.ch10_viewpagerexemple;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.CombinedChart;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import fr.eni.android.ch10_viewpagerexemple.conexion.ComunationTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class chart extends Fragment {

    private CombinedChart combinedChart;
    private ComunationTask com;

    public chart() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chart, container, false);
        combinedChart = view.findViewById(R.id.combinedChart);

        // Crea el objeto Date
        Date date = new Date();
        String fecha_formateada = new SimpleDateFormat("yyyyMMdd").format(date);


        com = new ComunationTask(combinedChart);
        String Url = "http://www.omie.es/datosPub/marginalpdbc/marginalpdbc_";
        Url = Url + fecha_formateada + ".1";

        com.execute(Url);
        return view;
    }

}
