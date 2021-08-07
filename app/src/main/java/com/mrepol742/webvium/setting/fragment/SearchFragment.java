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
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;

import com.mrepol742.webvium.Pref;
import com.mrepol742.webvium.R;
import com.mrepol742.webvium.Sear;
import com.mrepol742.webvium.Swit;
import com.mrepol742.webvium.app.Notifications;
import com.mrepol742.webvium.app.Resources;
import com.mrepol742.webvium.app.base.BasePreferenceFragment;
import com.mrepol742.webvium.app.main.MainNotification;
import com.mrepol742.webvium.app.Intents;
import com.mrepol742.webvium.util.Html;
import com.mrepol742.webvium.util.AwesomeToast;

public class SearchFragment extends BasePreferenceFragment {

    @Override
    public void onCreate(Bundle b1) {
        super.onCreate(b1);
        if (Build.VERSION.SDK_INT >= 23) {
            a5(R.xml.l);
        } else {
            a5(R.xml.d);
        }
        Pref b = (Pref) findPreference("asst");
        b.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

            @Override
            public boolean onPreferenceClick(Preference a1) {
                AlertDialog.Builder a = new AlertDialog.Builder(getActivity());
                a.setCancelable(true);
                a.setTitle(getString(R.string.z18));
                a.setMessage(getString(R.string.a21));
                a.setPositiveButton(getString(R.string.i6), new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface a12, int intetg) {
                        Intents.k(SearchFragment.this.getActivity(), "android.settings.VOICE_INPUT_SETTINGS");
                        AwesomeToast.d(SearchFragment.this.getActivity(), Html.b(SearchFragment.this.getString(R.string.b33)).toString());
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
                return true;
            }
        });
        Swit cbf = (Swit) findPreference("qckS");
        cbf.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if (newValue.toString().equals("true")) {
                    MainNotification.b(SearchFragment.this.getActivity(), SearchFragment.this.getActivity().getString(R.string.l33), SearchFragment.this.getActivity().getString(R.string.y18));
                    android.app.Notification.Builder m = Notifications.a(SearchFragment.this.getActivity(), SearchFragment.this.getActivity().getString(R.string.l33));
                    m.setSmallIcon(R.drawable.a18);
                    m.setContentTitle(SearchFragment.this.getActivity().getString(R.string.l33));
                    m.setContentText(SearchFragment.this.getActivity().getString(R.string.n39));
                    m.setOngoing(true);
                    m.setColor(Resources.getColor(SearchFragment.this.getActivity(), R.color.a));
                    m.setAutoCancel(false);
                    Intent j11 = new Intent(SearchFragment.this.getActivity(), Sear.class);

                    PendingIntent k567 = PendingIntent.getActivity(SearchFragment.this.getActivity(), 0, j11, PendingIntent.FLAG_UPDATE_CURRENT);
                    m.setContentIntent(k567);
                    if (Build.VERSION.SDK_INT <= 26) {
                        m.setPriority(android.app.Notification.PRIORITY_LOW);
                    }
                    m.setVisibility(android.app.Notification.VISIBILITY_PUBLIC);
                    m.setLargeIcon(BitmapFactory.decodeResource(SearchFragment.this.getActivity().getResources(), R.mipmap.b));
                    NotificationManager nmc = (NotificationManager) SearchFragment.this.getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
                    nmc.notify(Notifications.d, m.build());
                } else {
                    MainNotification.a(SearchFragment.this.getActivity(), Notifications.d);
                }
                return true;
            }
        });
    }
}