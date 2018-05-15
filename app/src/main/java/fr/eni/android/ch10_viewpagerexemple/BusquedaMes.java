package fr.eni.android.ch10_viewpagerexemple;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;

import java.util.Calendar;

import java.util.GregorianCalendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class BusquedaMes extends dia implements View.OnClickListener,AdapterView.OnItemSelectedListener  {



    public BusquedaMes() {
        super();
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_busqueda_mes, container, false);

        // recuperación de elementos del view
        fecha = view.findViewById(R.id.calendarView2);
        enviar = view.findViewById(R.id.mostrarGrafica);
        mes = view.findViewById(R.id.mes);
        ano = view.findViewById(R.id.año);



        //asignacion de eventos
        mes.setOnItemSelectedListener(this);
        ano.setOnItemSelectedListener(this);

        enviar.setOnClickListener(this);

        //seleccion de fecha actual
        c = Calendar.getInstance();

        meses = Month.values();

        /*valores de spinners

        A cada spiner se le asigna el valor correspondiente a la fecha actual al crear el fragment
        */

        //años

        String[] anos = aniosSpinner();
        ano.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, anos));


        //meses
       // spinnerMeses(mes);
        int mesActual = Calendar.getInstance().get(Calendar.MONTH);
        mes.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, meses));
        mes.setSelection(mesActual);

        //detectamos un cambio en el calendario
        fecha.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                //actualizamos los spinners
                ano.setSelection(Calendar.getInstance().get(Calendar.YEAR)-year);
                mes.setSelection(month);
                diaSeleccion = new GregorianCalendar(year,month,dayOfMonth).getTime();

            }
        });

        return view;
    }

    @Override
    public void onClick(View v) {

        changeFragment(R.id.cosa,MonthlyChart.newInstance(diaSeleccion));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int a  = Integer.parseInt(ano.getSelectedItem().toString());
        int m  = mes.getSelectedItemPosition();

        diaSeleccion = new GregorianCalendar(a,m,1).getTime();
        fecha.setDate(diaSeleccion.getTime());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
