package com.imman.iava.Schedule;

import android.content.Context;

import com.google.gson.Gson;
import com.imman.iava.Network.NetworkRequest;
import com.imman.iava.UserData.Profile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class SubjectDataParser {
    public static ArrayList<ArrayList<SubjectModel>> run(Context t)
    {
        JSONObject json;
        Gson gson = new Gson();
        ArrayList<ArrayList<SubjectModel>> fModel = new ArrayList<>();
        try {
            json = new JSONObject(StoreTable.ReadTable(new Profile(t).getUserTableLinkRough(),t));

            for (Iterator<String> it = json.keys(); it.hasNext(); ) {
                String key = it.next();
                JSONArray array = json.getJSONArray(key);
                ArrayList<SubjectModel> modelArray = new ArrayList<>();
                for (int i = 0; i < array.length(); i++) {
                    String ob1 = array.getJSONObject(i).toString();
                    modelArray.add(gson.fromJson(ob1, SubjectModel.class));
                }
                fModel.add(modelArray);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return fModel;
    }
}
