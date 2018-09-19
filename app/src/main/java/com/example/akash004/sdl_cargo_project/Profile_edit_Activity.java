package com.example.akash004.sdl_cargo_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Profile_edit_Activity extends AppCompatActivity {

    private EditText mname;
    private EditText mphone;
    private  EditText mcompany;
    private Button msave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit_);

        mcompany=(EditText)findViewById(R.id.edit_company);
        mname=(EditText)findViewById(R.id.edit_name);
        mphone=(EditText)findViewById(R.id.edit_phone);
        msave=(Button)findViewById(R.id.edit_save);

        msave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
