package com.auribises.firebase.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.auribises.firebase.R;
import com.auribises.firebase.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.editTextName)
    EditText eTxtName;

    @BindView(R.id.editTextEmail)
    EditText eTxtEmail;

    @BindView(R.id.editTextPassword)
    EditText eTxtPassword;

    @BindView(R.id.buttonRegister)
    Button btnRegister;

    @BindView(R.id.buttonLogin)
    Button btnLogin;

    User uRef;

    FirebaseAuth auth;
    DatabaseReference dbRef;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        uRef = new User();

        btnRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        dbRef = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();

        user = auth.getCurrentUser();
        //String uid = user.getUid();

        if(user!=null){
            Intent intent = new Intent(MainActivity.this,HomeActivity.class);
            startActivity(intent);
        }
    }

    void registerUser(){

        auth.createUserWithEmailAndPassword(uRef.email,uRef.password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"Registration Finished",Toast.LENGTH_LONG).show();
                            user = task.getResult().getUser();
                            String uid = user.getUid();

                            dbRef.child("users").child(uid).setValue(uRef);

                        }else{
                            Toast.makeText(MainActivity.this,"Registration Failed",Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    void loginUser(){

        auth.signInWithEmailAndPassword(uRef.email,uRef.password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                    startActivity(intent);

                    Toast.makeText(MainActivity.this,"Login Success",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this,"Login Failed",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public void onClick(View v) {

        uRef.name = eTxtName.getText().toString().trim();
        uRef.email = eTxtEmail.getText().toString().trim();
        uRef.password = eTxtPassword.getText().toString().trim();

        if(v.getId() == R.id.buttonLogin){
            loginUser();
        }else{
            registerUser();
        }


    }
}
