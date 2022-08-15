package com.imman.iava.Schedule;

import android.content.Context;
import android.util.Log;

import com.imman.iava.Network.NetworkRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class HandleSchedule {
    static NetworkRequest networkRequest;
    static String baseUrl = "https://raw.githubusercontent.com/Imman-coder/iava-dataset/main/";
    static final String fileLastUpdate = "lastUpdate.txt";
    static final String fileRouteTable = "Tables_rough.jsonc";
    static Context t ;
    public static void run(Context t){
        networkRequest = new NetworkRequest(t);
        HandleSchedule.t =t;
        String routeTable = networkRequest.GetN(baseUrl+fileRouteTable);
        Log.d("CheckList:", routeTable);
        String[] p = parserD(routeTable);

        //check if array in not null
        if(p!=null){

            //check if table already exist
            if(StoreTable.tableExist(p[0],t) && StoreTable.tableExist(fileLastUpdate,t)){

                //check if already exist table is outdated
                if(isFirstTimeGreater(p[1],StoreTable.ReadTable(fileLastUpdate,t))){

                    //download and update table
                    downloadUpdateTable(p);
                }

                //if table is all okay
                else{
                    System.out.println("Table already setup!");
                }
            }

            //if table or lastUpdate data isn't exist
            else {

                //download and update table
                downloadUpdateTable(p);
            }
        }

        //if p is null(File doesn't exist)
        else {
            System.out.println("Table not Uploaded!");
        }

    }

    static void downloadUpdateTable(String[] p){
        //download and store table
        String nowTable = networkRequest.GetN(baseUrl+p[0]);

        //check if nowTable is invalid
        if(nowTable.equals("404: Not Found"))
            System.out.println("UserTable not Uploaded");

            //if nowTable is valid
        else{
            StoreTable.WriteTable(nowTable,p[0],t);
            StoreTable.WriteTable(getTimeNow(), fileLastUpdate,t);
            System.out.println("Table Updated!");
        }
    }

    static String[] parserD(String data){
        data = "{\"DGI_BTECH_2021-2025\":{\"CSE\":{\"2\":{\"B\":[\"DGI_BTECH_2021-2025_CSE_2_B_rough.jsonc\",\"2022-08-15T01:07:05\"]}}}}";
        if(Objects.equals(data, "404: Not Found"))
            return null;
        String[] r = new String[2];
        try{
            JSONObject list = new JSONObject(data);
            JSONArray array  = list.getJSONObject("DGI_BTECH_2021-2025").getJSONObject("CSE").getJSONObject("2").getJSONArray("B");
            r[0]= String.valueOf(array.getString(0)).replace("\"","");
            r[1]= String.valueOf(array.getString(1)).replace("\"","");
        }
        catch (Exception e){
            Log.e("", "parserD: "+e.getMessage());
            r = null;
        }
        return r;
    }

    static Boolean isFirstTimeGreater(String timeServer, String timeLastGet){
        LocalDateTime dateTime1 = LocalDateTime.parse(timeServer);
        LocalDateTime dateTime2 = LocalDateTime.parse(timeLastGet);
        return !dateTime1.isBefore(dateTime2);
    }

    static String getTimeNow(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        return currentDateandTime;
    }

}
