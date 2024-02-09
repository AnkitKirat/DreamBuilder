package com.example.dreambuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.io.FileNotFoundException;
import java.util.List;

public class Admin extends AppCompatActivity {
     Button project,admin,queris,logout,add,appointment;
     EditText mname,madds,description1,rk1,bhk1,bhk2,bhk3,bhk4;



     CheckBox checkBox1,checkBox2,checkBox3,checkBox4,checkBox5,checkBox6,checkBox7 ,check1,check2,check3,check4,check5;
     TextView img1;
     ImageView targetImage;
    public static final int pick_image = 100;
    private Uri imagepath;
    private Bitmap imageTo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        DbHandler db =new DbHandler(Admin.this);
       SharedPreferences sp = getSharedPreferences("admin",MODE_PRIVATE);
        String email= sp.getString("email",null);
        if(email == null){
            Intent intent = new Intent(Admin.this, landingpage.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        if(email == null){
            Intent intent = new Intent(Admin.this, landingpage.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        mname = findViewById(R.id.name);
        madds = findViewById(R.id.adds);
        description1 = findViewById(R.id.description1);
        img1 = findViewById(R.id.img);
        checkBox1 = findViewById(R.id.checkBox1);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
        checkBox4 = findViewById(R.id.checkBox4);
        checkBox5 = findViewById(R.id.checkBox5);
        checkBox6 = findViewById(R.id.checkBox6);
        checkBox7 = findViewById(R.id.checkBox7);



        rk1 = findViewById(R.id.rk1);
        bhk1= findViewById(R.id.bhk1);
        bhk2 = findViewById(R.id.bhk2);
        bhk3= findViewById(R.id.bhk3);
        bhk4 = findViewById(R.id.bhk4);

        check1 = findViewById(R.id.check1);
        check2= findViewById(R.id.check2);
        check3 = findViewById(R.id.check3);
        check4= findViewById(R.id.check4);
        check5 = findViewById(R.id.check5);




        add = findViewById(R.id.add);

        project = findViewById(R.id.project);
        admin = findViewById(R.id.admin);
        appointment = findViewById(R.id.appointment);
        queris = findViewById(R.id.queries);
        logout = findViewById(R.id.logout);


        targetImage =  findViewById(R.id.targetImage);

        project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin.this, Admin.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            }
        });

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin.this, Addadmin.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin.this, ViewAppointment.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        queris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin.this, UserQueries.class);
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
                Intent intent = new Intent(Admin.this, landingpage.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

//        mimage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                int PICK_IMAGE_MULTIPLE = 1;
//                String imageEncoded;
//                List<String> imagesEncodedList;
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent,"Select Picture"), PICK_IMAGE_MULTIPLE);
//                Toast.makeText(Admin.this, PICK_IMAGE_MULTIPLE, Toast.LENGTH_SHORT).show();

//            }
//        });

        check1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                rk1.setEnabled(true);
                rk1.setHintTextColor(getResources().getColor(R.color.gray_txt));
            }

            else {
                rk1.setEnabled(false);
                rk1.getText().clear();
                rk1.setHintTextColor(getResources().getColor(R.color.white));
            }
        });

        check2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                bhk1.setEnabled(true);
                bhk1.setHintTextColor(getResources().getColor(R.color.gray_txt));
            }

            else {
                bhk1.setEnabled(false);
                bhk1.getText().clear();
                bhk1.setHintTextColor(getResources().getColor(R.color.white));
            }
        });
        check3.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                bhk2.setEnabled(true);
                bhk2.setHintTextColor(getResources().getColor(R.color.gray_txt));
            }

            else {
                bhk2.setEnabled(false);
                bhk2.getText().clear();
                bhk2.setHintTextColor(getResources().getColor(R.color.white));
            }
        });
        check4.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                bhk3.setEnabled(true);
                bhk3.setHintTextColor(getResources().getColor(R.color.gray_txt));
            }

            else {
                bhk3.setEnabled(false);
                bhk3.getText().clear();
                bhk3.setHintTextColor(getResources().getColor(R.color.white));
            }
        });
        check5.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                bhk4.setEnabled(true);
                bhk4.setHintTextColor(getResources().getColor(R.color.gray_txt));
            }

            else {
                bhk4.setEnabled(false);
                bhk4.getText().clear();
                bhk4.setHintTextColor(getResources().getColor(R.color.white));
            }
        });





        img1.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View arg0) {


                try {

                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent,pick_image);
                }catch (Exception e){
                    Toast.makeText(Admin.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mname.getText().toString().trim();
                String adds = madds.getText().toString().trim();
                String description = description1.getText().toString().trim();

                String [] msg=new String[7];
                String [] msg1=new String[6];
                String [] msg2=new String[5];

                if(TextUtils.isEmpty(name))
                {
                    mname.setError("Project Name is Required.");
                    return;
                }
                if(TextUtils.isEmpty(adds))
                {
                    madds.setError("Address Is Required.");
                    return;
                }
                if(TextUtils.isEmpty(description))
                {
                    description1.setError("Project Description is Required.");
                    return;
                }


                // Concatenation of the checked options in if

                // isChecked() is used to check whether
                // the CheckBox is in true state or not.

                if(checkBox1.isChecked())
                    msg[0] = "Gated Security";
                if(checkBox2.isChecked())
                    msg[1] = "Lift";
                if(checkBox3.isChecked())
                    msg[2] ="Club House";
                if(checkBox1.isChecked())
                    msg[3]="Visitors Parking";
                if(checkBox1.isChecked())
                    msg[4] ="Swimming Pool";
                if(checkBox1.isChecked())
                    msg[5] = "Gym";
                if(checkBox1.isChecked())
                    msg[6] ="Children Play Area";

                // Toast is created to display the
                // message using show() method.
                if(check1.isChecked()) {
                    msg1[0] = "1 RK";
                    msg2[0] = rk1.getText().toString();
                    if(TextUtils.isEmpty(msg2[0]))
                    {
                        rk1.setError("Price Range is Required.");
                        return;
                    }


                }
                if(check2.isChecked())
                {  msg1[1] ="1 BHK";
                    msg2[1] = bhk1.getText().toString();
                    if(TextUtils.isEmpty(msg2[1]))
                    {
                        bhk1.setError("Price Range is Required.");
                        return;
                    }
                }
                if(check3.isChecked()) {
                    msg1[2] = "2 BHK";
                    msg2[2] = bhk2.getText().toString();
                    if(TextUtils.isEmpty(msg2[2]))
                    {
                        bhk2.setError("Price Range is Required.");
                        return;
                    }
                }
                if(check4.isChecked()) {
                    msg1[3] = "3 BHK";
                    msg2[3] = bhk3.getText().toString();
                    if(TextUtils.isEmpty(msg2[3]))
                    {
                        bhk3.setError("Price Range is Required.");
                        return;
                    }
                }
                if(check5.isChecked()) {
                    msg1[4] = "4 BHK";
                    msg2[4] = bhk4.getText().toString();
                    if(TextUtils.isEmpty(msg2[4]))
                    {
                        bhk4.setError("Price Range is Required.");
                        return;
                    }
                }

                //Bitmap emptyBitmap = Bitmap.createBitmap(imageTo.getWidth(), imageTo.getHeight(), imageTo.getConfig());
                if (imagepath == null) {
                    img1.setText("Please Select Image First");
                    return;
                }

                long r= db.addProject(name,adds,msg,msg1,msg2,description,imageTo);
                if(r != -1){
                    Toast.makeText(Admin.this, "Project added", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Admin.this, Admin.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(Admin.this, "Failed", Toast.LENGTH_SHORT).show();
                }

            }
        });













    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        try {
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode==pick_image && resultCode== RESULT_OK && data.getData() !=null && data!= null)
            {
                imagepath=data.getData();
                imageTo = MediaStore.Images.Media.getBitmap(getContentResolver(),imagepath);
                targetImage.setImageBitmap(imageTo);
            }
        } catch (Exception e) {
            Toast.makeText(Admin.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }




    }
}