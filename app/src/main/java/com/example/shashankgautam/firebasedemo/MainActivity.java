package com.example.shashankgautam.firebasedemo;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mRootRefrence = firebaseDatabase.getReference();
    private DatabaseReference mChlildRrfrence = mRootRefrence.child("sample");
    //  private DatabaseReference mChlildRrfrence1=mRootRefrence.child("name");
    private TextView textView;
    private ListView listView;

    //HashMap<String, Integer> map = new HashMap<>();

    public class FirebaseData{
        public String key;
        public int avl;
        FirebaseData(){
            key="";
            avl=0;
        }
    }

    FirebaseData FBD = new FirebaseData();
    Integer m=1;

    //HashMap
    HashMap<Integer, FirebaseData> map = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //listView = (ListView) findViewById(R.id.list_id);
        textView = (TextView) findViewById(R.id.textView);
        //mChlildRrfrence.setValue("hello" );
    }

    @Override
    protected void onStart() {

        super.onStart();
        mChlildRrfrence.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {

                    //fetch Device ID
                    //FBD[m].key=postSnapshot.getKey();
                    //String key=postSnapshot.getKey();
                    //Integer avl=0;
                    FBD.key=postSnapshot.getKey();
                    //FBD[m].key=key;
                    //textView.setText(key);
                    for (DataSnapshot postSnapshot2: postSnapshot.getChildren()) {
                        // TODO: handle the post
                        //String message = postSnapshot2.getValue(String.class);
                        //textView.setText(message);
                        //String key = postSnapshot2.getKey();
                        FBD.avl = postSnapshot2.getValue(Integer.class);
                        //Integer message = 1001;

                        //fetch Available Data
                        //FBD[m].avl=message;
                        //textView.setText(String.valueOf(message));
                        //textView.setText(key + " : " + String.valueOf(message));

                    }
                    //Increment and intial value
                    //m++;
                    map.put(m, FBD);
                    m++;

                }

                //PrintMapData
                for(int i=1;i<m;i++){

                }


                //String message = dataSnapshot.getValue(String.class);
                //map.put("vishal", 10);
                //m=100;
                textView.setText(String.valueOf( map.get(1).key)+":"+String.valueOf(map.get(1).avl));
                //textView.setText(String.valueOf(FBD[0].key) + " : " + String.valueOf(FBD[0].avl));

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

}





