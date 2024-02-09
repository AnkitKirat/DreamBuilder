package com.example.dreambuilder;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class Signup extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText mFullName,mEmail,mAdds,mPhone, mPassword;
    Button mRegisterBtn;
    TextView mLoginBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        DbHandler db = new DbHandler(Signup.this);
        mFullName = findViewById(R.id.name);
        mEmail = findViewById(R.id.email);
        mAdds = findViewById(R.id.adds);
        mPhone = findViewById(R.id.phone);
        mPassword = findViewById(R.id.password);

        mRegisterBtn=findViewById(R.id.register);
        mLoginBtn = findViewById(R.id.login);


        mRegisterBtn.setOnClickListener(new View.OnClickListener ()
        {
            @Override
            public void onClick(View v)
            {
                String name= mFullName.getText().toString().trim();
                String email = mEmail.getText().toString().trim();
                String adds= mAdds.getText().toString().trim();
                String phone= mPhone.getText().toString().trim();
                String password= mPassword.getText().toString().trim();


                if(TextUtils.isEmpty(name))
                {
                    mFullName.setError("Name is Required.");
                    return;
                }
                if(!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

                      }
                else {
                    mEmail.setError("Invalid Email ");
                }
                if(TextUtils.isEmpty(adds))
                {
                    mAdds.setError("Address is Required.");
                    return;
                }
                if(TextUtils.isEmpty(phone))
                {
                    mPhone.setError("Phone is Required.");
                    return;
                }
                if(phone.length() !=10)
                {
                    mPhone.setError("Phone Number Must be 10 Digit");
                    return;
                }

                if(TextUtils.isEmpty(password))
                {
                    mPassword.setError("Password is Required.");
                    return;
                }
                if(password.length() < 6)
                {
                    mPassword.setError("Password Must be >=6 Characters");
                    return;
                }


              long result =  db.addUser(new Register(email,name,adds,phone,password));
                if(result != -1){
                    Toast.makeText(Signup.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Signup.this, Logup.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(Signup.this, "User Registered Failed", Toast.LENGTH_SHORT).show();
                }


            }


        });
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Logup.class));
            }
        });
    }
}