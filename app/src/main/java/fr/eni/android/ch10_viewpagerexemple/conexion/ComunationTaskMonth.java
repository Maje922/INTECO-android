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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import fr.eni.android.ch10_viewpagerexemple.Month;

public class ComunationTaskMonth extends ComunationTask {


    /*
     protected LineChart lineChart;
    protected ArrayList<BarEntry> entradas;
    protected String fecha;
    * */
    public ComunationTaskMonth(View view, String f) {
        super(view, f);
    }
    @Override
    protected String doInBackground(String... params) {
        String cadena= new String();
        String[] aux = fecha.split("/");
        int mes =  Integer.parseInt(aux[1])-1;
        Month[] meses = Month.values();
        int dias = meses[mes].maximoDias();
        for(int i = 1; i <= dias; i++){
            cadena += i;
            cadena += ";";

            String urlsdf= params[0];
            if(i<10){
                urlsdf += "0";
            }
            urlsdf += String.valueOf(i)+".1";
            cadena += getMediaDia(mediaDia(urlsdf));
            cadena += ";";
        }
        return cadena;
    }
    @Override
    protected void onPostExecute(String result) {
            entradas = getData(result);
            CrearGrafica();

    }
    //devuelve los precios de un dia
    private String mediaDia(String params){
        StringBuilder cadena = new StringBuilder();
        String media;
        try {
            URL url = new URL(params);
            URLConnection con = url.openConnection();
            //recuperacion de la respuesta JSON
            String s;
            InputStream is = con.getInputStream();
            //utilizamos UTF-8 para que interprete
            //correctamente las ñ y acentos
            BufferedReader bf = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            while ((s = bf.readLine()) != null) {
                if (s.equals("MARGINALPDBC;") || s.equals("*")) {

                } else{
                    cadena.append(s);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        //entradas = getData(cadena.toString());
        return cadena.toString();
    }

    //devuelve la media de precio de un dia
    protected float getMediaDia(String result) {
        String[] array = result.split(";");
        int horas = 0;
        float suma = 0;
        for (int i = 6; i <= array.length; i += 6) {
            horas ++;
            suma = Float.parseFloat(array[i - 2]);
        }
        return (suma/horas);
    }

    protected ArrayList getData(String result) {
        entradas = new ArrayList<>();
        String[] array = result.split(";");

        for (int i = 0; i < array.length; i += 2) {
            BarEntry entrada = new BarEntry(Float.parseFloat(array[i]), Float.parseFloat(array[i+1]));
            entradas.add(entrada);
        }
        return entradas;
    }

}
