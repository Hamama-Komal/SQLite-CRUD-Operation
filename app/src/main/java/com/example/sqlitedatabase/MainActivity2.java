package com.example.sqlitedatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.database.Cursor;
import android.os.Bundle;

import com.example.sqlitedatabase.databinding.ActivityMain2Binding;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    ActivityMain2Binding binding;
    DatabaseHelper databaseHelper;
    ArrayList<ModelClass> list;
    ModelClass modelClass;
    AdapterClass adapterClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        databaseHelper = new DatabaseHelper(this);
        databaseHelper.getWritableDatabase();


        list = new ArrayList<>();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);



            Cursor cursor = databaseHelper.fetchData();
            list.clear();

            if(cursor.getCount() == 0){

                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setCancelable(true);
                dialog.setMessage("No Data Found");
                dialog.setTitle("Warning");
                dialog.setIcon(R.drawable.baseline_warning_24);
                dialog.show();



                return;
            }

            while (cursor.moveToNext()) {
                modelClass = new ModelClass(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
                list.add(modelClass);

                 binding.recyclerView.scrollToPosition(list.size()-1);

                adapterClass = new AdapterClass(list);
                binding.recyclerView.setAdapter(adapterClass);
            }
        }





        }

