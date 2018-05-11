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
public class Transiccion extends Fragment {


    public Transiccion() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_transiccion, container, false);


        //por defecto contendrá el fragment info
        changeFragment(R.id.frag, new info());

        return view;
    }
    //metodo para cambiar de fragment un view
    protected void changeFragment(int view, Fragment fragment){
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(view,fragment).commit();
    }

}
