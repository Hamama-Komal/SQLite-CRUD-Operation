package com.example.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.view.Display;
import android.widget.Toast;

import com.example.sqlitedatabase.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    DatabaseHelper databaseHelper;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new DatabaseHelper(this);
        databaseHelper.getWritableDatabase();




        // To Insert Data

        binding.btnInsert.setOnClickListener(v -> {
            String name =binding.edtName.getText().toString().trim();
            String fname = binding.edtFname.getText().toString().trim();
            String email = binding.edtEmail.getText().toString().trim();
            String contact = binding.edtPhone.getText().toString().trim();



            if( name.matches("") || fname.matches("") || email.matches("") || contact.matches("")){
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
            }
            else {

                boolean isInserted;
                isInserted = databaseHelper.insertData(name, fname, email, contact);

                if (isInserted) {
                    Toast.makeText(this, "Data insertion successful", Toast.LENGTH_SHORT).show();
                    binding.edtName.setText("");
                    binding.edtFname.setText("");
                    binding.edtEmail.setText("");
                    binding.edtPhone.setText("");

                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(intent);



                } else {
                    Toast.makeText(this, "Data insertion failed", Toast.LENGTH_SHORT).show();
                }
            }


        });

        // To Fetch Data

        binding.btnView.setOnClickListener(v -> {

            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);



        });






    }
}