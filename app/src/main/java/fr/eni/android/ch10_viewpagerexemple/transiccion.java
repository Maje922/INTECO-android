package fr.eni.android.ch10_viewpagerexemple;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;


/**
 * este fragment solo es para moverse entre los fragments info, readPDF y readingPDF
 * utilizaremos el frameLayout para intercambiar los diferentes fragments
 */
public class transiccion extends Fragment {


    public transiccion() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_transiccion, container, false);



        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.frag,new info()).commit();

        return view;
    }

}
