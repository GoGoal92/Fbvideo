package gogoal.com.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import gogoal.com.Fragment.Fragment_Movies;
import gogoal.com.Fragment.Fragment_Series;

/**
 * Created by Go Goal on 11/25/2016.
 */

public class Fragmentadapter_Main extends FragmentStatePagerAdapter {


    public Fragmentadapter_Main(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return Fragment_Movies.newInstance();
        } else {
            return Fragment_Series.newInstance();
        }

    }

    @Override
    public int getCount() {
        return 2;
    }
}
