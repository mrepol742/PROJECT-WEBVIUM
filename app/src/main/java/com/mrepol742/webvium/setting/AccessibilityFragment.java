/*
 *
 * Created by Melvin Jones Repol on 4/17/21 10:27 AM
 * Copyright (c) 2021 . All rights reserved. Melvin Jones Repol(mrepol742.github.io)
 * Last modified 4/17/21 10:26 AM
 *
 *  License under the GNU General Public License, Version 3.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *  https://www.gnu.org/licenses/gpl-3.0.en.html
 *  Unless required by the applicable law or agreed in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.mrepol742.webvium.setting;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.mrepol742.webvium.MAIN;
import com.mrepol742.webvium.R;
import com.mrepol742.webvium.TEXT;
import com.mrepol742.webvium.app.base.BasePreferenceFragment;
import com.mrepol742.webvium.content.Intents;
import com.mrepol742.webvium.content.Resources;
import com.mrepol742.webvium.os.CountDownTimer;
import com.mrepol742.webvium.telemetry.DiagnosticData;

public class AccessibilityFragment extends BasePreferenceFragment {

    @Override
    public void onCreate(Bundle b1) {
        super.onCreate(b1);
        try {

            a5(R.xml.k);
            Preference l = findPreference("zoom");
            l.setOnPreferenceClickListener(a -> {
                t();
                return true;
            });
            Preference a1 = findPreference("textS");
            a1.setOnPreferenceClickListener(a -> {
                Intents.a(getActivity(), TEXT.class);
                return true;
            });

        } catch (Exception ex) {
            DiagnosticData.a(ex);
        }
    }

    private void t() {
        AlertDialog.Builder c = new AlertDialog.Builder(getActivity());
        LayoutInflater d = LayoutInflater.from(getActivity());
        View e = d.inflate(R.layout.a12, null);
        c.setCancelable(false);
        c.setView(e);
        TextView f = e.findViewById(R.id.g1);
        f.setText(getString(R.string.o1));
        if (!a221().getBoolean("autoUpdate", false)) {
            f.setTextColor(Resources.b(getActivity(), R.color.c));
        } else {
            f.setTextColor(Resources.b(getActivity(), R.color.b));
        }
        AlertDialog j5 = c.create();
        O5 timer = new O5(2000, 2000, j5);
        timer.start();
        j5.show();
    }

    private void a() {
        getActivity().finish();
        SharedPreferences c56 = getActivity().getSharedPreferences("wv,", 0);
        Intent it = new Intent(getActivity(), MAIN.class);
        it.putExtra("value", c56.getString("MyURL", ""));
        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(it);
    }

    class O5 extends CountDownTimer {
        AlertDialog a5;

        public O5(long a, long b, AlertDialog gj) {
            super(a, b);
            a5 = gj;
        }

        @Override
        public void onFinish() {
            a5.dismiss();
            a5 = null;
            a();

        }
    }
}