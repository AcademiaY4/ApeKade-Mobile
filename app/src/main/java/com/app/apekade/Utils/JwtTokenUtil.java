package com.app.apekade.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class JwtTokenUtil {
    private static final String PREFS_NAME = "MyPrefs";
    private static final String TOKEN_KEY = "token";

    // Save token to SharedPreferences
    public static void saveToken(Context context, String token) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TOKEN_KEY, token);

        //async way
        editor.apply();
        //sync way
        //editor.commit();
    }

    // Retrieve token from SharedPreferences
    public static String getToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(TOKEN_KEY, null); // Return null if token not found
    }

    // Clear token (optional if needed)
    public static void clearToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(TOKEN_KEY);
        editor.apply();
    }
}
