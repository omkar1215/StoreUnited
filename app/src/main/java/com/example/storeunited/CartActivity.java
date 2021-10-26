package com.example.storeunited;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    NetworkChangeListner networkChangeListner2222 = new NetworkChangeListner();
    Button Home,shopnow;
    RecyclerView cartview;
    List<Cart2> mCarts;
    Cart2Adapter cart2Adapter;
    DatabaseReference MyReff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        String itemsincart=getIntent().getStringExtra("itemcount");
        Home=(Button)findViewById(R.id.button12);
        cartview=(RecyclerView)findViewById(R.id.cartrecycleview);

        ClearrAll();

        GetDataFrommFirebase();
        cartview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mCarts=new ArrayList<>();


/*        shopnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home2=new Intent(CartActivity.this,HomeActivity.class);
                startActivity(home2);
                finish();
            }
        }); */

        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backtohome=new Intent(CartActivity.this,HomeActivity.class);
                startActivity(backtohome);
                finish();
            }
        });


    }
    private void ClearrAll(){
        if(mCarts != null){
            mCarts.clear();
            if (cart2Adapter != null){
                cart2Adapter.notifyDataSetChanged();
            }
        }

        mCarts=new ArrayList<>();

    }

    private void GetDataFrommFirebase(){

        String strr11= FirebaseAuth.getInstance().getCurrentUser().getEmail();
        String useriiddd = strr11.replaceAll("[^a-zA-Z0-9]", "");
        Query query = FirebaseDatabase.getInstance().getReference().child("Database").child("Cart").child(useriiddd);


        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ClearrAll();
                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    Cart2 cartt=new Cart2();

                    cartt.setIimgg(snapshot1.child("iimgg").getValue().toString());
                    cartt.setNnamee(snapshot1.child("nname").getValue().toString());
                    cartt.setPpricee((Integer) snapshot1.child("ppricee").getValue());
                    mCarts.add(cartt);
                }
                Toast.makeText(CartActivity.this, useriiddd, Toast.LENGTH_SHORT).show();

                cart2Adapter = new Cart2Adapter(getApplicationContext(),mCarts);
                cartview.setAdapter(cart2Adapter);
                cart2Adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListner2222,filter);

        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListner2222);

        super.onStop();
    }
}