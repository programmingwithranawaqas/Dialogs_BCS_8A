package com.example.dialogs_bcs_8a;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvPassengers;
    RecyclerView.LayoutManager manager;
    PassengerAdapter adapter;

    FloatingActionButton fabAddNewPassenger;
    String selectedPreference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();

        fabAddNewPassenger.setOnClickListener((v)->{
            addPassenger();
        });

    }

    private void addPassenger()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("New Passenger");
        View v = LayoutInflater.from(this)
                        .inflate(R.layout.edit_add_passenger_dialog, null, false);
        builder.setView(v);

        EditText etName = v.findViewById(R.id.etName);
        EditText etPhone = v.findViewById(R.id.etPhone);
        ImageView ivDialogPref = v.findViewById(R.id.ivDialogPref);

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


        builder.setPositiveButton("Save", (d, i)->{
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

            DataClass.passengers.add(new Passenger(name, phone, selectedPreference));
            adapter.notifyItemInserted(DataClass.passengers.size()-1);
            Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();

        });
        builder.setNegativeButton("Cancel", (d, i)->{

        });

        builder.create().show();

    }

    private void init()
    {
        rvPassengers = findViewById(R.id.rvPassengers);
        fabAddNewPassenger = findViewById(R.id.fabAddNewPassenger);
        rvPassengers.setHasFixedSize(true);
        manager = new LinearLayoutManager(this);
        rvPassengers.setLayoutManager(manager);
        adapter = new PassengerAdapter(this, DataClass.passengers);
        rvPassengers.setAdapter(adapter);
        selectedPreference = "train";

    }
}