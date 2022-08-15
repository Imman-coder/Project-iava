package com.imman.iava.Network;

import android.content.Context;
import android.util.Log;

import com.imman.iava.UserData.Profile;

import java.io.IOException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.CacheControl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NetworkRequest {
    Context ref;
    public Profile profile;
    OkHttpClient client = new OkHttpClient();
    public NetworkRequest(Context t){
        this.ref = t;
        this.profile = new Profile(t);
    }

    //Get Request
    public String Get(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            final Matcher matcher = Pattern.compile("^\\w+=\\w+(?=;)", Pattern.MULTILINE).matcher(Objects.requireNonNull(response.headers().get("Set-Cookie")));
            if(matcher.find()){
                if(profile.getSession_Id()!= matcher.group(0))
                    profile.setSession_Id(matcher.group(0));
            }
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    //Normal Get Request
    public String GetN(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    //Post Request
    public String Post(String url, RequestBody requestBody) {

        Request request = new Request.Builder()
                .url(url)
                .cacheControl(new CacheControl.Builder().noCache().build())
                .method("POST",requestBody)
                .addHeader("ContentType", "application/x-www-form-urlencoded")
                .addHeader("Cookie",profile.getSession_Id())
                .build();
        try (Response response = client.newCall(request).execute()) {
            String cookie = response.headers().get("Set-Cookie");
            if(cookie!=null){
                final Matcher matcher = Pattern.compile("^\\w+=\\w+(?=;)", Pattern.MULTILINE).matcher(cookie);
                if(matcher.find()){
                    if(!Objects.equals(profile.getSession_Id(), matcher.group(0)))
                        profile.setSession_Id(matcher.group(0));
                }
            }
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
