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

package com.mrepol742.webvium;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ScrollView;
import android.widget.TextView;

import com.mrepol742.webvium.app.base.BaseActivity;
import com.mrepol742.webvium.content.Package;
import com.mrepol742.webvium.util.Base64;
import com.mrepol742.webvium.util.Stream;

import java.io.OutputStream;
import java.util.Arrays;
import java.util.Objects;

public class EXPI extends BaseActivity {
    private TextView tv;

    @Override
    protected void onCreate(Bundle be) {
        super.onCreate(be);
        ScrollView scrollView = new ScrollView(this);
        tv = new TextView(this);
        scrollView.addView(tv);
        setContentView(scrollView);
        tv.append("TextView initialize and set as Content View\n");
        tv.setTypeface(type(Typeface.NORMAL));

        tv.append("Typeface initiated..\n");
        tv.setTextIsSelectable(true);
        if (Build.VERSION.SDK_INT >= 29 ) {
            write();
        } else {
            tv.append("This test is only meant for Android Higher than equal to Q or SDK >= 28\nSkipping Storage Write Test\n");
        }
        tv.append("Initiating update Checkup\n");
        try {
            SharedPreferences sharedPreferences = getSharedPreferences("b", 0);
            int b = Integer.parseInt(Package.e(this).replaceAll("\\.", ""));
            int newUpdate = Stream.i(Base64.decode(sharedPreferences.getString(WELC.TEMP_UPDATE_VERSION, "")) + "?raw=true");
            if (newUpdate > b) {
                tv.append("There's new update available\n");
                tv.append("Version parsed is " + newUpdate +"\n");
            } else {
                tv.append("Update check failed!\n");
                tv.append("Version parsed is " + newUpdate +"\n");
            }
        } catch (Exception w) {
            tv.append("Update checks Failed!\n");
            tv.append(Log.getStackTraceString(w) + "\n");
        }
        tv.append("Initiating notification Checkup\n");
        try {
            SharedPreferences sharedPreferences = getSharedPreferences("b", 0);
            int notif = Stream.g(Base64.decode(sharedPreferences.getString(WELC.TEMP_NOTIFICATION_STATE, "")) + "?raw=true");
            if (notif > 0) {
                String neTf = Stream.f(Base64.decode(sharedPreferences.getString(WELC.TEMP_NOTIFICATION_DATA, "")) + "?raw=true", getString(R.string.c33));
                String[] sp = neTf.split(";");
                SharedPreferences j988 = getSharedPreferences("wv,", 0);
                if (!Objects.requireNonNull(j988.getString("notif1", "")).equals(sp[0]) && !Objects.requireNonNull(j988.getString("notif2", "")).equals(sp[1])) {
                    tv.append("Notification Data wasnt found on database notifying user now\n");
                    tv.append("Push Notifications =" + sp[0] + " " + sp[1] + " " + sp[2] + "\n");
                    SharedPreferences.Editor gujh = j988.edit();
                    gujh.putString("notif1", sp[0]);
                    gujh.putString("notif2", sp[1]);
                    gujh.apply();
                } else {
                    tv.append("Notification data was found on database user wont be notified\n");
                    tv.append("Notification data from database: \n");
                    tv.append("notif1 " + j988.getString("notif1", "") + "\n");
                    tv.append("notif2 " + j988.getString("notif2", "") + "\n");
                }
            }
        } catch (Exception en) {
            tv.append("Notification checks Failed!\n");
            tv.append(Log.getStackTraceString(en)+"\n");
        }
        tv.append("Database URL Checks\n");
        try {
            SharedPreferences sharedPreferences = getSharedPreferences("b", 0);
            tv.append("Update URL: " + sharedPreferences.getString(WELC.TEMP_UPDATE_URL, "") +
                    "\nDecoded Update URL: " + Base64.decode(sharedPreferences.getString(WELC.TEMP_UPDATE_URL, "")) +
                    "\nNotification Data: " + sharedPreferences.getString(WELC.TEMP_NOTIFICATION_DATA, "") +
                    "\nDecoded Notification Data: " + Base64.decode(sharedPreferences.getString(WELC.TEMP_NOTIFICATION_DATA, "")) +
                    "\nNotification State: " + sharedPreferences.getString(WELC.TEMP_NOTIFICATION_STATE, "") +
                    "\nDecoded Notification State: " + Base64.decode(sharedPreferences.getString(WELC.TEMP_NOTIFICATION_STATE, "")) +
                    "\nUpdate Version: " + sharedPreferences.getString(WELC.TEMP_UPDATE_VERSION, "") +
                    "\nDecoded Update Version: " + Base64.decode(sharedPreferences.getString(WELC.TEMP_UPDATE_VERSION, "")));
        } catch (Exception en) {
            tv.append("Database URL Checks Failed!\n");
            tv.append(Log.getStackTraceString(en)+"\n");
        }
        tv.append("Initiating Database URL Update\n");
        try {
            SharedPreferences sharedPreferences = getSharedPreferences("b", 0);
            String sg = Stream.f(Base64.decode(sharedPreferences.getString(WELC.TEMP_B, "") + "?raw=true"), "404");
            if (sg.equals("404")) {
                tv.append("an error was occured on Stream f");
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
            tv.append("Database URL update was finished. Restart Webvium to take effect\n");
            tv.append("The array below was been split by semi colons\n");
            tv.append(Arrays.toString(arr) +"\n");
        } catch (Exception en) {
            tv.append("Database URL Update Failed!\n");
            tv.append(Log.getStackTraceString(en)+"\n");
        }


    }

    @TargetApi(Build.VERSION_CODES.Q)
    private void write() {
        try {
            ContentValues values = new ContentValues();
            values.put(MediaStore.MediaColumns.DISPLAY_NAME, "webvium_test.txt");
            values.put(MediaStore.MediaColumns.MIME_TYPE, "text/plain");
            values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOCUMENTS + "/Webvium");
            Uri uri = getContentResolver().insert(MediaStore.Files.getContentUri("external"), values);
            OutputStream outputStream = getContentResolver().openOutputStream(uri);
            outputStream.write("Testing write".getBytes());
            outputStream.close();
tv.append("Write succeeds.\n");
        } catch (Exception e) {
tv.append(Log.getStackTraceString(e)+"\n");
        }
    }

}
