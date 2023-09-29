package com.example.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.sqlitedatabase.databinding.ActivityUpdateBinding;

public class UpdateActivity extends AppCompatActivity {

    ActivityUpdateBinding binding;

    DatabaseHelper databaseHelper;
    String id, newName, newFname, newEmail, newContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new DatabaseHelper(UpdateActivity.this);
        databaseHelper.getWritableDatabase();

        id = getIntent().getStringExtra("ID");
        newName = getIntent().getStringExtra("Name");
        newFname = getIntent().getStringExtra("Fname");
        newEmail = getIntent().getStringExtra("Email");
        newContact = getIntent().getStringExtra("Phone");

        binding.editName.setText(newName);
        binding.editFname.setText(newFname);
        binding.editEmail.setText(newEmail);
        binding.editPhone.setText(newContact);


        binding.button2.setOnClickListener(v -> {


            boolean isUpdated = databaseHelper.updateData(id, binding.editName.getText().toString(), binding.editFname.getText().toString(), binding.editEmail.getText().toString(), binding.editPhone.getText().toString());

            if (isUpdated) {

                Toast.makeText(this, "Data Updated", Toast.LENGTH_SHORT).show();

            }


            else Toast.makeText(this, "Data is not Updated", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(UpdateActivity.this,MainActivity2.class);
            startActivity(intent);
            finish();
            

        });


    }
}