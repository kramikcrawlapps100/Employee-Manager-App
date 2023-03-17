package com.example.employeemanagerapp.model;

public class Employee {

    public static final String TABLE_NAME = "employees";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FIRST_NAME = "firstName";
    public static final String COLUMN_LAST_NAME = "lastName";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PHONE_NUMBER = "phoneNumber";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_PROFILE_PICTURE = "profilePicture";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_DESIGNATION = "designation";
    public static final String COLUMN_EXPERIENCE = "experience";
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_FIRST_NAME + " varchar(255) NOT NULL,"
                    + COLUMN_LAST_NAME + " varchar(255) NOT NULL,"
                    + COLUMN_EMAIL + " varchar(255) NOT NULL,"
                    + COLUMN_PHONE_NUMBER + " varchar(255) NOT NULL,"
                    + COLUMN_GENDER + " INTEGER NOT NULL,"
                    + COLUMN_PROFILE_PICTURE + " TEXT NOT NULL,"
                    + COLUMN_ADDRESS + " varchar(255) NOT NULL,"
                    + COLUMN_DESIGNATION + " varchar(255) NOT NULL,"
                    + COLUMN_EXPERIENCE + " REAL NOT NULL"
                    + ");";

    int id;
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    int gender;
    String profilePicture;
    String address;
    String designation;
    double experience;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String email, String phoneNumber, int gender, String profilePicture, String address, String designation, double experience) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.profilePicture = profilePicture;
        this.address = address;
        this.designation = designation;
        this.experience = experience;
    }

    public Employee(int id, String firstName, String lastName, String email, String phoneNumber, int gender, String profilePicture, String address, String designation, double experience) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.profilePicture = profilePicture;
        this.address = address;
        this.designation = designation;
        this.experience = experience;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getExperience() {
        return experience;
    }

    public void setExperience(double experience) {
        this.experience = experience;
    }
}
