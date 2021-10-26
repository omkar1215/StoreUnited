package com.example.storeunited;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class SignupActivity extends AppCompatActivity {

    NetworkChangeListner networkChangeListnerr = new NetworkChangeListner();
    ImageView logo;
    EditText em,ps,ps2,mb,nm;
    Button signupp,alreadyhaveaccount;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuthh;
    String success="none";
    String succeess="none";
    String success1="none";
    String success2="none";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        em=(EditText)findViewById(R.id.editTextTextEmailAddress2);
        ps=(EditText)findViewById(R.id.editTextTextPassword2);
        ps2=(EditText)findViewById(R.id.editTextTextPassword3);
        mb=(EditText)findViewById(R.id.editTextPhone);
        nm=(EditText)findViewById(R.id.editTextTextPersonName);

        logo=(ImageView)findViewById(R.id.imageView7);

        progressBar=(ProgressBar)findViewById(R.id.progressbar);

        signupp=(Button)findViewById(R.id.button6);
        alreadyhaveaccount=(Button)findViewById(R.id.button8);


        firebaseAuthh = FirebaseAuth.getInstance();

        FirebaseUser firebaseUser = firebaseAuthh.getCurrentUser();

        if(firebaseUser != null){
            startActivity(new Intent(SignupActivity.this,HomeActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }


        alreadyhaveaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent al=new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(al);
                finish();
            }
        });

        signupp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eml=em.getText().toString().trim();
                String pas=ps.getText().toString();
                String pas2=ps2.getText().toString();
                String mbb=mb.getText().toString();
                String nmm=nm.getText().toString();
                String namePattern = "^[A-Za-z]+$";
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String passwordPattern= "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";

                if (eml.isEmpty()){
                    em.setError("Email Required");
                }else{
                    if (eml.matches(emailPattern))
                    {
                        success="true";
                    }else{
                        em.setError("Enter Valid Email ID");
                    }
                }
                if (pas.isEmpty()){
                    ps.setError("Password Required");
                }else{
                    if(!pas2.isEmpty()){
                        if(pas.matches(pas2)){
                            if (pas.matches(passwordPattern))
                            {
                                success1="true";
                            }else{
                                ps.setError("Password must contain minimum 8 characters at least 1 Alphabet, 1 Number and 1 Special Character");
                            }
                        }else{
                            ps.setError("Password Must Be Same");
                            ps2.setError("Password Must Be Same");
                        }
                    }else{
                        ps2.setError("Confirm Password Required");
                    }
                }
                if(nmm.isEmpty()){
                    nm.setError("Name Required");
                }else{
                    if (nmm.matches(namePattern))
                    {
                        succeess="true";
                    }else{
                        nm.setError("Enter Your Name Properly");
                    }
                }
                if (pas2.isEmpty()){
                    ps2.setError("Re-Password Required");
                }
                if (mbb.isEmpty()){
                    mb.setError("Mobile Number Required");
                }else{
                    if(mbb.length()!=10){
                        mb.setError("Mobile Number Must be of 10 Digits");
                    }else{
                        success2="true";
                    }
                }

                if(success=="true" && success1=="true" && success2=="true" && succeess=="true"){
                    progressBar.setVisibility(View.VISIBLE);
                    em.animate().alpha(0).setDuration(100);
                    nm.animate().alpha(0).setDuration(100);
                    signupp.animate().alpha(0).setDuration(100);
                    ps.animate().alpha(0).setDuration(100);
                    ps2.animate().alpha(0).setDuration(100);
                    mb.animate().alpha(0).setDuration(100);
                    alreadyhaveaccount.animate().alpha(0).setDuration(100);
                    logo.animate().alpha(0).setDuration(100);

                    Toast.makeText(SignupActivity.this, "Verifying", Toast.LENGTH_SHORT).show();
                    PhoneAuthProvider.getInstance().verifyPhoneNumber("+91" + mbb, 60, TimeUnit.SECONDS, SignupActivity.this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                        @Override
                        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                            progressBar.setVisibility(View.GONE);
                            em.animate().alpha(1).setDuration(100);
                            nm.animate().alpha(1).setDuration(100);
                            signupp.animate().alpha(1).setDuration(100);
                            ps.animate().alpha(1).setDuration(100);
                            ps2.animate().alpha(1).setDuration(100);
                            mb.animate().alpha(1).setDuration(100);
                            alreadyhaveaccount.animate().alpha(1).setDuration(100);
                            logo.animate().alpha(1).setDuration(100);
                        }

                        @Override
                        public void onVerificationFailed(@NonNull FirebaseException e) {
                            progressBar.setVisibility(View.GONE);
                            em.animate().alpha(1).setDuration(100);
                            nm.animate().alpha(1).setDuration(100);
                            signupp.animate().alpha(1).setDuration(100);
                            ps.animate().alpha(1).setDuration(100);
                            ps2.animate().alpha(1).setDuration(100);
                            mb.animate().alpha(1).setDuration(100);
                            alreadyhaveaccount.animate().alpha(1).setDuration(100);
                            logo.animate().alpha(1).setDuration(100);
                            Toast.makeText(SignupActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCodeSent(@NonNull String verificationID, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken){
                            Intent signup=new Intent(SignupActivity.this,OtpActivity.class);

                            signup.putExtra("finalemail",eml);
                            signup.putExtra("finalpassword",pas);
                            signup.putExtra("finalnumber",mbb);
                            signup.putExtra("finalname",nmm);
                            signup.putExtra("verficationID",verificationID);
                            progressBar.setVisibility(View.GONE);
                            em.animate().alpha(1).setDuration(100);
                            nm.animate().alpha(1).setDuration(100);
                            signupp.animate().alpha(1).setDuration(100);
                            ps.animate().alpha(1).setDuration(100);
                            ps2.animate().alpha(1).setDuration(100);
                            mb.animate().alpha(1).setDuration(100);
                            alreadyhaveaccount.animate().alpha(1).setDuration(100);
                            logo.animate().alpha(1).setDuration(100);
                            startActivity(signup);
                        }

                    });
                }else{
                    Toast.makeText(SignupActivity.this, "Please Enter Valid Details", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListnerr,filter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListnerr);
        super.onStop();
    }
}