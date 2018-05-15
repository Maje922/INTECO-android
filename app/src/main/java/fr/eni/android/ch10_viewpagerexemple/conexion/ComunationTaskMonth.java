package fr.eni.android.ch10_viewpagerexemple.conexion;

import android.view.View;

import com.github.mikephil.charting.data.BarEntry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import fr.eni.android.ch10_viewpagerexemple.Month;

public class ComunationTaskMonth extends ComunationTask {

    public ComunationTaskMonth(View view, String f) {
        super(view, f);
    }
    @Override
    protected String doInBackground(String... params) {
        String cadena= new String();
        String[] aux = fecha.split("/");
        fecha = aux[0] + "/" + aux[1];      //guardamos la fecha solo como año y mes puesto que el dia no nos interesa
        GregorianCalendar calendar = new GregorianCalendar(Integer.parseInt(aux[0]),Integer.parseInt(aux[1])-1, 1);

        int dias = diasMes(calendar);
        for(int i = 1; i <= dias; i++){
            cadena += i;
            cadena += ";";

            String urlsdf= params[0];
            if(i<10){
                urlsdf += "0";
            }
            urlsdf += String.valueOf(i)+".1";
            cadena += getMediaDia(obtenerDatosDia(urlsdf));
            cadena += ";";
        }
        return cadena;
    }
    @Override
    protected void onPostExecute(String result) {
            entradas = getData(result);
            CrearGrafica();
    }

    @Override
    protected ArrayList getData(String result) {
        entradas = new ArrayList<>();
        String[] array = result.split(";");

        for (int i = 0; i < array.length; i += 2) {
            BarEntry entrada = new BarEntry(Float.parseFloat(array[i]), Float.parseFloat(array[i+1]));
            entradas.add(entrada);
        }
        return entradas;
    }

    private int diasMes( GregorianCalendar fecha){
        int totalDias;
        GregorianCalendar cosa = new GregorianCalendar();
        int actualMes = cosa.get(GregorianCalendar.MONTH);
        int fechaMes = fecha.get(GregorianCalendar.MONTH);
        if(fechaMes == actualMes){
            totalDias = cosa.get(GregorianCalendar.DAY_OF_MONTH);
        }
        else{
            totalDias =  fecha.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
        return totalDias;
    }



}
