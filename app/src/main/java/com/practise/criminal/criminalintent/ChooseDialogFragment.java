package com.practise.criminal.criminalintent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;

import java.util.Date;

/**
 * Created by e00959 on 1/30/2015.
 */
public class ChooseDialogFragment extends DialogFragment {

    private Button mDateButton;
    private Button mTimeButton;
    private Date mCrimeDate;
    private static final String EXTRA_DATE="com.practise.criminal.criminalintent.choosedate";
    public static final String DIALOG_PICKER="choose_dialog";
    private static final int REQUEST_DATE=1;
    private static final int REQUEST_TIME=2;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        mCrimeDate = (Date)getArguments().getSerializable(EXTRA_DATE);
        View v=getActivity().getLayoutInflater().inflate(R.layout.dialog_picker_dialog,null);
        final FragmentManager fm=getActivity().getSupportFragmentManager();

        mDateButton=(Button)v.findViewById(R.id.change_date_btn);

        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerFragment datePickerFragment=  DatePickerFragment.getDatePickerInstance(mCrimeDate);
                datePickerFragment.setTargetFragment(ChooseDialogFragment.this,REQUEST_DATE);
                datePickerFragment.show(fm,DIALOG_PICKER);


            }
        });

        mTimeButton=(Button)v.findViewById(R.id.change_time_btn);
        mTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimePickerFragment timePickerFragment=  TimePickerFragment.getTimeFragInstance(mCrimeDate);
                timePickerFragment.setTargetFragment(ChooseDialogFragment.this,REQUEST_TIME);
                timePickerFragment.show(fm,DIALOG_PICKER);

            }
        });


        return new AlertDialog.Builder(getActivity())
                              .setTitle(R.string.dialog_picker_title)
                              .setView(v)
                              .create();



    }

    public static ChooseDialogFragment getDialogInstance(Date date)
    {
        Bundle args=new Bundle();
        args.putSerializable(EXTRA_DATE,date);
        ChooseDialogFragment dialogFragment = new ChooseDialogFragment();
        dialogFragment.setArguments(args);
        return dialogFragment;

    }


}
