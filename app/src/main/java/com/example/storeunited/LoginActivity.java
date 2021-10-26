package com.example.storeunited;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GithubAuthProvider;
import com.google.firebase.auth.GoogleAuthProvider;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    NetworkChangeListner networkChangeListner = new NetworkChangeListner();
    ImageView logoo;
    EditText email,password;
    Button Login,forgotpassword,signup;
    ProgressBar progressBarr;
    FirebaseAuth firebaseAuth;
    String succeess="none";
    String succeess1="none";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logoo=(ImageView)findViewById(R.id.imageView2);
        logoo.animate().alpha(1).setDuration(2000);
        email=(EditText)findViewById(R.id.editTextTextEmailAddress);
        password=(EditText)findViewById(R.id.editTextTextPassword);
        Login=(Button)findViewById(R.id.button2);
        progressBarr=(ProgressBar)findViewById(R.id.progressbarr);
        forgotpassword=(Button)findViewById(R.id.button3);
        signup=(Button)findViewById(R.id.button5);


        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if(firebaseUser != null){
            startActivity(new Intent(LoginActivity.this,HomeActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupp=new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(signupp);
                finish();
            }
        });

        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgotpass=new Intent(LoginActivity.this,ForgotpasswordActivity.class);
                startActivity(forgotpass);
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emll=email.getText().toString().trim();
                String pass=password.getText().toString();
                String emailPatternn = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String passwordPatternn= "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";

                if (emll.isEmpty()){
                    email.setError("Email Required");
                }else{
                    if (emll.matches(emailPatternn))
                    {
                        succeess="true";
                    }else{
                        email.setError("Enter Valid Email ID");
                    }
                }
                if (pass.isEmpty()){
                    password.setError("Password Required");
                }else{
                    if (pass.matches(passwordPatternn))
                    {
                        succeess1="true";
                    }else{
                        password.setError("Password must contain minimum 8 characters at least 1 Alphabet, 1 Number and 1 Special Character");
                    }
                }

                if(succeess=="true" && succeess1=="true"){

                    progressBarr.setVisibility(View.VISIBLE);
                    email.animate().alpha(0).setDuration(100);
                    Login.animate().alpha(0).setDuration(100);
                    password.animate().alpha(0).setDuration(100);
                    forgotpassword.animate().alpha(0).setDuration(100);
                    signup.animate().alpha(0).setDuration(100);
                    logoo.animate().alpha(0).setDuration(100);

                    Toast.makeText(LoginActivity.this, "All Details Are Valid", Toast.LENGTH_SHORT).show();
                    firebaseAuth=FirebaseAuth.getInstance();
                    firebaseAuth.signInWithEmailAndPassword(emll,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                Intent log=new Intent(LoginActivity.this,HomeActivity.class);
                                startActivity(log);
                                finish();

                                progressBarr.setVisibility(View.INVISIBLE);
                                email.animate().alpha(1).setDuration(100);
                                Login.animate().alpha(1).setDuration(100);
                                password.animate().alpha(1).setDuration(100);
                                forgotpassword.animate().alpha(1).setDuration(100);
                                signup.animate().alpha(1).setDuration(100);
                                logoo.animate().alpha(1).setDuration(100);

                            }else{
                                Toast.makeText(LoginActivity.this, emll+" Email Not Found", Toast.LENGTH_SHORT).show();

                                progressBarr.setVisibility(View.INVISIBLE);
                                email.animate().alpha(1).setDuration(100);
                                Login.animate().alpha(1).setDuration(100);
                                password.animate().alpha(1).setDuration(100);
                                forgotpassword.animate().alpha(1).setDuration(100);
                                signup.animate().alpha(1).setDuration(100);
                                logoo.animate().alpha(1).setDuration(100);

                            }
                        }
                    });
                }else{
                    Toast.makeText(LoginActivity.this, "Please Enter Valid Details", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListner,filter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListner);
        super.onStop();
    }
}