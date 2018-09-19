package com.example.akash004.sdl_cargo_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile_activity extends AppCompatActivity {
    private android.support.v7.widget.Toolbar mtoolbar;

    private DatabaseReference muserDatabse;
    private FirebaseUser mcurrentUser;
    private TextView mname;
    private TextView mphone;
    private TextView memail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_activity);

        mtoolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mtoolbar);
        mname=(TextView)findViewById(R.id.profile_name);
        memail=(TextView)findViewById(R.id.profile_email);
        mphone=(TextView)findViewById(R.id.profile_mob);

        mcurrentUser=FirebaseAuth.getInstance().getCurrentUser();
        String current_uid=mcurrentUser.getUid();
        muserDatabse= FirebaseDatabase.getInstance().getReference().child("Users").child(current_uid);
        muserDatabse.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name=dataSnapshot.child("name").getValue().toString();
                String email=dataSnapshot.child("Email").getValue().toString();
                String  phone=dataSnapshot.child("mob_no").getValue().toString();

                mname.setText(name);
                memail.setText(email);
                mphone.setText(phone);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.profile_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.myprofile_edit) {


             Intent peditIntent=new Intent(Profile_activity.this,Profile_edit_Activity.class);
            startActivity(peditIntent);
        }
        return true;
    }

}
