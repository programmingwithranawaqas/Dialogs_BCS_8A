package com.example.dialogs_bcs_8a;

import android.app.AlertDialog;
import android.content.Context;
import android.text.NoCopySpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
    String selectedPreference;
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

    private void editPassenger(int position)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Update Passenger");
        View v = LayoutInflater.from(context)
                .inflate(R.layout.edit_add_passenger_dialog, null, false);
        builder.setView(v);

        EditText etName = v.findViewById(R.id.etName);
        EditText etPhone = v.findViewById(R.id.etPhone);
        ImageView ivDialogPref = v.findViewById(R.id.ivDialogPref);

        Passenger p = passengers.get(position);
        etName.setText(p.getName());
        etPhone.setText(p.getPhone());
        selectedPreference = p.getPref();
        if(selectedPreference.equals("bus"))
        {
            ivDialogPref.setImageResource(R.drawable.icon_bus);
        }
        else
        {
            ivDialogPref.setImageResource(R.drawable.icon_train);
        }



        ivDialogPref.setOnClickListener((v1)->{
            if(selectedPreference == "bus")
            {
                selectedPreference = "train";
                ivDialogPref.setImageResource(R.drawable.icon_train);
            }
            else
            {
                selectedPreference = "bus";
                ivDialogPref.setImageResource(R.drawable.icon_bus);
            }
        });


        builder.setPositiveButton("Update", (d, i)->{
            String name = etName.getText().toString().trim();
            String phone = etPhone.getText().toString();

            if(name.isEmpty())
            {
                etName.setError("Name can't be empty");
                return;
            }
            if(phone.isEmpty())
            {
                etPhone.setError("Phone can't be empty");
                return;
            }

            p.setName(name);
            p.setPhone(phone);
            p.setPref(selectedPreference);

            notifyItemChanged(position);

            Toast.makeText(context, "Data updated", Toast.LENGTH_SHORT).show();

        });
        builder.setNegativeButton("Cancel", (d, i)->{

        });

        builder.create().show();

    }

    @Override
    public void onBindViewHolder(@NonNull PassengerViewHolder holder, int position) {

        Passenger p = DataClass.passengers.get(position);

        holder.tvName.setText(p.getName());
        holder.tvPhone.setText(p.getPhone());

        holder.ibDel.setOnClickListener((v1)->{
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Confirmation");
            builder.setMessage("Are you sure?");
            builder.setPositiveButton("Delete", (d, v2)->{
                DataClass.passengers.remove(position);
                notifyItemRemoved(position);
                Toast.makeText(context, "Data deleted", Toast.LENGTH_SHORT).show();
            });
            builder.setNegativeButton("Cancel", (d, v2)->{});
            builder.create().show();

        });
        holder.ibEdit.setOnClickListener((v)->{
            editPassenger(position);
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
