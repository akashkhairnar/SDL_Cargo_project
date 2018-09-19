package com.example.akash004.sdl_cargo_project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class registerActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText reg_email;
    private EditText reg_password;
    private EditText reg_phone;
    private Button reg_summit;
    private EditText reg_name;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference mdatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        progressDialog=new ProgressDialog(this);
        firebaseAuth=FirebaseAuth.getInstance();



        reg_name = (EditText) findViewById(R.id.re_name);
        reg_email = (EditText) findViewById(R.id.re_email);
        reg_password = (EditText) findViewById(R.id.re_password);
        reg_phone = (EditText) findViewById(R.id.re_phone);
        reg_summit = (Button) findViewById(R.id.re_summit);
        reg_summit.setOnClickListener(this);

    }
    private void registerUser(View v)
    {

        final String email=reg_email.getText().toString().trim();
        final String name=reg_name.getText().toString().trim();
        final String phone = reg_phone.getText().toString().trim();
        String password = reg_password.getText().toString().trim();
        if(TextUtils.isEmpty(email)||TextUtils.isEmpty(name)||TextUtils.isEmpty(phone)||TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"Enter All details",Toast.LENGTH_LONG).show();
            return;
        }
        String regEx =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        Matcher matcherObj = Pattern.compile(regEx).matcher(email);

        if (matcherObj.matches()) {
            // Toast.makeText(v.getContext(), email+" is valid", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(v.getContext(), email+" is InValid email address", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Registering User.....");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser current_user=FirebaseAuth.getInstance().getCurrentUser();
                            String uid=current_user.getUid();


                            mdatabase=FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
                            HashMap<String,String>userMap=new HashMap<>();
                            userMap.put("name",name);
                            userMap.put("mob_no",phone);
                           // userMap.put("image","profile");
                            userMap.put("Email",email);
                            mdatabase.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful())

                                    {
                                    }
                                    progressDialog.dismiss();
                                    FirebaseAuth.getInstance().signOut();
                                    Intent main_Intent = new Intent(registerActivity.this, MainActivity.class);
                                    main_Intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(main_Intent);
                                    finish();
                                }

                                });


                        }
                        else

                        {
                            progressDialog.hide();
                            FirebaseAuthException e =(FirebaseAuthException)task.getException();

                            Toast.makeText(registerActivity.this,"You got some error :"+e.getMessage(),Toast.LENGTH_LONG).show();

                        }
                        progressDialog.dismiss();
                    }
                });

    }

    @Override
    public void onClick(View v) {
        if(v==reg_summit)
        {

            registerUser(v);
        }

    }
}

