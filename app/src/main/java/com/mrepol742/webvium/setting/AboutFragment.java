/*
 *
 * Copyright (c) 2021 Melvin Jones Repol (mrepol742.github.io). All rights reserved.
 *
 * License under the GNU General Public License, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.gnu.org/licenses/gpl-3.0.en.html
 *
 * Unless required by the applicable law or agreed in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mrepol742.webvium.setting;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.provider.Settings;

import com.mrepol742.webvium.CRED;
import com.mrepol742.webvium.MAIN;
import com.mrepol742.webvium.R;
import com.mrepol742.webvium.TERM;
import com.mrepol742.webvium.WELC;
import com.mrepol742.webvium.app.base.BasePreferenceFragment;
import com.mrepol742.webvium.content.Intents;
import com.mrepol742.webvium.content.Package;
import com.mrepol742.webvium.net.Connectivity;
import com.mrepol742.webvium.util.Log;
import com.mrepol742.webvium.util.Base64;
import com.mrepol742.webvium.util.Stream;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AboutFragment extends BasePreferenceFragment {

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
                Intents.a(getActivity(), TERM.class);
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
            Preference preference = findPreference("ins12");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM dd, yyyy | hh:mm:ss", Locale.US);
            try {
                preference.setTitle(getString(R.string.x56));
                preference.setSummary(simpleDateFormat.format(new Date(Package.g(getActivity()))));
            } catch (Exception exception) {
                Log.a(exception);
                preference.setSummary(getString(R.string.x58));
            }


        } catch (Exception ex) {
            Log.a(ex);
        }
    }

    private void a(Preference e) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("b", 0);
        String string = sharedPreferences.getString(WELC.TEMP_UPDATE_VERSION, "");
        String string0 = sharedPreferences.getString(WELC.TEMP_UPDATE_URL, "");
        Runnable re = () -> {
            try {
                int b = Integer.parseInt(Package.e(getActivity()).replaceAll("\\.", ""));
                int newUpdate = Stream.i(string + "?raw=true");
                if (newUpdate > b) {
                    getActivity().runOnUiThread(() -> {
                        e.setTitle(getString(R.string.z28));
                        e.setSummary(getString(R.string.z29));
                        e.setOnPreferenceClickListener(a -> {
                            Intents.e(getActivity(), "value", Base64.decode(string0), MAIN.class);
                            return true;
                        });
                    });
                } else {
                    getActivity().runOnUiThread(() -> {
                        e.setTitle(getString(R.string.z26));
                        e.setSummary(getString(R.string.z27));
                    });
                }
            } catch (Exception w) {
                Log.a(w);
                getActivity().runOnUiThread(() -> {
                    e.setTitle(getString(R.string.z21));
                    e.setSummary(getString(R.string.z30));
                });
            }
        };
        new Thread(re).start();
    }

}

