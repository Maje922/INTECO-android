package fr.eni.android.ch10_viewpagerexemple;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

    public info() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);

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
            case R.id.B_email:
                Toast.makeText(getActivity(), "seleccionado email", Toast.LENGTH_LONG).show();
                break;
            case R.id.B_gps:
                // Uri geoLocation = "geo:0,0?q=1600+Amphitheatre+Parkway%2C+CA";
              /*intent = new Intent(Intent.ACTION_VIEW);
                intent.setData("geo:47.6,-122.3");

                Toast.makeText(this, "seleccionado gps", Toast.LENGTH_LONG).show();*/
                break;
            case R.id.B_pdf:
                Toast.makeText(getActivity(), "seleccionado pdf", Toast.LENGTH_LONG).show();
                break;
            case R.id.B_tel:
                intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + 123456789));
                Toast.makeText(getActivity(), "marcando teléfono", Toast.LENGTH_LONG).show();
                break;
        }
        /*if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);

        }*/
        startActivity(intent);
    }

}
