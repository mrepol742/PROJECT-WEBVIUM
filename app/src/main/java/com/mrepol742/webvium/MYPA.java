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

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;

import com.mrepol742.webvium.app.main.MainReceiver;
import com.mrepol742.webvium.content.Intents;
import com.mrepol742.webvium.content.Package;
import com.mrepol742.webvium.io.Files;
import com.mrepol742.webvium.io.StorageDirectory;
import com.mrepol742.webvium.telemetry.DiagnosticData;
import com.mrepol742.webvium.util.Hardware;
import com.mrepol742.webvium.util.U4;

import java.util.Objects;

// @Class MyPackageReplaced
public class MYPA extends MainReceiver {

    @Override
    public void onReceive(Context a, Intent b) {
        if (Objects.requireNonNull(b.getAction()).equals("android.intent.action.MY_PACKAGE_REPLACED")) {
            Files.delete(StorageDirectory.getCacheDir(a) + "/log");
            try {
                DiagnosticData.a("\nStorage \nFiles: " + a.getFilesDir() + "\nCache: " + StorageDirectory.getCacheDir(a) + "\n\nBuild Log \nDevice: " + Hardware.c(a) + "\nNight: " + U4.a(Hardware.isNightMode(a)) + "\n\nPackage Update\nPackage Label: " + Package.c() + "\nPackage Name: " + Package.b() + "\nPackage Version Name: " + Package.e(a) + "\nPackage Version Code: " + Package.f(a));
            } catch (PackageManager.NameNotFoundException q) {
                DiagnosticData.a(q);
            }
            if (PreferenceManager.getDefaultSharedPreferences(a).getBoolean("bcP", true)) {
                Intents.b(a, BACK.class);
            }
            if (PreferenceManager.getDefaultSharedPreferences(a).getBoolean("asd71", false)) {
                Intents.a(a, MAIN.class);
            }
        }
        super.onReceive(a, b);
    }

}