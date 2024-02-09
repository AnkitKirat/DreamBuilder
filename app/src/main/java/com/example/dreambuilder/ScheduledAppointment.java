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


public class ScheduledAppointment extends AppCompatActivity {


    DbHandler db;
    RecyclerView recyclerView;
    ArrayList<String> pro_name,date,time;
    Cursor cursor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduled_appointment);
        db = new DbHandler(ScheduledAppointment.this);
        SharedPreferences sp = getSharedPreferences("my",MODE_PRIVATE);
        String email= sp.getString("email",null);


        pro_name = new ArrayList<>();
        date = new ArrayList<>();
        time = new ArrayList<>();

        cursor= db.getUserAppointment(email);
        if(cursor == null){
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
        else{
            cursor.moveToFirst();
            do{
                pro_name.add(cursor.getString(1));
                date.add(cursor.getString(2));
                time.add(cursor.getString(6));
            }while (cursor.moveToNext());
        }
        recyclerView=findViewById(R.id.recyclerView_3);
        
        if(pro_name!= null) {
            UserAppointmentAdapter complaintAdapter1 = new UserAppointmentAdapter(ScheduledAppointment.this, pro_name, date, time);
            recyclerView.setAdapter(complaintAdapter1);
            recyclerView.setLayoutManager(new LinearLayoutManager(ScheduledAppointment.this));
        }
    }

}