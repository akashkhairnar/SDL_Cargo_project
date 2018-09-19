package com.example.akash004.sdl_cargo_project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class loginActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView signup;
    private Button st_login;
    private EditText st_email;
    private EditText st_password;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private Toolbar mtoolbar;
    private static final String TAG = "loginActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog=new ProgressDialog(this);
        setContentView(R.layout.activity_login);
        firebaseAuth=FirebaseAuth.getInstance();
        mtoolbar=(Toolbar)findViewById(R.id.login_page_toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Login");



        signup=(TextView)findViewById(R.id.lg_signup);
        st_login=(Button)findViewById(R.id.lg_login);
        st_email=(EditText)findViewById(R.id.lg_email);
        st_password=(EditText) findViewById(R.id.lg_password);
        signup.setOnClickListener(this);
        st_login.setOnClickListener(this);


    }
    private void loginuser()
    {
        String email=st_email.getText().toString().trim();
        String password=st_password.getText().toString().trim();
        if(TextUtils.isEmpty(email)||TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"Enter All details",Toast.LENGTH_LONG).show();
            return;
        }
        progressDialog.setMessage("Registering User.....");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            Log.d(TAG,"stat");
                            Intent login_Intent=new Intent(loginActivity.this,MainActivity.class);
                            startActivity(login_Intent);
                            finish();

                        }
                        else

                        {
                            progressDialog.hide();
                            FirebaseAuthException e =(FirebaseAuthException)task.getException();

                            Toast.makeText(loginActivity.this,"You got some error :"+e.getMessage(),Toast.LENGTH_LONG).show();

                        }
                        progressDialog.dismiss();
                    }
                });

    }

    @Override
    public void onClick(View v) {
        if(v==signup){
            finish();
            Intent login_Intent=new Intent(loginActivity.this,registerActivity.class);
            login_Intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(login_Intent);
            finish();
        }
        if(v==st_login)
        {
            //Toast.makeText(loginActivity.this,"Button press",Toast.LENGTH_SHORT).show();
            loginuser();
        }

    }
}
