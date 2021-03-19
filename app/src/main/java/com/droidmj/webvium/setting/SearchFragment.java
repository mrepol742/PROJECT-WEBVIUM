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

import android.app.AlertDialog;
import android.os.Build;
import android.os.Bundle;

import com.droidmj.webvium.PREF;
import com.droidmj.webvium.QUIC;
import com.droidmj.webvium.R;
import com.droidmj.webvium.SWIT;
import com.droidmj.webvium.app.BuildConfiguration;
import com.droidmj.webvium.app.base.BasePreferenceFragment;
import com.droidmj.webvium.app.main.MainNotification;
import com.droidmj.webvium.content.Intents;
import com.droidmj.webvium.telemetry.DiagnosticData;
import com.droidmj.webvium.text.Html;
import com.droidmj.webvium.widget.Toast;

public class SearchFragment extends BasePreferenceFragment {

    @Override
    public void onCreate(Bundle b1) {
        super.onCreate(b1);
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                a5(R.xml.l);
            } else {
                a5(R.xml.d);
            }
            PREF b = (PREF) findPreference("asst");
            b.setOnPreferenceClickListener(a -> {
                b14();
                return true;
            });
            SWIT cbf = (SWIT) findPreference("qckS");
            cbf.setOnPreferenceChangeListener((preference, newValue) -> {
                if (newValue.toString().equals("true")) {
                    Intents.b(SearchFragment.this.getActivity(), QUIC.class);
                } else {
                    Intents.i(SearchFragment.this.getActivity(), QUIC.class);
                    MainNotification.a(SearchFragment.this.getActivity(), BuildConfiguration.Notification.d);
                }
                return true;
            });

        } catch (Exception ex) {
            DiagnosticData.a(ex);
        }
    }

    private void b14() {
        final AlertDialog.Builder a = new AlertDialog.Builder(getActivity());
        a.setCancelable(true);
        a.setTitle(getString(R.string.z18));
        a.setMessage(getString(R.string.a21));
        a.setPositiveButton(getString(R.string.i6), (a12, intetg) -> {
            Intents.k(getActivity(), "android.settings.VOICE_INPUT_SETTINGS");
            Toast.d(getActivity(), Html.b(getString(R.string.b33)).toString());
            a12.dismiss();
        });
        a.setNegativeButton(getString(R.string.i7), (a1, intetg) -> a1.dismiss());
        a.create().show();
    }

}