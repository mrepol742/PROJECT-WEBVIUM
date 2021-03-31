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

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.provider.Settings;

import com.droidmj.webvium.CRED;
import com.droidmj.webvium.MAIN;
import com.droidmj.webvium.R;
import com.droidmj.webvium.EULA;
import com.droidmj.webvium.app.BuildConfiguration;
import com.droidmj.webvium.app.base.BasePreferenceFragment;
import com.droidmj.webvium.content.Intents;
import com.droidmj.webvium.content.Package;
import com.droidmj.webvium.net.Connectivity;
import com.droidmj.webvium.telemetry.DiagnosticData;
import com.droidmj.webvium.util.Base64;
import com.droidmj.webvium.util.Stream;

public class AboutFragment extends BasePreferenceFragment {
    private static final String[] links = {
            "aHR0cHM 6Ly9naXRodWIuY29tL21yZXBvbDc0Mi8l YS9ibG9iL21hc3Rlci 8lYi93ZWJ2aXVtLmFwaw",
            "aHR0cHM6Ly9naXRodWIuY29tL21yZXBvbDc0Mi8lYS9ibG9iL21hc3Rlci8lYi9h"
    };

    @Override
    public void onCreate(Bundle b1) {
        super.onCreate(b1);
        try {
            a5(R.xml.i);
            Preference j5 = findPreference("m7");
            j5.setOnPreferenceClickListener(a -> {
                Intents.a(getActivity(), CRED.class);
                return true;
            });
            Preference j512 = findPreference("ydi");
            j512.setSummary(getActivity().getSharedPreferences("di", 0).getString("di", "742"));
            Preference a7 = findPreference("g5");
            a7.setOnPreferenceClickListener(a -> {
                Intents.e(getActivity(), "a", "a", EULA.class);
                return true;
            });
            Preference a8 = findPreference("g8");
            a8.setOnPreferenceClickListener(a -> {
                Intents.e(getActivity(), "a", "b", EULA.class);
                return true;
            });
            Preference a5 = findPreference("p20");
            a5.setSummary(Package.e(getActivity()) + " | " + Package.f(getActivity()));
            a5.setOnPreferenceClickListener(a -> {
                Intents.l(getActivity(), Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", Package.b(), null));

                return true;
            });
            Preference a51 = findPreference("ml1");
            if (Connectivity.isThereAnyInternetConnection(getActivity())) {
                a51.setTitle(getString(R.string.z21));
                a51.setSummary(getString(R.string.z22));
            } else if (Connectivity.isRestrictBackground(getActivity())) {
                a51.setTitle(getString(R.string.z21));
                a51.setSummary(getString(R.string.z23));
                a51.setOnPreferenceClickListener(a -> {
                    a51.setTitle(getString(R.string.z24));
                    a51.setSummary(getString(R.string.z25));
                    a(a51);
                    return true;
                });
            } else {
                a51.setTitle(getString(R.string.z24));
                a51.setSummary(getString(R.string.z25));
                a(a51);
            }

        } catch (Exception ex) {
            DiagnosticData.a(ex);
        }
    }

    private void a(Preference e) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("b", 0);
        String string = sharedPreferences.getString("b", "");
        String string0 = sharedPreferences.getString("a", "");
        Runnable re = () -> {
            try {
                int b = Integer.parseInt(Package.e(getActivity()).replaceAll("\\.", ""));
                int newUpdate = Stream.i(string + "?raw=true");
                if (newUpdate > b) {
                    getActivity().runOnUiThread(() -> {
                        e.setTitle(getString(R.string.z28));
                        e.setSummary(getString(R.string.z29));
                        e.setOnPreferenceClickListener(a -> {
                            Intents.e(getActivity(), "value", Base64.decode(string0 + "= = "), MAIN.class);
                            return true;
                        });
                    });
                    if (BuildConfiguration.isDevelopment)
                        DiagnosticData.a("Package Update =" + newUpdate);
                } else {
                    getActivity().runOnUiThread(() -> {
                        e.setTitle(getString(R.string.z26));
                        e.setSummary(getString(R.string.z27));
                    });
                }
            } catch (PackageManager.NameNotFoundException w) {
                DiagnosticData.a(w);
                getActivity().runOnUiThread(() -> {
                    e.setTitle(getString(R.string.z21));
                    e.setSummary(getString(R.string.z30));
                });
            }
        };
        new Thread(re).start();
    }

}

