package com.app.apekade.Utils;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.WindowInsetsController;

public class StatusBarUtil {
    public static void setStatusBarAppearance(Activity activity , boolean isLightScreen){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            WindowInsetsController controller = activity.getWindow().getInsetsController();
            if (controller != null) {
                if (isLightScreen) {
                    controller.setSystemBarsAppearance(
                            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                    );
                } else {
                    controller.setSystemBarsAppearance(0, WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS);
                }
            } else {
                // Legacy method for API < 30
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    int flags = activity.getWindow().getDecorView().getSystemUiVisibility();
                    if (isLightScreen) {
                        flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                    } else {
                        flags &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                    }
                    activity.getWindow().getDecorView().setSystemUiVisibility(flags);
                }
            }
        }
    }
}
