package com.example.dreambuilder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAppointmentAdapter extends RecyclerView.Adapter<UserAppointmentAdapter.MyViewHolder> {

    private Context context;
    private ArrayList pro_name,date,time;

    public UserAppointmentAdapter(Context context, ArrayList pro_name, ArrayList date, ArrayList time) {
        this.context = context;
        this.pro_name = pro_name;
        this.date = date;
        this.time = time;
    }

    @NonNull
    @Override
    public UserAppointmentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.user_scheduled_appointment,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAppointmentAdapter.MyViewHolder holder, int position) {
        holder.pro_name1.setText(String.valueOf(pro_name.get(position)));
        holder.date.setText(String.valueOf(date.get(position)));
        holder.time.setText(String.valueOf(time.get(position)));
    }

    @Override
    public int getItemCount() {
        return date.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView pro_name1,date,time;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            pro_name1 = itemView.findViewById(R.id.pro_name_1);
            date = itemView.findViewById(R.id.date_1);
            time = itemView.findViewById(R.id.time_1);
        }
    }
}
