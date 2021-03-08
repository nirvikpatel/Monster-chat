package com.nirvik.monsterchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.nirvik.monsterchat.models.User;

public class SignUpActivity extends AppCompatActivity {

    FirebaseAuth auth;
    TextView name,email,password;
    Button singnup,login,googlesignin,facebooksignin;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        singnup=findViewById(R.id.signup);
        login=findViewById(R.id.login);
        googlesignin=findViewById(R.id.googlesignin);
        facebooksignin=findViewById(R.id.facebooksignin);
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        singnup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            User user=new User(name.getText().toString(),email.getText().toString(),password.getText().toString(),task.getResult().getUser().getUid().toString());
                            String a=task.getResult().getUser().getUid();
                            database.getReference().child("users").child(a).setValue(user);

                            Toast.makeText(SignUpActivity.this, "Register Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(SignUpActivity.this,LoginActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(SignUpActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        facebooksignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SignUpActivity.this, "This feature is in Developing Mode", Toast.LENGTH_SHORT).show();
            }
        });
        googlesignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SignUpActivity.this, "This feature is in Developing Mode", Toast.LENGTH_SHORT).show();
            }
        });






    }
}