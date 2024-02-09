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

public class CustomeAdapter extends RecyclerView.Adapter<CustomeAdapter.MyViewHolder> {

    private Context context;
    private ArrayList id,name,adds;
    private List images;


    public CustomeAdapter(Context context, ArrayList id,ArrayList name, ArrayList adds, List images) {
        this.context = context;
        this.id = id;
        this.name = name;
        this.adds = adds;
        this.images = images;

    }

    @NonNull
    @Override
    public CustomeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.main,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.name.setText(String.valueOf(name.get(position)));
        holder.adds.setText(String.valueOf(adds.get(position)));
        Bitmap image = (Bitmap) images.get(position);
        holder.imageView.setImageBitmap(image);
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, Property.class);
                intent.putExtra("id",String.valueOf(id.get(position)));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,adds;
        ImageView imageView;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.title);
            adds = itemView.findViewById(R.id.adds);
            imageView= itemView.findViewById(R.id.imageView);

            mainLayout = itemView.findViewById(R.id.mainLayout);

        }
    }
}
