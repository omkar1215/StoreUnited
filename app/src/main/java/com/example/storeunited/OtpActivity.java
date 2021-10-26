package com.example.storeunited;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class OtpActivity extends AppCompatActivity {

    NetworkChangeListner networkChangeListner22 = new NetworkChangeListner();
    EditText code1,code2,code3,code4,code5,code6;
    Button verify,resend;
    TextView numbeer,titlee,timer;
    ProgressBar progressBar2;
    String verificationIDD,eemail,ppasword,nnumber,nname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        numbeer=(TextView)findViewById(R.id.textView8);
        numbeer.setText(String.format("+91-%s",getIntent().getStringExtra("finalnumber")));

        titlee=(TextView)findViewById(R.id.textView5);
        timer=(TextView)findViewById(R.id.textView4);

        long duration = TimeUnit.MINUTES.toMillis(1);

        new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long l) {
                String sDuration= String.format(Locale.ENGLISH,"%02d : %02d",TimeUnit.MILLISECONDS.toMinutes(l),TimeUnit.MILLISECONDS.toSeconds(l) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l)));

                timer.setText("Resend OTP in "+sDuration);

            }

            @Override
            public void onFinish() {
                timer.setVisibility(View.GONE);
                resend.setVisibility(View.VISIBLE);
            }
        }.start();

        code1=(EditText)findViewById(R.id.inputcode1);
        code2=(EditText)findViewById(R.id.inputcode2);
        code3=(EditText)findViewById(R.id.inputcode3);
        code4=(EditText)findViewById(R.id.inputcode4);
        code5=(EditText)findViewById(R.id.inputcode5);
        code6=(EditText)findViewById(R.id.inputcode6);
        verify=(Button)findViewById(R.id.button9);
        resend=(Button)findViewById(R.id.button4);
        progressBar2=(ProgressBar)findViewById(R.id.progressbar2);

        verificationIDD=getIntent().getStringExtra("verficationID");
        eemail=getIntent().getStringExtra("finalemail");
        ppasword=getIntent().getStringExtra("finalpassword");
        nnumber=getIntent().getStringExtra("finalnumber");
        nname=getIntent().getStringExtra("finalname");


        timer.setVisibility(View.VISIBLE);
        resend.setVisibility(View.GONE);
        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber("+91" + nnumber, 60, TimeUnit.SECONDS, OtpActivity.this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        titlee.setVisibility(View.VISIBLE);
                        numbeer.setVisibility(View.VISIBLE);
                        timer.setVisibility(View.VISIBLE);
                        resend.setVisibility(View.GONE);
                        code1.setVisibility(View.VISIBLE);
                        code2.setVisibility(View.VISIBLE);
                        code3.setVisibility(View.VISIBLE);
                        code4.setVisibility(View.VISIBLE);
                        code5.setVisibility(View.VISIBLE);
                        code6.setVisibility(View.VISIBLE);
                        progressBar2.setVisibility(View.INVISIBLE);
                        verify.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        titlee.setVisibility(View.VISIBLE);
                        numbeer.setVisibility(View.VISIBLE);
                        code1.setVisibility(View.VISIBLE);
                        code2.setVisibility(View.VISIBLE);
                        code3.setVisibility(View.VISIBLE);
                        code4.setVisibility(View.VISIBLE);
                        code5.setVisibility(View.VISIBLE);
                        code6.setVisibility(View.VISIBLE);
                        progressBar2.setVisibility(View.INVISIBLE);
                        verify.setVisibility(View.VISIBLE);
                        Toast.makeText(OtpActivity.this, "Verification Failed"+e, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCodeSent(@NonNull String verificationID, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken){
                        titlee.setVisibility(View.VISIBLE);
                        numbeer.setVisibility(View.VISIBLE);
                        timer.setVisibility(View.VISIBLE);
                        resend.setVisibility(View.GONE);
                        code1.setVisibility(View.VISIBLE);
                        code2.setVisibility(View.VISIBLE);
                        code3.setVisibility(View.VISIBLE);
                        code4.setVisibility(View.VISIBLE);
                        code5.setVisibility(View.VISIBLE);
                        code6.setVisibility(View.VISIBLE);
                        progressBar2.setVisibility(View.INVISIBLE);
                        verify.setVisibility(View.VISIBLE);
                        new CountDownTimer(duration, 1000) {
                            @Override
                            public void onTick(long l) {
                                String sDuration= String.format(Locale.ENGLISH,"%02d : %02d",TimeUnit.MILLISECONDS.toMinutes(l),TimeUnit.MILLISECONDS.toSeconds(l) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l)));

                                timer.setText("Resend OTP in "+sDuration);

                            }

                            @Override
                            public void onFinish() {
                                timer.setVisibility(View.GONE);
                                resend.setVisibility(View.VISIBLE);
                            }
                        }.start();
                        Toast.makeText(OtpActivity.this, "OTP Send to "+nnumber, Toast.LENGTH_SHORT).show();
                    }

                });
            }
        });

        setOTPInputs();



        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(code1.getText().toString().trim().isEmpty() || code2.getText().toString().trim().isEmpty() || code3.getText().toString().trim().isEmpty()|| code4.getText().toString().trim().isEmpty()|| code5.getText().toString().trim().isEmpty()|| code6.getText().toString().trim().isEmpty()){
                    Toast.makeText(OtpActivity.this, "Please Enter Valid Code", Toast.LENGTH_SHORT).show();
                }
                String code=code1.getText().toString() + code2.getText().toString() + code3.getText().toString() +code4.getText().toString() +code5.getText().toString() +code6.getText().toString();
                if(verificationIDD != null){
                    titlee.setVisibility(View.INVISIBLE);
                    numbeer.setVisibility(View.INVISIBLE);
                    timer.setVisibility(View.INVISIBLE);
                    resend.setVisibility(View.INVISIBLE);
                    code1.setVisibility(View.INVISIBLE);
                    code2.setVisibility(View.INVISIBLE);
                    code3.setVisibility(View.INVISIBLE);
                    code4.setVisibility(View.INVISIBLE);
                    code5.setVisibility(View.INVISIBLE);
                    code6.setVisibility(View.INVISIBLE);
                    progressBar2.setVisibility(View.VISIBLE);
                    verify.setVisibility(View.INVISIBLE);

                    PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(verificationIDD,code);
                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                FirebaseAuth.getInstance().createUserWithEmailAndPassword(eemail,ppasword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(task.isSuccessful()){

                                            titlee.setVisibility(View.VISIBLE);
                                            numbeer.setVisibility(View.VISIBLE);
                                            code1.setVisibility(View.VISIBLE);
                                            code2.setVisibility(View.VISIBLE);
                                            code3.setVisibility(View.VISIBLE);
                                            code4.setVisibility(View.VISIBLE);
                                            code5.setVisibility(View.VISIBLE);
                                            code6.setVisibility(View.VISIBLE);
                                            progressBar2.setVisibility(View.INVISIBLE);
                                            verify.setVisibility(View.VISIBLE);

                                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                                            DatabaseReference myRef = database.getReference("Database");

                                            String str11= FirebaseAuth.getInstance().getCurrentUser().getEmail();
                                            String ID = str11.replaceAll("[^a-zA-Z0-9]", "");
                                            Customer customer=new Customer(nname,eemail,ID,nnumber);
                                            myRef.child("Customer").child(ID).setValue(customer);
                                            Toast.makeText(OtpActivity.this, "Sign up Successful", Toast.LENGTH_SHORT).show();
                                            Intent ot=new Intent(OtpActivity.this,HomeActivity.class);
                                            ot.putExtra("userID",ID);
                                            startActivity(ot);
                                            finish();

                                        }else{
                                            titlee.setVisibility(View.VISIBLE);
                                            numbeer.setVisibility(View.VISIBLE);
                                            timer.setVisibility(View.VISIBLE);
                                            resend.setVisibility(View.VISIBLE);
                                            code1.setVisibility(View.VISIBLE);
                                            code2.setVisibility(View.VISIBLE);
                                            code3.setVisibility(View.VISIBLE);
                                            code4.setVisibility(View.VISIBLE);
                                            code5.setVisibility(View.VISIBLE);
                                            code6.setVisibility(View.VISIBLE);
                                            progressBar2.setVisibility(View.INVISIBLE);
                                            verify.setVisibility(View.VISIBLE);
                                            Toast.makeText(OtpActivity.this, "Email already Exists", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }else{
                                titlee.setVisibility(View.VISIBLE);
                                numbeer.setVisibility(View.VISIBLE);
                                timer.setVisibility(View.VISIBLE);
                                resend.setVisibility(View.VISIBLE);
                                code1.setVisibility(View.VISIBLE);
                                code2.setVisibility(View.VISIBLE);
                                code3.setVisibility(View.VISIBLE);
                                code4.setVisibility(View.VISIBLE);
                                code5.setVisibility(View.VISIBLE);
                                code6.setVisibility(View.VISIBLE);
                                progressBar2.setVisibility(View.INVISIBLE);
                                verify.setVisibility(View.VISIBLE);
                                Toast.makeText(OtpActivity.this, "Entered OTP is Invalid ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }

    private void setOTPInputs(){
        code1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(!s.toString().trim().isEmpty()){
                    code2.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        code2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(!s.toString().trim().isEmpty()){
                    code3.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        code3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(!s.toString().trim().isEmpty()){
                    code4.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        code4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(!s.toString().trim().isEmpty()){
                    code5.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        code5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(!s.toString().trim().isEmpty()){
                    code6.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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