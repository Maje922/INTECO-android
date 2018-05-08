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
import android.widget.FrameLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class Search extends Fragment {
    private Fragment fragment;

    private Fragment[] fragments;

    public Search() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        FragmentManager fm = getFragmentManager();
        //fragment = fm.findFragmentById(R.id.cosa);

        fm.beginTransaction().add(R.id.cosa,MyFragment.newInstance("cosa")).commit();
        /*fragments = new Fragment[2];
        fragments[0] = MyFragment.newInstance("dia");
        fragments[1] = MyFragment.newInstance("mes");


        replaceFragment(new info());*/





        return view;
    }

   /* public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.vista, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
*/

}
