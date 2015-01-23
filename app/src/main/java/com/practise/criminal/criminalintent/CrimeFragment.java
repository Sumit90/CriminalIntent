package com.practise.criminal.criminalintent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

/**
 * Created by e00959 on 1/23/2015.
 */


public class CrimeFragment extends Fragment {

    private Crime mCrime;
    private EditText mCrimeTitle;
    private Button mDateButton;
    private CheckBox mIsSolved;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCrime = new Crime();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_crime,container,false);
        mCrimeTitle=(EditText)v.findViewById(R.id.crime_title);
        mDateButton=(Button)v.findViewById(R.id.crime_date);
        mIsSolved=(CheckBox)v.findViewById(R.id.crime_solved);

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


        DateFormat l_dateFormat =   new SimpleDateFormat("E,MM dd,yyyy hh:mm:ss");
        mDateButton.setText(l_dateFormat.format(mCrime.getmDate()));
        mDateButton.setEnabled(false);

        mIsSolved.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                mCrime.setmIsResolved(isChecked);
            }
        });

         return v;
    }
}
