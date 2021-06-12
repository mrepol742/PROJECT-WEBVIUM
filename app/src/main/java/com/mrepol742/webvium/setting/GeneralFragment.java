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
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.mrepol742.webvium.EDIT0;
import com.mrepol742.webvium.LIST;
import com.mrepol742.webvium.PREF;
import com.mrepol742.webvium.R;
import com.mrepol742.webvium.UPDA;
import com.mrepol742.webvium.app.base.BasePreferenceFragment;
import com.mrepol742.webvium.content.Intents;
import com.mrepol742.webvium.util.U3;
import com.mrepol742.webvium.widget.Toast;

import java.util.Objects;

public class GeneralFragment extends BasePreferenceFragment {
    private static final String[] eng = {
            "https://google.com",
            "https://duckduckgo.com",
            "https://bing.com",
            "https://yahoo.com",
            "https://ask.com",
            "https://aol.com",
            "https://baidu.com",
            "https://wolframalpha.com",
            "https://0.discoverapp.com",
            "https://ecosia.org",
            "https://stackoverflow.com",
            "https://youtube.com",
            "https://github.com",
            "https://facebook.com"
    };

    @Override
    public void onCreate(Bundle b1) {
        super.onCreate(b1);
        try {
            if (Build.VERSION.SDK_INT >= 28) {
                a5(R.xml.b);
            } else if (Build.VERSION.SDK_INT >= 26) {
                a5(R.xml.a);
            } else {
                a5(R.xml.c);
            }
            final LIST hj9 = (LIST) findPreference("general");

            final EDIT0 hj89 = (EDIT0) findPreference("cGeneral");

            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());

            SharedPreferences a19 = getActivity().getSharedPreferences("wv", 0);
            final String a20 = a19.getString("MyURL", c48());

            if (Objects.equals(sp.getString("general", ""), "1o")) {
                hj9.setSummary(getActivity().getResources().getString(R.string.x17));
                hj89.setEnabled(false);
                hj89.setSummary(sp.getString("cGeneral", ""));
            } else if (Objects.equals(sp.getString("general", ""), "7o")) {
                hj9.setSummary(a20);
                hj89.setEnabled(false);
                hj89.setSummary(sp.getString("cGeneral", ""));
            } else if (Objects.equals(sp.getString("general", ""), "30o")) {
                hj9.setSummary(getActivity().getResources().getString(R.string.c26));
                hj89.setEnabled(false);
                hj89.setSummary(sp.getString("cGeneral", ""));
            } else if (Objects.equals(sp.getString("general", ""), "60o")) {
                hj9.setSummary(getActivity().getResources().getString(R.string.q20));
                hj89.setEnabled(true);
                String pre = sp.getString("cGeneral", "");
                if (pre != null && U3.b(pre)) {
                    hj89.setSummary(pre);
                } else {
                    hj89.setSummary(c48());
                }
            }

            hj89.setOnPreferenceChangeListener((preference, newValue) -> {
                if (newValue != null && U3.b((newValue.toString()))) {
                    hj89.setSummary(newValue.toString());
                } else {
                    hj89.setSummary(c48());

                }
                return true;
            });
            hj9.setOnPreferenceChangeListener((preference, newValue) -> {
                if (newValue.toString().equals("1o")) {
                    hj9.setSummary(getActivity().getResources().getString(R.string.x17));
                    hj89.setEnabled(false);
                    hj89.setSummary(sp.getString("cGeneral", ""));
                } else if (newValue.toString().equals("7o")) {
                    hj9.setSummary(a20);
                    hj89.setEnabled(false);
                    hj89.setSummary(sp.getString("cGeneral", ""));
                } else if (newValue.toString().equals("30o")) {
                    hj9.setSummary(getActivity().getResources().getString(R.string.c26));
                    hj89.setEnabled(false);
                    hj89.setSummary(sp.getString("cGeneral", ""));
                } else if (newValue.toString().equals("60o")) {
                    hj89.setEnabled(true);
                    hj9.setSummary(getActivity().getResources().getString(R.string.q20));
                    String pre = sp.getString("cGeneral", "");
                    if (pre != null && U3.b(pre)) {
                        hj89.setSummary(pre);
                    } else {
                        hj89.setSummary(c48());

                    }
                }
                return true;
            });
            PREF a1 = (PREF) findPreference("cfu");
            a1.setOnPreferenceClickListener(a -> {
                Intents.b(getActivity(), UPDA.class);
                Toast.b(getActivity(), getString(R.string.m30));

                return true;
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String c48() {
        switch (Objects.requireNonNull(a221().getString("searchP", "7b"))) {
            case "1b":
                return eng[1];
            default:
            case "7b":
                return eng[0];
            case "30b":
                return eng[2];
            case "60b":
                return eng[3];
            case "120b":
                return eng[4];
            case "240b":
                return eng[5];
            case "480b":
                return eng[6];
            case "860b":
                return eng[7];
            case "1720b":
                return eng[8];
            case "3440b":
                return eng[9];
            case "6880b":
                return eng[10];
        }
    }
}