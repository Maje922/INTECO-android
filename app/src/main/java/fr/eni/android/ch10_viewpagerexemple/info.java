package fr.eni.android.ch10_viewpagerexemple;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class info extends Fragment implements View.OnClickListener {


    public info() {
        // Required empty public constructor
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
            // recuperamos el email del singleton y enviamos a una app compatible
            case R.id.B_email:

                intent = new Intent(Intent.ACTION_SEND);


                String[] to = {this.getResources().getString(R.string.email)};
                intent.setType("text/html");
                intent.putExtra(Intent.EXTRA_EMAIL,to);
                //no envia el valor del email :s

                break;
            case R.id.B_gps:

                // Create a Uri from an intent string. Use the result to create an Intent.
                Uri gmmIntentUri = Uri.parse("google.navigation:q="+ this.getResources().getString(R.string.coordenadas));

                // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
                intent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

                // Make the Intent explicit by setting the Google Maps package
                intent.setPackage("com.google.android.apps.maps");


                break;
            case R.id.B_pdf:

                Fragment fragment = MyFragment.newInstance("pdf");


                //transation to MoreInfo fragment
                FragmentTransaction t = getFragmentManager().beginTransaction();

                t.replace(R.id.viewpager, fragment);

                t.addToBackStack(null);
                t.commit();
                break;

            case R.id.B_tel:
                intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + this.getResources().getString(R.string.telefono)));
            Toast.makeText(getActivity(), "marcando teléfono", Toast.LENGTH_LONG).show();
            break;
        }
        //se comprueba que existe el intent para evitar un error
        if (intent.getAction() != null) {
            startActivity(intent);
        }

    }

}
