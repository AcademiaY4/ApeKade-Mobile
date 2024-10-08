package com.app.apekade.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.nio.charset.StandardCharsets;

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

    public static boolean isTokenValid(Context context) {
        String token = getToken(context);
        return token != null && !token.isEmpty() && !isTokenExpired(token);
    }

    private static boolean isTokenExpired(String token) {
        // JWT tokens have three parts: header, payload, signature
        String[] parts = token.split("\\.");
        if (parts.length != 3) {
            return true; // Invalid token structure
        }

        // Decode the payload
        String payload = parts[1];
        String decodedPayload = new String(Base64.decode(payload, Base64.URL_SAFE | Base64.NO_WRAP), StandardCharsets.UTF_8);

        // Parse the JSON payload
        JsonObject jsonObject = new Gson().fromJson(decodedPayload, JsonObject.class);

        // Get the expiration time (exp claim)
        long exp = jsonObject.get("exp").getAsLong();

        // Compare it to the current time
        return exp < (System.currentTimeMillis() / 1000); // Convert to seconds
    }

    // Clear token (optional if needed)
    public static void clearToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(TOKEN_KEY);
        editor.apply();
    }
}
