package com.example.dreambuilder;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.Toast;


import java.util.ArrayList;


public class UserQueries extends AppCompatActivity {
    Button project,admin,queris,logout,appointment;

    DbHandler db;
    RecyclerView recyclerView;
    ArrayList<String> name,des,phone;
    ComplaintAdapter complaintAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_queries);
        db = new DbHandler(UserQueries.this);
        SharedPreferences sp = getSharedPreferences("admin",MODE_PRIVATE);
        String email= sp.getString("email",null);
        if(email == null){
            Intent intent = new Intent(UserQueries.this, landingpage.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        project = findViewById(R.id.project);
        admin = findViewById(R.id.admin);
        appointment = findViewById(R.id.appointment);
        queris = findViewById(R.id.queries);
        logout = findViewById(R.id.logout);

        recyclerView=findViewById(R.id.recyclerView);



        project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserQueries.this, Admin.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserQueries.this, Addadmin.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserQueries.this, ViewAppointment.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        queris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserQueries.this, UserQueries.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor ed = sp.edit();
                ed.clear();
                ed.apply();
                Intent intent = new Intent(UserQueries.this, landingpage.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        name = new ArrayList<>();
        des = new ArrayList<>();
        phone = new ArrayList<>();
        displayComplaint();
        if(name!= null) {
            ComplaintAdapter complaintAdapter1 = new ComplaintAdapter(UserQueries.this, name, des, phone);
            recyclerView.setAdapter(complaintAdapter1);
            recyclerView.setLayoutManager(new LinearLayoutManager(UserQueries.this));
        }

    }
    void displayComplaint(){
        Cursor cursor= db.getComplaint();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
        else{
            cursor.moveToFirst();
            do{
                name.add(cursor.getString(0));
                des.add(cursor.getString(1));
                phone.add(cursor.getString(2));
            }while (cursor.moveToNext());
        }
    }
}