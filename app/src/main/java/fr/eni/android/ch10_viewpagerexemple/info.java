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
    private ImageButton B_tel;
    private ImageButton B_email;
    private ImageButton B_gps;
    private ImageButton B_more;

    //singleton con la informacion de la empresa almacenada
    private Informacion informacion;

    public info() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        informacion = Informacion.getInstance();

        //recuperacion de botones
        B_email =  view.findViewById(R.id.B_email);
        B_gps = view.findViewById(R.id.B_gps);
        B_more = view.findViewById(R.id.B_pdf);
        B_tel = view.findViewById(R.id.B_tel);

        B_tel.setOnClickListener(this);
        B_gps.setOnClickListener(this);
        B_email.setOnClickListener(this);
        B_more.setOnClickListener(this);

        return view;
    }
    public void onClick(View v) {
        Intent intent = new Intent();

        switch (v.getId()){
            // recuperamos el email del singleton y enviamos a una app compatible
            case R.id.B_email:

                intent = new Intent(Intent.ACTION_SEND);

                intent.setType("text/html");
                intent.putExtra(Intent.EXTRA_EMAIL, this.getResources().getString(R.string.email));
                //no envia el valor del email :s

                break;
            case R.id.B_gps:

                // Create a Uri from an intent string. Use the result to create an Intent.
                Uri gmmIntentUri = Uri.parse("google.streetview:cbll="+ this.getResources().getString(R.string.coordenadas));

                // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
                intent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

                // Make the Intent explicit by setting the Google Maps package
                intent.setPackage("com.google.android.apps.maps");

                Toast.makeText(getActivity(), "seleccionado gps", Toast.LENGTH_LONG).show();
                break;
            case R.id.B_pdf:
                //transation to MoreInfo fragment
                FragmentTransaction t = this.getFragmentManager().beginTransaction();
                Fragment mFrag = new MoreInfo();
                t.replace(R.id.viewpager, mFrag);
                t.commit();
                break;
            case R.id.B_tel:
                intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + this.getResources().getString(R.string.telefono)));
            Toast.makeText(getActivity(), "marcando teléfono", Toast.LENGTH_LONG).show();
            break;
        }
        if (intent.getAction() != null) {
            startActivity(intent);
        }

    }

}
