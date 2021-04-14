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

package com.mrepol742.webvium.setting;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.provider.Settings;

import com.mrepol742.webvium.R;
import com.mrepol742.webvium.app.base.BasePreferenceFragment;
import com.mrepol742.webvium.content.Intents;
import com.mrepol742.webvium.telemetry.DiagnosticData;
import com.mrepol742.webvium.text.Html;
import com.mrepol742.webvium.widget.Toast;

public class DownloadFragment extends BasePreferenceFragment {

    @Override
    public void onCreate(Bundle b1) {
        super.onCreate(b1);
        try {
            if (Build.VERSION.SDK_INT >= 24) {
                a5(R.xml.n);
            } else if (Build.VERSION.SDK_INT >= 23) {
                a5(R.xml.m);
            } else {
                a5(R.xml.e);
            }
            if (Build.VERSION.SDK_INT >= 23) {
                Preference d = findPreference("batt");
                d.setOnPreferenceClickListener(a -> {
                    p();
                    return true;
                });
            }
        } catch (Exception ex) {
            DiagnosticData.a(ex);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void p() {
        final AlertDialog.Builder a = new AlertDialog.Builder(getActivity());
        a.setCancelable(true);
        a.setTitle(getString(R.string.f));
        a.setMessage(Html.b(getString(R.string.u12)));
        a.setPositiveButton(getString(R.string.i6), (a12, intetg) -> {
            Intents.k(getActivity(), Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS);
            Toast.d(getActivity(), Html.b(getString(R.string.a25)).toString());
        });
        a.setNegativeButton(getString(R.string.i7), (a1, intetg) -> a1.dismiss());
        a.create().show();
    }

}