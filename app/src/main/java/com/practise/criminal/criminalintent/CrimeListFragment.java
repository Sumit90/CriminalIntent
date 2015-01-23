package com.practise.criminal.criminalintent;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by e00959 on 1/23/2015.
 */
public class CrimeListFragment extends ListFragment {

    private ArrayList<Crime> mCrimeList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.crimes_title);

        mCrimeList = CrimeLab.getInstance(getActivity()).getCrimeList();
        ArrayAdapter<Crime> adapter = new ArrayAdapter<Crime>(getActivity(),android.R.layout.simple_list_item_1,mCrimeList);

        setListAdapter(adapter);
    }
}
