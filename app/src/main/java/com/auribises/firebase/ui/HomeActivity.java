package com.auribises.firebase.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.auribises.firebase.R;
import com.auribises.firebase.adapter.UserAdapter;
import com.auribises.firebase.adapter.UserViewHolder;
import com.auribises.firebase.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    DatabaseReference dbRef;
    FirebaseAuth auth;
    Query query;

    ArrayList<User> userList;
    ArrayList<String> keyList;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        //dbRef = FirebaseDatabase.getInstance().getReference();
        dbRef = FirebaseDatabase.getInstance().getReference().child("users");
        auth = FirebaseAuth.getInstance();

        userList = new ArrayList<>();

        //String uid = auth.getCurrentUser().getUid();

//        Product p = new Product(101,uid,"5Star",20);
//
//        String pKey = dbRef.child("products").push().getKey();
//        dbRef.child("products").child(pKey).setValue(p);


        query = dbRef.orderByKey();

        /*query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousKey) {
                User user = dataSnapshot.getValue(User.class);
                String key = dataSnapshot.getKey();
                userList.add(user);
                keyList.add(key);
                Log.i("TEST","Name: "+user.name);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/


        userAdapter = new UserAdapter(User.class,R.layout.user_list_item, UserViewHolder.class,query);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(userAdapter);
    }
}
