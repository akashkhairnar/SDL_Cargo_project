package com.example.akash004.sdl_cargo_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class beginActivity extends AppCompatActivity implements View.OnClickListener{
    private Button login;
    private Button signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);
        login=(Button)findViewById(R.id.bg_login);
        signup=(Button)findViewById(R.id.bg_signup);

        login.setOnClickListener(this);
        signup.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        if(v==login)
        {
            Intent bg_Intent=new Intent(beginActivity.this,loginActivity.class);
            startActivity(bg_Intent);
            finish();

        }
        if(v==signup)
        {
            Intent bg1_Intent=new Intent(beginActivity.this,registerActivity.class);
            startActivity(bg1_Intent);
            finish();

        }

    }
}
