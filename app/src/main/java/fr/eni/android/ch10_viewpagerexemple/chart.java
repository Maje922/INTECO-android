package fr.eni.android.ch10_viewpagerexemple;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.CombinedChart;

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

        Date date = new Date();
        // Crea el objeto Date
        Calendar calendar = Calendar.getInstance(); // Obtiene una instancia de Calendar
        calendar.setTime(date);

        int year        = calendar.get(Calendar.YEAR);
        int month       = calendar.get(Calendar.MONTH);
        int day         = calendar.get(Calendar.DAY_OF_MONTH);

        com = new ComunationTask(combinedChart);
        String Url = "http://www.omie.es/datosPub/marginalpdbc/marginalpdbc_";
        Url = Url + String.valueOf(year) + "0" + String.valueOf(month + 1) + "0" + String.valueOf(day) + ".1";
        System.out.println(Url);
        com.execute(Url);
        return view;
    }

}
