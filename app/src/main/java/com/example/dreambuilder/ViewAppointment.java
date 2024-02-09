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


public class ViewAppointment extends AppCompatActivity {
    Button project,admin,queris,logout,appointment;

    DbHandler db;
    RecyclerView recyclerView_4;
    ArrayList<String> pname,date,cemail,cname,cphone,time;
    AppointmentAdapter AppointmentAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appointment);
        db = new DbHandler(ViewAppointment.this);
        SharedPreferences sp = getSharedPreferences("admin",MODE_PRIVATE);
        String email= sp.getString("email",null);
        if(email == null){
            Intent intent = new Intent(ViewAppointment.this, landingpage.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        project = findViewById(R.id.project);
        admin = findViewById(R.id.admin);
        appointment = findViewById(R.id.appointment);
        queris = findViewById(R.id.queries);
        logout = findViewById(R.id.logout);

        recyclerView_4=findViewById(R.id.recyclerView_admin);



        project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewAppointment.this, Admin.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewAppointment.this, Addadmin.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewAppointment.this, ViewAppointment.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        queris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewAppointment.this, UserQueries.class);
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
                Intent intent = new Intent(ViewAppointment.this, landingpage.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        pname = new ArrayList<>();
        date = new ArrayList<>();
        cemail = new ArrayList<>();
        cname = new ArrayList<>();
        cphone = new ArrayList<>();
        time = new ArrayList<>();
        displayComplaint();
        if(pname!= null) {
            AppointmentAdapter appointmentAdapter = new AppointmentAdapter(ViewAppointment.this, pname, date, cemail,cname,cphone,time);
            recyclerView_4.setAdapter(appointmentAdapter);
            recyclerView_4.setLayoutManager(new LinearLayoutManager(ViewAppointment.this));
        }

    }
    void displayComplaint(){
        Cursor cursor= db.getAllAppointment();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
        else{
            cursor.moveToFirst();
            do{
                pname.add(cursor.getString(1));
                date.add(cursor.getString(2));
                cemail.add(cursor.getString(3));
                cname.add(cursor.getString(4));
                cphone.add(cursor.getString(5));
                time.add(cursor.getString(6));
            }while (cursor.moveToNext());
        }
    }
}