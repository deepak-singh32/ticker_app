package com.example.ticker.classes;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {


    public static boolean saveInteger(Activity activity, String key, Integer value){
        SharedPreferences sharedPref = activity.getSharedPreferences("MySharedPref",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(key, value);
        editor.apply();
        return true;
    }

    public static boolean saveString(Activity activity,String key,String value){
        SharedPreferences sharedPref = activity.getSharedPreferences("MySharedPref",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.apply();
        return true;
    }

    public static String getString(Activity activity,String key){
        SharedPreferences sharedPref = activity.getSharedPreferences("MySharedPref",Context.MODE_PRIVATE);
        return sharedPref.getString(key, "");

    }

    public static Integer getInteger(Activity activity,String key){
        SharedPreferences sharedPref = activity.getSharedPreferences("MySharedPref",Context.MODE_PRIVATE);
        return sharedPref.getInt(key, -1);

    }


}
