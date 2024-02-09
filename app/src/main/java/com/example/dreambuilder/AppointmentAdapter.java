package com.example.dreambuilder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.MyViewHolder> {

    private Context context;
    private ArrayList pname,date,cemail,cname,cphone,time;

    public AppointmentAdapter(Context context, ArrayList pname, ArrayList date, ArrayList cemail,ArrayList cname,ArrayList cphone,ArrayList time) {
        this.context = context;
        this.pname = pname;
        this.date = date;
        this.cemail = cemail;
        this.cname = cname;
        this.cphone = cphone;
        this.time = time;
    }

    @NonNull
    @Override
    public AppointmentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.appointment_table,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentAdapter.MyViewHolder holder, int position) {
        holder.pname.setText(String.valueOf(pname.get(position)));
        holder.date.setText(String.valueOf(date.get(position)));
        holder.cemail.setText(String.valueOf(cemail.get(position)));
        holder.cname.setText(String.valueOf(cname.get(position)));
        holder.cphone.setText(String.valueOf(cphone.get(position)));
        holder.time.setText(String.valueOf(time.get(position)));
    }

    @Override
    public int getItemCount() {
        return pname.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView pname,date,cemail,cname,cphone,time;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            pname = itemView.findViewById(R.id.pname_3);
            date = itemView.findViewById(R.id.date_3);
            cemail = itemView.findViewById(R.id.cemail_3);
            cname = itemView.findViewById(R.id.cname_3);
            cphone = itemView.findViewById(R.id.cphone_3);
            time = itemView.findViewById(R.id.time_3);
        }
    }
}
