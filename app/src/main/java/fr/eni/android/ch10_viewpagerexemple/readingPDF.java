package fr.eni.android.ch10_viewpagerexemple;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.github.barteksc.pdfviewer.PDFView;



public class readingPDF extends Transiccion implements View.OnClickListener{


    private PDFView pdfView;
    public static String nombreFile;


    public readingPDF() {

    }

    //creamos nueva instancia para poder generar el fragment con un pdf determinado
    public static Fragment newInstance(final String msg) {
        readingPDF f = new readingPDF();
        Bundle bdl = new Bundle(1);
        bdl.putString(nombreFile, msg);
        f.setArguments(bdl);
        return f;

    }

    //recuperamos el valor del string nombreFile
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
           nombreFile = getArguments().getString(nombreFile, "");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reading_pd, container, false);

        //recuperamos pdfView del view
        pdfView = view.findViewById(R.id.pdfView);

        view.findViewById(R.id.returnBP).setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //abrimos el pdf deseado y lo asignamos a la vista
        pdfView.fromAsset(nombreFile).load();
    }

    //cuando se pulse el botón volverá al fragment readPDF
    @Override
    public void onClick(View v) {
        changeFragment(R.id.frag,new ReadPDF());
    }

    public boolean onKeyUp(View v, int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                changeFragment(R.id.frag,new ReadPDF());
                return true;
        }
        return false;
    }


}
