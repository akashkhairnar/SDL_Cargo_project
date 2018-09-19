package com.example.akash004.sdl_cargo_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Shipinfo_Activity extends AppCompatActivity {
    TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10;

    String name,id,dod,doa,dept_time,arr_time,price,source,destination,category;

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipinfo_);
        tv1 = findViewById(R.id.tv);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        tv5 = findViewById(R.id.tv5);
        tv6 = findViewById(R.id.tv6);
        tv7 = findViewById(R.id.tv7);
        tv8 = findViewById(R.id.tv8);
        tv9 = findViewById(R.id.tv9);
        tv10 = findViewById(R.id.tv10);

        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("Ship_name");
        id = bundle.getString("Ship_id");
        dod=bundle.getString("DoD");
        doa=bundle.getString("DoA");
        dept_time=bundle.getString("Departure_Time");
        arr_time=bundle.getString("Arrival_Time");
        price=bundle.getString("Price");
        category=bundle.getString("Category");
        source=bundle.getString("port1");
        destination=bundle.getString("port2");

        tv1.setText(name);
        tv2.setText(id);
        tv3.setText(dept_time);
        tv4.setText(arr_time);
        tv5.setText(price);
        tv6.setText(category);
        tv7.setText(dod);
        tv8.setText(doa);
        tv9.setText(source);
        tv10.setText(destination);

        lv = findViewById(R.id.listView);
        String[] ports = bundle.getStringArray("ports");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,ports);

        lv.setAdapter(adapter);

        Button b1 = findViewById(R.id.button1);

        final Intent intent = new Intent(this,Booking_Activity.class);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });


    }
}

