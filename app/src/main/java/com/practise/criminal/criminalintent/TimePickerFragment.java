package com.practise.criminal.criminalintent;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
        final int year=cal.get(Calendar.YEAR);
        final int month=cal.get(Calendar.MONTH);
        final int day=cal.get(Calendar.DAY_OF_MONTH);

        TimePicker timePicker   =   (TimePicker)v.findViewById(R.id.time_pick_wid);
        timePicker.setCurrentHour(hour);
        timePicker.setCurrentMinute(min);

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {

                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, month);
                cal.set(Calendar.DAY_OF_MONTH, day);
                cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                cal.set(Calendar.MINUTE, minute);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);

                mCrimeDate=cal.getTime();
                getArguments().putSerializable(EXTRA_TIME,mCrimeDate);
            }
        });


       return new AlertDialog.Builder(getActivity())
                            .setTitle(R.string.time_picker_title)
                            .setView(v)
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    sendBackResult(Activity.RESULT_OK);
                                }
                            })
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

    private void sendBackResult(int resultCode)
    {
        if(getTargetFragment()==null)
            return;
        Intent i=new Intent();
        i.putExtra(EXTRA_TIME,mCrimeDate);
        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode,i);
    }
}
