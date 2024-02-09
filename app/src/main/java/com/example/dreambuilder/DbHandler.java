package com.example.dreambuilder;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

public class DbHandler extends SQLiteOpenHelper {

    private static final String  name ="emp.db";
    private Context context;
    public DbHandler(@Nullable Context context) {
        super(context, "DreamBuilder.db", null, 1);
        this.context=context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //creating Register Table
        String register = "create table if not exists register (email TEXT PRIMARY KEY, name TEXT, adds TEXT,phone TEXT,password TEXT)";
        db.execSQL(register);

        //creating admin Table
        String admin = "create table if not exists admin (email TEXT PRIMARY KEY,password TEXT)";
        db.execSQL(admin);

        //inserting 1st admin email and pass

        ContentValues values = new ContentValues();
        values.put("email", "admin123@gmail.com");
        values.put("password", "Admin@123");
        db.insert("admin", null, values);

        //creating Project Table
        String project = "create table if not exists project (id INTEGER  PRIMARY KEY AUTOINCREMENT,name TEXT ,adds TEXT, amanities TEXT,flat TEXT,price TEXT,description TEXT,image  BLOB)";
        db.execSQL(project);

        //creating Complaint Table
        String complaint = "create table if not exists complaint (name TEXT ,description TEXT, phone TEXT)";
        db.execSQL(complaint);

        //creating Appointment Table
        String appointment = "create table if not exists appointment (id INTEGER  PRIMARY KEY AUTOINCREMENT,proj_name TEXT ,date TEXT, cust_email TEXT,cust_name TEXT,cust_phone TEXT,time_slot TEXT)";
        db.execSQL(appointment);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop = "DROP TABLE IF EXISTS" + " register";
        db.execSQL(drop);
        String dropa = "DROP TABLE IF EXISTS" + " admin";
        db.execSQL(dropa);
        String dropp = "DROP TABLE IF EXISTS" + " project";
        db.execSQL(dropp);

        String dropc = "DROP TABLE IF EXISTS" + " complaint";
        db.execSQL(dropc);

        String dropap = "DROP TABLE IF EXISTS" + " appointment";
        db.execSQL(dropap);

        onCreate(db);

    }


    public long addUser(Register reg){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", reg.getEmail());
        values.put("name", reg.getName());
        values.put("adds", reg.getAdds());
        values.put("phone", reg.getPhone());
        values.put("password", reg.getPassword());
        long result= db.insert("register", null, values);

        db.close();
        return result;


    }

    public String getLogin(String user, String pass){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from register where email = ? and password =?", new String[]{user,pass});
        if(cursor.getCount() >0){
            cursor.moveToFirst();
            @SuppressLint("Range") String email1 = cursor.getString(cursor.getColumnIndex("email"));

            return email1;
        }
        else {
            return "fail";
        }


    }

   Cursor getUser(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from register where email = '"+email+"'", null);
        return cursor;
    }

    public long updateUser(String email,String name,String adds,String phone,String password){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("name",name);
        cv.put("adds",adds);
        cv.put("phone",phone);
        cv.put("password",password);
       long result=  db.update("register",cv,"email=?",new String[]{email});
       return result;
    }


    public long addAdmin(String email,String pass){

        SQLiteDatabase db = this.getWritableDatabase();
        String admin = "create table if not exists admin (email TEXT PRIMARY KEY,password TEXT)";
        db.execSQL(admin);
        ContentValues values = new ContentValues();
        values.put("email", email);

        values.put("password", pass);
        long result= db.insert("admin", null, values);

        db.close();
        return result;


    }

    public String getAdmin(String user, String pass){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from admin where email = ? and password =?", new String[]{user,pass});
        if(cursor.getCount() >0){
            cursor.moveToFirst();
            @SuppressLint("Range") String email1 = cursor.getString(cursor.getColumnIndex("email"));

            return email1;
        }
        else {
            return "fail";
        }



    }

  public long  raiseComplaint(String name,String des,String phone){
      SQLiteDatabase db = this.getWritableDatabase();
      String complaint = "create table if not exists complaint (name TEXT ,description TEXT, phone TEXT)";
      db.execSQL(complaint);
      ContentValues values = new ContentValues();
      values.put("name", name);
      values.put("description", des);

      values.put("phone", phone);
      long result= db.insert("complaint", null, values);

      db.close();
      return result;
  }

    Cursor getComplaint(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from complaint ",null);
        return cursor;
    }

    public long  addProject(String name, String adds, String [] msg, String [] msg1,String [] msg2,String description, Bitmap imageto){
        SQLiteDatabase db = this.getWritableDatabase();

            String project = "create table if not exists project (id INTEGER  PRIMARY KEY AUTOINCREMENT,name TEXT ,adds TEXT, amanities TEXT,flat TEXT,price TEXT,description TEXT,image  BLOB)";
            db.execSQL(project);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                imageto.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] imageData = baos.toByteArray();

                ContentValues values = new ContentValues();
                values.put("name", name);
                values.put("adds", adds);
                values.put("amanities", Arrays.toString(msg));
                values.put("flat", Arrays.toString(msg1));
                values.put("price", Arrays.toString(msg2));
                values.put("description", description);
                values.put("image", imageData);

                long result = db.insert("project", null, values);

        db.close();
       // return result;
        return result;
    }

    Cursor getProject(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from project ",null);
        return cursor;
    }

    Cursor getOneProject(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from project where id='"+id+"'",null);
        return cursor;
    }

    Cursor getProperty(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM project WHERE TRIM(id) = '"+id.trim()+"'", null);
        cursor.moveToFirst();
        return cursor;
    }

    public long setAppointment(String proj_name,String date,String cust_email,String Cust_name,String Cust_phone,String time_slot){
        SQLiteDatabase db = this.getWritableDatabase();

        String appointment = "create table if not exists appointment (id INTEGER  PRIMARY KEY AUTOINCREMENT,proj_name TEXT ,date TEXT, cust_email TEXT,cust_name TEXT,cust_phone TEXT,time_slot TEXT)";
        db.execSQL(appointment);



        ContentValues values = new ContentValues();
        values.put("proj_name", proj_name);
        values.put("date", date);
        values.put("cust_email",cust_email);
        values.put("cust_name", Cust_name);
        values.put("cust_phone", Cust_phone);
        values.put("time_slot", time_slot);


        long result = db.insert("appointment", null, values);

        db.close();
        return result;
    }

    Cursor getAppointment(String date ,String proj_name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM appointment WHERE date = '"+date+"' AND proj_name ='"+proj_name+"' ", null);
        cursor.moveToFirst();
        return cursor;
    }

    Cursor getAllAppointment(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM appointment ", null);
        cursor.moveToFirst();
        return cursor;
    }

    Cursor getUserAppointment(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM appointment where cust_email ='"+email+"'", null);
        cursor.moveToFirst();
        return cursor;
    }

}
