package fr.eni.android.ch10_viewpagerexemple;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;

import java.text.SimpleDateFormat;
import java.util.Date;

import fr.eni.android.ch10_viewpagerexemple.conexion.ComunationTask;
import fr.eni.android.ch10_viewpagerexemple.conexion.ComunationTaskMonth;


/**
 * A simple {@link Fragment} subclass.
 */
public class MonthlyChart extends SearchChart {

    private ComunationTaskMonth com;
    private LineChart lineChart;
    public MonthlyChart() {
        super();
        // Required empty public constructor
    }


    public static Fragment newInstance(Date fecha){
       MonthlyChart chart = new MonthlyChart();
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
        View view =inflater.inflate(R.layout.fragment_monthly_chart, container, false);
        //lineChart = view.findViewById(R.id.searchMonthlyChart);
        lineChart = view.findViewById(R.id.searchMonthlyChart);
        return view;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //recuperamos lineChart del view


        if(isOnlineNet()){
            //generacion de la grafica con ComunationTask
            setChart(date);
        }
        else {

            Toast.makeText(getActivity(),"no hay conexion",Toast.LENGTH_LONG).show();
        }


    }


    protected void setChart(Date date){
        //obtencion de la fecha en sus formatos
        String fecha = new SimpleDateFormat("yyyyMM").format(date);;
        String fechaformat = setDateFormat(date);

        //generacion de la gráfica
        com = new ComunationTaskMonth(lineChart,fechaformat);
        String Url = "http://www.omie.es/datosPub/marginalpdbc/marginalpdbc_";
        Url = Url + fecha ;   //fecha +1 para comprobar si esta la del dia siguiente
        com.execute(Url);
    }



}
