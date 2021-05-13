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
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.mrepol742.webvium.MAIN;
import com.mrepol742.webvium.R;
import com.mrepol742.webvium.app.base.BasePreferenceFragment;
import com.mrepol742.webvium.content.Intents;
import com.mrepol742.webvium.content.Resources;
import com.mrepol742.webvium.io.StorageDirectory;
import com.mrepol742.webvium.os.CountDownTimer;
import com.mrepol742.webvium.util.Log;
import com.mrepol742.webvium.util.cache.BitmapCache;
import com.mrepol742.webvium.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

public class InterfaceFragment extends BasePreferenceFragment {

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 79 && data != null) {
            Runnable p15 = () -> {
                try {
                    InputStream c = getActivity().getContentResolver().openInputStream(Objects.requireNonNull(data.getData()));
                    OutputStream d = new FileOutputStream(StorageDirectory.getBackground(getActivity()));
                    byte[] e = new byte[1024];
                    int f;
                    if (c != null) {
                        while ((f = c.read(e)) != -1) {
                            d.write(e, 0, f);
                        }
                    }
                    Objects.requireNonNull(c).close();
                    d.flush();
                    d.close();
                    BitmapCache.getInstance().b(StorageDirectory.getBackground(getActivity()));
                    Intent it = new Intent(Intents.ACTION_INVALIDATE);
                    getActivity().sendBroadcast(it);
                    getActivity().runOnUiThread(() -> Toast.b(getActivity(), getString(R.string.o29)));
                } catch (Exception en) {
                    Log.a(en);
                    getActivity().runOnUiThread(() -> Toast.b(getActivity(), getString(R.string.p33)));
                }
            };
            new Thread(p15).start();
        }
    }

    @Override
    public void onCreate(Bundle b1) {
        super.onCreate(b1);
        try {
            if (Build.VERSION.SDK_INT >= 29) {
                a5(R.xml.t);
            } else {
                a5(R.xml.o);
            }
            Preference b = findPreference("autoUpdate");
            b.setOnPreferenceClickListener(a -> {
                t(1);
                return true;
            });
            Preference b742 = findPreference("autoUpdate742");
            b742.setOnPreferenceClickListener(a -> {
                t(0);
                return true;
            });
            Preference a4 = findPreference("cus");
            a4.setOnPreferenceClickListener(a -> {
                r();
                return true;
            });

            Preference a7 = findPreference("webviumB");
            a7.setOnPreferenceClickListener(a -> {
                t(0);
                return true;
            });

        } catch (Exception ex) {
            Log.a(ex);
        }
    }

    private void t(int vr) {
        AlertDialog.Builder c = new AlertDialog.Builder(getActivity());
        LayoutInflater d = LayoutInflater.from(getActivity());
        View e = d.inflate(R.layout.a12, null);
        c.setCancelable(false);
        c.setView(e);
        TextView f = e.findViewById(R.id.g1);
        f.setText(getString(R.string.o1));
        if (!a221().getBoolean("autoUpdate", false)) {
            if (vr == 1) {
                f.setTextColor(Resources.getColor(getActivity(), R.color.b));
            } else {
                f.setTextColor(Resources.getColor(getActivity(), R.color.c));
            }
        } else {
            if (vr == 1) {
                f.setTextColor(Resources.getColor(getActivity(), R.color.c));
            } else {
                f.setTextColor(Resources.getColor(getActivity(), R.color.b));
            }
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

    private void r() {
        Intent d = new Intent(Intent.ACTION_GET_CONTENT);
        d.setType("image/*");
        d.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(Intent.createChooser(d, getString(R.string.a26)), 79);
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