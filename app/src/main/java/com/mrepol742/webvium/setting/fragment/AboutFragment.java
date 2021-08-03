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

package com.mrepol742.webvium.setting.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.mrepol742.webvium.BuildConfig;
import com.mrepol742.webvium.Webv;
import com.mrepol742.webvium.R;
import com.mrepol742.webvium.app.Resources;
import com.mrepol742.webvium.app.base.BasePreferenceFragment;
import com.mrepol742.webvium.app.Intents;
import com.mrepol742.webvium.app.Package;
import com.mrepol742.webvium.net.Connectivity;
import com.mrepol742.webvium.net.Stream;
import com.mrepol742.webvium.util.Html;
import com.mrepol742.webvium.util.JSON;

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
                public boolean onPreferenceClick(Preference a1) {
                    AlertDialog.Builder a = new AlertDialog.Builder(getActivity());
                    LayoutInflater b = getActivity().getLayoutInflater();
                    View c = b.inflate(R.layout.c20, null);
                    a.setView(c);
                    TextView tv = c.findViewById(R.id.c10);
                    ScrollView rl = c.findViewById(R.id.c3);
                    ImageView iv = c.findViewById(R.id.o39);
                    TextView tv1 = c.findViewById(R.id.o40);
                    iv.setImageResource(R.mipmap.c);
                    tv.setTextColor(Resources.getColor(getActivity(), R.color.b));
                    tv1.setTextColor(Resources.getColor(getActivity(), R.color.b));
                    rl.setBackground(Resources.getDrawable(getActivity(), R.drawable.f11));
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("di", 0);
                    try {
                        tv.setText(Html.b(String.format(getString(R.string.y64),
                                sharedPreferences.getString("di", ""),
                                sharedPreferences.getString("di1", ""))));
                        tv1.setText(Html.b(String.format(getString(R.string.y90), Package.e(getActivity()))));
                    } catch (Exception en) {
                        en.printStackTrace();
                    }
                    a.setCancelable(true);
                    a.create().show();
                    return true;
                }
            });
            Preference a722 = findPreference("g6");
            a722.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference a45) {
                    AlertDialog.Builder a = new AlertDialog.Builder(getActivity());
                    a.setCancelable(true);
                    a.setTitle(getString(R.string.x44));
                    a.setMessage(getString(R.string.g5));
                    a.setPositiveButton(getString(R.string.l15), new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface a12, int intetg) {
                            a12.dismiss();
                        }
                    });
                    a.create().show();
                    return true;
                }
            });
            Preference a5 = findPreference("p20");
            if (BuildConfig.DEBUG) {
                a5.setSummary("v" + Package.e(getActivity()) + " c" + Package.f(getActivity()) + " dev");
            } else {
                a5.setSummary("v" + Package.e(getActivity()) + " c" + Package.f(getActivity()));
            }
            a5.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference a) {
                    Intents.l(AboutFragment.this.getActivity(), Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", Package.b(), null));
                    return true;
                }
            });
            final Preference a51 = findPreference("ml1");
            a(a51);
            a51.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference a) {
                    a(a51);
                    return true;
                }
            });
            Preference preference = findPreference("ins12");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh.mm aa, MMMM dd, yyyy", Locale.US);
            try {
                preference.setTitle(getString(R.string.x56));
                preference.setSummary(simpleDateFormat.format(new Date(Package.g(getActivity()))));
            } catch (Exception exception) {
                exception.printStackTrace();
                preference.setSummary(simpleDateFormat.format(new Date()));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void a(final Preference e) {
        if (Connectivity.isThereAnyInternetConnection(getActivity())) {
            e.setTitle(getString(R.string.z21));
            e.setSummary(getString(R.string.z22));
        } else {
            e.setTitle(getString(R.string.z24));
            e.setSummary(getString(R.string.z25));
            Runnable re = new Runnable() {

                @Override
                public void run() {
                    try {
                        int b = Integer.parseInt(Package.e(AboutFragment.this.getActivity()).replaceAll("\\.", ""));
                        int[] newUpdate = JSON.getUpdate(Stream.f("https://github.com/" + AboutFragment.this.getString(R.string.github_username) + "/" + AboutFragment.this.getString(R.string.github_repository) + "/blob/" + AboutFragment.this.getString(R.string.github_branch) + "/" + AboutFragment.this.getString(R.string.github_path) + "/Upda.json?raw=true", "742"));
                        if (AboutFragment.this.getActivity() == null) {
                            return;
                        }
                        if (newUpdate[0] > b) {
                            AboutFragment.this.getActivity().runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    e.setTitle(AboutFragment.this.getString(R.string.z28));
                                    e.setSummary(AboutFragment.this.getString(R.string.z29));
                                    e.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                                        @Override
                                        public boolean onPreferenceClick(Preference a) {
                                            Intents.e(AboutFragment.this.getActivity(), "value", "https://mrepol742.github.io/PROJECT-WEBVIUM", Webv.class);
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
}

