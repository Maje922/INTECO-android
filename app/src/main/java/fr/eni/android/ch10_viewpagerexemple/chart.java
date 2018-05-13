package fr.eni.android.ch10_viewpagerexemple;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import fr.eni.android.ch10_viewpagerexemple.conexion.ComunationTask;


/**
 *
 */
public class Chart extends Fragment {

    protected LineChart lineChart;
    private ComunationTask com;
    private Date date = new Date();

    public Chart() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chart, container, false);

        //recuperación de los elementos del view
        lineChart = view.findViewById(R.id.combinedChart);

        //obtencion de la fecha acual
        date = setCurrentDate();

        //detectamos si ay conexion
        if(isOnlineNet()){
            //generacion de la grafica con ComunationTask
            setChart(date);
        }
        else {

            Toast.makeText(getActivity(),"no hay conexion",Toast.LENGTH_LONG).show();
        }

        return view;
    }

    //devuelve la fecha actual
    private Date setCurrentDate(){

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendar.DAY_OF_YEAR,1);

        return calendar.getTime();
    }

    //se obtiene la fecha en los dos formatos necesarios
    protected String setDateURL(Date date){
        return new SimpleDateFormat("yyyyMMdd").format(date);
    }


    protected String setDateFormat(Date date) {
        return new SimpleDateFormat("yyyy/MM/dd").format(date);
    }

    //generacion de la grafica con ComunationTask
    protected void setChart(Date date){
        //obtencion de la fecha en sus formatos
        String fecha = setDateURL(date);
        String fechaformat = setDateFormat(date);

        //generacion de la gráfica
        com = new ComunationTask(lineChart,fechaformat);
        String Url = "http://www.omie.es/datosPub/marginalpdbc/marginalpdbc_";
        Url = Url + fecha + ".1";   //fecha +1 para comprobar si esta la del dia siguiente
        com.execute(Url);
    }

    //metodo para detectar si hay conexion a internet
    protected Boolean isOnlineNet() {

        try {
            Process p = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.es");

            int val           = p.waitFor();
            boolean reachable = (val == 0);
            return reachable;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
}
