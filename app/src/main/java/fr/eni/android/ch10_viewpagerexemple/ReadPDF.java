package fr.eni.android.ch10_viewpagerexemple;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class ReadPDF extends Transiccion implements View.OnClickListener {

    private View[] botones = new View[7];

    private FloatingActionButton buttonAtras;

    public ReadPDF() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_read_pd, container, false);

        //Se crea un array de botones y se recuperan del view
        botones[0] = view.findViewById(R.id.button);
        botones[1] = view.findViewById(R.id.button2);
        botones[2] = view.findViewById(R.id.button3);
        botones[3] = view.findViewById(R.id.button4);
        botones[4] = view.findViewById(R.id.button5);
        botones[5] = view.findViewById(R.id.button6);
        botones[6] = view.findViewById(R.id.returnBB);

        //se recorre el arrray de botones para asignarles el evento setOnClickListener
        for (int i = 0; i < botones.length;i++) {
            botones[i].setOnClickListener(this);
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        int view = R.id.frag;
        Fragment destino = new Fragment();

        switch (v.getId()) {
            case R.id.button:
                destino = readingPDF.newInstance("PotenciasNormalizadas.pdf");
                break;
            case R.id.button2:
                destino = readingPDF.newInstance("Calendario.pdf");
                break;
            case R.id.button3:
                destino = readingPDF.newInstance("Calendario2.pdf");
                break;
            case R.id.button4:
                destino = readingPDF.newInstance("Calendario3.pdf");
                break;
            case R.id.button5:
                destino = readingPDF.newInstance("CURVAICP.pdf");
                break;
            case R.id.button6:
                destino = readingPDF.newInstance( "DHsTarifas.pdf");
                break;
            case R.id.returnBB:
                destino = new info();
                break;
        }

        changeFragment(R.id.frag,destino);
    }




}

