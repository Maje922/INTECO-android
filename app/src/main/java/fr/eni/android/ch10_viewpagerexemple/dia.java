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
import java.util.GregorianCalendar;


/**
 * en este fragment se selecciona un dia para que muestre la grafica de ese día
 */
public class dia extends Transiccion implements View.OnClickListener{

    private CalendarView fecha;
    private Spinner dia;
    private Spinner mes;
    private Spinner ano;
    private Button enviar;
    private Date diaSeleccion;
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


        enviar.setOnClickListener(this);



        //valores de spinners
        //Dias

        //Long fechaL = fecha.getDate();



        //meses
        adapter = ArrayAdapter.createFromResource(getActivity(), R.array.meses, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mes.setAdapter(adapter);

        //años

        String[] anos = aniosSpinner();
        ano.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, anos));


        //detectamos un cambio en el calendario
        fecha.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                diaSeleccion = new GregorianCalendar(year,month,dayOfMonth).getTime();

            }
        });


        return view;
    }

    @Override
    public void onClick(View v) {

        changeFragment(R.id.cosa,SearchChart.newInstance(diaSeleccion));
    }

    //calculo del array para los años, desde el actual hasta el 2002
    private String[] aniosSpinner(){
        Calendar c = Calendar.getInstance();
        String[] anos;
        int anoActual = Calendar.getInstance().get(Calendar.YEAR);
        anos = new String[anoActual - 2002 + 1];

        for (int i = 0; i < anos.length; i++){
            anos[i] = String.valueOf(anoActual-i);
        }
        return anos;
    }
}
