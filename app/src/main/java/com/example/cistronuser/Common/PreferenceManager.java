package com.example.cistronuser.Common;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.cistronuser.API.Model.LoginuserModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class PreferenceManager {



    public static ArrayList<LoginuserModel>loginuserModels=new ArrayList<>();


    public static void setLoggedStatus(Activity activity, boolean isLogged) {
        SharedPreferences preferences;
        preferences = activity.getSharedPreferences("SETTINGS", MODE_PRIVATE);
        preferences.edit().putBoolean("IS_LOGGED_STATUS", isLogged).apply();
    }

    public static boolean isLogged(Activity activity) {
        SharedPreferences preferences;
        preferences = activity.getSharedPreferences("SETTINGS", MODE_PRIVATE);
        return preferences.getBoolean("IS_LOGGED_STATUS", false);


    }
    public static void saveData(Activity activity,ArrayList<LoginuserModel>loginuserModels)
    {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(loginuserModels);
        editor.putString("task list",json);
        editor.apply();
    }


    public static ArrayList<LoginuserModel> loadData(Activity activity)
    {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("SETTINGS",Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences. getString("task list",null);
        Type type = new TypeToken<ArrayList<LoginuserModel>>(){}.getType();
        loginuserModels = gson.fromJson(json,type);
        return loginuserModels;
    }


    public static void setEmpID(Context context, String empid) {
        SharedPreferences preferences;
        preferences = context.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
        preferences.edit().putString("empid", empid).apply();
    }

    public static String getEmpID(Context activity) {
        SharedPreferences preferences;
        preferences = activity.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
        return preferences.getString("empid", "");

    }


    //get


    public static void setUserModelData(Activity activity,LoginuserModel userModel) {
        SharedPreferences preferences;
        Gson gson = new Gson();
        String json = gson.toJson(userModel);
        preferences = activity.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
        preferences.edit().putString("USER_DATA", json).apply();
    }

    public static LoginuserModel getUserModelData(Activity activity) {
        SharedPreferences preferences;
        preferences = activity.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString("USER_DATA", "");
        LoginuserModel userModel = gson.fromJson(json, LoginuserModel.class);
        return userModel;
    }


    //photo

    public static void setphoto(Activity activity, String photo) {
        SharedPreferences preferences;
        preferences = activity.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
        preferences.edit().putString("photo", photo).apply();
    }


    public static String getphoto(Activity activity) {
        SharedPreferences preferences;
        preferences = activity.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
        return preferences.getString("photo", "");
    }





}
