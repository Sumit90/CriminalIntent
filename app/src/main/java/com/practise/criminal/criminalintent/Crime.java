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
}
