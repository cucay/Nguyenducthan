package com.example.pcthan.ketoan;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth=FirebaseAuth.getInstance();
        SystemClock.sleep(3000);
        Intent intentLogin=new Intent(MainActivity.this,Register.class);
        startActivity(intentLogin);
        finish();
    }

    @Override
    protected void onStart() {
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        if (firebaseUser==null){
            Intent login=new Intent(MainActivity.this,Register.class);
            startActivity(login);
            finish();
        }else{
            Intent intent=new Intent(MainActivity.this,Main2Activity.class);
            startActivity(intent);
            finish();
        }
        super.onStart();
    }
}
