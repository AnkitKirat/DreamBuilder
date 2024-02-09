package com.example.dreambuilder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ImageView prof_img;
    DbHandler db;
    RecyclerView recyclerView;
    ArrayList<String> name,adds,id;
    List<Bitmap> images;
   // ComplaintAdapter complaintAdapter;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DbHandler(MainActivity.this);


       SharedPreferences sp = getSharedPreferences("my",MODE_PRIVATE);
        String email= sp.getString("email",null);

        SharedPreferences sp1 = getSharedPreferences("admin",MODE_PRIVATE);
        String email1= sp1.getString("email",null);
        if(email == null && email1 == null){
            Intent intent = new Intent(MainActivity.this, landingpage.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if (email1 != null) {
            Intent intent = new Intent(MainActivity.this, Admin.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }





        recyclerView=findViewById(R.id.recyclerView);
        prof_img =  findViewById(R.id.prof_img);
       prof_img.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               PopupMenu popup = new PopupMenu(MainActivity.this, prof_img);
               //Inflating the Popup using xml file
               popup.getMenuInflater()
                       .inflate(R.menu.poupup_menu, popup.getMenu());

               //registering popup with OnMenuItemClickListener
               popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                   public boolean onMenuItemClick(MenuItem item) {
                       String[] id = getResources().getResourceName(item.getItemId()).split("\\/");
                       String title = id[1];

                            if( title.equals("Profile"))
                            {
                                Intent intent = new Intent(MainActivity.this, Profile.class);

                                startActivity(intent);
                            }
                       if( title.equals("Appointment"))
                       {
                           Intent intent = new Intent(MainActivity.this, ScheduledAppointment.class);

                           startActivity(intent);
                       }
                       if( title.equals("Complaint"))
                       {
                           Intent intent = new Intent(MainActivity.this, Complaint.class);

                           startActivity(intent);
                       }
                       if( title.equals("logout"))
                       {
                           SharedPreferences.Editor ed = sp.edit();
                           ed.clear();
                           ed.apply();
                           Intent intent = new Intent(MainActivity.this, landingpage.class);
                           intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                           startActivity(intent);
                       }

                       return true;
                   }
               });

               popup.show(); //showing popup menu
           }
       });


        id = new ArrayList<>();
        name = new ArrayList<>();
        adds = new ArrayList<>();
        images = new ArrayList<Bitmap>();

        displayComplaint();

        CustomeAdapter complaintAdapter1 = new CustomeAdapter(MainActivity.this,id,name,adds,images);
        recyclerView.setAdapter(complaintAdapter1);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        }

    void displayComplaint(){
        Cursor cursor= db.getProject();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
        else{
            while (cursor.moveToNext()){
                id.add(cursor.getString(0));
                name.add(cursor.getString(1));
                adds.add(cursor.getString(2));
                @SuppressLint("Range") byte[] imageData = cursor.getBlob(cursor.getColumnIndex("image"));
                Bitmap image = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
                images.add(image);
            }
        }
    }

    }
