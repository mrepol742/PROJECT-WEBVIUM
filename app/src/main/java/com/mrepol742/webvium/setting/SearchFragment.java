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

import android.app.AlertDialog;
import android.os.Build;
import android.os.Bundle;

import com.mrepol742.webvium.PREF;
import com.mrepol742.webvium.QUIC;
import com.mrepol742.webvium.R;
import com.mrepol742.webvium.SWIT;
import com.mrepol742.webvium.app.Notifications;
import com.mrepol742.webvium.app.base.BasePreferenceFragment;
import com.mrepol742.webvium.app.main.MainNotification;
import com.mrepol742.webvium.content.Intents;
import com.mrepol742.webvium.telemetry.DiagnosticData;
import com.mrepol742.webvium.text.Html;
import com.mrepol742.webvium.widget.Toast;

public class SearchFragment extends BasePreferenceFragment {

    @Override
    public void onCreate(Bundle b1) {
        super.onCreate(b1);
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                a5(R.xml.l);
            } else {
                a5(R.xml.d);
            }
            PREF b = (PREF) findPreference("asst");
            b.setOnPreferenceClickListener(a -> {
                b14();
                return true;
            });
            SWIT cbf = (SWIT) findPreference("qckS");
            cbf.setOnPreferenceChangeListener((preference, newValue) -> {
                if (newValue.toString().equals("true")) {
                    Intents.b(SearchFragment.this.getActivity(), QUIC.class);
                } else {
                    Intents.i(SearchFragment.this.getActivity(), QUIC.class);
                    MainNotification.a(SearchFragment.this.getActivity(), Notifications.d);
                }
                return true;
            });

        } catch (Exception ex) {
            DiagnosticData.a(ex);
        }
    }

    private void b14() {
        final AlertDialog.Builder a = new AlertDialog.Builder(getActivity());
        a.setCancelable(true);
        a.setTitle(getString(R.string.z18));
        a.setMessage(getString(R.string.a21));
        a.setPositiveButton(getString(R.string.i6), (a12, intetg) -> {
            Intents.k(getActivity(), "android.settings.VOICE_INPUT_SETTINGS");
            Toast.d(getActivity(), Html.b(getString(R.string.b33)).toString());
            a12.dismiss();
        });
        a.setNegativeButton(getString(R.string.i7), (a1, intetg) -> a1.dismiss());
        a.create().show();
    }

}