package fr.eni.android.ch10_viewpagerexemple;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class ViewPagerActivity extends AppCompatActivity implements View.OnKeyListener {

    private ViewPager mViewPager;
    private MyViewPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mAdapter = new MyViewPagerAdapter(
                getSupportFragmentManager());

       mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position,
                                       float positionOffset,
                                       int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mViewPager.setAdapter(mAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#21447F"));
        //indica a tablayout cuál es el viewpager a escuchar
        try {
            tabLayout.setupWithViewPager(mViewPager);
        }catch (Exception e) {
            e.getMessage();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view_pager, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

                EstadoLogin conf = new EstadoLogin(this);
                conf.setLog(false);
                finish();


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public void onBackPressed() {
        EstadoLogin conf = new EstadoLogin(this);
        conf.setLog(false);
        finish();
    }
}
