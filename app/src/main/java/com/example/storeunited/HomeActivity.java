package com.example.storeunited;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    NetworkChangeListner networkChangeListner2 = new NetworkChangeListner();
    ImageView menu,search;
    RecyclerView snneakers,smmartwatches,waatches,suunglasses,spportshoes;
    List<Sneakers> sneakers;
    List<Smartwatches> smartwatches;
    List<Watches> watches;
    List<Sunglasses> sunglasses;
    List<Sportshoes> sportshoes;
    DatabaseReference MyRef;
    SneakersAdapter sneakersAdapter;
    SmartwatchesAdapter smartwatchesAdapter;
    WatchesAdapter watchesAdapter;
    SunglassesAdapter sunglassesAdapter;
    SportshoesAdapter sportshoesAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        menu=(ImageView)findViewById(R.id.imageView4);
        search=(ImageView)findViewById(R.id.imageView5);
        snneakers=(RecyclerView)findViewById(R.id.recyclerView2);
        smmartwatches=(RecyclerView)findViewById(R.id.recyclerView3);
        waatches=(RecyclerView)findViewById(R.id.recyclerView4);
        suunglasses=(RecyclerView)findViewById(R.id.recyclerView5);
        spportshoes=(RecyclerView)findViewById(R.id.recyclerView6);

        snneakers.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        smmartwatches.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        waatches.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        suunglasses.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        spportshoes.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        MyRef=FirebaseDatabase.getInstance().getReference();

        sneakers=new ArrayList<>();
        smartwatches=new ArrayList<>();
        watches=new ArrayList<>();
        sunglasses=new ArrayList<>();
        sportshoes=new ArrayList<>();

        ClearAll();

        GetDataFromFirebase();

        ClearAll1();

        GetDataFromFirebase1();

        ClearAll2();

        GetDataFromFirebase2();

        ClearAll3();

        GetDataFromFirebase3();

        ClearAll4();

        GetDataFromFirebase4();
       /*sneakers.add(new Sneakers("Jordan 1"));
        sneakers.add(new Sneakers("Jordan 2"));
        sneakers.add(new Sneakers("Jordan 3"));
        sneakers.add(new Sneakers("Jordan 4"));

        SneakersAdapter sneakersAdapter=new SneakersAdapter(this,sneakers);
        snneakers.setAdapter(sneakersAdapter);
        snneakers.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));*/


    }

    private void GetDataFromFirebase1(){
        Query query1 = MyRef.child("Database").child("smartwatches");

        query1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ClearAll1();
                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    Smartwatches smartwatchess=new Smartwatches();

                    smartwatchess.setImg(snapshot1.child("img").getValue().toString());

                    smartwatches.add(smartwatchess);

                }

                smartwatchesAdapter = new SmartwatchesAdapter(getApplicationContext(),smartwatches);
                smmartwatches.setAdapter(smartwatchesAdapter);
                smartwatchesAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void ClearAll1(){

        if(smartwatches != null){
            smartwatches.clear();
            if (watchesAdapter != null){
                watchesAdapter.notifyDataSetChanged();
            }
        }


        smartwatches=new ArrayList<>();

    }


    private void ClearAll(){
        if(sneakers != null){
            sneakers.clear();
            if (sneakersAdapter != null){
                sneakersAdapter.notifyDataSetChanged();
            }
        }

        sneakers=new ArrayList<>();

    }

    private void GetDataFromFirebase(){
        Query query = MyRef.child("Database").child("sneakers");


        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ClearAll();
                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    Sneakers sneakerss=new Sneakers();

                    sneakerss.setImg(snapshot1.child("img").getValue().toString());
                    sneakerss.setID(snapshot1.child("ID").getValue().toString());
                    sneakerss.setSize(snapshot1.child("size").getValue().toString());
                    String userid=getIntent().getStringExtra("userID");
                    sneakerss.setUserID(userid);

                    sneakers.add(sneakerss);

                }

                sneakersAdapter = new SneakersAdapter(getApplicationContext(),sneakers);
                snneakers.setAdapter(sneakersAdapter);
                sneakersAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    private void ClearAll2(){

        if(watches != null){
            watches.clear();
            if (watchesAdapter != null){
                watchesAdapter.notifyDataSetChanged();
            }
        }

        watches=new ArrayList<>();

    }

    private void GetDataFromFirebase2(){
        Query query2 = MyRef.child("Database").child("watches");




        query2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ClearAll2();
                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    Watches watchess=new Watches();

                    watchess.setImg(snapshot1.child("img").getValue().toString());

                    watches.add(watchess);

                }

                watchesAdapter = new WatchesAdapter(getApplicationContext(),watches);
                waatches.setAdapter(watchesAdapter);
                watchesAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



    private void ClearAll3(){

        if(sunglasses != null){
            sunglasses.clear();
            if (sunglassesAdapter != null){
                sunglassesAdapter.notifyDataSetChanged();
            }
        }


        sunglasses=new ArrayList<>();
    }

    private void GetDataFromFirebase3(){
        Query query3 = MyRef.child("Database").child("sunglasses");

        query3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ClearAll3();
                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    Sunglasses sunglassess=new Sunglasses();

                    sunglassess.setImg(snapshot1.child("img").getValue().toString());

                    sunglasses.add(sunglassess);

                }

                sunglassesAdapter = new SunglassesAdapter(getApplicationContext(),sunglasses);
                suunglasses.setAdapter(sunglassesAdapter);
                sunglassesAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void ClearAll4(){

        if(sportshoes != null){
            sportshoes.clear();
            if (sportshoesAdapter != null){
                sportshoesAdapter.notifyDataSetChanged();
            }
        }

        sportshoes=new ArrayList<>();

    }

    private void GetDataFromFirebase4(){
        Query query4 = MyRef.child("Database").child("sportshoes");


        query4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ClearAll4();
                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    Sportshoes sportshoess=new Sportshoes();

                    sportshoess.setImg(snapshot1.child("img").getValue().toString());

                    sportshoes.add(sportshoess);

                }

                sportshoesAdapter = new SportshoesAdapter(getApplicationContext(),sportshoes);
                spportshoes.setAdapter(sportshoesAdapter);
                sportshoesAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListner2,filter);

        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListner2);

        super.onStop();
    }
}