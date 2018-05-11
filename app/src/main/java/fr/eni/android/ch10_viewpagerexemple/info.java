package fr.eni.android.ch10_viewpagerexemple;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 *
 */
public class info extends Transiccion implements View.OnClickListener {


    public info() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        //recuperacion de botones

         view.findViewById(R.id.B_email).setOnClickListener(this);
         view.findViewById(R.id.B_gps).setOnClickListener(this);
         view.findViewById(R.id.B_pdf).setOnClickListener(this);
         view.findViewById(R.id.B_tel).setOnClickListener(this);

        return view;
    }


    public void onClick(View v) {
        Intent intent = new Intent();

        switch (v.getId()){

            case R.id.B_email:
                intent =  sendToSendEmail();
                break;
            case R.id.B_gps:
                intent = sendToGPS();
                break;

            case R.id.B_pdf:
                changeFragment(R.id.frag,new ReadPDF());       //cambiamos al fragment donde se muestran los diferentes archivos pdf
                break;

            case R.id.B_tel:
                intent = sendToPhoneCall();
            break;
        }
        //se comprueba que existe el intent para evitar un error
        if (intent.getAction() != null) {
            startActivity(intent);
        }

    }

    //enviar a una app compatible con el envío de email y con un email destino ubicada en el archivo string
    private Intent sendToSendEmail(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        String[] to = {this.getResources().getString(R.string.email)};
        intent.setType("text/html");
        intent.putExtra(Intent.EXTRA_EMAIL,to);
        return intent;
    }

    //enviar a google navigation con la direccion destino ubicada en el archivo string
    private Intent sendToGPS(){
        // Create a Uri from an intent string. Use the result to create an Intent.
        Uri gmmIntentUri = Uri.parse("google.navigation:q="+ this.getResources().getString(R.string.coordenadas));

        // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
        Intent intent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

        // Make the Intent explicit by setting the Google Maps package
        intent.setPackage("com.google.android.apps.maps");
        return intent;
    }


    //envia al marcador de telefono con el numero marcado
    private Intent sendToPhoneCall(){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + this.getResources().getString(R.string.telefono)));
        Toast.makeText(getActivity(), "marcando teléfono", Toast.LENGTH_LONG).show();
        return intent;
    }



}
