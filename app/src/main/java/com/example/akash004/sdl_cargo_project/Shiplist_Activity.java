package com.example.akash004.sdl_cargo_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.transform.Source;


public class Shiplist_Activity extends AppCompatActivity {

    private ListView lvproduct;
    private productlistadapter adapter;
    private List<init_Ship> mproductlist;

    private  String Ship_name,Ship_id,DoD,DoA,Price,Departure_Time,Arrival_Time,Category;
    private int i=0,j=0,Si,Di;


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Ship");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shiplist_);

        lvproduct = findViewById(R.id.list);

        mproductlist = new ArrayList<>();

        final String Source,Destination,Day;
        Bundle bundle1 = getIntent().getExtras();
        Source = bundle1.getString("Source");
        Destination = bundle1.getString("Destination");
        Day = bundle1.getString("DoD");

        final Intent intent1 = new Intent(this,Shipinfo_Activity.class);


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                mproductlist.clear();
                i=0;

                for(DataSnapshot ds : dataSnapshot.getChildren())
                {

                    String port[] = new String[10];

                    Ship_name = ds.child("name").getValue().toString();
                    Ship_id = ds.child("id").getValue().toString();
                    DoD = ds.child("dod").getValue().toString();
                    DoA = ds.child("doa").getValue().toString();
                    Departure_Time = ds.child("departure_time").getValue().toString();
                    Arrival_Time = ds.child("arrival_time").getValue().toString();
                    Price = ds.child("price").getValue().toString();
                    Category = ds.child("category").getValue().toString();

                    j=0;
                    for (DataSnapshot ds1 : ds.child("ports").getChildren())
                    {
                        port[j] = ds1.getValue().toString();
                        j++;
                    }

                    if(Arrays.asList(port).contains(Source) && Arrays.asList(port).contains(Destination) && DoD.equals(Day))
                    {
                        i++;
                        Si = Arrays.asList(port).indexOf(Source);
                        Di = Arrays.asList(port).indexOf(Destination);
                        mproductlist.add(new init_Ship(i,Ship_name,Ship_id,DoD,DoA,Departure_Time,Arrival_Time,Price,Category,port));
                    }

                }

                adapter = new productlistadapter(getApplicationContext(),mproductlist);

                lvproduct.setAdapter(adapter);

                lvproduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        Toast.makeText(getApplicationContext(),"Clicked on id : "+view.getTag(),Toast.LENGTH_SHORT).show();
                        Bundle bundle = new Bundle();

                        bundle.putString("Ship_name", String.valueOf(mproductlist.get(i).getShip_name()));
                        bundle.putString("Ship_id",String.valueOf(mproductlist.get(i).getShip_id()));
                        bundle.putString("DoD",String.valueOf(mproductlist.get(i).getDate_of_Departure()));
                        bundle.putString("DoA",String.valueOf(mproductlist.get(i).getDate_of_Arrival()));
                        bundle.putString("Departure_Time",String.valueOf(mproductlist.get(i).getDate_of_Arrival()));
                        bundle.putString("Arrival_Time",String.valueOf(mproductlist.get(i).getArrival_Time()));
                        bundle.putString("Price",String.valueOf(mproductlist.get(i).getPrice()));
                        bundle.putString("Category",String.valueOf(mproductlist.get(i).getCategory()));
                        bundle.putString("port2",String.valueOf(mproductlist.get(i).getPort(9)));
                        bundle.putString("port1", String.valueOf(mproductlist.get(i).getPort(0)));
                        bundle.putStringArray("ports",mproductlist.get(i).getPorts());

                        intent1.putExtras(bundle);
                        startActivity(intent1);
                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
