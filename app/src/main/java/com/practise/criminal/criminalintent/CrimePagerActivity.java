package com.practise.criminal.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by e00959 on 1/28/2015.
 */
public class CrimePagerActivity extends FragmentActivity{

    private ViewPager mViewPager;
    private ArrayList mCrimeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewPager=new ViewPager(this);
        mViewPager.setId(R.id.view_pager);
        setContentView(mViewPager);

        mCrimeList=CrimeLab.getInstance(this).getCrimeList();

        FragmentManager fm=getSupportFragmentManager();

        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {

                Crime crime = (Crime)mCrimeList.get(position);
                return CrimeFragment.getFragmentInstance(crime.getmUid());
            }

            @Override
            public int getCount() {
                return mCrimeList.size();
            }
        });

        UUID mUUID=(UUID)getIntent().getSerializableExtra(CrimeListFragment.EXTRA_CRIME_ID);

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                Log.d("CrimePagerActivity","--------------");
                Crime crime = (Crime)mCrimeList.get(position);
                if(crime.getmTitle()!=null)
                {
                    Log.d("CrimePagerActivity","--------------"+crime.getmTitle());
                    setTitle(crime.getmTitle());
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        for(int i=0;i<mCrimeList.size();i++)
        {
           if(((Crime)mCrimeList.get(i)).getmUid().equals(mUUID))
           {
               mViewPager.setCurrentItem(i);
               break;
           }
        }
    }
}
