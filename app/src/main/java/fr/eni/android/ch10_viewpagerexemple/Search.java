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

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class Search extends Transiccion implements View.OnClickListener{
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
        mesFrag = SearchChart.newInstance(new GregorianCalendar(2018, 0, 1).getTime());

        view.findViewById(R.id.Bdia).setOnClickListener(this);
        view.findViewById(R.id.Bmes).setOnClickListener(this);

        changeFragment(R.id.cosa,diaFrag);

        return view;
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.Bdia:
                changeFragment(R.id.cosa,diaFrag);
                break;
            case R.id.Bmes:
                changeFragment(R.id.cosa,mesFrag);
                break;
        }

    }

}
