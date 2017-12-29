package com.auribises.firebase.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.auribises.firebase.R;
import com.auribises.firebase.model.Product;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity {

    DatabaseReference dbRef;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dbRef = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();

        String uid = auth.getCurrentUser().getUid();

        Product p = new Product(101,uid,"5Star",20);

        String pKey = dbRef.child("products").push().getKey();
        dbRef.child("products").child(pKey).setValue(p);

    }
}
