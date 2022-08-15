package com.imman.iava.Network;

import com.imman.iava.UserData.ExtractProfile;
import com.imman.iava.UserData.Profile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class Login {
    public Boolean authenticate(String username, String password, NetworkRequest networkRequest){
        //save password and username
        networkRequest.profile.setRegistration_Number(username);
        networkRequest.profile.setPassword(password);

        //Get hash
        String hash = ExtractHash(networkRequest.Get("https://driems.online/index.php"));

        //hash passwords and username
        String hashPassword = Encrypt.getMD5(username,password,hash);
        String shaPassword = Encrypt.getSha512(username,password,hash);

        //create a RequestBody
        RequestBody body = RequestBody.create("cmbInstitute=DGI&key="+hash+"&password="+hashPassword+"&shapassword="+shaPassword+"&username="+username, MediaType.parse("application/x-www-form-urlencoded"));

        //make a Post request
        String response = networkRequest.Post("https://driems.online/index.php",body);

        //check id successful
        return is_success(response,networkRequest.profile);
    }

    //Hash Extractor
    String ExtractHash(String data){
        final String regex_get_hash = "(?<=do_submit\\(\\')\\w+(?=\\'\\);)";
        final Matcher matcher = Pattern.compile(regex_get_hash, Pattern.MULTILINE).matcher(data);
        if(matcher.find()){
            return matcher.group(0);
        }
        return "";
    }

    boolean is_success(String data, Profile person){

        final Pattern pattern = Pattern.compile("Invalid Credentials!!!", Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(data);
        if(matcher.find()){
            System.out.println("enp1: Failed!");
            return false;
        }
        else{
            System.out.println("enp1: Succeed");
            new ExtractProfile().extract(data,person);
            return true;

        }

    }
}
