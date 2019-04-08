package com.example.pcthan.ketoan;


import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.pcthan.ketoan.Register.onResetPassFramegment;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignFragment extends Fragment {


    public SignFragment() {
        // Required empty public constructor
    }
    private TextView khongCoTaiKhoan;
    private FrameLayout parentFrameLayout;

    private TextView email_signin;
    private TextView matkhau_signin;
    private TextView quenmatkhau;
    private ImageButton dong;
    private Button signin_btn;

    private FirebaseAuth firebaseAuth;

    private ProgressBar progressBar;
    private String email_c="^[A-Za-z0-9_\\.]{6,32}@([a-zA-Z0-9]{2,12})(\\.[a-zA-Z]{2,12})+$";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign, container, false);
        khongCoTaiKhoan=view.findViewById(R.id.khongcotaikhoan);
        parentFrameLayout=getActivity().findViewById(R.id.register);

        email_signin=view.findViewById(R.id.sign_in_email);
        matkhau_signin=view.findViewById(R.id.sign_in_pass);

        progressBar=view.findViewById(R.id.progressBar2);

        dong= view.findViewById(R.id.dong);
        signin_btn=  view.findViewById(R.id.signin_btn);
        quenmatkhau=view.findViewById(R.id.sign_in_foget_pass);



        firebaseAuth=FirebaseAuth.getInstance();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        khongCoTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new SignupFragment());
            }
        });

        email_signin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkinput();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        matkhau_signin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkinput();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        dong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trangChu();
            }
        });
        quenmatkhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onResetPassFramegment=true;
                setFragment(new ResetPassFragment());
            }
        });
        signin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkEmailMatKhau();
            }
        });
    }

    private void checkinput() {

        if (!TextUtils.isEmpty(email_signin.getText())){
            if (!TextUtils.isEmpty(matkhau_signin.getText())){
                signin_btn.setEnabled(true);
                signin_btn.setTextColor(Color.rgb(255,255,255));
            }else{
                signin_btn.setEnabled(false);
                signin_btn.setTextColor(Color.argb(50,255,255,255));
            }
        }else{
            signin_btn.setEnabled(false);
            signin_btn.setTextColor(Color.argb(50,255,255,255));
        }
    }
    private void checkEmailMatKhau() {
        if (email_signin.getText().toString().matches(email_c)) {
            if (matkhau_signin.length() >= 8) {
                progressBar.setVisibility(View.VISIBLE);
                signin_btn.setEnabled(false);
                signin_btn.setTextColor(Color.argb(50, 255, 255, 255));
                firebaseAuth.signInWithEmailAndPassword(email_signin.getText().toString(), matkhau_signin.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                   trangChu();
                                } else {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    signin_btn.setEnabled(true);
                                    signin_btn.setTextColor(Color.rgb(255, 255, 255));
                                    String error = task.getException().getMessage();
                                    Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            } else {
                Toast.makeText(getActivity(), "Mat khau lon hon 8 kitu", Toast.LENGTH_SHORT).show();

            }
        } else {
            Toast.makeText(getActivity(), "Nhap dung dinh dang email", Toast.LENGTH_SHORT).show();

        }
    }

    private void trangChu() {
        Intent intent=new Intent(getActivity(),Main2Activity.class);
        startActivity(intent);
        getActivity().finish();
    }
    private void setFragment(Fragment fragment) {
        android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(parentFrameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }
}
