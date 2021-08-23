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

package com.mrepol742.webvium.app.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.provider.Settings;

import com.mrepol742.webvium.R;
import com.mrepol742.webvium.app.base.BasePreferenceFragment;
import com.mrepol742.webvium.app.Intents;
import com.mrepol742.webvium.util.Html;
import com.mrepol742.webvium.util.AwesomeToast;

public class DownloadFragment extends BasePreferenceFragment {

    @Override
    public void onCreate(Bundle b1) {
        super.onCreate(b1);
        if (Build.VERSION.SDK_INT >= 23) {
            a5(R.xml.m);
            Preference d = findPreference("batt");
            d.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference a5) {
                    AlertDialog.Builder a = new AlertDialog.Builder(getActivity());
                    a.setCancelable(true);
                    a.setTitle(getString(R.string.f));
                    a.setMessage(Html.b(getString(R.string.u12)));
                    a.setPositiveButton(getString(R.string.i6), new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface a12, int intetg) {
                            Intents.k(DownloadFragment.this.getActivity(), Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS);
                            AwesomeToast.d(DownloadFragment.this.getActivity(), Html.b(DownloadFragment.this.getString(R.string.a25)).toString());
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
        } else {
            a5(R.xml.e);
        }
    }
}