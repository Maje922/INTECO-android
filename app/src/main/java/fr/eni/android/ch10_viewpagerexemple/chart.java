package fr.eni.android.ch10_viewpagerexemple;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.LineChart;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import fr.eni.android.ch10_viewpagerexemple.conexion.ComunationTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class chart extends Fragment {

    private LineChart lineChart;
    private ComunationTask com;

    public chart() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chart, container, false);
        lineChart = view.findViewById(R.id.combinedChart);


        // Crea el objeto Date
        Date date = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendar.DAY_OF_YEAR,1);
        date = calendar.getTime();
        String fecha = new SimpleDateFormat("yyyyMMdd").format(date);
        String fechaformat = new SimpleDateFormat("yyyy/MM/dd").format(date);
        TextView tv = view.findViewById(R.id.textView);
        com = new ComunationTask(lineChart,fechaformat);
        String Url = "http://www.omie.es/datosPub/marginalpdbc/marginalpdbc_";
        Url = Url + fecha + ".1";

        com.execute(Url);
        return view;
    }

}
