package com.app.apekade.Helpers;

import com.app.apekade.Model.Enum.District;

public class CheckIsDistrict {
    public static boolean isValidDistrict(District district) {
        try {
            District.valueOf(district.name());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
