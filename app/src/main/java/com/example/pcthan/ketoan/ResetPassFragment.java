package com.example.pcthan.ketoan;


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
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResetPassFragment extends Fragment {


    public ResetPassFragment() {
        // Required empty public constructor
    }
    private EditText registerEmail;
    private Button btn_resetpass;
    private TextView goBack;

    private FrameLayout parentFramelayout;
    private FirebaseAuth firebaseAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_reset_pass, container, false);
        registerEmail=view.findViewById(R.id.foget_pass_email);
        btn_resetpass=view.findViewById(R.id.btn_reset);
        goBack=view.findViewById(R.id.goback);

        firebaseAuth=FirebaseAuth.getInstance();

        parentFramelayout=getActivity().findViewById(R.id.register);
        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        registerEmail.addTextChangedListener(new TextWatcher() {
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

        btn_resetpass.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                btn_resetpass.setEnabled(false);
                btn_resetpass.setTextColor(Color.argb(50,255,255,255));
                firebaseAuth.sendPasswordResetEmail(registerEmail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getActivity(),"Đã gửi email",Toast.LENGTH_SHORT);
                        }else{
                            String err=task.getException().getMessage();
                            Toast.makeText(getActivity(),err,Toast.LENGTH_SHORT);
                        }
                        btn_resetpass.setEnabled(true);
                        btn_resetpass.setTextColor(Color.rgb(255,255,255));
                    }
                });
            }
        });
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new SignFragment());
            }
        });
    }

    private void checkinput() {
        if (TextUtils.isEmpty(registerEmail.getText())){
            btn_resetpass.setEnabled(false);
            btn_resetpass.setTextColor(Color.argb(50,255,255,255));
        }else{
            btn_resetpass.setEnabled(true);
            btn_resetpass.setTextColor(Color.rgb(255,255,255));
        }
    }
    private void setFragment(Fragment fragment) {
        android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(parentFramelayout.getId(), fragment);
        fragmentTransaction.commit();
    }
}
