package com.example.sqlitedatabase;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.renderscript.ScriptGroup;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlitedatabase.databinding.ItemLayoutBinding;

import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.ViewHolder> {



    public AdapterClass(ArrayList list) {
        this.list = list;
    }

    ArrayList<ModelClass> list;


    @NonNull
    @Override
    public AdapterClass.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterClass.ViewHolder holder, int position) {

        ModelClass obj = list.get(position);

        holder.binding.txtid.setText(obj.getId());
        holder.binding.txtname.setText(obj.getName());
        holder.binding.txtfname.setText(obj.getFname());
        holder.binding.txtemail.setText(obj.getEmail());
        holder.binding.txtphone.setText(obj.getContact());


        // For deleting data

        holder.itemView.setOnLongClickListener(v -> {


            AlertDialog.Builder dialog2 = new AlertDialog.Builder(v.getContext());
            dialog2.setMessage("Do you want to delete data?");
            dialog2.setTitle("Warning");
            dialog2.setIcon(R.drawable.baseline_delete_24);
            dialog2.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            dialog2.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    DatabaseHelper databaseHelper = new DatabaseHelper(v.getContext());
                    databaseHelper.getWritableDatabase();


                    Integer deleteData = databaseHelper.deleteData(holder.binding.txtid.getText().toString());

                    if (deleteData > 0) {
                        Toast.makeText(v.getContext(), "Data Deleted", Toast.LENGTH_SHORT).show();

                        Activity activity = (Activity) v.getContext();
                        Intent intent = new Intent(activity, MainActivity2.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

                        // Start the next activity
                        activity.startActivity(intent);

                        activity.finish();

                        notifyDataSetChanged();

                    } else
                        Toast.makeText(v.getContext(), "Error! Data not deleted", Toast.LENGTH_SHORT).show();
                }
            });

            dialog2.create().show();

            return true;
        });

        // Button for Updating Data


         holder.binding.updateBtn.setOnClickListener(v -> {

                 Intent intent = new Intent(v.getContext(), UpdateActivity.class);
                 intent.putExtra("ID",list.get(position).getId());
                 intent.putExtra("Name",list.get(position).getName());
                 intent.putExtra("Fname",list.get(position).getFname());
                 intent.putExtra("Email",list.get(position).getEmail());
                 intent.putExtra("Phone",list.get(position).getContact());
                /* v.getContext().startActivity(intent);*/

             // Get the current activity
             Activity activity = (Activity) v.getContext();

             // Get the intent of the next activity
             //Intent intent = new Intent(activity, UpdateActivity.class);

             // Add the flags to remove the previous activity from the stack
             intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

             // Start the next activity
             activity.startActivity(intent);

             activity.finish();




             });





    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemLayoutBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            binding = ItemLayoutBinding.bind(itemView);
        }
    }
}
