package com.practise.criminal.criminalintent;

import java.util.Date;
import java.util.UUID;

/**
 * Created by e00959 on 1/21/2015.
 */
public class Crime {
    private UUID mUid;
    private String mTitle;
    private Date mDate;
    private boolean mIsResolved;

    public Crime()

    {
        mUid= UUID.randomUUID();
        mDate=new Date();
    }

    public UUID getmUid() {
        return mUid;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public boolean ismIsResolved() {
        return mIsResolved;
    }

    public void setmIsResolved(boolean mIsResolved) {
        this.mIsResolved = mIsResolved;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    @Override
    public String toString() {
        return mTitle;
    }
}
