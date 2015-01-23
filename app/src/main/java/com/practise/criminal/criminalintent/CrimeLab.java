package com.practise.criminal.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by e00959 on 1/23/2015.
 */
public class CrimeLab {

    private Context mContext;
    private static CrimeLab sCrimeLab;
    private ArrayList<Crime> mCrimes  ;

    private CrimeLab(Context appContext)
    {
        mContext=appContext;
        mCrimes=new ArrayList<Crime>();

        for(int i=0;i<100;i++)
        {
            Crime c=new Crime();
            c.setmTitle(i+" Serious Crime");
            c.setmIsResolved(1%2==0);
            mCrimes.add(c);
        }
    }

    public static CrimeLab getInstance(Context appContext)
    {
        if(sCrimeLab==null)
        {
            sCrimeLab = new CrimeLab(appContext.getApplicationContext());
        }

        return sCrimeLab;
    }

    public  ArrayList<Crime> getCrimeList()
    {
        return mCrimes;
    }

    public Crime getCrime(UUID uiid)
    {
        for(Crime c:mCrimes)
        {
            if(c.getmUid()==uiid)
            {
                return c;

            }
        }
        return null;
    }

}
