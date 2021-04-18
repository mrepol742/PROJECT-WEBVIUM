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

import android.app.Activity;
import android.app.AlertDialog;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebStorage;

import com.mrepol742.webvium.DEVI;
import com.mrepol742.webvium.EDIT0;
import com.mrepol742.webvium.FLOA;
import com.mrepol742.webvium.LOCK;
import com.mrepol742.webvium.LIST;
import com.mrepol742.webvium.PREF;
import com.mrepol742.webvium.R;
import com.mrepol742.webvium.app.Notifications;
import com.mrepol742.webvium.app.main.MainReceiver;
import com.mrepol742.webvium.SETT;
import com.mrepol742.webvium.SWIT;
import com.mrepol742.webvium.app.base.BasePreferenceFragment;
import com.mrepol742.webvium.app.main.MainNotification;
import com.mrepol742.webvium.content.Intents;
import com.mrepol742.webvium.content.IntentsFilter;
import com.mrepol742.webvium.telemetry.DiagnosticData;
import com.mrepol742.webvium.widget.Toast;

import java.util.Objects;

public class AdvancedFragment extends BasePreferenceFragment {
    private final IntentsFilter is = new IntentsFilter();
    private R7 r7;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 345 && resultCode == Activity.RESULT_OK) {
            Intents.e(getActivity(), "search", SETT.FRAGMENT_SECURITY_LOCK, SETT.class);

        }
        if (requestCode == 123 && resultCode == Activity.RESULT_OK) {
            Intents.e(getActivity(), "search", SETT.FRAGMENT_PRETEND_MODE, SETT.class);
        }
        if (requestCode == 246 && resultCode != Activity.RESULT_OK) {
            getActivity().getFragmentManager().popBackStack();
        }
    }

    @Override
    public void onCreate(Bundle b1) {
        super.onCreate(b1);
        try {
            if (a221().getBoolean("lockWn99", false) && a221().getBoolean("scrON", false)) {
                is.act(Intent.ACTION_SCREEN_ON);
                r7 = new R7();
                getActivity().registerReceiver(r7, is);
            }
            if (Build.VERSION.SDK_INT >= 30) {
                a5(R.xml.y);
            } else if (Build.VERSION.SDK_INT >= 29) {
                a5(R.xml.a2);
            } else if (Build.VERSION.SDK_INT >= 26) {
                a5(R.xml.a3);
            } else {
                a5(R.xml.f);
            }
            final LIST lp90 = (LIST) findPreference("textE");
            final EDIT0 etp12 = (EDIT0) findPreference("CtextE");
            SWIT cbf = (SWIT) findPreference("fltWeb");
            cbf.setOnPreferenceChangeListener((preference, newValue) -> {
                if (newValue.toString().equals("true")) {

                    Intents.b(getActivity(), FLOA.class);

                } else {

                    Intents.i(getActivity(), FLOA.class);
                    MainNotification.a(getActivity(), Notifications.e);
                }
                return true;
            });
            etp12.setEnabled(Objects.equals(a221().getString("textE", ""), "3840a"));
            etp12.setSummary(a221().getString("CtextE", ""));
            etp12.setOnPreferenceChangeListener((preference, newValue) -> {
                etp12.setSummary(newValue.toString());
                return true;
            });
            lp90.setOnPreferenceChangeListener((preference, newValue) -> {
                etp12.setEnabled(newValue.toString().equals("3840a"));
                return true;
            });
            PREF a9 = (PREF) findPreference("wLock");
            a9.setOnPreferenceClickListener(a -> {

                if (a221().getBoolean("lockWn99", false)) {
                    Intent it = new Intent(getActivity(), LOCK.class);
                    startActivityForResult(it, 345);
                } else {
                    Intents.e(getActivity(), "search", SETT.FRAGMENT_SECURITY_LOCK, SETT.class);
                }
                return true;
            });
            PREF a9zx = (PREF) findPreference("wPretend");
            a9zx.setOnPreferenceClickListener(a -> {

                if (a221().getBoolean("lockWn99", false)) {
                    Intent it = new Intent(getActivity(), LOCK.class);
                    startActivityForResult(it, 123);
                } else {
                    Intents.e(getActivity(), "search", SETT.FRAGMENT_PRETEND_MODE, SETT.class);
                }
                return true;
            });
            final SWIT java9 = (SWIT) findPreference("Java9");
            final SWIT java10 = (SWIT) findPreference("Java10");
            final SWIT java11 = (SWIT) findPreference("Java11");
            final SWIT java12 = (SWIT) findPreference("Java12");
            LIST java = (LIST) findPreference("java");
            java.setOnPreferenceChangeListener((preference, newValue) -> {
                if (newValue.toString().equals("7f")) {
                    java9.setEnabled(false);
                    java10.setEnabled(false);
                    java11.setEnabled(false);
                    java12.setEnabled(false);
                } else {
                    java9.setEnabled(true);
                    java10.setEnabled(true);
                    java11.setEnabled(true);
                    java12.setEnabled(true);
                }
                return true;
            });
            PREF pc = (PREF) findPreference("cjsa");
            pc.setOnPreferenceClickListener(a -> {
                b26();
                return true;
            });
            PREF a96755 = (PREF) findPreference("admn");
            a96755.setOnPreferenceClickListener(a -> {
                b16();
                return true;
            });
        } catch (Exception ex) {
            DiagnosticData.a(ex);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (r7 != null) {
            getActivity().unregisterReceiver(r7);
        }
    }

    private void b16() {
        Intent i = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        i.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, new ComponentName(getActivity(), DEVI.class));
        i.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "");
        startActivityForResult(i, 5);
    }

    private void b26() {
        final AlertDialog.Builder a = new AlertDialog.Builder(getActivity());
        a.setCancelable(true);
        a.setTitle(getString(R.string.c));
        a.setMessage(getString(R.string.a33));
        a.setPositiveButton(getString(R.string.i6), (a12, intetg) -> {
            WebStorage.getInstance().deleteAllData();
            g(getString(R.string.a34));
            a12.dismiss();

        });
        a.setNegativeButton(getString(R.string.i7), (a1, intetg) -> a1.dismiss());
        a.create().show();
    }

    private void g(String a) {
        Toast.b(getActivity(), a);
    }

    private class R7 extends MainReceiver {

        @Override
        public void onReceive(Context a, Intent b) {
            super.onReceive(a, b);
            String sg = b.getAction();
            if (sg.equals(Intent.ACTION_SCREEN_ON)) {
                if (a221().getBoolean("lockWn99", false)) {
                    Intent it = new Intent(a, LOCK.class);
                    startActivityForResult(it, 246);
                    getActivity().overridePendingTransition(R.anim.f, R.anim.b);
                }
            }
        }
    }
}