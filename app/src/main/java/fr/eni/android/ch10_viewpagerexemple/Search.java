package fr.eni.android.ch10_viewpagerexemple;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 */
public class Search extends Transiccion implements View.OnClickListener{
    private Fragment diaFrag;
    private Fragment mesFrag;

    private Fragment[] fragments;
    private Button dia, mes;

    public Search() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        diaFrag = new dia();
        mesFrag = new BusquedaMes();
       // mesFrag = SearchChart.newInstance(new GregorianCalendar(2018, 0, 1).getTime());

        dia = view.findViewById(R.id.Bdia);
        mes = view.findViewById(R.id.Bmes);

        dia.setOnClickListener(this);
        mes.setOnClickListener(this);

        changeFragment(R.id.cosa,diaFrag);
        botonSel(dia);
        botonNotSel(mes);



        return view;
    }


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

    private void botonNotSel(Button b){
        b.setBackgroundColor(Color.parseColor("#f8f8f8"));
        b.setTextColor(Color.parseColor("#21447F"));
    }

    private void botonSel(Button b){
        b.setBackgroundColor(Color.parseColor("#21447F"));
        b.setTextColor(Color.parseColor("#f8f8f8"));
    }

}
