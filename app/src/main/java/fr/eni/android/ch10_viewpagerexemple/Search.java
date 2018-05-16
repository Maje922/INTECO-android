package fr.eni.android.ch10_viewpagerexemple;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Search extends Transiccion implements View.OnClickListener{
    private Fragment diaFrag;
    private Fragment mesFrag;

    private Button dia, mes;

    public Search() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        //recuperamos elementos del view
        dia = view.findViewById(R.id.Bdia);
        mes = view.findViewById(R.id.Bmes);

        //inicializamos los fragment
        diaFrag = new dia();
        mesFrag = new BusquedaMes();

        //asignamos eventos a los botones
        dia.setOnClickListener(this);
        mes.setOnClickListener(this);

        //por defecto seleccionamos el fragment de busqueda por dia y las vistas de los botones correspondientes
        changeFragment(R.id.cosa,diaFrag);
        botonSel(dia);
        botonNotSel(mes);



        return view;
    }

    //segun el boton seleccionado se activa un fragment y las vistas correspondientes a los botones
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.Bdia:
                changeFragment(R.id.cosa,diaFrag);
                botonSel(dia);
                botonNotSel(mes);

                break;
            case R.id.Bmes:
                changeFragment(R.id.cosa,mesFrag);
                botonSel(mes);
                botonNotSel(dia);
                break;
        }

    }

    //metodo para cambiar el estilo del boton seleccionado
    private void botonNotSel(Button b){
        b.setBackgroundColor(Color.parseColor("#f8f8f8"));
        b.setTextColor(Color.parseColor("#21447F"));
    }
    //metodo para cambiar el estilo del boton no seleccionado
    private void botonSel(Button b){
        b.setBackgroundColor(Color.parseColor("#21447F"));
        b.setTextColor(Color.parseColor("#f8f8f8"));
    }

}
