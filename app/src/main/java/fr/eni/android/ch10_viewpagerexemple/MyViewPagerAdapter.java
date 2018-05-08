package fr.eni.android.ch10_viewpagerexemple;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by nazim on 25/11/14.
 */
public class MyViewPagerAdapter extends FragmentPagerAdapter {

    private String[] tab;
    private Fragment[] fragments = new Fragment[3];
    public MyViewPagerAdapter(FragmentManager fm) {
        super(fm);
        tab = new String[]
                {"posición 1", "posición 2", "posición 3"};

        fragments[0] = new chart();
        fragments[1] = MyFragment.newInstance(tab[1]);
        fragments[2] = MyFragment.newInstance(tab[2]);
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