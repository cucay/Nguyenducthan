package com.example.pcthan.ketoan;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment {


    public SignupFragment() {
        // Required empty public constructor
    }
    private TextView cotaikhoan;
    private FrameLayout parentFrameLayout;
    private EditText email;
    private EditText ten;
    private EditText matkhau;
    private EditText c_matkhau;

    private ImageButton imgDong;
    private Button btn_dangki;

    private ProgressBar progressBar;

    private FirebaseAuth firebaseAuth;

    private String email_kt="^[A-Za-z0-9_\\.]{6,32}@([a-zA-Z0-9]{2,12})(\\.[a-zA-Z]{2,12})+$";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        cotaikhoan=(TextView) view.findViewById(R.id.cotaikhoan);
        parentFrameLayout=getActivity().findViewById(R.id.register);

        email=view.findViewById(R.id.sign_up_email);
        ten=view.findViewById(R.id.sign_up_ten);
        matkhau=view.findViewById(R.id.sign_up_pass);
        c_matkhau=view.findViewById(R.id.sign_up_cpass);

        imgDong=view.findViewById(R.id.dong);
        btn_dangki=view.findViewById(R.id.button2);

        progressBar=view.findViewById(R.id.progressBar);

        firebaseAuth=FirebaseAuth.getInstance();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cotaikhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new SignFragment() );
            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInput();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        ten.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInput();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        matkhau.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInput();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        c_matkhau.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInput();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btn_dangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkEmailPass();
            }
        });

        imgDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trangChu();
            }
        });
    }

    private void checkEmailPass() {

        if (email.getText().toString().matches(email_kt)){
            if (matkhau.getText().toString().equals(c_matkhau.getText().toString())){

                progressBar.setVisibility(View.VISIBLE);
                btn_dangki.setEnabled(false);
                btn_dangki.setTextColor(Color.argb(50,255,255,255));
                firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(),matkhau.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    trangChu();
                                }else{
                                    progressBar.setVisibility(View.INVISIBLE);
                                    btn_dangki.setEnabled(false);
                                    btn_dangki.setTextColor(Color.argb(50,255,255,255));
                                    String error=task.getException().getMessage();
                                    Toast.makeText(getActivity(),error,Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }else{
                c_matkhau.setError("Mật khẩu không đúng");
            }
        }else{
            email.setError("Nhập đúng email");
        }

    }

    private void checkInput() {
        if (!TextUtils.isEmpty(email.getText())){
            if (!TextUtils.isEmpty(ten.getText())){
                if (!TextUtils.isEmpty(matkhau.getText()) && matkhau.length()>=8 ){
                    if (!TextUtils.isEmpty(c_matkhau.getText())){
                        btn_dangki.setEnabled(true);
                        btn_dangki.setTextColor(Color.rgb(255,255,255));
                    }
                }else {
                    btn_dangki.setEnabled(false);
                    btn_dangki.setTextColor(Color.argb(50,255,255,255));
                }
            }else{
                btn_dangki.setEnabled(false);
                btn_dangki.setTextColor(Color.argb(50,255,255,255));
            }
        }else{
            btn_dangki.setEnabled(false);
            btn_dangki.setTextColor(Color.argb(50,255,255,255));
        }
    }
    private void trangChu(){
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
