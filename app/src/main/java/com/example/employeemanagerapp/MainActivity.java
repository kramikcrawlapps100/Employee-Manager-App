package com.example.employeemanagerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.employeemanagerapp.adapter.EmployeeAdapter;
import com.example.employeemanagerapp.database.DatabaseHelper;
import com.example.employeemanagerapp.databinding.ActivityMainBinding;
import com.example.employeemanagerapp.model.Employee;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;
    private DatabaseHelper databaseHelper;
    private ArrayList<Employee> employees;
    private EmployeeAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initialization();
        employees= databaseHelper.getAllEmployees();
        if(employees!=null){
            for(Employee employee : employees){
                adapter.setEmployees(employees);
                setRecyclerView();
            }
        }
    }

    private void setRecyclerView() {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);

    }

    private void initialization() {
        databaseHelper = new DatabaseHelper(this);
        adapter = new EmployeeAdapter();
    }

    public void goToAddActivity(View view) {
        startActivity(new Intent(MainActivity.this, AddActivity.class));
    }
}