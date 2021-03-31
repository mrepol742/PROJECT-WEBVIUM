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

package com.droidmj.webvium;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.droidmj.webvium.annotation.Development;
import com.droidmj.webvium.annotation.Test;
import com.droidmj.webvium.app.main.MainService;
import com.droidmj.webvium.net.Connectivity;
import com.droidmj.webvium.util.Base64;
import com.droidmj.webvium.util.Stream;

// @Class UpdateService
public class UPDA0 extends MainService {
    private SharedPreferences sp;

    @Override
    public void onCreate() {
        super.onCreate();
        sp = PreferenceManager.getDefaultSharedPreferences(this);
    }

    @Override
    public int onStartCommand(Intent a, int c, int d) {
        if (Connectivity.isThereAnyInternetConnection(this) && Connectivity.isRestrictBackground(this)) {
            s1();
        }
        e();
        s1();
        return super.onStartCommand(a, c, d);
    }

    @Test
    @Development
    private void e() {
        SharedPreferences sharedPreferences = getSharedPreferences("b", 0);
        String sg = Stream.f(Base64.decode(sharedPreferences.getString(WELC.TEMP_B, "") + "?raw=true"), "404");
        if (sg.equals("404")) {
            return;
        }
        String[] arr = sg.trim().split(";");
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String[] abc = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        editor.clear();
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            editor.putString(abc[i], arr[i] + abc[i]);
        }
        editor.apply();
    }

}
