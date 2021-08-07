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
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.webkit.WebStorage;

import com.mrepol742.webvium.Edit0;
import com.mrepol742.webvium.Lock;
import com.mrepol742.webvium.List;
import com.mrepol742.webvium.Pref;
import com.mrepol742.webvium.R;
import com.mrepol742.webvium.Webv;
import com.mrepol742.webvium.app.Notifications;
import com.mrepol742.webvium.Sett;
import com.mrepol742.webvium.Swit;
import com.mrepol742.webvium.app.Resources;
import com.mrepol742.webvium.app.base.BasePreferenceFragment;
import com.mrepol742.webvium.app.main.MainNotification;
import com.mrepol742.webvium.app.Intents;
import com.mrepol742.webvium.util.AwesomeToast;

import java.util.Objects;

public class AdvancedFragment extends BasePreferenceFragment implements Preference.OnPreferenceClickListener, Preference.OnPreferenceChangeListener {
    private final IntentFilter is = new IntentFilter();
    private R7 r7;
    private Edit0 etp12;
    private Swit java9;
    private Swit java10;
    private Swit java11;
    private Swit java12;
    private Swit javaweb;

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        switch (preference.getKey()) {
            case "fltWeb":
                if (newValue.toString().equals("true")) {
                    MainNotification.c(AdvancedFragment.this.getActivity(), AdvancedFragment.this.getActivity().getString(R.string.l32), AdvancedFragment.this.getActivity().getString(R.string.l34));
                    android.app.Notification.Builder m = Notifications.a(AdvancedFragment.this.getActivity(), AdvancedFragment.this.getActivity().getString(R.string.l32));
                    m.setSmallIcon(R.drawable.a18);
                    m.setContentTitle(AdvancedFragment.this.getActivity().getString(R.string.l32));
                    m.setContentText(AdvancedFragment.this.getActivity().getString(R.string.n40));
                    m.setOngoing(true);
                    m.setColor(Resources.getColor(AdvancedFragment.this.getActivity(), R.color.a));
                    m.setAutoCancel(false);
                    Intent j11 = new Intent(AdvancedFragment.this.getActivity(), Webv.class);
                    PendingIntent k567 = PendingIntent.getActivity(AdvancedFragment.this.getActivity(), 0, j11, PendingIntent.FLAG_UPDATE_CURRENT);
                    m.setContentIntent(k567);
                    if (Build.VERSION.SDK_INT <= 26) {
                        m.setPriority(android.app.Notification.PRIORITY_MIN);
                    }
                    m.setVisibility(android.app.Notification.VISIBILITY_PUBLIC);
                    m.setLargeIcon(BitmapFactory.decodeResource(AdvancedFragment.this.getActivity().getResources(), R.mipmap.c));
                    NotificationManager nmc = (NotificationManager) AdvancedFragment.this.getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
                    nmc.notify(Notifications.e, m.build());
                } else {
                    MainNotification.a(AdvancedFragment.this.getActivity(), Notifications.e);
                }
                return true;
            case "CTextE":
                etp12.setSummary(newValue.toString());
                return true;
            case "textE":
                etp12.setEnabled(newValue.toString().equals("3840a"));
                return true;
            case "java":
                boolean bn = !newValue.toString().equals("7f");
                java9.setEnabled(bn);
                java10.setEnabled(bn);
                java11.setEnabled(bn);
                java12.setEnabled(bn);
                if (Build.VERSION.SDK_INT >= 29) {
                    javaweb.setEnabled(bn);
                }
                return true;
        }
        return false;
    }

    @Override
    public boolean onPreferenceClick(Preference a123) {
        switch (a123.getKey()) {
            case "wPretend":
                if (AdvancedFragment.this.a221().getBoolean("lockWn99", false)) {
                    Intent it = new Intent(AdvancedFragment.this.getActivity(), Lock.class);
                    AdvancedFragment.this.startActivityForResult(it, 123);
                } else {
                    Intents.e(AdvancedFragment.this.getActivity(), "search", Sett.FRAGMENT_PRETEND_MODE, Sett.class);
                }
                return true;
            case "wLock":
                if (AdvancedFragment.this.a221().getBoolean("lockWn99", false)) {
                    Intent it = new Intent(AdvancedFragment.this.getActivity(), Lock.class);
                    AdvancedFragment.this.startActivityForResult(it, 345);
                } else {
                    Intents.e(AdvancedFragment.this.getActivity(), "search", Sett.FRAGMENT_SECURITY_LOCK, Sett.class);
                }
                return true;
        }
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 345 && resultCode == Activity.RESULT_OK) {
            Intents.e(getActivity(), "search", Sett.FRAGMENT_SECURITY_LOCK, Sett.class);
        }
        if (requestCode == 123 && resultCode == Activity.RESULT_OK) {
            Intents.e(getActivity(), "search", Sett.FRAGMENT_PRETEND_MODE, Sett.class);
        }
        if (requestCode == 246 && resultCode != Activity.RESULT_OK) {
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
        if (Build.VERSION.SDK_INT >= 30) {
            a5(R.xml.y);
        } else if (Build.VERSION.SDK_INT >= 29) {
            a5(R.xml.a2);
        } else if (Build.VERSION.SDK_INT >= 26) {
            a5(R.xml.a3);
        } else {
            a5(R.xml.f);
        }
        List lp90 = (List) findPreference("textE");
        etp12 = (Edit0) findPreference("CtextE");
        Swit cbf = (Swit) findPreference("fltWeb");
        cbf.setOnPreferenceChangeListener(this);
        etp12.setEnabled(Objects.equals(a221().getString("textE", ""), "3840a"));
        etp12.setSummary(a221().getString("CtextE", ""));
        etp12.setOnPreferenceChangeListener(this);
        lp90.setOnPreferenceChangeListener(this);
        Pref a9 = (Pref) findPreference("wLock");
        a9.setOnPreferenceClickListener(this);
        Pref a9zx = (Pref) findPreference("wPretend");
        a9zx.setOnPreferenceClickListener(this);
        java9 = (Swit) findPreference("Java9");
        java10 = (Swit) findPreference("Java10");
        java11 = (Swit) findPreference("Java11");
        java12 = (Swit) findPreference("Java12");
        if (Build.VERSION.SDK_INT >= 29) {
            javaweb = (Swit) findPreference("Javaweb");
        }
        List java = (List) findPreference("java");
        java.setOnPreferenceChangeListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (r7 != null) {
            getActivity().unregisterReceiver(r7);
        }
    }

    private class R7 extends BroadcastReceiver {

        @Override
        public void onReceive(Context a, Intent b) {
            String sg = b.getAction();
            if (sg.equals(Intent.ACTION_SCREEN_ON)) {
                if (a221().getBoolean("lockWn99", false)) {
                    Intent it = new Intent(a, Lock.class);
                    startActivityForResult(it, 246);
                    getActivity().overridePendingTransition(R.anim.f, R.anim.b);
                }
            }
        }
    }
}