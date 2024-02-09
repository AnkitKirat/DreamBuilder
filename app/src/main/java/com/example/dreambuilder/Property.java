package com.example.dreambuilder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Property extends AppCompatActivity {
    private  String id,sid,sname,sadds,samanities,sflat,sprice,sdescription;
    Cursor cursor;
    Bitmap image;
    TextView prop_name,description;
    RecyclerView recyclerView,recyclerView2;
   private ArrayList<String> arrflat = new ArrayList<>();
   private ArrayList<String> arrprice = new ArrayList<>();
   private ArrayList<String> arramanities = new ArrayList<>();
   private ArrayList<Integer> vector = new ArrayList<>();

   Button appointment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);
        java.text.DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
        DbHandler db = new DbHandler(Property.this);
        id = getIntent().getStringExtra("id");
        if (id != null) {
            cursor = db.getProperty(id);
        }


        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            sid = cursor.getString(0);
            sname = cursor.getString(1);
            sadds = cursor.getString(2);
            samanities = cursor.getString(3);
            sflat = cursor.getString(4);
            sprice = cursor.getString(5);
            sdescription = cursor.getString(6);
            @SuppressLint("Range") byte[] imageData = cursor.getBlob(cursor.getColumnIndex("image"));
            image = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);

            samanities = samanities.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("null,", "").replaceAll(", null", "").replaceAll("null", "");
            arramanities = new ArrayList<String>(Arrays.asList(samanities.split(",")));


            sflat = sflat.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("null,", "").replaceAll(", null", "").replaceAll("null", "");
            arrflat = new ArrayList<String>(Arrays.asList(sflat.split(",")));

            sprice = sprice.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("null,", "").replaceAll(", null", "").replaceAll("null", "");
             arrprice = new ArrayList<String>(Arrays.asList(sprice.split(",")));


        }


        prop_name = findViewById(R.id.prop_name);
        description = findViewById(R.id.description);
        prop_name.setText(sname);
        description.setText(sdescription);


        // gettting flat_type
        recyclerView = findViewById(R.id.recyclerView);
        CustomeAdapter2 customeAdapter2 = new CustomeAdapter2(Property.this, arrflat, arrprice);
        recyclerView.setAdapter(customeAdapter2);
        recyclerView.setLayoutManager(new LinearLayoutManager(Property.this));


        //identifying amanities for specified vector
        for (int i=0;i < arramanities.size();i++)
        {
               if(Objects.equals(arramanities.get(i), "Gated Security")) {
                   vector.add(R.drawable.gated);
               }
            else if(Objects.equals(arramanities.get(i), " Lift")) {
                 vector.add(R.drawable.lift);
             }

            else if(Objects.equals(arramanities.get(i), " Club House")) {
                vector.add(R.drawable.club);
            }

            else if(Objects.equals(arramanities.get(i), " Visitors Parking")) {
                vector.add(R.drawable.visi);
            }

            else if(Objects.equals(arramanities.get(i), " Swimming Pool")) {
                vector.add(R.drawable.swim);
            }

            else if(Objects.equals(arramanities.get(i), " Gym")) {
                vector.add(R.drawable.gym);
            }

            else if(Objects.equals(arramanities.get(i), " Children Play Area")) {
                 vector.add(R.drawable.children);
             }



            }




    //getting amanities
        recyclerView2 = findViewById(R.id.recyclerView2);
        CustomeAdapter3 customeAdapter3 = new CustomeAdapter3(Property.this, arramanities, vector);
        recyclerView2.setAdapter(customeAdapter3);
        recyclerView2.setLayoutManager(new LinearLayoutManager(Property.this,LinearLayoutManager.HORIZONTAL,false));


        //setting multiple images
        LinearLayout layout = (LinearLayout) findViewById(R.id.linear);

            ImageView imageView = new ImageView(this);
            //imageView.setId(i);
            imageView.setPadding(2, 2, 10, 2);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(680, 800);
            imageView.setLayoutParams(layoutParams);
            imageView.setImageBitmap(image);
            imageView.setScaleType(ImageView.ScaleType.MATRIX);
            layout.addView(imageView);



        //click appointment
        appointment = findViewById(R.id.appointment);
        appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(Property.this,Appointment.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
    }
}