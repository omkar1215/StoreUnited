package com.example.storeunited;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class ForgotpasswordActivity extends AppCompatActivity {

    NetworkChangeListner networkChangeListner1 = new NetworkChangeListner();
    TextView title1,success;
    EditText numb;
    Button submit;
    ProgressBar progressBbarr;
    String success22="none";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        title1=(TextView)findViewById(R.id.textView6);
        numb=(EditText)findViewById(R.id.editTextPhone2);
        submit=(Button)findViewById(R.id.button7);
        success=(TextView)findViewById(R.id.textView7);
        progressBbarr=(ProgressBar)findViewById(R.id.progressbbarr);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numm=numb.getText().toString();
                String emaillPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (numm.isEmpty()){
                    numb.setError("Email Required");
                }else{
                    if (numm.matches(emaillPattern))
                    {
                        success22="true";
                    }else{
                        numb.setError("Enter Valid Email Address");
                    }
                }
                if(success22=="true"){
                    progressBbarr.setVisibility(View.VISIBLE);
                    title1.animate().alpha(0).setDuration(100);
                    numb.animate().alpha(0).setDuration(100);
                    submit.animate().alpha(0).setDuration(100);
                    FirebaseAuth.getInstance().sendPasswordResetEmail(numm).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(ForgotpasswordActivity.this, "Password Reset Link Send", Toast.LENGTH_SHORT).show();
                                progressBbarr.setVisibility(View.GONE);
                                success.setVisibility(View.VISIBLE);
                            }else{
                                progressBbarr.setVisibility(View.GONE);
                                title1.animate().alpha(1).setDuration(100);
                                numb.animate().alpha(1).setDuration(100);
                                submit.animate().alpha(1).setDuration(100);
                                Toast.makeText(ForgotpasswordActivity.this, "Email Address Not Found", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });

    }

    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListner1,filter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListner1);
        super.onStop();
    }
}