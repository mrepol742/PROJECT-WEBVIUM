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

import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.provider.Settings;

import com.mrepol742.webvium.CRED;
import com.mrepol742.webvium.MAIN;
import com.mrepol742.webvium.PRIV;
import com.mrepol742.webvium.R;
import com.mrepol742.webvium.TERM;
import com.mrepol742.webvium.app.base.BasePreferenceFragment;
import com.mrepol742.webvium.content.Intents;
import com.mrepol742.webvium.content.Package;
import com.mrepol742.webvium.net.Connectivity;
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
            j5.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference a) {
                    Intents.a(AboutFragment.this.getActivity(), CRED.class);
                    return true;
                }
            });
            Preference a7 = findPreference("g5");
            a7.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference a) {
                    Intents.a(AboutFragment.this.getActivity(), TERM.class);
                    return true;
                }
            });
            Preference a722 = findPreference("g6");
            a722.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference a) {
                    Intents.a(AboutFragment.this.getActivity(), PRIV.class);
                    return true;
                }
            });
            Preference a5 = findPreference("p20");
            a5.setSummary(Package.e(getActivity()) + " | " + Package.f(getActivity()));
            a5.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference a) {
                    Intents.l(AboutFragment.this.getActivity(), Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", Package.b(), null));

                    return true;
                }
            });
            Preference a51 = findPreference("ml1");
            if (Connectivity.isThereAnyInternetConnection(getActivity())) {
                a51.setTitle(getString(R.string.z21));
                a51.setSummary(getString(R.string.z22));
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
                exception.printStackTrace();
                preference.setSummary(getString(R.string.x58));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void a(final Preference e) {
        Runnable re = new Runnable() {

            @Override
            public void run() {
                try {
                    int b = Integer.parseInt(Package.e(AboutFragment.this.getActivity()).replaceAll("\\.", ""));
                    int newUpdate = Stream.i("https://github.com/" + AboutFragment.this.getString(R.string.github_username) + "/" + AboutFragment.this.getString(R.string.github_repository) + "/blob/" + AboutFragment.this.getString(R.string.github_branch) + "/" + AboutFragment.this.getString(R.string.github_path) + "/newVersion.int?raw=true");
                    if (AboutFragment.this.getActivity() == null) {
                        return;
                    }
                    if (newUpdate > b) {
                        AboutFragment.this.getActivity().runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                e.setTitle(AboutFragment.this.getString(R.string.z28));
                                e.setSummary(AboutFragment.this.getString(R.string.z29));
                                e.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                                    @Override
                                    public boolean onPreferenceClick(Preference a) {
                                        Intents.e(AboutFragment.this.getActivity(), "value", "https://mrepol742.github.io/PROJECT-WEBVIUM", MAIN.class);
                                        return true;
                                    }
                                });
                            }
                        });
                    } else {
                        AboutFragment.this.getActivity().runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                e.setTitle(AboutFragment.this.getString(R.string.z26));
                                e.setSummary(AboutFragment.this.getString(R.string.z27));
                            }
                        });
                    }
                } catch (Exception w) {
                    w.printStackTrace();
                    AboutFragment.this.getActivity().runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            e.setTitle(AboutFragment.this.getString(R.string.z21));
                            e.setSummary(AboutFragment.this.getString(R.string.z30));
                        }
                    });
                }
            }
        };
        new Thread(re).start();
    }

}

