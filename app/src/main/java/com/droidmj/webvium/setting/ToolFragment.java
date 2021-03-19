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

package com.droidmj.webvium.setting;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.provider.Settings;

import com.droidmj.webvium.R;
import com.droidmj.webvium.app.base.BasePreferenceFragment;
import com.droidmj.webvium.content.Intents;
import com.droidmj.webvium.content.Package;
import com.droidmj.webvium.io.Files;
import com.droidmj.webvium.io.StorageDirectory;
import com.droidmj.webvium.manifest.Permission;
import com.droidmj.webvium.telemetry.DiagnosticData;
import com.droidmj.webvium.text.Html;
import com.droidmj.webvium.widget.Toast;

public class ToolFragment extends BasePreferenceFragment {

    @Override
    public void onCreate(Bundle b1) {
        super.onCreate(b1);
        try {

            a5(R.xml.j);
            Preference u1 = findPreference("clearScreen");
            u1.setOnPreferenceClickListener(a -> {
                j();
                return true;
            });

            Preference u2 = findPreference("clearSource");
            u2.setOnPreferenceClickListener(a -> {
                i();
                return true;
            });
        } catch (Exception ex) {
            DiagnosticData.a(ex);
        }
    }

    @Override
    @TargetApi(Build.VERSION_CODES.M)
    public void onRequestPermissionsResult(int a, String[] b, int[] c) {
        super.onRequestPermissionsResult(a, b, c);
        switch (a) {
            case 1:
                if (c.length > 0 && c[0] == PackageManager.PERMISSION_GRANTED) {
                    e();
                } else {
                    if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        d(getString(R.string.u15));
                    } else {
                        a(getString(R.string.u16));
                    }
                }
                break;
            case 2:
                if (c.length > 0 && c[0] == PackageManager.PERMISSION_GRANTED) {
                    f();
                } else {
                    if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        d(getString(R.string.u15));
                    } else {
                        a(getString(R.string.u16));
                    }
                }
                break;
        }
    }

    private void a(String jk) {
        final AlertDialog.Builder a = new AlertDialog.Builder(getActivity());


        a.setCancelable(true);
        a.setTitle(getString(R.string.s26));
        a.setMessage(Html.b(jk));
        a.setPositiveButton(getString(R.string.u14), (a12, intetg) -> {
            Intents.l(getActivity(), Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", Package.b(), null));

            a12.dismiss();
        });
        a.setNegativeButton(getString(R.string.i7), (a1, intetg) -> a1.dismiss());
        a.create().show();
    }

    private void d(String a) {
        Toast.c(getActivity(), a);
    }


    private void i() {
        if (Permission.check(getActivity(), Permission.STORAGE, 1)) {
            e();
        }
    }

    private void j() {
        if (Permission.check(getActivity(), Permission.STORAGE, 2)) {
            f();
        }
    }

    private void e() {
        final AlertDialog.Builder a = new AlertDialog.Builder(getActivity());


        a.setCancelable(true);
        a.setTitle(getString(R.string.h10));
        a.setMessage(getString(R.string.u8));
        a.setPositiveButton(getString(R.string.i6), (a12, intetg) -> {
            h(StorageDirectory.getWebviumDir() + "/Source Code/");
            g(getString(R.string.q1));
        });
        a.setNegativeButton(getString(R.string.i7), (a1, intetg) -> a1.dismiss());
        a.create().show();
    }

    private void g(String a) {
        Toast.b(getActivity(), a);
    }

    private void h(final String a) {
        Runnable p15 = () -> {
            try {
                Files.deleteAll(new java.io.File(a));
            } catch (Exception en) {
                DiagnosticData.a(en);
            }
        };
        new Thread(p15).start();
    }

    private void f() {
        final AlertDialog.Builder a = new AlertDialog.Builder(getActivity());


        a.setCancelable(true);
        a.setTitle(getString(R.string.h10));
        a.setMessage(getString(R.string.u9));
        a.setPositiveButton(getString(R.string.i6), (a12, intetg) -> {
            h(StorageDirectory.getWebviumDir() + "/Screenshot/");
            g(getString(R.string.l16));

        });
        a.setNegativeButton(getString(R.string.i7), (a1, intetg) -> a1.dismiss());
        a.create().show();
    }
}