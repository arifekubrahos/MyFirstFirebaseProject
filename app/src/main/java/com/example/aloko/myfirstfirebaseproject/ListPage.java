package com.example.aloko.myfirstfirebaseproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by aloko on 18.06.2017.
 */

public class ListPage extends AppCompatActivity {
    private ListView pastList;
    private ArrayAdapter arrayAdapter;
    private ArrayList<String> arrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.past_activity);
        arrayList=new ArrayList<>();
        pastList = (ListView) findViewById(R.id.pastCon);



        //hazır olarak kullandığımız arrayadapterimıza arraylistimizi atıyoruz
        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,android.R.id.text1,arrayList);
        //listvewimize da oluşturduğumuz içinde listemizin bulunduğu arrayadapterı atıyoruz
        pastList.setAdapter(arrayAdapter);


        //database bağlantımızı kuruyoruz
        DatabaseReference dbRef= FirebaseDatabase.getInstance().getReference().child("kullanıcılar");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    dataSnapshot.getKey();
                    Model model = ds.getValue(Model.class);
                    Log.d("Height",model.getHeight());
                    Log.d("Weight",model.getWeight());
                    Log.d("Result",model.getResult());
                    arrayList.add(model.getHeight()+model.getWeight()+model.getName());


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
}
