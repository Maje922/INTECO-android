package fr.eni.android.ch10_viewpagerexemple.conexion;

import android.graphics.Color;
import android.os.AsyncTask;
import android.view.View;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

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

/**
 * Created by Usuario on 04/05/2018.
 */

public class ComunationTask extends AsyncTask<String, Void, String> {

    private CombinedChart combinedChart;
    private ArrayList<BarEntry> entradas;
    private String fecha;


    public ComunationTask(View view,String f) {
        this.combinedChart = (CombinedChart) view;
        this.fecha = f;
    }

    @Override
    protected String doInBackground(String... params) {
        StringBuilder cadena = new StringBuilder();
        try {


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

                } else{
                    cadena.append(s);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return cadena.toString();
    }

    @Override
    protected void onPostExecute(String result) {

        if(result.charAt(0) != '<') {
            entradas = new ArrayList<>();
            String[] array = result.split(";");


            for (int x = 6; x <= array.length; x += 6) {
                BarEntry entrada = new BarEntry(Float.parseFloat(array[x - 3]), Float.parseFloat(array[x - 2]));
                entradas.add(entrada);
            }
            CrearGrafica();
        }else {
            Date date = new Date();
            String fecha = new SimpleDateFormat("yyyyMMdd").format(date);
            String fechaformat = new SimpleDateFormat("yyyy/MM/dd").format(date);
            date.toString();
            ComunationTask ct = new ComunationTask(combinedChart,fechaformat);
            String Url = "http://www.omie.es/datosPub/marginalpdbc/marginalpdbc_";
            Url = Url + fecha + ".1";

            ct.execute(Url);
        }


    }
    private void CrearGrafica(){
        CombinedData data = new CombinedData();
        //no añado el dataset de la grafica de barras
        //data.setData(barData());
        data.setData(lineData());
        combinedChart.setData(data);
        combinedChart.animateY(3000, Easing.EasingOption.EaseInOutExpo);
        combinedChart.getLegend().setEnabled(false);
        combinedChart.getDescription().setEnabled(true);
        combinedChart.getDescription().setText(fecha);
        combinedChart.getDescription().setTextSize(15f);
        Xaxis(combinedChart.getXAxis());
        YaxisLeft(combinedChart.getAxisLeft());
        YaxisRight(combinedChart.getAxisRight());
    }
    private String[] getXAxisValues(){
        String[] labels = new String[24];
        for (int i = 0;i<24;i++){
            labels[i] = String.valueOf(i+1);
        }

        return labels;
    }

    private void Xaxis(XAxis xAxis){
        xAxis.setGranularityEnabled(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setAxisMinimum(0.45f);

    }
    private void YaxisLeft(YAxis yAxis){
        // yAxis.setSpaceBottom(0);
        yAxis.setSpaceTop(20);
        yAxis.setGranularityEnabled(true);
        yAxis.setGranularity(5f);
        yAxis.setAxisMinimum(0);


    }
    private void YaxisRight(YAxis yAxis){
        yAxis.setEnabled(false);
    }
    private LineData lineData(){
        ArrayList<Entry> line = new ArrayList<>();
        line.addAll(entradas);

        LineDataSet lineDataSet = new LineDataSet(line, "Brand 2");
        lineDataSet.setColors(Color.parseColor("#36b4fc"));
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lineDataSet.setDrawFilled(true);
        lineDataSet.setValueTextSize(10);
        LineData lineData = new LineData(lineDataSet);

        return lineData;

    }
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
}
