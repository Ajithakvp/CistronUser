package com.example.cistronuser.Common;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.cistronuser.API.Model.LoginuserModel;
import com.example.cistronuser.API.Model.SalesQuoteProductsAddonModel;
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



    //profile details

    //name
    public static void setEmpName(Context context, String empid) {
        SharedPreferences preferences;
        preferences = context.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
        preferences.edit().putString("name", empid).apply();
    }

    public static String getEmpName(Context activity) {
        SharedPreferences preferences;
        preferences = activity.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
        return preferences.getString("name", "");

    }
    //Mobile
    public static void setEmpMobile(Context context, String empid) {
        SharedPreferences preferences;
        preferences = context.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
        preferences.edit().putString("mobile", empid).apply();
    }

    public static String getEmpMobile(Context activity) {
        SharedPreferences preferences;
        preferences = activity.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
        return preferences.getString("mobile", "");

    }
    //Email
    public static void setEmpemail(Context context, String empid) {
        SharedPreferences preferences;
        preferences = context.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
        preferences.edit().putString("email", empid).apply();
    }

    public static String getEmpemail(Context activity) {
        SharedPreferences preferences;
        preferences = activity.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
        return preferences.getString("email", "");

    }
    //dob

    public static void setEmpdob(Context context, String empid) {
        SharedPreferences preferences;
        preferences = context.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
        preferences.edit().putString("dob", empid).apply();
    }

    public static String getEmpdob(Context activity) {
        SharedPreferences preferences;
        preferences = activity.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
        return preferences.getString("dob", "");

    }

    //doj
    public static void setEmpdoj(Context context, String empid) {
        SharedPreferences preferences;
        preferences = context.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
        preferences.edit().putString("doj", empid).apply();
    }

    public static String getEmpdoj(Context activity) {
        SharedPreferences preferences;
        preferences = activity.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
        return preferences.getString("doj", "");

    }

    //designation

    public static void setEmpdesignation(Context context, String empid) {
        SharedPreferences preferences;
        preferences = context.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
        preferences.edit().putString("designation", empid).apply();
    }

    public static String getEmpdesignation(Context activity) {
        SharedPreferences preferences;
        preferences = activity.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
        return preferences.getString("designation", "");

    }
    //branch
    public static void setEmpbranch(Context context, String empid) {
        SharedPreferences preferences;
        preferences = context.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
        preferences.edit().putString("branch", empid).apply();
    }

    public static String getEmpbranch(Context activity) {
        SharedPreferences preferences;
        preferences = activity.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
        return preferences.getString("branch", "");

    }

//teamleader
    public static void setEmpteamleader(Context context, String empid) {
        SharedPreferences preferences;
        preferences = context.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
        preferences.edit().putString("teamleader", empid).apply();
    }

    public static String getEmpteamleader(Context activity) {
        SharedPreferences preferences;
        preferences = activity.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
        return preferences.getString("teamleader", "");


    }



    //photo
    public static void setEmpphoto(Context context, String empid) {
        SharedPreferences preferences;
        preferences = context.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
        preferences.edit().putString("photo", empid).apply();
    }

    public static String getEmpphoto(Context activity) {
        SharedPreferences preferences;
        preferences = activity.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
        return preferences.getString("photo", "");

    }

    //Emply

    public static void setEmpUser(Context context, String empid) {
        SharedPreferences preferences;
        preferences = context.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
        preferences.edit().putString("user", empid).apply();
    }

    public static String getEmpuser(Context activity) {
        SharedPreferences preferences;
        preferences = activity.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
        return preferences.getString("user", "");
    }


    public static void setEmpCompany(Activity activity, String company) {
        SharedPreferences preferences;
        preferences = activity.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
        preferences.edit().putString("company", company).apply();
    }
    public static String getEmpCompany(Context activity) {
        SharedPreferences preferences;
        preferences = activity.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
        return preferences.getString("company", "");
    }

    public static void set_ismanager(Activity activity, String manager) {
        SharedPreferences preferences;
        preferences = activity.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
        preferences.edit().putString("ismanger", manager).apply();
    }
    public static String get_ismanager(Context activity) {
        SharedPreferences preferences;
        preferences = activity.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
        return preferences.getString("ismanger", "");
    }



    //Service Engineer Call No

    public static void saveCallNo(Activity activity, String CallNo) {
        SharedPreferences preferences;
        preferences = activity.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
        preferences.edit().putString("callNo", CallNo).apply();
    }
    public static String getCallNo(Context activity) {
        SharedPreferences preferences;
        preferences = activity.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
        return preferences.getString("callNo", "");
    }



    //Category

    public static void saveLoginEmpID(Activity activity, String Empid) {
        SharedPreferences preferences;
        preferences = activity.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
        preferences.edit().putString("empid", Empid).apply();
    }
    public static String getLoginEmpID(Context activity) {
        SharedPreferences preferences;
        preferences = activity.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
        return preferences.getString("empid", "");
    }

    public static void saveLoginPwd(Activity activity, String pwd) {
        SharedPreferences preferences;
        preferences = activity.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
        preferences.edit().putString("pass", pwd).apply();
    }
    public static String getLoginPwd(Context activity) {
        SharedPreferences preferences;
        preferences = activity.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
        return preferences.getString("pass", "");
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


    public static void setShowdialog(Activity activity, boolean isDialog) {
        SharedPreferences preferences;
        preferences = activity.getSharedPreferences("SETTINGS", MODE_PRIVATE);
        preferences.edit().putBoolean("show", isDialog).apply();
    }

    public static boolean isDialog(Activity activity) {
        SharedPreferences preferences;
        preferences = activity.getSharedPreferences("SETTINGS", MODE_PRIVATE);
        return preferences.getBoolean("show", false);


    }
}
