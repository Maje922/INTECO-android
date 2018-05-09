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

public class ReadPDF extends Fragment implements View.OnClickListener {

    private Button[] botones = new Button[6];
    private View view;
    private PDFView pdfView;
    private String nombreFile = "";
    private FloatingActionButton buttonAtras;

    public ReadPDF() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_read_pd, container, false);

        //Se crea un array de botones que abrirán los PDF
        botones[0] = view.findViewById(R.id.button);
        botones[1] = view.findViewById(R.id.button2);
        botones[2] = view.findViewById(R.id.button3);
        botones[3] = view.findViewById(R.id.button4);
        botones[4] = view.findViewById(R.id.button5);
        botones[5] = view.findViewById(R.id.button6);

        buttonAtras = view.findViewById(R.id.floatingActionButton);
        buttonAtras.setOnClickListener(this);
        pdfView = view.findViewById(R.id.pdfView);

        //se recorre el array, siendo los botones visibles y los PDF(junto al ActionButton) invisibles
        for (int i = 0; i < botones.length;i++) {
            botones[i].setOnClickListener(this);
            botones[i].setVisibility(VISIBLE);
        }
        pdfView.setVisibility(INVISIBLE);

        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                nombreFile = "PotenciasNormalizadas.pdf";
                cargarPDF();
                break;
            case R.id.button2:
                nombreFile = "Calendario.pdf";
                cargarPDF();
                break;
            case R.id.button3:
                nombreFile = "Calendario2.pdf";
                cargarPDF();
                break;
            case R.id.button4:
                nombreFile = "Calendario3.pdf";
                cargarPDF();
                break;
            case R.id.button5:
                nombreFile = "CURVAICP.pdf";
                cargarPDF();
                break;
            case R.id.button6:
                nombreFile = "DHsTarifas.pdf";
                cargarPDF();
                break;
            case R.id.floatingActionButton:
                pushButton();
                break;
        }
    }

    //método para leer los pdf desde Asset.folder y ocultar botones
    public void cargarPDF() {
        pdfView = view.findViewById(R.id.pdfView);
        pdfView.fromAsset(nombreFile).load();
        for (int i = 0; i < botones.length; i++) {
            botones[i].setVisibility(INVISIBLE);
        }

        pdfView.setVisibility(VISIBLE);
    }

    public void pushButton() {
        for (int i = 0; i < botones.length; i++) {
            botones[i].setVisibility(VISIBLE);
        }
        pdfView.setVisibility(View.INVISIBLE);

        //pdfView.setVisibility(pdfView.getVisibility()==View.INVISIBLE?View.INVISIBLE:View.VISIBLE);

    }
}

