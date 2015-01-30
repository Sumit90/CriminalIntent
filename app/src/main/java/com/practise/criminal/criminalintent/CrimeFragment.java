package com.practise.criminal.criminalintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by e00959 on 1/23/2015.
 */


public class CrimeFragment extends Fragment {

    private Crime mCrime;
    private EditText mCrimeTitle;
    private Button mDateButton;
    private CheckBox mIsSolved;
    public static final String DATE_PICKER="date";
    private static final int REQUEST_DATE = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // mCrime = new Crime();

        UUID mUUID = (UUID)getArguments().get(CrimeListFragment.EXTRA_CRIME_ID);

        if(mUUID!=null)
        mCrime=CrimeLab.getInstance(getActivity()).getCrime(mUUID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_crime,container,false);
        mCrimeTitle=(EditText)v.findViewById(R.id.crime_title);
        mCrimeTitle.setText(mCrime.getmTitle());

        mDateButton=(Button)v.findViewById(R.id.crime_date);
        updateDate();
        //mDateButton.setEnabled(false);

        mIsSolved=(CheckBox)v.findViewById(R.id.crime_solved);
        mIsSolved.setChecked(mCrime.ismIsResolved());

        mCrimeTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                mCrime.setmTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm=getActivity().getSupportFragmentManager();
               // DatePickerFragment datePicker=DatePickerFragment.getDatePickerInstance(mCrime.getmDate());
                //datePicker.setTargetFragment(CrimeFragment.this,REQUEST_DATE);
               // datePicker.show(fm,DATE_PICKER);
                ChooseDialogFragment dialogChooser=ChooseDialogFragment.getDialogInstance(mCrime.getmDate());
                dialogChooser.setTargetFragment(CrimeFragment.this,REQUEST_DATE);
                dialogChooser.show(fm,DATE_PICKER);


            }
        });


        mIsSolved.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                mCrime.setmIsResolved(isChecked);
            }
        });

         return v;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode!= Activity.RESULT_OK) return;
        if(requestCode==REQUEST_DATE)
        {
            Date date=(Date)data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mCrime.setmDate(date);
            updateDate();
        }
    }

    public void updateDate()
    {
        DateFormat l_dateFormat =   new SimpleDateFormat("E,MM dd,yyyy hh:mm:ss");
        mDateButton.setText(l_dateFormat.format(mCrime.getmDate()));

    }
    public static CrimeFragment getFragmentInstance(UUID uuid)
    {
        Bundle args=new Bundle();
        args.putSerializable(CrimeListFragment.EXTRA_CRIME_ID,uuid);
        CrimeFragment crimeFrag =   new CrimeFragment();
        crimeFrag.setArguments(args);
        return  crimeFrag;
    }
}
