package com.example.storeunited;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SneakersActivity extends AppCompatActivity {

    NetworkChangeListner networkChangeListner22 = new NetworkChangeListner();
    ImageView imgg;
    Button addtocart,buynow,cart;
    TextView namee,price,size,s1,s2,s3,s4,s5,descrip;
    TabLayout indicator;
    ViewPager sliderpager;
    FirebaseUser firebaseUser;
    DatabaseReference newReff,newReff2;
    List<Slide> lstSlides;
    String finalshoesize;
    SliderPagerAdapter adapter;
    Timer timer=new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sneakers);

        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        String id=getIntent().getStringExtra("SneakerID");
        String siz=getIntent().getStringExtra("SneakerSIZE");

        lstSlides=new ArrayList<>();

        imgg=(ImageView)findViewById(R.id.imageView13);
        indicator=(TabLayout)findViewById(R.id.indicator);
        sliderpager=(ViewPager)findViewById(R.id.slider_pager);
        namee=(TextView)findViewById(R.id.textView9);
        price=(TextView)findViewById(R.id.textView12);
        addtocart=(Button)findViewById(R.id.button10);
        cart=(Button)findViewById(R.id.button13);
        buynow=(Button)findViewById(R.id.button11);
        size=(TextView)findViewById(R.id.textView13);
        s1=(TextView)findViewById(R.id.textView14);
        s2=(TextView)findViewById(R.id.textView15);
        s3=(TextView)findViewById(R.id.textView16);
        s4=(TextView)findViewById(R.id.textView17);
        s5=(TextView)findViewById(R.id.textView18);
        descrip=(TextView)findViewById(R.id.textView19);


        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent carrt=new Intent(SneakersActivity.this,CartActivity.class);
                startActivity(carrt);
            }
        });

        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(finalshoesize!=null){

                    newReff2=FirebaseDatabase.getInstance().getReference().child("Database").child("Products").child(id);
                    newReff2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String namee=snapshot.child("name").getValue().toString();
                            String imgg=snapshot.child("img").getValue().toString();
                            String priceee=snapshot.child("Price").getValue().toString();
                            String str1= FirebaseAuth.getInstance().getCurrentUser().getEmail();
                            String userid = str1.replaceAll("[^a-zA-Z0-9]", "");
                            int finalshoesize2 = Integer.parseInt(finalshoesize);
                            int priceee2 = Integer.parseInt(priceee);
                            Cart cart=new Cart(id,namee,imgg,finalshoesize2,priceee2);
                            FirebaseDatabase.getInstance().getReference().child("Database").child("Cart").child("ID"+userid).child(id).setValue(cart);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }else{
                    Toast.makeText(SneakersActivity.this, "Please select the size", Toast.LENGTH_SHORT).show();
                }
            }
        });

        s1.setBackgroundResource(R.drawable.sizeselector);
        s1.setTextColor(Color.parseColor("#000000"));

        if(siz.equals("5")){
            s2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    s2.setBackgroundResource(R.drawable.sizeselector);
                    s2.setTextColor(Color.parseColor("#000000"));
                    s1.setBackgroundResource(R.drawable.sizeunselector);
                    s1.setTextColor(Color.parseColor("#FFFFFF"));
                    s3.setBackgroundResource(R.drawable.sizeunselector);
                    s3.setTextColor(Color.parseColor("#FFFFFF"));
                    s4.setBackgroundResource(R.drawable.sizeunselector);
                    s4.setTextColor(Color.parseColor("#FFFFFF"));
                    s5.setBackgroundResource(R.drawable.sizeunselector);
                    s5.setTextColor(Color.parseColor("#FFFFFF"));
                    finalshoesize=s2.getText().toString().trim();
                }
            });
            s1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    s1.setBackgroundResource(R.drawable.sizeselector);
                    s1.setTextColor(Color.parseColor("#000000"));
                    s2.setBackgroundResource(R.drawable.sizeunselector);
                    s2.setTextColor(Color.parseColor("#FFFFFF"));
                    s3.setBackgroundResource(R.drawable.sizeunselector);
                    s3.setTextColor(Color.parseColor("#FFFFFF"));
                    s4.setBackgroundResource(R.drawable.sizeunselector);
                    s4.setTextColor(Color.parseColor("#FFFFFF"));
                    s5.setBackgroundResource(R.drawable.sizeunselector);
                    s5.setTextColor(Color.parseColor("#FFFFFF"));
                    finalshoesize=s1.getText().toString().trim();
                }
            });
            s3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    s3.setBackgroundResource(R.drawable.sizeselector);
                    s3.setTextColor(Color.parseColor("#000000"));
                    s1.setBackgroundResource(R.drawable.sizeunselector);
                    s1.setTextColor(Color.parseColor("#FFFFFF"));
                    s2.setBackgroundResource(R.drawable.sizeunselector);
                    s2.setTextColor(Color.parseColor("#FFFFFF"));
                    s4.setBackgroundResource(R.drawable.sizeunselector);
                    s4.setTextColor(Color.parseColor("#FFFFFF"));
                    s5.setBackgroundResource(R.drawable.sizeunselector);
                    s5.setTextColor(Color.parseColor("#FFFFFF"));
                    finalshoesize=s3.getText().toString().trim();
                }
            });
            s4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    s4.setBackgroundResource(R.drawable.sizeselector);
                    s4.setTextColor(Color.parseColor("#000000"));
                    s1.setBackgroundResource(R.drawable.sizeunselector);
                    s1.setTextColor(Color.parseColor("#FFFFFF"));
                    s3.setBackgroundResource(R.drawable.sizeunselector);
                    s3.setTextColor(Color.parseColor("#FFFFFF"));
                    s2.setBackgroundResource(R.drawable.sizeunselector);
                    s2.setTextColor(Color.parseColor("#FFFFFF"));
                    s5.setBackgroundResource(R.drawable.sizeunselector);
                    s5.setTextColor(Color.parseColor("#FFFFFF"));
                    finalshoesize=s4.getText().toString().trim();
                }
            });
            s5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    s5.setBackgroundResource(R.drawable.sizeselector);
                    s5.setTextColor(Color.parseColor("#000000"));
                    s1.setBackgroundResource(R.drawable.sizeunselector);
                    s1.setTextColor(Color.parseColor("#FFFFFF"));
                    s3.setBackgroundResource(R.drawable.sizeunselector);
                    s3.setTextColor(Color.parseColor("#FFFFFF"));
                    s2.setBackgroundResource(R.drawable.sizeunselector);
                    s2.setTextColor(Color.parseColor("#FFFFFF"));
                    s4.setBackgroundResource(R.drawable.sizeunselector);
                    s4.setTextColor(Color.parseColor("#FFFFFF"));
                    finalshoesize=s5.getText().toString().trim();
                }
            });
        }
        if(siz.equals("4")){
            s2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    s2.setBackgroundResource(R.drawable.sizeselector);
                    s2.setTextColor(Color.parseColor("#000000"));
                    s1.setBackgroundResource(R.drawable.sizeunselector);
                    s1.setTextColor(Color.parseColor("#FFFFFF"));
                    s3.setBackgroundResource(R.drawable.sizeunselector);
                    s3.setTextColor(Color.parseColor("#FFFFFF"));
                    s4.setBackgroundResource(R.drawable.sizeunselector);
                    s4.setTextColor(Color.parseColor("#FFFFFF"));
                    finalshoesize=s2.getText().toString().trim();
                }
            });
            s1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    s1.setBackgroundResource(R.drawable.sizeselector);
                    s1.setTextColor(Color.parseColor("#000000"));
                    s2.setBackgroundResource(R.drawable.sizeunselector);
                    s2.setTextColor(Color.parseColor("#FFFFFF"));
                    s3.setBackgroundResource(R.drawable.sizeunselector);
                    s3.setTextColor(Color.parseColor("#FFFFFF"));
                    s4.setBackgroundResource(R.drawable.sizeunselector);
                    s4.setTextColor(Color.parseColor("#FFFFFF"));
                    finalshoesize=s1.getText().toString().trim();
                }
            });
            s3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    s3.setBackgroundResource(R.drawable.sizeselector);
                    s3.setTextColor(Color.parseColor("#000000"));
                    s1.setBackgroundResource(R.drawable.sizeunselector);
                    s1.setTextColor(Color.parseColor("#FFFFFF"));
                    s2.setBackgroundResource(R.drawable.sizeunselector);
                    s2.setTextColor(Color.parseColor("#FFFFFF"));
                    s4.setBackgroundResource(R.drawable.sizeunselector);
                    s4.setTextColor(Color.parseColor("#FFFFFF"));
                    finalshoesize=s3.getText().toString().trim();
                }
            });
            s4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    s4.setBackgroundResource(R.drawable.sizeselector);
                    s4.setTextColor(Color.parseColor("#000000"));
                    s1.setBackgroundResource(R.drawable.sizeunselector);
                    s1.setTextColor(Color.parseColor("#FFFFFF"));
                    s3.setBackgroundResource(R.drawable.sizeunselector);
                    s3.setTextColor(Color.parseColor("#FFFFFF"));
                    s2.setBackgroundResource(R.drawable.sizeunselector);
                    s2.setTextColor(Color.parseColor("#FFFFFF"));
                    finalshoesize=s4.getText().toString().trim();
                }
            });

        }
        if(siz.equals("3")){
            s2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    s2.setBackgroundResource(R.drawable.sizeselector);
                    s2.setTextColor(Color.parseColor("#000000"));
                    s1.setBackgroundResource(R.drawable.sizeunselector);
                    s1.setTextColor(Color.parseColor("#FFFFFF"));
                    s3.setBackgroundResource(R.drawable.sizeunselector);
                    s3.setTextColor(Color.parseColor("#FFFFFF"));
                    finalshoesize=s2.getText().toString().trim();
                }
            });
            s1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    s1.setBackgroundResource(R.drawable.sizeselector);
                    s1.setTextColor(Color.parseColor("#000000"));
                    s2.setBackgroundResource(R.drawable.sizeunselector);
                    s2.setTextColor(Color.parseColor("#FFFFFF"));
                    s3.setBackgroundResource(R.drawable.sizeunselector);
                    s3.setTextColor(Color.parseColor("#FFFFFF"));
                    finalshoesize=s1.getText().toString().trim();
                }
            });
            s3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    s3.setBackgroundResource(R.drawable.sizeselector);
                    s3.setTextColor(Color.parseColor("#000000"));
                    s1.setBackgroundResource(R.drawable.sizeunselector);
                    s1.setTextColor(Color.parseColor("#FFFFFF"));
                    s2.setBackgroundResource(R.drawable.sizeunselector);
                    s2.setTextColor(Color.parseColor("#FFFFFF"));
                    finalshoesize=s3.getText().toString().trim();
                }
            });
        }
        if(siz.equals("2")){
            s2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    s2.setBackgroundResource(R.drawable.sizeselector);
                    s2.setTextColor(Color.parseColor("#000000"));
                    s1.setBackgroundResource(R.drawable.sizeunselector);
                    s1.setTextColor(Color.parseColor("#FFFFFF"));
                    finalshoesize=s2.getText().toString().trim();
                }
            });
            s1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    s1.setBackgroundResource(R.drawable.sizeselector);
                    s1.setTextColor(Color.parseColor("#000000"));
                    s2.setBackgroundResource(R.drawable.sizeunselector);
                    s2.setTextColor(Color.parseColor("#FFFFFF"));
                    finalshoesize=s1.getText().toString().trim();
                }
            });
        }
        if(siz.equals("1")){
            s1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    s1.setBackgroundResource(R.drawable.sizeselector);
                    s1.setTextColor(Color.parseColor("#000000"));
                    finalshoesize=s1.getText().toString().trim();
                }
            });
        }



        newReff=FirebaseDatabase.getInstance().getReference().child("Database").child("Products").child(id);
        newReff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name=snapshot.child("name").getValue().toString();
                String img=snapshot.child("img").getValue().toString();
                String pricee=snapshot.child("Price").getValue().toString();
                String s11=snapshot.child("size1").getValue().toString();
                String s22=snapshot.child("size2").getValue().toString();
                String s33=snapshot.child("size3").getValue().toString();
                String s44=snapshot.child("size4").getValue().toString();
                String s55=snapshot.child("size5").getValue().toString();
                String a1=snapshot.child("img").getValue().toString();
                String a2=snapshot.child("img1").getValue().toString();
                String a3=snapshot.child("img2").getValue().toString();
                String a4=snapshot.child("img3").getValue().toString();
                String a5=snapshot.child("img4").getValue().toString();
                String descripp=snapshot.child("description").getValue().toString();

                Slide slide1=new Slide();

                slide1.setIimmgg(a1);
                lstSlides.add(slide1);
                slide1.setIimmgg(a2);
                lstSlides.add(slide1);
                slide1.setIimmgg(a3);
                lstSlides.add(slide1);
                slide1.setIimmgg(a4);
                lstSlides.add(slide1);
                slide1.setIimmgg(a5);
                lstSlides.add(slide1);
                timer.scheduleAtFixedRate(new SneakersActivity .SliderTimer(),4000,5000);
                adapter = new SliderPagerAdapter(getApplicationContext(),lstSlides);
                sliderpager.setAdapter(adapter);
                indicator.setupWithViewPager(sliderpager,true);
                adapter.notifyDataSetChanged();


                //Glide.with(SneakersActivity.this).load(img).into(imgg);
                price.setText("₹ "+pricee);
                size.setText("SIZE : ");

                if(siz.equals("5")){
                    s1.setText(s11);
                    s2.setText(s22);
                    s3.setText(s33);
                    s4.setText(s44);
                    s5.setText(s55);
                    finalshoesize=s11;
                }
                if(siz.equals("4")){
                    s1.setText(s11);
                    s2.setText(s22);
                    s3.setText(s33);
                    s4.setText(s44);
                    finalshoesize=s11;
                }
                if(siz.equals("3")){
                    s1.setText(s11);
                    s2.setText(s22);
                    s3.setText(s33);
                    finalshoesize=s11;
                }
                if(siz.equals("2")){
                    s1.setText(s11);
                    s2.setText(s22);
                    finalshoesize=s11;
                }
                if(siz.equals("1")){
                    s1.setText(s11);
                    finalshoesize=s11;
                }

                descrip.setText(descripp);
                namee.setText(name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        

    }

    class SliderTimer extends TimerTask{
        @Override
        public void run() {
            SneakersActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (sliderpager.getCurrentItem()<lstSlides.size()-1){
                        sliderpager.setCurrentItem(sliderpager.getCurrentItem()+1);
                    }else{
                        sliderpager.setCurrentItem(0);
                    }
                }
            });
        }
    }

    private void ClearAll11(){

        if(lstSlides!= null){
            lstSlides.clear();
            if (adapter != null){
                adapter.notifyDataSetChanged();
            }
        }


        lstSlides=new ArrayList<>();

    }

    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListner22,filter);

        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListner22);

        super.onStop();
    }
}