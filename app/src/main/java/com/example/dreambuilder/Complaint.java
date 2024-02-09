package com.example.dreambuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;





public class Complaint extends AppCompatActivity {
    EditText mFullName,mPhone,mDes;
    Button complaint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);
        DbHandler db = new DbHandler(Complaint.this);
        mFullName = findViewById(R.id.name);

        mDes = findViewById(R.id.des);

        mPhone = findViewById(R.id.phone);
        complaint=findViewById(R.id.complaint);

        complaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String name= mFullName.getText().toString().trim();
                String des = mDes.getText().toString().trim();

                String phone= mPhone.getText().toString().trim();





                if(TextUtils.isEmpty(name))
                {
                    mFullName.setError("Name Is Required.");
                    return;
                }

                if(TextUtils.isEmpty(des))
                {
                    mDes.setError("Description Is Required.");
                    return;
                }
                if(TextUtils.isEmpty(phone))
                {
                    mPhone.setError("Phone Number Is Required.");
                    return;
                }

                if(phone.length() !=10)
                {
                    mPhone.setError("Phone Number Must Be 10 Digit");
                    return;
                }

                      long result =  db.raiseComplaint(name,des,phone);
                if (result != -1){
                    Toast.makeText(Complaint.this, "Complaint is Raised", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Complaint.this, Admin.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(Complaint.this, " Failed to Raise Complaint", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Complaint.this, Complaint.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }

            }
        });
    }
}