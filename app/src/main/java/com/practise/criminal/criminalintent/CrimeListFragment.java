package com.practise.criminal.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by e00959 on 1/23/2015.
 */
public class CrimeListFragment extends ListFragment {

    private ArrayList<Crime> mCrimeList;
    public static final String EXTRA_CRIME_ID="com.practise.criminal.criminalintent.mUIID";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.crimes_title);

        mCrimeList = CrimeLab.getInstance(getActivity()).getCrimeList();
        CrimeListAdapter adapter = new CrimeListAdapter(mCrimeList);

        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        Crime l_c   =   ((CrimeListAdapter)getListAdapter()).getItem(position);
        Intent l_callCrime = new Intent(getActivity(),CrimeActivity.class);
        l_callCrime.putExtra(EXTRA_CRIME_ID,l_c.getmUid());
        startActivity(l_callCrime);
    }

    private class CrimeListAdapter extends ArrayAdapter<Crime>
    {

        public CrimeListAdapter(ArrayList<Crime> crimes) {
            super(getActivity(),0,crimes);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView==null)
            {
                convertView=getActivity().getLayoutInflater().inflate(R.layout.list_item_crime,null);
            }

            Crime l_crime= getItem(position);
            TextView l_title=(TextView)convertView.findViewById(R.id.crime_list_title);
            l_title.setText(l_crime.getmTitle());

            TextView l_date=(TextView)convertView.findViewById(R.id.crime_list_date);
            l_date.setText(l_crime.getmDate().toString());

            CheckBox l_isCrimeSolved=(CheckBox)convertView.findViewById(R.id.crime_list_solved);
            l_isCrimeSolved.setChecked(l_crime.ismIsResolved());

            Log.d("UUID",l_crime.getmUid().toString());
            return convertView;

        }
    }
}
