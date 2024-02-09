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

import java.util.ArrayList;
import java.util.List;

public class CustomeAdapter2 extends RecyclerView.Adapter<CustomeAdapter2.MyViewHolder> {

    private Context context;
    private ArrayList flat,price;



    public CustomeAdapter2(Context context, ArrayList flat,ArrayList price) {
        this.context = context;
        this.flat = flat;
        this.price = price;


    }

    @NonNull
    @Override
    public CustomeAdapter2.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.flat_type,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.flatt.setText(String.valueOf(flat.get(position)));
        holder.pricet.setText(String.valueOf(price.get(position)));



    }

    @Override
    public int getItemCount() {
        return price.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView flatt,pricet;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            flatt = itemView.findViewById(R.id.flatt);
            pricet = itemView.findViewById(R.id.pricet);


        }
    }
}
