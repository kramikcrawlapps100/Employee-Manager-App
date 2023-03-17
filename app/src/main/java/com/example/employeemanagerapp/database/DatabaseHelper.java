package com.example.employeemanagerapp.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.AbstractWindowedCursor;
import android.database.Cursor;
import android.database.CursorWindow;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.employeemanagerapp.model.Employee;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "employee_db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Employee.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Employee.TABLE_NAME);
        onCreate(db);
    }

    public void insertEmployee(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Employee.COLUMN_FIRST_NAME, employee.getFirstName());
        values.put(Employee.COLUMN_LAST_NAME, employee.getLastName());
        values.put(Employee.COLUMN_EMAIL, employee.getEmail());
        values.put(Employee.COLUMN_PHONE_NUMBER, employee.getPhoneNumber());
        values.put(Employee.COLUMN_GENDER, employee.getGender());
        values.put(Employee.COLUMN_PROFILE_PICTURE, employee.getProfilePicture());
        values.put(Employee.COLUMN_ADDRESS, employee.getAddress());
        values.put(Employee.COLUMN_DESIGNATION, employee.getDesignation());
        values.put(Employee.COLUMN_EXPERIENCE, employee.getExperience());
        db.insert(Employee.TABLE_NAME, null, values);
    }

    @SuppressLint("Range")
    public ArrayList<Employee> getAllEmployees() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Employee> employees = new ArrayList<>();
        String[] projection = {
                Employee.COLUMN_ID,
                Employee.COLUMN_FIRST_NAME,
                Employee.COLUMN_LAST_NAME,
                Employee.COLUMN_EMAIL,
                Employee.COLUMN_PHONE_NUMBER,
                Employee.COLUMN_GENDER,
                Employee.COLUMN_PROFILE_PICTURE,
                Employee.COLUMN_ADDRESS,
                Employee.COLUMN_DESIGNATION,
                Employee.COLUMN_EXPERIENCE
        };
        String sortOrder = Employee.COLUMN_ID + " ASC";
        Cursor cursor = db.query(Employee.TABLE_NAME, projection, null, null, null, null, sortOrder);
//        CursorWindow cw = null;
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
//            cw = new CursorWindow(Employee.COLUMN_PROFILE_PICTURE, 15000);
//        }
//        AbstractWindowedCursor ac = (AbstractWindowedCursor) cursor;
//        ac.setWindow(cw);
        if (cursor.moveToFirst()) {
            do {
                Employee employee = new Employee();
                employee.setId(cursor.getInt(cursor.getColumnIndex(Employee.COLUMN_ID)));
                employee.setFirstName(cursor.getString(cursor.getColumnIndex(Employee.COLUMN_FIRST_NAME)));
                employee.setLastName(cursor.getString(cursor.getColumnIndex(Employee.COLUMN_LAST_NAME)));
                employee.setEmail(cursor.getString(cursor.getColumnIndex(Employee.COLUMN_EMAIL)));
                employee.setPhoneNumber(cursor.getString(cursor.getColumnIndex(Employee.COLUMN_PHONE_NUMBER)));
                employee.setGender(cursor.getInt(cursor.getColumnIndex(Employee.COLUMN_GENDER)));
                employee.setProfilePicture(cursor.getString(cursor.getColumnIndex(Employee.COLUMN_PROFILE_PICTURE)));
                employee.setAddress(cursor.getString(cursor.getColumnIndex(Employee.COLUMN_ADDRESS)));
                employee.setDesignation(cursor.getString(cursor.getColumnIndex(Employee.COLUMN_DESIGNATION)));
                employee.setExperience(cursor.getDouble(cursor.getColumnIndex(Employee.COLUMN_EXPERIENCE)));
                employees.add(employee);
            } while (cursor.moveToNext());
        }
        return employees;
    }
}
