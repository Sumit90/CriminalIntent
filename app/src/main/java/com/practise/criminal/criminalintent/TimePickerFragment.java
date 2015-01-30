package com.practise.criminal.criminalintent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by e00959 on 1/30/2015.
 */
public class TimePickerFragment extends DialogFragment {

    public static final String EXTRA_TIME="com.practise.criminal.criminalintent.TimeFrag.date";

    Date mCrimeDate;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        mCrimeDate=(Date)getArguments().getSerializable(EXTRA_TIME);
        View v=getActivity().getLayoutInflater().inflate(R.layout.time_picker_dialog,null);

        Calendar cal=Calendar.getInstance();
        cal.setTime(mCrimeDate);
        int hour=cal.get(Calendar.HOUR_OF_DAY);
        int min=cal.get(Calendar.MINUTE);
        int sec=cal.get(Calendar.SECOND);
        int millSec=cal.get(Calendar.MILLISECOND);

        TimePicker timePicker   =   (TimePicker)v.findViewById(R.id.time_pick_wid);



       return new AlertDialog.Builder(getActivity())
                            .setTitle(R.string.time_picker_title)
                            .setView(v)
                            .setPositiveButton(android.R.string.ok, null)
                            .create();
    }

    public static TimePickerFragment getTimeFragInstance(Date date)
    {
        Bundle args=new Bundle();
        args.putSerializable(EXTRA_TIME,date);
        TimePickerFragment fragment=  new TimePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
