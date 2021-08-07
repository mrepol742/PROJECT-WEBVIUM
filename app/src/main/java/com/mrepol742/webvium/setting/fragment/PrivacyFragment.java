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

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.webkit.CookieManager;
import android.webkit.WebStorage;
import android.webkit.WebViewDatabase;

import com.mrepol742.webvium.Clip;
import com.mrepol742.webvium.Edit0;
import com.mrepol742.webvium.List;
import com.mrepol742.webvium.Lock;
import com.mrepol742.webvium.Mana;
import com.mrepol742.webvium.Pref;
import com.mrepol742.webvium.R;
import com.mrepol742.webvium.Swit;
import com.mrepol742.webvium.app.base.BasePreferenceFragment;
import com.mrepol742.webvium.app.main.MainWebView;
import com.mrepol742.webvium.app.Clipboard;
import com.mrepol742.webvium.app.Intents;
import com.mrepol742.webvium.app.Package;
import com.mrepol742.webvium.util.AwesomeToast;

import java.util.Objects;

public class PrivacyFragment extends BasePreferenceFragment implements Preference.OnPreferenceClickListener, Preference.OnPreferenceChangeListener {
    private static final String[] userAgents = {
            "Mozilla/5.0 (Linux; Android 10) AppleWebKit/537.36 (KHTML, like Gecko) %b/%changedTo Mobile Safari/537.36",
            "Mozilla/5.0 (Mobile; Windows Phone 8.1; Android 4.0; ARM; Trident/7.0; Touch; rv:11.0; IEMobile/11.0; NOKIA; Lumia 635) like iPhone OS 7_0_3 Mac OS X AppleWebKit/537 (KHTML, like Gecko) Mobile Safari/537",
            "Mozilla/5.0 (Linux; Android 10) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.162 Mobile Safari/537.36",
            "Mozilla/5.0 (Android 8.0.0; Mobile; rv:61.0) Gecko/61.0 Firefox/68.0",
            "Mozilla/5.0 (Linux; Android 10; SM-N975F) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.162 Mobile Safari/537.36 OPR/55.2.2719",
            "Mozilla/5.0 (iPhone; CPU iPhone OS 13_4 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) CriOS/80.0.3987.95 Mobile/15E148 Safari/604.1",
            "Mozilla/5.0 (Windows Mobile 10; Android 8.0.0; Microsoft; Lumia 950XL) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.162 Mobile Safari/537.36 Edge/80.0.361.109",
            "Mozilla/5.0 (Linux; Android 8.0.0;) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.162 Mobile Safari/537.36",
            "Mozilla/5.0 (Linux; Android 8.0.0; SM-G935F Build/R16NW) AppleWebKit/537.36 (KHTML, like Gecko) Brave Chrome/69.0.3497.100 Mobile Safari/537.36",
    };
    private final IntentFilter is = new IntentFilter();
    private CookieManager cm;
    private WebViewDatabase wd;
    private R7 r7;
    private Edit0 etp;

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        switch (preference.getKey()) {
            case "CustomuserA":
                etp.setSummary(newValue.toString());
                return true;
            case "userA":
                etp.setEnabled(newValue.toString().equals("7680e"));
                return true;
            case "nCV":
                if (newValue.toString().equals("true")) {
                    Intents.b(PrivacyFragment.this.getActivity(), Clip.class);
                } else {
                    Intents.i(PrivacyFragment.this.getActivity(), Clip.class);
                }
                return true;
        }
        return false;
    }

    @Override
    public boolean onPreferenceClick(Preference a1) {
        switch (a1.getKey()) {
            case "cjsa":
                AlertDialog.Builder a45 = new AlertDialog.Builder(getActivity());
                a45.setCancelable(true);
                a45.setTitle(getString(R.string.c));
                a45.setMessage(getString(R.string.a33));
                a45.setPositiveButton(getString(R.string.i6), new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface a12, int intetg) {
                        WebStorage.getInstance().deleteAllData();
                        AwesomeToast.b(getActivity(), PrivacyFragment.this.getString(R.string.a34));
                        a12.dismiss();
                    }
                });
                a45.setNegativeButton(getString(R.string.i7), new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface a1, int intetg) {
                        a1.dismiss();
                    }
                });
                a45.create().show();
                return true;
            case "clearCache":
            case "clearBook":
                Intents.a(getActivity(), Mana.class);
                return true;
            case "clearCookies":
                if (cm.hasCookies()) {
                    PrivacyFragment.this.w(true);
                }
                return true;
            case "clearSCookies":
                if (cm.hasCookies()) {
                    PrivacyFragment.this.w(false);
                }
                return true;
            case "clearForm":
                if (wd.hasFormData()) {
                    AlertDialog.Builder a = new AlertDialog.Builder(getActivity());
                    a.setCancelable(true);
                    a.setTitle(getString(R.string.b));
                    a.setMessage(getString(R.string.a31));
                    a.setPositiveButton(getString(R.string.i6), new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface a12, int intetg) {
                            wd.clearFormData();
                            PrivacyFragment.this.g(PrivacyFragment.this.getString(R.string.a32));
                            a12.dismiss();
                        }
                    });
                    a.setNegativeButton(getString(R.string.i7), new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface a1, int intetg) {
                            a1.dismiss();
                        }
                    });
                    a.create().show();
                }
                return true;
            case "clearClipboard":
                AlertDialog.Builder a6 = new AlertDialog.Builder(getActivity());
                a6.setCancelable(true);
                a6.setTitle(getString(R.string.b));
                a6.setMessage(getString(R.string.b36));
                a6.setPositiveButton(getString(R.string.i6), new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface a12, int intetg) {
                        Clipboard.a(PrivacyFragment.this.getActivity(), "");
                        PrivacyFragment.this.g(PrivacyFragment.this.getString(R.string.b37));
                        a12.dismiss();
                    }
                });
                a6.setNegativeButton(getString(R.string.i7), new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface a1, int intetg) {
                        a1.dismiss();
                    }
                });
                a6.create().show();
                return true;
            case "clearHTTP":
                if (wd.hasHttpAuthUsernamePassword()) {
                    final AlertDialog.Builder a44 = new AlertDialog.Builder(getActivity());
                    a44.setCancelable(true);
                    a44.setTitle(getString(R.string.b));
                    a44.setMessage(getString(R.string.b21));
                    a44.setPositiveButton(getString(R.string.i6), new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface a12, int intetg) {
                            wd.clearHttpAuthUsernamePassword();
                            PrivacyFragment.this.g(PrivacyFragment.this.getString(R.string.b22));
                            a12.dismiss();
                        }
                    });
                    a44.setNegativeButton(getString(R.string.i7), new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface a1, int intetg) {
                            a1.dismiss();
                        }
                    });
                    a44.create().show();
                }
                return true;
            case "clearSSL":
                AlertDialog.Builder a9 = new AlertDialog.Builder(getActivity());
                a9.setCancelable(true);
                a9.setTitle(getString(R.string.b));
                a9.setMessage(getString(R.string.a37));
                a9.setPositiveButton(getString(R.string.i6), new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface a12, int intetg) {
                        try {
                            MainWebView w4 = new MainWebView(PrivacyFragment.this.getActivity());
                            w4.clearSslPreferences();
                            w4.destroy();
                            PrivacyFragment.this.g(PrivacyFragment.this.getString(R.string.a38));
                        } catch (Exception en) {
                            en.printStackTrace();
                            PrivacyFragment.this.d(PrivacyFragment.this.getString(R.string.a39));
                        }
                        a12.dismiss();
                    }
                });
                a9.setNegativeButton(getString(R.string.i7), new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface a1, int intetg) {
                        a1.dismiss();
                    }
                });
                a9.create().show();
                return true;
        }
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 345 && resultCode != Activity.RESULT_OK) {
            getActivity().getFragmentManager().popBackStack();
        }
    }

    @Override
    public void onCreate(Bundle b1) {
        super.onCreate(b1);
        if (a221().getBoolean("lockWn99", false) && a221().getBoolean("scrON", false)) {
            is.addAction(Intent.ACTION_SCREEN_ON);
            r7 = new R7();
            getActivity().registerReceiver(r7, is);
        }
        cm = CookieManager.getInstance();
        wd = WebViewDatabase.getInstance(getActivity().getApplicationContext());
        if (Build.VERSION.SDK_INT >= 26) {
            a5(R.xml.r);
        } else {
            a5(R.xml.g);
        }
        etp = (Edit0) findPreference("CustomuserA");
        etp.setEnabled(Objects.equals(a221().getString("userA", ""), "7680e"));
        try {
            if (a221().getString("CustomuserA", "") == null) {
                etp.setSummary(userAgents[0].replace("%changedTo", Package.e(getActivity())).replace("%b", Package.c()));
            } else {
                etp.setSummary(a221().getString("CustomuserA", ""));
            }
        } catch (Exception ne) {
            ne.printStackTrace();
        }
        etp.setOnPreferenceChangeListener(this);
        final List lp67 = (List) findPreference("userA");
        lp67.setOnPreferenceChangeListener(this);
        Preference f = findPreference("clearCache");
        f.setOnPreferenceClickListener(this);
        Preference f6 = findPreference("clearBook");
        f6.setOnPreferenceClickListener(this);
        Preference f67 = findPreference("clearSSL");
        f67.setOnPreferenceClickListener(this);
        Preference f6767 = findPreference("clearHTTP");
        f6767.setOnPreferenceClickListener(this);
        final Preference h = findPreference("clearCookies");
        h.setOnPreferenceClickListener(this);
        final Preference hS = findPreference("clearSCookies");
        hS.setOnPreferenceClickListener(this);
        if (!cm.hasCookies()) {
            h.setEnabled(false);
            hS.setEnabled(false);
        }
        Preference i = findPreference("clearForm");
        i.setOnPreferenceClickListener(this);
        Preference j5 = findPreference("clearClipboard");
        j5.setOnPreferenceClickListener(this);
        Swit cbf = (Swit) findPreference("nCV");
        cbf.setOnPreferenceChangeListener(this);
        if (Build.VERSION.SDK_INT >= 26) {
            Pref pc = (Pref) findPreference("cjsa");
            pc.setOnPreferenceClickListener(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (r7 != null) {
            getActivity().unregisterReceiver(r7);
        }
    }

    private void g(String a) {
        AwesomeToast.b(getActivity(), a);
    }

    private void d(String a) {
        AwesomeToast.c(getActivity(), a);
    }

    private void w(final boolean bn) {
        AlertDialog.Builder a = new AlertDialog.Builder(getActivity());
        a.setCancelable(true);
        a.setTitle(getString(R.string.b));
        if (bn) {
            a.setMessage(getString(R.string.a28));
        } else {
            a.setMessage(getString(R.string.x13));
        }
        a.setPositiveButton(getString(R.string.i6), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a12, int intetg) {
                if (bn) {
                    cm.removeAllCookies(null);
                    PrivacyFragment.this.g(PrivacyFragment.this.getString(R.string.a29));
                } else {
                    cm.removeSessionCookies(null);
                    PrivacyFragment.this.g(PrivacyFragment.this.getString(R.string.h28));
                }
                a12.dismiss();
            }
        });
        a.setNegativeButton(getString(R.string.i7), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a1, int intetg) {
                a1.dismiss();
            }
        });
        a.create().show();
    }

    private class R7 extends BroadcastReceiver {

        @Override
        public void onReceive(Context a, Intent b) {
            String sg = b.getAction();
            if (sg.equals(Intent.ACTION_SCREEN_ON)) {
                if (a221().getBoolean("lockWn99", false)) {
                    Intent it = new Intent(a, Lock.class);
                    startActivityForResult(it, 345);
                    getActivity().overridePendingTransition(R.anim.f, R.anim.b);
                }
            }
        }
    }
}