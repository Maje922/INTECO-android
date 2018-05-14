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
    //devuelve los precios de un dia
    private String datosDia(String params){
        StringBuilder cadena = new StringBuilder();

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

        return cadena.toString();
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
