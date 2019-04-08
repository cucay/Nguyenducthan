package com.example.pcthan.ketoan;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.FrameLayout;

public class Register extends AppCompatActivity {
    private FrameLayout frameLayout;
    public static boolean onResetPassFramegment=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        frameLayout=(FrameLayout) findViewById(R.id.register);
        setDefaultFragment(new SignFragment());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            if (onResetPassFramegment){
                onResetPassFramegment=false;
                setFragment(new SignFragment());
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void setDefaultFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(frameLayout.getId(),fragment);
        fragmentTransaction.commit();
    }
    private void setFragment(Fragment fragment) {
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(frameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }
}
