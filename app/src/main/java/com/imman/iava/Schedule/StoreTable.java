package com.imman.iava.Schedule;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class StoreTable {

    public static void WriteTable(final String fileContents, String fileName, Context t) {
        File path = t.getFilesDir();
        File file = new File(path, "/"+fileName);
        try {
            FileWriter out = new FileWriter(file);
            out.write(fileContents);
            out.close();
        } catch (IOException e) {
            Log.d("TAG", e.toString());
        }
    }

    public static String ReadTable(String fileName, Context t) {
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        BufferedReader in = null;

        try {
            in = new BufferedReader(new FileReader(new File(t.getFilesDir(), "/"+fileName)));
            while ((line = in.readLine()) != null) stringBuilder.append(line);

        } catch (IOException e) {
            Log.e("TAG", e.toString());
        }

        return stringBuilder.toString();
    }

    public static boolean tableExist(String fileName, Context t) {


        StringBuilder stringBuilder = new StringBuilder();
        String line;
        BufferedReader in = null;

        try {
            in = new BufferedReader(new FileReader(new File(t.getFilesDir(), "/"+fileName)));
            while ((line = in.readLine()) != null) stringBuilder.append(line);

        } catch (IOException e) {
            Log.e("TAG", e.toString());
            return false;
        }
        return stringBuilder.toString().length()!=0;
    }
}
