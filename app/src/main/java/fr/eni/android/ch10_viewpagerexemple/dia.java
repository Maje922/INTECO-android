package fr.eni.android.ch10_viewpagerexemple;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
public class dia extends Transiccion implements View.OnClickListener, AdapterView.OnItemSelectedListener{

    protected CalendarView fecha;
    private Spinner dia;
    protected Spinner mes;
    protected Spinner ano;
    protected Button enviar;
    protected Date diaSeleccion;
    protected Calendar c;
    protected Month[] meses;

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

        //asignacion de eventos
        dia.setOnItemSelectedListener(this);
        mes.setOnItemSelectedListener(this);
        ano.setOnItemSelectedListener(this);

        enviar.setOnClickListener(this);

        //seleccion de fecha actual
        c = Calendar.getInstance();
        diaSeleccion = c.getTime();

        meses = Month.values();



        /*valores de spinners

        A cada spiner se le asigna el valor correspondiente a la fecha actual al crear el fragment
        */

//años

        String[] anos = aniosSpinner();
        ano.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, anos));


        //meses
        int mesActual = Calendar.getInstance().get(Calendar.MONTH);
        mes.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, meses));
        mes.setSelection(mesActual);

        //Dias
        spinnerDias(Calendar.getInstance().get(Calendar.MONTH));


        //detectamos un cambio en el calendario
        fecha.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                //actualizamos los spinners
                ano.setSelection(Calendar.getInstance().get(Calendar.YEAR)-year);
                mes.setSelection(month);
                dia.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, diasSpinner(meses[month].maximoDias())));
                dia.setSelection(dayOfMonth-1);
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
    protected String[] aniosSpinner(){
        Calendar c = Calendar.getInstance();
        String[] anos;
        int anoActual = Calendar.getInstance().get(Calendar.YEAR);
        anos = new String[anoActual - 2002 + 1];

        for (int i = 0; i < anos.length; i++){
            anos[i] = String.valueOf(anoActual-i);
        }
        return anos;
    }
    //array para rellenar la cantidad de dias que mostrará el spinner
    private String[] diasSpinner(int cantidadDias){
        String[] dias = new String[cantidadDias];
        for (int i = 0; i < dias.length; i ++){
            dias[i] = String.valueOf(i+1);
        }
      return dias;
    }


    //al detectar un cambio en cualquier spinner se actualizan el dia seleccionado y el calendario
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int a  = Integer.parseInt(ano.getSelectedItem().toString());
        int m  = mes.getSelectedItemPosition();
        int d = Integer.parseInt(dia.getSelectedItem().toString());

         diaSeleccion = new GregorianCalendar(a,m,d).getTime();
       fecha.setDate(diaSeleccion.getTime());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

   /* protected void spinnerMeses(Spinner mes){
        int mesActual = Calendar.getInstance().get(Calendar.MONTH);
        mes.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, meses));
        mes.setSelection(mesActual);

    }*/

    private void spinnerDias(int mesActual){
        int diasDelMesActual= meses[mesActual].maximoDias();
        String[] valorSpinnerDias = diasSpinner(diasDelMesActual);
        dia.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, valorSpinnerDias));
        dia.setSelection(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)-1);
    }
}
