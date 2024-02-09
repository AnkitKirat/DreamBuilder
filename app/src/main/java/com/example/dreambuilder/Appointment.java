package com.example.dreambuilder;

import androidx.appcompat.app.AppCompatActivity;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;

import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Appointment extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener  {
    private String id;
    private ImageView dateimg;
    TextView text,text2;
    Spinner spinner;

    Button schedule;

    Cursor cursor;

    String [] getTime_slots = new String[10];
    ArrayList<String> time_slots = new ArrayList<>();
    long result;
    String proj_name,selected_date,selected_time_slot=null,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        DbHandler db=new DbHandler(Appointment.this);
        id= getIntent().getStringExtra("id");
       cursor= db.getOneProject(id);
        cursor.moveToFirst();
       proj_name=cursor.getString(1);
        time_slots.add("Select Time Slots");
        dateimg = findViewById(R.id.dateimg);
        text=findViewById(R.id.date);
        text2=findViewById(R.id.text2);
        spinner = findViewById(R.id.spinner);
        schedule=findViewById(R.id.schedule);
        SharedPreferences sp = getSharedPreferences("my",MODE_PRIVATE);
         email= sp.getString("email",null);



        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat format1 = new SimpleDateFormat("dd");
        Date date = new Date();
        String s1= format.format(date);
        String s2= format1.format(date);

        text.setText(s1);
        dateimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c= Calendar.getInstance();
                int y=c.get(Calendar.YEAR);
                int m=c.get(Calendar.MONTH);
                int d=c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog d1=new DatePickerDialog(Appointment.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month+=1;
                        selected_date=dayOfMonth +"/"+month+"/"+year;


                        text.setText(selected_date);




                        String curDate= String.valueOf(s2);
                        int Currentdate= Integer.parseInt(curDate);


                        if( Currentdate > dayOfMonth){
                            text2.setVisibility(View.VISIBLE);
                            schedule.setEnabled(false);
                            spinner.setEnabled(false);

                            text2.setText("*Select Appropriate Date To Schedule The Appointment");
                        }
                        else {
                            time_slots.clear();
                            time_slots.add("11-12PM");
                            time_slots.add("12-1PM");
                            time_slots.add("1-2PM");
                            time_slots.add("2-3PM");
                            time_slots.add("3-4PM");
                            time_slots.add("4-5PM");
                            time_slots.add("5-6PM");
                            text2.setVisibility(View.INVISIBLE);
                            schedule.setEnabled(true);
                            spinner.setEnabled(true);

                                cursor =db.getAppointment(selected_date,proj_name);


                            if(cursor != null && cursor.getCount() != 0 && cursor.moveToFirst() ){
                                cursor.moveToFirst();
                                int j=0;
                                do {
                                    getTime_slots[j] =cursor.getString(6);
                                    j++;

                                }while (cursor.moveToNext());
                                List<String> arrgetTime_slots = new ArrayList<String>(Arrays.asList(getTime_slots));

                                if(getTime_slots.length >0) {
                                    for (int i = 0; i < arrgetTime_slots.size(); i++) {
                                        if (Objects.equals(arrgetTime_slots.get(i), "11-12PM")) {
                                            time_slots.remove("11-12PM");
                                        } else if (Objects.equals(arrgetTime_slots.get(i), "12-1PM")) {
                                            time_slots.remove("12-1PM");
                                        } else if (Objects.equals(arrgetTime_slots.get(i), "1-2PM")) {
                                            time_slots.remove("1-2PM");
                                        } else if (Objects.equals(arrgetTime_slots.get(i), "2-3PM")) {
                                            time_slots.remove("2-3PM");
                                        }  else if (Objects.equals(arrgetTime_slots.get(i), "3-4PM")) {
                                            time_slots.remove("3-4PM");
                                        }  else if (Objects.equals(arrgetTime_slots.get(i), "4-5PM")) {
                                            time_slots.remove("4-5PM");
                                        }  else if (Objects.equals(arrgetTime_slots.get(i), "5-6PM")) {
                                            time_slots.remove("5-6PM");
                                        }


                                    }
                                }
                            }



                        }
                    }
                },y,m,d);

                d1.show();
            }
        });





        spinner.setOnItemSelectedListener(this);



        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,R.layout.simple_spinner_item,time_slots);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinner.setAdapter(aa);

        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cursor = db.getUser(email);
                cursor.moveToFirst();
                if(selected_time_slot == null){
                    text2.setText("*Select Appropriate Time");
                    return;
                }
                if(cursor != null) {
                    String name = cursor.getString(1);
                    String phone = cursor.getString(3);
                     result = db.setAppointment(proj_name, selected_date, email, name, phone, selected_time_slot);
                }
                if(result != -1){
                    Toast.makeText(Appointment.this, "Appointment Scheduled Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Appointment.this,ScheduledAppointment.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(Appointment.this, "Error In Appointment Scheduling ", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        if(time_slots.get(position) != "Select Time Slots"){
            selected_time_slot = time_slots.get(position);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}