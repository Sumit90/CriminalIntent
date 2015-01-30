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
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by e00959 on 1/29/2015.
 */
public class DatePickerFragment extends DialogFragment{

    public static final String EXTRA_DATE="com.practise.criminal.criminalintent.date";
    Date mCrimeDate;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        mCrimeDate=(Date)getArguments().getSerializable(EXTRA_DATE);

        Calendar c= Calendar.getInstance();
        c.setTime(mCrimeDate);

        int year=c.get(Calendar.YEAR);
        final int month=c.get(Calendar.MONTH);
        final int day=c.get(Calendar.DAY_OF_MONTH);

        View v=getActivity().getLayoutInflater().inflate(R.layout.date_picker_dialoge,null);
        DatePicker datePick=(DatePicker)v.findViewById(R.id.date_picker);
        datePick.init(year,month,day,new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mCrimeDate=new GregorianCalendar(year,monthOfYear,dayOfMonth).getTime();
                getArguments().putSerializable(EXTRA_DATE,mCrimeDate);
            }
        });
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.date_picker_title)
                .setView(v)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sendBackResult(Activity.RESULT_OK);
                            }
                        })
                .create();


    }

    public static DatePickerFragment getDatePickerInstance(Date date)
    {
        Bundle args=new Bundle();
        args.putSerializable(EXTRA_DATE,date);
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void sendBackResult(int resultCode)
    {
        if(getTargetFragment()==null)
            return;
        Intent i=new Intent();
        i.putExtra(EXTRA_DATE,mCrimeDate);
        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode,i);
    }
}
