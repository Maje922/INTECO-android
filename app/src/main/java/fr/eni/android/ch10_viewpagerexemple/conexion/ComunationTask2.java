package fr.eni.android.ch10_viewpagerexemple.conexion;

import android.graphics.Color;
import android.os.AsyncTask;
import android.view.View;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import android.widget.Toast;

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

import fr.eni.android.ch10_viewpagerexemple.CustomValueFormatter;
import fr.eni.android.ch10_viewpagerexemple.Month;

/**
 * Created by Usuario on 04/05/2018.
 */

public class ComunationTask2 extends AsyncTask<String, Void, String> {

    protected LineChart lineChart;
    protected ArrayList<BarEntry> entradas;
    protected String fecha;
    protected boolean mensual;


    public ComunationTask2(View view,String f, boolean mens) {
        this.lineChart = (LineChart) view;
        this.fecha = f;
        this.mensual = mens;
    }

    @Override
    protected String doInBackground(String... params) {
        /*
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
        * */
        StringBuilder cadena = new StringBuilder();
        try {
            if(!mensual) {
                URL url = new URL(params[0]);
                URLConnection con = url.openConnection();
                //recuperacion de la respuesta JSON
                String s;
                InputStream is = con.getInputStream();
                //utilizamos UTF-8 para que interprete
                //correctamente las ñ y acentos
                BufferedReader bf = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
                while ((s = bf.readLine()) != null) {
                    if (s.equals("MARGINALPDBC;") || s.equals("*")) {

                    } else {
                        cadena.append(s);
                    }
                }
            }
            else{
                String cadenaMes = new String();
                String[] aux = fecha.split("/");
                int mes =  Integer.parseInt(aux[1])-1;
                Month[] meses = Month.values();
                int dias = meses[mes].maximoDias();
                for(int i = 1; i <= dias; i++){
                    cadenaMes += i;
                    cadenaMes += ";";

                    String urlsdf= params[0];
                    if(i<10){
                        urlsdf += "0";
                    }
                    urlsdf += String.valueOf(i)+".1";
                    cadenaMes += getMediaDia(mediaDia(urlsdf));
                    cadenaMes += ";";
                }
                return cadenaMes;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return cadena.toString();
    }

    @Override
    protected void onPostExecute(String result) {
        if(!mensual) {
            if (result.charAt(0) != '<') {
                entradas = getData(result);
                CrearGrafica();
            } else {
                Date date = new Date();
                String fecha = new SimpleDateFormat("yyyyMMdd").format(date);
                String fechaformat = new SimpleDateFormat("yyyy/MM/dd").format(date);
                date.toString();
                ComunationTask ct = new ComunationTask(lineChart, fechaformat);
                String Url = "http://www.omie.es/datosPub/marginalpdbc/marginalpdbc_";
                Url = Url + fecha + ".1";

                ct.execute(Url);
            }

        }
        else{
            entradas = getData(result);
            CrearGrafica();
        }

    }
    protected void CrearGrafica(){
try{
        lineChart.setData(lineData());
        lineChart.animateY(3000, Easing.EasingOption.EaseInOutExpo);
        lineChart.getLegend().setEnabled(false);
        lineChart.getDescription().setEnabled(true);
        lineChart.getDescription().setText(fecha);
        lineChart.getDescription().setTextSize(15f);
        Xaxis(lineChart.getXAxis());
        YaxisLeft(lineChart.getAxisLeft());
        YaxisRight(lineChart.getAxisRight());
        }catch(Exception e){
    System.out.println(e.getMessage());
}
    }

    protected void Xaxis(XAxis xAxis){
        xAxis.setGranularityEnabled(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setAxisMinimum(0.45f);

    }
    protected void YaxisLeft(YAxis yAxis){
        // yAxis.setSpaceBottom(0);
        yAxis.setSpaceTop(20);
        yAxis.setGranularityEnabled(true);
        yAxis.setGranularity(5f);
        yAxis.setAxisMinimum(25);
        yAxis.setValueFormatter(new CustomValueFormatter());


    }
    private void YaxisRight(YAxis yAxis){
        yAxis.setEnabled(false);
    }


    protected LineData lineData(){
         LineData lineData = null;
        try{
        ArrayList<Entry> line = new ArrayList<>();
        line.addAll(entradas);

        LineDataSet lineDataSet = new LineDataSet(line, "Brand 2");
        lineDataSet.setColors(Color.parseColor("#36b4fc"));
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lineDataSet.setDrawFilled(true);
        lineDataSet.setValueTextSize(10);
        lineData = new LineData(lineDataSet);


        return lineData;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return lineData;

}



    }
    /* datos de barras
    private BarData barData(){
        ArrayList<BarEntry> group1 = new ArrayList<>();
        group1.addAll(entradas);

        BarDataSet barDataSet = new BarDataSet(group1, "Brand 1");
        //barDataSet.setColor(Color.rgb(0, 155, 0));

        barDataSet.setStackLabels(getXAxisValues());  // no funciona
        barDataSet.setColors(Color.parseColor("#3062b8"));

        BarData barData = new BarData(barDataSet);

        barData.setValueTextSize(10);

        return barData;
    }
    */

    protected ArrayList getData(String result) {
        entradas = new ArrayList<>();
        String[] array = result.split(";");

        if(!mensual) {
            for (int x = 6; x <= array.length; x += 6) {
                BarEntry entrada = new BarEntry(Float.parseFloat(array[x - 3]), Float.parseFloat(array[x - 2]));
                entradas.add(entrada);
            }
        }
        else{
            for (int i = 0; i < array.length; i += 2) {
                BarEntry entrada = new BarEntry(Float.parseFloat(array[i]), Float.parseFloat(array[i+1]));
                entradas.add(entrada);
            }
        }
        return entradas;
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
}
