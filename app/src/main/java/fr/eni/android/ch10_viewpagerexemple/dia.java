package fr.eni.android.ch10_viewpagerexemple;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class dia extends Fragment implements View.OnClickListener{

    private CalendarView fecha;
    private Spinner dia;
    private Spinner mes;
    private Spinner ano;
    private Button enviar;
    public dia() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dia, container, false);
        ArrayAdapter<CharSequence> adapter;

        // recuperación de elementos del view
        fecha = view.findViewById(R.id.calendarView);
        enviar = view.findViewById(R.id.mostrarGrafica);
        dia = view.findViewById(R.id.dia);
        mes = view.findViewById(R.id.mes);
        ano = view.findViewById(R.id.año);


        //valores de spinners
        //Dias

        //Long fechaL = fecha.getDate();



        //meses
        adapter = ArrayAdapter.createFromResource(getActivity(), R.array.meses, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mes.setAdapter(adapter);

        //años
        //calculo del array para los años, desde el actual hasta el 2002
        Calendar c = Calendar.getInstance();
        String[] anos;
        int anoActual = Calendar.getInstance().get(Calendar.YEAR);
        anos = new String[anoActual - 2002 + 1];

        for (int i = 0; i < anos.length; i++){
            anos[i] = String.valueOf(anoActual-i);
        }

        ano.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, anos));


        enviar.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        /*
        CalendarView v = new CalendarView( this );
        v.setOnDateChangeListener( new CalendarView.OnDateChangeListener() {
        public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
            this.calendar = new GregorianCalendar( year, month, dayOfMonth );
            }//met
        });
        * */
        Long fechaL = fecha.getDate();
        Date date = new Date(fechaL);
        Date d = new Date(1220227200);


        Toast.makeText(getActivity(),String.valueOf(date),Toast.LENGTH_LONG).show();
    }

    /*private String[] diasMes(){
        String dias[] ;

        return dias;
    }*/
}
