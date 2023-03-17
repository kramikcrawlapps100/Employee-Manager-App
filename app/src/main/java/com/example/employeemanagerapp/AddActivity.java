package com.example.employeemanagerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.employeemanagerapp.database.DatabaseHelper;
import com.example.employeemanagerapp.databinding.ActivityAddBinding;
import com.example.employeemanagerapp.model.Employee;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AddActivity extends AppCompatActivity {

    private ActivityAddBinding binding;
    private DatabaseHelper databaseHelper;
    private final int SELECT_PICTURE = 7;
    private Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initialization();

        binding.submit.setOnClickListener(v->{
            try {
                addEmployee();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void initialization() {
        databaseHelper = new DatabaseHelper(this);
    }

    private void addEmployee() throws IOException {
        String firstName = binding.firstName.getText().toString();
        String lastName = binding.lastName.getText().toString();
        String email = binding.emailAddress.getText().toString();
        String phoneNumber = binding.phoneNumber.getText().toString();
        int gender = binding.maleButton.isChecked() ? 1 : 0;

        String profilePicture = imageUri.toString();
        String address = binding.address.getText().toString();
        String designation = binding.designation.getText().toString();
        String experience = binding.experience.getText().toString();
        if(validation()){
            Employee employee = new Employee(firstName,lastName,email,phoneNumber,gender,profilePicture,address,designation,Double.parseDouble(experience));
            databaseHelper.insertEmployee(employee);
            startActivity(new Intent(this,MainActivity.class));
        }

    }

    public void imageChooser(View view) {
        Intent imageIntent = new Intent();
        imageIntent.setType("image/*");
        imageIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(imageIntent, "Select Picture"), SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    imageUri = selectedImageUri;
                    binding.profileImage.setImageURI(imageUri);
                }
            }
        }
    }

    public byte[] convertImageUriToByteArray(Uri imageUri) throws IOException {
        InputStream inputStream = getContentResolver().openInputStream(imageUri);
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }

    private byte[] byteImage(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    private boolean validation() {
        if(binding.firstName.getText().toString().equals("")){
            binding.firstName.setError("first name can not be empty.");
            return false;
        }
        if(binding.lastName.getText().toString().equals("")){
            binding.lastName.setError("last name can not be empty.");
            return false;
        }
        if(imageUri.toString().equals("")){
            binding.lastName.setError("please upload profile picture.");
            return false;
        }
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(binding.emailAddress.getText().toString()).matches()){
            binding.address.setError("enter valid email address.");
            return false;
        }
        if(!android.util.Patterns.PHONE.matcher(binding.phoneNumber.getText().toString()).matches()){
            binding.address.setError("enter valid phone number.");
            return false;
        }
        if(binding.address.getText().toString().equals("")){
            binding.address.setError("address can not be empty.");
            return false;
        }
        if(binding.designation.getText().toString().equals("")){
            binding.designation.setError("designation can not be empty.");
            return false;
        }
        if(binding.experience.getText().toString().equals("")){
            binding.experience.setError("experience can not be empty.");
            return false;
        }
        return true;
    }


}