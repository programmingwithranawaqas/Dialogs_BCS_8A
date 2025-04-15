package com.example.dialogs_bcs_8a;

import android.content.Context;
import android.text.NoCopySpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PassengerAdapter extends RecyclerView.Adapter<PassengerAdapter.PassengerViewHolder> {

    Context context;
    ArrayList<Passenger> passengers;
    public PassengerAdapter(Context c, ArrayList<Passenger> passengers)
    {
        context = c;
        this.passengers = passengers;
    }

    @NonNull
    @Override
    public PassengerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.single_item_passenger_design, parent, false);
        return new PassengerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PassengerViewHolder holder, int position) {

        Passenger p = DataClass.passengers.get(position);

        holder.tvName.setText(p.getName());
        holder.tvPhone.setText(p.getPhone());

        holder.ibDel.setOnClickListener((v1)->{
            DataClass.passengers.remove(position);
            notifyItemRemoved(position);
            Toast.makeText(context, "Data deleted", Toast.LENGTH_SHORT).show();
        });

        if(p.getPref().equals("train"))
        {
            holder.ivPref.setImageResource(R.drawable.icon_train);
        }
        else
        {
            holder.ivPref.setImageResource(R.drawable.icon_bus);
        }
    }

    @Override
    public int getItemCount() {
        return passengers.size();
    }

    public class PassengerViewHolder extends RecyclerView.ViewHolder
    {
        // hooks
        TextView tvName, tvPhone;
        ImageButton ibEdit, ibDel;
        ImageView ivPref;
        public PassengerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPhone = itemView.findViewById(R.id.tvPhone);
            ibDel = itemView.findViewById(R.id.ibDel);
            ibEdit = itemView.findViewById(R.id.ibEdit);
            ivPref = itemView.findViewById(R.id.ivPref);
        }
    }
}
