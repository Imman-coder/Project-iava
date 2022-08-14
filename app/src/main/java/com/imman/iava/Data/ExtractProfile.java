package com.imman.iava.Data;

import android.content.Context;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractProfile {
    static final String regex_BranchCode = "(?<=branch_code=)\\w+(?=&)";
    static final String regex_SemesterCode = "(?<=semester_code=)\\w+(?=&)";
    static final String regex_Program = "(?<=program=)\\w+(?=&)";
    static final String regex_Batch = "(?<=batch=)\\w+-\\w+(?=&)";
    static final String regex_Section = "(?<=section=)\\w";
    static final String regex_UserName = "(?<=<span>)(\\w+ )+";

    public void extract(String page, Profile person){

        //User Branch code extraction
        Pattern pattern = Pattern.compile(regex_BranchCode, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(page);
        if(matcher.find())
            person.setBranch_Code(matcher.group(0));

        //User Semester code extraction
        pattern = Pattern.compile(regex_SemesterCode, Pattern.MULTILINE);
        matcher = pattern.matcher(page);
        if(matcher.find())
            person.setSemester_Code(matcher.group(0));

        //User Program extraction
        pattern = Pattern.compile(regex_Program, Pattern.MULTILINE);
        matcher = pattern.matcher(page);
        if(matcher.find())
            person.setProgram(matcher.group(0));

        //User Batch extraction
        pattern = Pattern.compile(regex_Batch, Pattern.MULTILINE);
        matcher = pattern.matcher(page);
        if(matcher.find())
            person.setBatch(matcher.group(0));

        //User Section extraction
        pattern = Pattern.compile(regex_Section, Pattern.MULTILINE);
        matcher = pattern.matcher(page);
        if(matcher.find())
            person.setSection(matcher.group(0));

        //User Section extraction
        pattern = Pattern.compile(regex_UserName, Pattern.MULTILINE);
        matcher = pattern.matcher(page);
        if(matcher.find())
            person.setUser_Name(matcher.group(0));
    }
}
