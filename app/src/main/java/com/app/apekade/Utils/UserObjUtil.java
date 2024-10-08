package com.app.apekade.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.app.apekade.Model.User;
import com.google.gson.Gson;

public class UserObjUtil {

    private static final String PREFS_NAME = "MyPrefs";
    private static final String USER_KEY = "user";

    // Save user object to SharedPreferences
    public static void saveUser(Context context, User user) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Convert user object to JSON string using Gson
        Gson gson = new Gson();
        String userJson = gson.toJson(user);
        editor.putString(USER_KEY, userJson);

        // Save the data asynchronously
        editor.apply();
    }
    // Retrieve user object from SharedPreferences
    public static User getUser(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String userJson = sharedPreferences.getString(USER_KEY, null);

        if (userJson != null) {
            // Convert JSON string back to User object
            Gson gson = new Gson();
            return gson.fromJson(userJson, User.class);
        }

        return null; // Return null if user not found
    }

    // Check if the user is logged in (i.e., user object exists)
    public static boolean isUserLoggedIn(Context context) {
        return getUser(context) != null;
    }
    // Clear user data (optional if needed)
    public static void clearUser(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(USER_KEY);
        editor.apply();
    }
}
