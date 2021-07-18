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
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.webkit.CookieManager;
import android.webkit.WebViewDatabase;

import com.mrepol742.webvium.CLIP;
import com.mrepol742.webvium.EDIT0;
import com.mrepol742.webvium.LIST;
import com.mrepol742.webvium.LOCK;
import com.mrepol742.webvium.MAIN;
import com.mrepol742.webvium.R;
import com.mrepol742.webvium.SWIT;
import com.mrepol742.webvium.app.base.BasePreferenceFragment;
import com.mrepol742.webvium.app.main.MainReceiver;
import com.mrepol742.webvium.app.main.MainWebView;
import com.mrepol742.webvium.bookmark.BookmarkHelper;
import com.mrepol742.webvium.content.Clipboard;
import com.mrepol742.webvium.content.Intents;
import com.mrepol742.webvium.content.IntentsFilter;
import com.mrepol742.webvium.content.Package;
import com.mrepol742.webvium.history.HistoryHelper;
import com.mrepol742.webvium.io.Files;
import com.mrepol742.webvium.io.StorageDirectory;
import com.mrepol742.webvium.search.SearchHelper;
import com.mrepol742.webvium.widget.AwesomeToast;

import java.util.Objects;

public class PrivacyFragment extends BasePreferenceFragment {
    private static final String[] userAgents = {
            "Mozilla/5.0 (Linux; Android 10) AppleWebKit/537.36 (KHTML, like Gecko) %b/%a Mobile Safari/537.36",
            "Mozilla/5.0 (Mobile; Windows Phone 8.1; Android 4.0; ARM; Trident/7.0; Touch; rv:11.0; IEMobile/11.0; NOKIA; Lumia 635) like iPhone OS 7_0_3 Mac OS X AppleWebKit/537 (KHTML, like Gecko) Mobile Safari/537",
            "Mozilla/5.0 (Linux; Android 10) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.162 Mobile Safari/537.36",
            "Mozilla/5.0 (Android 8.0.0; Mobile; rv:61.0) Gecko/61.0 Firefox/68.0",
            "Mozilla/5.0 (Linux; Android 10; SM-N975F) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.162 Mobile Safari/537.36 OPR/55.2.2719",
            "Mozilla/5.0 (iPhone; CPU iPhone OS 13_4 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) CriOS/80.0.3987.95 Mobile/15E148 Safari/604.1",
            "Mozilla/5.0 (Windows Mobile 10; Android 8.0.0; Microsoft; Lumia 950XL) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.162 Mobile Safari/537.36 Edge/80.0.361.109",
            "Mozilla/5.0 (Linux; Android 8.0.0;) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.162 Mobile Safari/537.36",
            "Mozilla/5.0 (Linux; Android 8.0.0; SM-G935F Build/R16NW) AppleWebKit/537.36 (KHTML, like Gecko) Brave Chrome/69.0.3497.100 Mobile Safari/537.36",
    };
    private final IntentsFilter is = new IntentsFilter();
    private CookieManager cm;
    private WebViewDatabase wd;
    private R7 r7;

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
        try {
            if (a221().getBoolean("lockWn99", false) && a221().getBoolean("scrON", false)) {
                is.act(Intent.ACTION_SCREEN_ON);
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
            final EDIT0 etp = (EDIT0) findPreference("CustomuserA");

            etp.setEnabled(Objects.equals(a221().getString("userA", ""), "7680e"));
            if (a221().getString("CustomuserA", "") == null) {
                etp.setSummary(userAgents[0].replace("%a", Package.e(getActivity())).replace("%b", Package.c()));
            } else {
                etp.setSummary(a221().getString("CustomuserA", ""));
            }
            etp.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    etp.setSummary(newValue.toString());
                    return true;
                }
            });
            final LIST lp67 = (LIST) findPreference("userA");
            lp67.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    etp.setEnabled(newValue.toString().equals("7680e"));
                    return true;
                }
            });
            Preference f = findPreference("clearCache");
            f.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference a) {
                    PrivacyFragment.this.u();
                    return true;
                }
            });
            Preference f6 = findPreference("clearBook");
            f6.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference a) {
                    PrivacyFragment.this.a1();
                    return true;
                }
            });
            Preference f67 = findPreference("clearSSL");
            f67.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference a) {
                    PrivacyFragment.this.a2();
                    return true;
                }
            });
            Preference f6767 = findPreference("clearHTTP");
            f6767.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference a) {
                    if (wd.hasHttpAuthUsernamePassword()) {
                        PrivacyFragment.this.a4();
                    }
                    return true;
                }
            });

            Preference f2 = findPreference("clearSearch");
            f2.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference a) {
                    PrivacyFragment.this.a3();
                    return true;
                }
            });
            Preference g = findPreference("clearHistory");
            g.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference a) {
                    PrivacyFragment.this.v();
                    return true;
                }
            });
            final Preference h = findPreference("clearCookies");
            h.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference a) {

                    if (cm.hasCookies()) {
                        PrivacyFragment.this.w(true);
                    }
                    return true;
                }
            });
            final Preference hS = findPreference("clearSCookies");
            hS.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference a) {

                    if (cm.hasCookies()) {
                        PrivacyFragment.this.w(false);
                    }
                    return true;
                }
            });
            if (!cm.hasCookies()) {
                h.setEnabled(false);
                hS.setEnabled(false);
            }
            Preference i = findPreference("clearForm");
            i.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference a) {
                    if (wd.hasFormData()) {
                        PrivacyFragment.this.x();
                    }
                    return true;
                }
            });


            Preference j5 = findPreference("clearClipboard");
            j5.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference a) {
                    PrivacyFragment.this.b8();
                    return true;
                }
            });
            SWIT cbf = (SWIT) findPreference("nCV");
            cbf.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    if (newValue.toString().equals("true")) {

                        Intents.b(PrivacyFragment.this.getActivity(), CLIP.class);

                    } else {

                        Intents.i(PrivacyFragment.this.getActivity(), CLIP.class);

                    }
                    return true;
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (r7 != null) {
            getActivity().unregisterReceiver(r7);
        }
    }

    private void u() {
        final AlertDialog.Builder a = new AlertDialog.Builder(getActivity());
        a.setCancelable(true);
        a.setTitle(getString(R.string.b));
        a.setMessage(getString(R.string.u5));
        a.setPositiveButton(getString(R.string.i6), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a12, int intetg) {
                Runnable p15 = new Runnable() {

                    @Override
                    public void run() {
                        PrivacyFragment.this.h(StorageDirectory.getCacheDir(PrivacyFragment.this.getActivity()));
                    }
                };
                new Thread(p15).start();
                PrivacyFragment.this.g(PrivacyFragment.this.getString(R.string.a27));
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

    private void h(java.io.File a) {
        try {
            Files.deleteAll(a);
        } catch (Exception en) {
            en.printStackTrace();
        }
    }

    private void g(String a) {
        AwesomeToast.b(getActivity(), a);
    }

    private void a1() {
        final AlertDialog.Builder a = new AlertDialog.Builder(getActivity());
        a.setCancelable(true);
        a.setTitle(getString(R.string.b));
        a.setMessage(getString(R.string.u4));
        a.setPositiveButton(getString(R.string.i6), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a12, int intetg) {
                SearchHelper d2 = SearchHelper.getInstance(PrivacyFragment.this.getActivity().getApplicationContext());
                d2.delete();
                PrivacyFragment.this.g(PrivacyFragment.this.getString(R.string.u3));
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

    private void a2() {
        final AlertDialog.Builder a = new AlertDialog.Builder(getActivity());
        a.setCancelable(true);
        a.setTitle(getString(R.string.b));
        a.setMessage(getString(R.string.a37));
        a.setPositiveButton(getString(R.string.i6), new DialogInterface.OnClickListener() {

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
        a.setNegativeButton(getString(R.string.i7), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a1, int intetg) {
                a1.dismiss();
            }
        });
        a.create().show();
    }

    private void a3() {
        final AlertDialog.Builder a = new AlertDialog.Builder(getActivity());
        a.setCancelable(true);
        a.setTitle(getString(R.string.b));
        a.setMessage(getString(R.string.u6));
        a.setPositiveButton(getString(R.string.i6), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a12, int intetg) {
                BookmarkHelper d3 = BookmarkHelper.getInstance(PrivacyFragment.this.getActivity().getApplicationContext());
                d3.delete();
                PrivacyFragment.this.g(PrivacyFragment.this.getString(R.string.a40));
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

    private void a4() {
        final AlertDialog.Builder a = new AlertDialog.Builder(getActivity());
        a.setCancelable(true);
        a.setTitle(getString(R.string.b));
        a.setMessage(getString(R.string.b21));
        a.setPositiveButton(getString(R.string.i6), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a12, int intetg) {
                wd.clearHttpAuthUsernamePassword();
                PrivacyFragment.this.g(PrivacyFragment.this.getString(R.string.b22));
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

    private void d(String a) {
        AwesomeToast.c(getActivity(), a);
    }

    private void v() {
        final AlertDialog.Builder a = new AlertDialog.Builder(getActivity());
        a.setCancelable(true);
        a.setTitle(getString(R.string.b));
        a.setMessage(getString(R.string.t5));
        a.setPositiveButton(getString(R.string.i6), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a12, int intetg) {
                HistoryHelper d1 = HistoryHelper.getInstance(PrivacyFragment.this.getActivity().getApplicationContext());
                d1.delete();
                if (MAIN.bl2) {
                    MAIN.c63();
                }
                PrivacyFragment.this.g(PrivacyFragment.this.getString(R.string.t1));
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

    private void w(final boolean bn) {
        final AlertDialog.Builder a = new AlertDialog.Builder(getActivity());
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

    private void x() {
        final AlertDialog.Builder a = new AlertDialog.Builder(getActivity());
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

    private void b8() {
        final AlertDialog.Builder a = new AlertDialog.Builder(getActivity());
        a.setCancelable(true);
        a.setTitle(getString(R.string.b));
        a.setMessage(getString(R.string.b36));
        a.setPositiveButton(getString(R.string.i6), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a12, int intetg) {
                Clipboard.a(PrivacyFragment.this.getActivity(), "");
                PrivacyFragment.this.g(PrivacyFragment.this.getString(R.string.b37));
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

    private class R7 extends MainReceiver {

        @Override
        public void onReceive(Context a, Intent b) {
            super.onReceive(a, b);
            String sg = b.getAction();
            if (sg.equals(Intent.ACTION_SCREEN_ON)) {
                if (a221().getBoolean("lockWn99", false)) {
                    Intent it = new Intent(a, LOCK.class);
                    startActivityForResult(it, 345);
                    getActivity().overridePendingTransition(R.anim.f, R.anim.b);
                }
            }
        }
    }

}