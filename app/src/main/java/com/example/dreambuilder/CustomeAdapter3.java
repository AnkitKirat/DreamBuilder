package com.example.dreambuilder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CustomeAdapter3 extends RecyclerView.Adapter<CustomeAdapter3.MyViewHolder> {

    private Context context;
    private ArrayList name,vector;



    public CustomeAdapter3(Context context, ArrayList name,ArrayList vector) {
        this.context = context;
        this.name = name;
        this.vector = vector;



    }

    @NonNull
    @Override
    public CustomeAdapter3.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.amanities,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

            holder.name1.setText(String.valueOf(name.get(position)));



           Glide.with(context).load(vector.get(position)).into(holder.vector1);

    }

    @Override
    public int getItemCount() {
        return vector.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name1;
        ImageView vector1;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name1 = (TextView) itemView.findViewById(R.id.text);
            vector1 = (ImageView) itemView.findViewById(R.id.imageView);


        }
    }
}
