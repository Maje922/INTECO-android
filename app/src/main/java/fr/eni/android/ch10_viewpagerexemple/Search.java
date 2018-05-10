package fr.eni.android.ch10_viewpagerexemple;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class Search extends Fragment implements View.OnClickListener{
    private Fragment fragment;
    private Fragment diaFrag;
    private Fragment mesFrag;

    private Fragment[] fragments;
    private Button dia, mes;

    public Search() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        diaFrag = new dia();
        mesFrag = new chart();

        view.findViewById(R.id.Bdia).setOnClickListener(this);
        view.findViewById(R.id.Bmes).setOnClickListener(this);

       setFragment(diaFrag);
        //fragment = fm.findFragmentById(R.id.cosa);


        /*fragments = new Fragment[2];
        fragments[0] = MyFragment.newInstance("dia");
        fragments[1] = MyFragment.newInstance("mes");


        replaceFragment(new info());*/





        return view;
    }

    private void setFragment(Fragment fr){
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.cosa,fr).commit();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.Bdia:
                setFragment(diaFrag);
                break;
            case R.id.Bmes:
                setFragment(mesFrag);
                break;
        }

    }

   /* public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.vista, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
*/

}
