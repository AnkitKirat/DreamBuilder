package com.example.dreambuilder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ComplaintAdapter extends RecyclerView.Adapter<ComplaintAdapter.MyViewHolder> {

   private Context context;
   private ArrayList name,des,phone;

    public ComplaintAdapter(Context context, ArrayList name, ArrayList des, ArrayList phone) {
        this.context = context;
        this.name = name;
        this.des = des;
        this.phone = phone;
    }

    @NonNull
    @Override
    public ComplaintAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
       View view = inflater.inflate(R.layout.compliant_table,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComplaintAdapter.MyViewHolder holder, int position) {
            holder.name.setText(String.valueOf(name.get(position)));
            holder.des.setText(String.valueOf(des.get(position)));
            holder.phone.setText(String.valueOf(phone.get(position)));
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,des,phone;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            des = itemView.findViewById(R.id.des);
            phone = itemView.findViewById(R.id.phone);
        }
    }
}
