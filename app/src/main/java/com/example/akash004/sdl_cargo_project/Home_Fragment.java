package com.example.akash004.sdl_cargo_project;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.app.DatePickerDialog;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;



import android.widget.DatePicker;


import com.google.android.gms.common.SupportErrorDialogFragment;

import java.text.DateFormat;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home_Fragment extends Fragment implements DatePickerDialog.OnDateSetListener{

    View mmainView;
    Button b1,b2;
    EditText et1,et2;
    TextView tv4,tv6;

    private String Date;

    public Home_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mmainView= inflater.inflate(R.layout.fragment_home_, container, false);

        b1 = (Button)mmainView.findViewById(R.id.button1);
        b2 = (Button)mmainView.findViewById(R.id.button2);

        et1 = (EditText)mmainView. findViewById(R.id.editText1);
        et2 = (EditText)mmainView.findViewById(R.id.editText2);

        tv4 = (TextView)mmainView.findViewById(R.id.tv4);
        tv6 = (TextView)mmainView.findViewById(R.id.tv6);

        tv6.setVisibility(View.INVISIBLE);

        final Intent hintent = new Intent(getActivity(),Shiplist_Activity.class);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new datePickerFragment();
                ((datePickerFragment) datePicker).listener=Home_Fragment.this;
                datePicker.show(getActivity().getSupportFragmentManager(),"Date Picker");

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                Bundle bundle = new Bundle();

                bundle.putString("Source",et1.getText().toString());
                bundle.putString("Destination",et2.getText().toString());
                bundle.putString("DoD",tv6.getText().toString());
                hintent.putExtras(bundle);
                startActivity(hintent);

            }
        });



        return mmainView;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,day);

        Date = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
Log.d("DATE123",Date.toString());
        String[] dod = Date.split("[ ]+");

        tv4.setText(dod[1]+" " +dod[2]+" "+dod[3]);

        tv6.setText(dod[0]);
    }

}
