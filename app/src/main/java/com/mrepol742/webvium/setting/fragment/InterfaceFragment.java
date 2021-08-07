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
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.Preference;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.mrepol742.webvium.Webv;
import com.mrepol742.webvium.R;
import com.mrepol742.webvium.app.base.BasePreferenceFragment;
import com.mrepol742.webvium.app.Intents;
import com.mrepol742.webvium.app.Resources;
import com.mrepol742.webvium.app.StorageDirectory;
import com.mrepol742.webvium.util.BitmapCache;
import com.mrepol742.webvium.util.AwesomeToast;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

public class InterfaceFragment extends BasePreferenceFragment implements Preference.OnPreferenceClickListener {
    private static final int PRIMARY_FONT = 1234;
    private static final int SECONDARY_FONT = 1235;

    @Override
    public boolean onPreferenceClick(Preference a123) {
        switch (a123.getKey()) {
            case "autoUpdate":
                InterfaceFragment.this.t(1);
                return true;
            case "autoUpdate742":
            case "webviumB":
                InterfaceFragment.this.t(0);
                return true;
            case "cus":
                Intent d = new Intent(Intent.ACTION_GET_CONTENT);
                d.setType("image/*");
                d.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(Intent.createChooser(d, getString(R.string.a26)), 79);
                return true;
            case "cfnt5":
                InterfaceFragment.this.b19(PRIMARY_FONT);
                return true;
            case "cfnt2":
                InterfaceFragment.this.b19(SECONDARY_FONT);
                return true;
        }
        return false;
    }

    @Override
    public void onActivityResult(final int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 79 && data != null) {
            Runnable p15 = new Runnable() {

                @Override
                public void run() {
                    try {
                        InputStream c = InterfaceFragment.this.getActivity().getContentResolver().openInputStream(Objects.requireNonNull(data.getData()));
                        OutputStream d = new FileOutputStream(StorageDirectory.getBackground(InterfaceFragment.this.getActivity()));
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
                        BitmapCache.getInstance().b(StorageDirectory.getBackground(InterfaceFragment.this.getActivity()));
                        Intent it = new Intent(Intents.ACTION_INVALIDATE);
                        InterfaceFragment.this.getActivity().sendBroadcast(it);
                        InterfaceFragment.this.getActivity().runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                AwesomeToast.b(InterfaceFragment.this.getActivity(), InterfaceFragment.this.getString(R.string.o29));
                            }
                        });
                    } catch (Exception en) {
                        en.printStackTrace();
                        InterfaceFragment.this.getActivity().runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                AwesomeToast.b(InterfaceFragment.this.getActivity(), InterfaceFragment.this.getString(R.string.p33));
                            }
                        });
                    }
                }
            };
            new Thread(p15).start();
        } else if ((requestCode == PRIMARY_FONT || requestCode == SECONDARY_FONT) && data != null) {
            Runnable p15 = new Runnable() {

                @Override
                public void run() {
                    try {
                        InputStream c = InterfaceFragment.this.getActivity().getContentResolver().openInputStream(Objects.requireNonNull(data.getData()));
                        OutputStream d = new FileOutputStream(InterfaceFragment.this.b20(requestCode));
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
                        InterfaceFragment.this.getActivity().runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                AwesomeToast.b(InterfaceFragment.this.getActivity(), InterfaceFragment.this.getString(R.string.z55));
                                AlertDialog.Builder c = new AlertDialog.Builder(getActivity());
                                LayoutInflater d = LayoutInflater.from(getActivity());
                                View e = d.inflate(R.layout.a12, null);
                                c.setCancelable(false);
                                c.setView(e);
                                TextView f = e.findViewById(R.id.g1);
                                f.setText(getString(R.string.o1));
                                if (!a221().getBoolean("autoUpdate", false)) {
                                    f.setTextColor(Resources.getColor(getActivity(), R.color.c));
                                } else {
                                    f.setTextColor(Resources.getColor(getActivity(), R.color.b));
                                }
                                AlertDialog j5 = c.create();
                                O5 timer = new O5(2000, 2000, j5);
                                timer.start();
                                j5.show();
                            }
                        });
                    } catch (Exception en) {
                        en.printStackTrace();
                        InterfaceFragment.this.getActivity().runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                AwesomeToast.b(InterfaceFragment.this.getActivity(), InterfaceFragment.this.getString(R.string.z54));
                            }
                        });
                    }
                }
            };
            new Thread(p15).start();
        }
    }

    @Override
    public void onCreate(Bundle b1) {
        super.onCreate(b1);
        if (Build.VERSION.SDK_INT >= 29) {
            a5(R.xml.t);
        } else {
            a5(R.xml.o);
        }
        Preference b = findPreference("autoUpdate");
        b.setOnPreferenceClickListener(this);
        Preference b742 = findPreference("autoUpdate742");
        b742.setOnPreferenceClickListener(this);
        Preference a4 = findPreference("cus");
        a4.setOnPreferenceClickListener(this);
        Preference a7 = findPreference("webviumB");
        a7.setOnPreferenceClickListener(this);
        Preference a455 = findPreference("cfnt5");
        a455.setOnPreferenceClickListener(this);
        Preference a433 = findPreference("cfnt2");
        a433.setOnPreferenceClickListener(this);
    }

    private String b20(int cn) {
        if (cn == PRIMARY_FONT) {
            return StorageDirectory.Fonts.getPrimaryFont(getActivity());
        }
        return StorageDirectory.Fonts.getSecondaryFont(getActivity());
    }

    private void b19(int id) {
        Intent d = new Intent(Intent.ACTION_GET_CONTENT);
        d.setType("*/*");
        d.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(Intent.createChooser(d, getString(R.string.a26)), id);
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

    class O5 extends CountDownTimer {
        AlertDialog a5;

        public O5(long a, long b, AlertDialog gj) {
            super(a, b);
            a5 = gj;
        }

        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            a5.dismiss();
            a5 = null;
            getActivity().finish();
            SharedPreferences c56 = getActivity().getSharedPreferences("wv", 0);
            Intent it = new Intent(getActivity(), Webv.class);
            it.putExtra("value", c56.getString("MyURL", ""));
            it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(it);
        }
    }
}