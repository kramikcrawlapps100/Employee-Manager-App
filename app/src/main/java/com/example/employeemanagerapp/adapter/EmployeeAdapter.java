package com.example.employeemanagerapp.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.employeemanagerapp.R;
import com.example.employeemanagerapp.model.Employee;

import java.util.ArrayList;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder>{

    ArrayList<Employee> employees;

    public EmployeeAdapter() {
    }

    @NonNull
    @Override
    public EmployeeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeAdapter.ViewHolder holder, int position) {
        Employee employee = employees.get(position);
        holder.fullName.setText(employee.getFirstName() + " " + employee.getLastName());
        holder.email.setText(employee.getEmail());
        String gender;
        if (employee.getGender() == 1){
            gender = "Male";
        }else{
            gender = "Female";
        }
        holder.gender.setText(gender);
        holder.profileImage.setImageURI(Uri.parse(employee.getProfilePicture()));
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    public void setEmployees(ArrayList<Employee> employees){
        this.employees = employees;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView profileImage;
        TextView fullName,email,gender;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            profileImage = itemView.findViewById(R.id.profileImage);
            fullName = itemView.findViewById(R.id.full_name);
            email = itemView.findViewById(R.id.email);
            gender = itemView.findViewById(R.id.gender);
        }
    }
}
