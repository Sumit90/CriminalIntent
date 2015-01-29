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
        DateFormat l_dateFormat =   new SimpleDateFormat("E,MM dd,yyyy hh:mm:ss");
        mDateButton.setText(l_dateFormat.format(mCrime.getmDate()));
        mDateButton.setEnabled(false);

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





        mIsSolved.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                mCrime.setmIsResolved(isChecked);
            }
        });

         return v;
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
