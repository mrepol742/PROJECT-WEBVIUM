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

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.webkit.CookieManager;
import android.webkit.WebViewDatabase;

import com.droidmj.webvium.CLIP;
import com.droidmj.webvium.EDIT0;
import com.droidmj.webvium.LOCK;
import com.droidmj.webvium.MAIN;
import com.droidmj.webvium.LIST;
import com.droidmj.webvium.R;
import com.droidmj.webvium.app.main.MainReceiver;
import com.droidmj.webvium.SWIT;
import com.droidmj.webvium.app.base.BasePreferenceFragment;
import com.droidmj.webvium.app.main.MainWebView;
import com.droidmj.webvium.bookmark.BookmarkHelper;
import com.droidmj.webvium.content.Clipboard;
import com.droidmj.webvium.content.Intents;
import com.droidmj.webvium.content.IntentsFilter;
import com.droidmj.webvium.content.Package;
import com.droidmj.webvium.history.HistoryHelper;
import com.droidmj.webvium.io.Files;
import com.droidmj.webvium.io.StorageDirectory;
import com.droidmj.webvium.search.SearchHelper;
import com.droidmj.webvium.telemetry.DiagnosticData;
import com.droidmj.webvium.widget.Toast;

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
            etp.setOnPreferenceChangeListener((preference, newValue) -> {
                etp.setSummary(newValue.toString());
                return true;
            });
            final LIST lp67 = (LIST) findPreference("userA");
            lp67.setOnPreferenceChangeListener((preference, newValue) -> {
                etp.setEnabled(newValue.toString().equals("7680e"));
                return true;
            });
            Preference f = findPreference("clearCache");
            f.setOnPreferenceClickListener(a -> {
                u();
                return true;
            });
            Preference f6 = findPreference("clearBook");
            f6.setOnPreferenceClickListener(a -> {
                a1();
                return true;
            });
            Preference f67 = findPreference("clearSSL");
            f67.setOnPreferenceClickListener(a -> {
                a2();
                return true;
            });
            Preference f6767 = findPreference("clearHTTP");
            f6767.setOnPreferenceClickListener(a -> {
                if (wd.hasHttpAuthUsernamePassword()) {
                    a4();
                }
                return true;
            });

            Preference f2 = findPreference("clearSearch");
            f2.setOnPreferenceClickListener(a -> {
                a3();
                return true;
            });
            Preference g = findPreference("clearHistory");
            g.setOnPreferenceClickListener(a -> {
                v();
                return true;
            });
            final Preference h = findPreference("clearCookies");
            h.setOnPreferenceClickListener(a -> {

                if (cm.hasCookies()) {
                    w(true);
                }
                return true;
            });
            final Preference hS = findPreference("clearSCookies");
            hS.setOnPreferenceClickListener(a -> {

                if (cm.hasCookies()) {
                    w(false);
                }
                return true;
            });
            if (!cm.hasCookies()) {
                h.setEnabled(false);
                hS.setEnabled(false);
            }
            Preference i = findPreference("clearForm");
            i.setOnPreferenceClickListener(a -> {
                if (wd.hasFormData()) {
                    x();
                }
                return true;
            });


            Preference j5 = findPreference("clearClipboard");
            j5.setOnPreferenceClickListener(a -> {
                b8();
                return true;
            });
            SWIT cbf = (SWIT) findPreference("nCV");
            cbf.setOnPreferenceChangeListener((preference, newValue) -> {
                if (newValue.toString().equals("true")) {

                    Intents.b(getActivity(), CLIP.class);

                } else {

                    Intents.i(getActivity(), CLIP.class);

                }
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

    private void u() {
        final AlertDialog.Builder a = new AlertDialog.Builder(getActivity());
        a.setCancelable(true);
        a.setTitle(getString(R.string.b));
        a.setMessage(getString(R.string.u5));
        a.setPositiveButton(getString(R.string.i6), (a12, intetg) -> {
            h(StorageDirectory.getCacheDir(getActivity()));
            g(getString(R.string.a27));
            a12.dismiss();
        });
        a.setNegativeButton(getString(R.string.i7), (a1, intetg) -> a1.dismiss());
        a.create().show();
    }

    private void h(final java.io.File a) {
        Runnable p15 = () -> {
            try {
                Files.deleteAll(a);
            } catch (Exception en) {
                DiagnosticData.a(en);
            }
        };
        new Thread(p15).start();
    }

    private void g(String a) {
        Toast.b(getActivity(), a);
    }

    private void a1() {
        final AlertDialog.Builder a = new AlertDialog.Builder(getActivity());
        a.setCancelable(true);
        a.setTitle(getString(R.string.b));
        a.setMessage(getString(R.string.u4));
        a.setPositiveButton(getString(R.string.i6), (a12, intetg) -> {
            SearchHelper d2 = SearchHelper.getInstance(getActivity().getApplicationContext());
            d2.delete();
            g(getString(R.string.u3));
            a12.dismiss();

        });
        a.setNegativeButton(getString(R.string.i7), (a1, intetg) -> a1.dismiss());
        a.create().show();
    }

    private void a2() {
        final AlertDialog.Builder a = new AlertDialog.Builder(getActivity());
        a.setCancelable(true);
        a.setTitle(getString(R.string.b));
        a.setMessage(getString(R.string.a37));
        a.setPositiveButton(getString(R.string.i6), (a12, intetg) -> {
            try {
                MainWebView w4 = new MainWebView(getActivity());
                w4.clearSslPreferences();
                w4.destroy();
                g(getString(R.string.a38));
            } catch (Exception en) {
                DiagnosticData.a(en);
                d(getString(R.string.a39));
            }
            a12.dismiss();

        });
        a.setNegativeButton(getString(R.string.i7), (a1, intetg) -> a1.dismiss());
        a.create().show();
    }

    private void a3() {
        final AlertDialog.Builder a = new AlertDialog.Builder(getActivity());
        a.setCancelable(true);
        a.setTitle(getString(R.string.b));
        a.setMessage(getString(R.string.u6));
        a.setPositiveButton(getString(R.string.i6), (a12, intetg) -> {
            BookmarkHelper d3 = BookmarkHelper.getInstance(getActivity().getApplicationContext());
            d3.delete();
            g(getString(R.string.a40));
            a12.dismiss();
        });
        a.setNegativeButton(getString(R.string.i7), (a1, intetg) -> a1.dismiss());
        a.create().show();
    }

    private void a4() {
        final AlertDialog.Builder a = new AlertDialog.Builder(getActivity());
        a.setCancelable(true);
        a.setTitle(getString(R.string.b));
        a.setMessage(getString(R.string.b21));
        a.setPositiveButton(getString(R.string.i6), (a12, intetg) -> {
            wd.clearHttpAuthUsernamePassword();
            g(getString(R.string.b22));
            a12.dismiss();

        });
        a.setNegativeButton(getString(R.string.i7), (a1, intetg) -> a1.dismiss());
        a.create().show();
    }

    private void d(String a) {
        Toast.c(getActivity(), a);
    }

    private void v() {
        final AlertDialog.Builder a = new AlertDialog.Builder(getActivity());
        a.setCancelable(true);
        a.setTitle(getString(R.string.b));
        a.setMessage(getString(R.string.t5));
        a.setPositiveButton(getString(R.string.i6), (a12, intetg) -> {
            HistoryHelper d1 = HistoryHelper.getInstance(getActivity().getApplicationContext());
            d1.delete();
            if (MAIN.bl2) {
                MAIN.c63();
            }
            g(getString(R.string.t1));
            a12.dismiss();

        });
        a.setNegativeButton(getString(R.string.i7), (a1, intetg) -> a1.dismiss());
        a.create().show();
    }

    private void w(boolean bn) {
        final AlertDialog.Builder a = new AlertDialog.Builder(getActivity());
        a.setCancelable(true);
        a.setTitle(getString(R.string.b));
        if (bn) {
            a.setMessage(getString(R.string.a28));
        } else {
            a.setMessage(getString(R.string.x13));
        }
        a.setPositiveButton(getString(R.string.i6), (a12, intetg) -> {
            if (bn) {
                cm.removeAllCookies(null);
                g(getString(R.string.a29));
            } else {
                cm.removeSessionCookies(null);
                g(getString(R.string.h28));
            }
            a12.dismiss();
        });
        a.setNegativeButton(getString(R.string.i7), (a1, intetg) -> a1.dismiss());
        a.create().show();
    }

    private void x() {
        final AlertDialog.Builder a = new AlertDialog.Builder(getActivity());
        a.setCancelable(true);
        a.setTitle(getString(R.string.b));
        a.setMessage(getString(R.string.a31));
        a.setPositiveButton(getString(R.string.i6), (a12, intetg) -> {
            wd.clearFormData();
            g(getString(R.string.a32));
            a12.dismiss();
        });
        a.setNegativeButton(getString(R.string.i7), (a1, intetg) -> a1.dismiss());
        a.create().show();
    }

    private void b8() {
        final AlertDialog.Builder a = new AlertDialog.Builder(getActivity());
        a.setCancelable(true);
        a.setTitle(getString(R.string.b));
        a.setMessage(getString(R.string.b36));
        a.setPositiveButton(getString(R.string.i6), (a12, intetg) -> {
            Clipboard.a(getActivity(), "");
            g(getString(R.string.b37));
            a12.dismiss();
        });
        a.setNegativeButton(getString(R.string.i7), (a1, intetg) -> a1.dismiss());
        a.create().show();
    }

    private class R7 extends MainReceiver {

        @Override
        public void onReceive(Context a, Intent b) {
            super.onReceive(a, b);
            String sg = b.getAction();
            assert sg != null;
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