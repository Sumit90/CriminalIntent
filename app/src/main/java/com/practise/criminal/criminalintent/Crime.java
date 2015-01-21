package com.practise.criminal.criminalintent;

import java.util.UUID;

/**
 * Created by e00959 on 1/21/2015.
 */
public class Crime {
    private UUID mUid;
    private String mTitle;

    public Crime()

    {
        mUid= UUID.randomUUID();
    }

    public UUID getmUid() {
        return mUid;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }
}
