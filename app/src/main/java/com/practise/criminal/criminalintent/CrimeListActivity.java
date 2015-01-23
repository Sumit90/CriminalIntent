package com.practise.criminal.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by e00959 on 1/23/2015.
 */
public class CrimeListActivity extends SinglefragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
