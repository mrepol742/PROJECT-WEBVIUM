/*
 *
 *
 *
 * DROID MJ Property || Confidential
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package com.droidmj.webvium.util;

import android.app.UiModeManager;
import android.content.Context;
import android.content.res.Configuration;
import android.os.PowerManager;

import com.droidmj.webvium.annotation.release.Keep;

public class Hardware {
    @Keep
    private Hardware() {
    }

    public static boolean isTablet(Context context) {
        return (context.getApplicationContext().getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public static String c(Context a1) {
        boolean a = Hardware.isTablet(a1);
        if (a) {
            return U4.a(E2.Tablet);
        }
        return U4.a(E2.Phone);
    }

    public static Object isNightMode(Context a) {
        UiModeManager ui = (UiModeManager) a.getSystemService(Context.UI_MODE_SERVICE);
        switch (ui.getNightMode()) {
            case UiModeManager.MODE_NIGHT_YES:
                return E1.Yes;
            case UiModeManager.MODE_NIGHT_NO:
                return E1.No;
            default:
            case UiModeManager.MODE_NIGHT_AUTO:
                return E1.Auto;
            case UiModeManager.MODE_NIGHT_CUSTOM:
                return E1.Custom;
        }
    }

    public static boolean isBatterySaveMode(Context a) {
        PowerManager pm = (PowerManager) a.getSystemService(Context.POWER_SERVICE);
        return pm.isPowerSaveMode();
    }

    enum E2 {
        Tablet, Phone;

        @Keep
        E2() {

        }
    }

    public enum E1 {
        Yes, No, Custom, Auto;

        @Keep
        E1() {
        }
    }
}
