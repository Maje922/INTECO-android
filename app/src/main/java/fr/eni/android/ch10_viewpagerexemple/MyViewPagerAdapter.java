package fr.eni.android.ch10_viewpagerexemple;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class MyViewPagerAdapter extends FragmentPagerAdapter{
    private String[] tab;
    private Fragment[] fragments = new Fragment[3];
    public MyViewPagerAdapter(FragmentManager fm) {
        super(fm);
        tab = new String[]
                {"Últimos datos", "Buscar por fecha", "Información y contacto"};

        fragments[0] = new chart();
        fragments[1] = new Search();
        fragments[2] = new info();
    }

    @Override
    public Fragment getItem(final int pos) {
        return fragments[pos];
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tab[position];
    }
}
