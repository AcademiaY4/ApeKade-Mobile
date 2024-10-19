package com.app.apekade.Helpers;

import com.app.apekade.Model.Enum.Province;

public class CheckIsProvince {
    public static boolean isValidProvince(Province province) {
        try {
            Province.valueOf(province.name());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
