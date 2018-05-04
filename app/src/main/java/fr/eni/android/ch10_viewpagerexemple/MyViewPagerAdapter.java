package fr.eni.android.ch10_viewpagerexemple;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class MyViewPagerAdapter extends FragmentPagerAdapter {

    private String[] tab;
    private Fragment[] arrayFragment;

    public MyViewPagerAdapter(FragmentManager fm) {
        super(fm);
        tab = new String[]
                {"posición 1", "posición 2", "posición 3"};
        arrayFragment=new Fragment[3];
        arrayFragment[0] = MyFragment.newInstance(tab[0]);
        arrayFragment[1] = MyFragment.newInstance(tab[1]);
        arrayFragment[2] = new ReadPDF();
    }

    @Override
    public Fragment getItem(final int pos) {
        return arrayFragment[pos];
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
