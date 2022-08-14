package com.imman.iava.Data;


import android.content.Context;
import android.content.SharedPreferences;

import java.util.Objects;

public class Profile {

     final String MYPREF = "ProfileBucket";
     final String Branch_Code = "BranchCode";
     final String Semester_Code = "Semester_Code";
    final String Program = "Program";
     final String Batch = "Batch";
    final String Section = "Section";
     final String Last_Status = "Last_Status";
     final String Registration_Number = "Registration_Number";
     final String Password = "Password";
    final String Session_Id = "Session_Cookie";
    final String UserName = "User_Name";
    Context context;

    private final SharedPreferences sharedpreferences;
    private final SharedPreferences.Editor myEdit ;

    public Profile(Context t){
        this.context = t;
        sharedpreferences = t.getSharedPreferences(MYPREF,Context.MODE_PRIVATE);
        myEdit = sharedpreferences.edit();

    }

    /*
    * Getter Function.
    * */
    public String getBatch() {
        return sharedpreferences.getString(Batch,"");
    }

    public  String getBranch_Code() {
        return sharedpreferences.getString(Branch_Code,"");
    }

    public  String getProgram() {
        return sharedpreferences.getString(Program,"");
    }

    public  String getSemester_Code() {
        return sharedpreferences.getString(Semester_Code,"");
    }

    public  boolean getLast_Status() {
        return sharedpreferences.getBoolean(Last_Status,false);
    }

    public  String getPassword() {
        return sharedpreferences.getString(Password,"");
    }

    public String getSection() {
        return sharedpreferences.getString(Section,"");
    }

    public  String getRegistration_Number() {
        return sharedpreferences.getString(Registration_Number,"");
    }

    public String getUser_Name() {
        return sharedpreferences.getString(UserName,"");
    }

    public String getSession_Id() {
        return sharedpreferences.getString(Session_Id,"");
    }

    /*
    * Setter Function.
    * */
    public  void setBatch(String data) {
        myEdit.putString(Batch,data);
        myEdit.commit();
    }

    public  void setBranch_Code(String data) {
        myEdit.putString(Branch_Code,data);
        myEdit.commit();
    }

    public  void setProgram(String data) {
        myEdit.putString(Program,data);
        myEdit.commit();
    }

    public  void setSemester_Code(String data) {
        myEdit.putString(Semester_Code,data);
        myEdit.commit();
    }

    public  void setLast_Status(boolean data) {
        myEdit.putBoolean(Last_Status,data);
        myEdit.commit();
    }

    public  void setPassword(String data) {
        myEdit.putString(Password,data);
        myEdit.commit();
    }

    public  void setRegistration_Number(String data) {
        myEdit.putString(Registration_Number,data);
        myEdit.commit();
    }

    public void setSection(String data) {
        myEdit.putString(Section,data);
        myEdit.commit();
    }

    public void setSession_Id(String data){
        myEdit.putString(Session_Id,data);
        myEdit.commit();
    }
    public void setUser_Name(String data){
        myEdit.putString(UserName,data);
        myEdit.commit();
    }

    public boolean is_loggedin(){
        return !Objects.equals(sharedpreferences.getString(Registration_Number, "abb"), "abb") && !Objects.equals(sharedpreferences.getString(Password, "abb"), "abb");
    }

}
