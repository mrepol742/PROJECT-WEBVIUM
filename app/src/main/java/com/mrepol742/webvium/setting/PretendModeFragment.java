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

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.mrepol742.webvium.EDIT;
import com.mrepol742.webvium.LOCK;
import com.mrepol742.webvium.R;
import com.mrepol742.webvium.app.main.MainReceiver;
import com.mrepol742.webvium.SWIT;
import com.mrepol742.webvium.app.NoSuchStringToReturn;
import com.mrepol742.webvium.app.base.BasePreferenceFragment;
import com.mrepol742.webvium.content.C10;
import com.mrepol742.webvium.content.IntentsFilter;
import com.mrepol742.webvium.content.Resources;
import com.mrepol742.webvium.security.Hash;
import com.mrepol742.webvium.text.Password;
import com.mrepol742.webvium.text.TextWatcher;
import com.mrepol742.webvium.util.U3;
import com.mrepol742.webvium.widget.AwesomeToast;

import java.util.Objects;

public class PretendModeFragment extends BasePreferenceFragment {
    private final IntentsFilter is = new IntentsFilter();
    private SWIT spe;
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
            a5(R.xml.a1);

            spe = (SWIT) findPreference("ptm");
            spe.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

            @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    if (newValue.toString().equals("true")) {
                        PretendModeFragment.this.a5();
                    } else {
                        PretendModeFragment.this.e();
                    }
                    return true;
                }
            });
            Preference a1 = findPreference("cHANS");
            a1.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

            @Override
                public boolean onPreferenceClick(Preference a) {
                    PretendModeFragment.this.i();
                    return true;
                }
            });
            Preference a12 = findPreference("cLANS");
            a12.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

            @Override
                public boolean onPreferenceClick(Preference a) {
                    PretendModeFragment.this.k();
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

    private void a5() {
        SharedPreferences a = getActivity().getSharedPreferences("a", 0);
        String sg = a.getString("gsJsGsKSIgPes", "");
        if (sg != null && U3.b(sg)) {
            b();
        } else {
            d();
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void b() {
        AlertDialog.Builder a5 = new AlertDialog.Builder(getActivity());
        LayoutInflater a6 = LayoutInflater.from(getActivity());
        View a7 = a6.inflate(R.layout.u, null);
        a5.setCancelable(false);
        a5.setTitle(getActivity().getResources().getString(R.string.j28));
        a5.setView(a7);
        final EDIT e15 = a7.findViewById(R.id.e15);
        final EDIT a67 = a7.findViewById(R.id.a6);
        e15.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return (event.getFlags() & MotionEvent.FLAG_WINDOW_IS_OBSCURED) != 0;
            }
        });
        a67.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return (event.getFlags() & MotionEvent.FLAG_WINDOW_IS_OBSCURED) != 0;
            }
        });
        int c = Resources.getColor(getActivity(), R.color.c);
        int d1 = Resources.getColor(getActivity(), R.color.b);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        if (!sp.getBoolean("autoUpdate", false)) {
            a67.setTextColor(c);
            e15.setTextColor(c);
        } else {
            a67.setTextColor(d1);
            e15.setTextColor(d1);
        }
        e15.setHint(getActivity().getResources().getString(R.string.j29));
        a67.setHint(getActivity().getResources().getString(R.string.j30));
        e15.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        a67.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        e15.setTransformationMethod(new Password());
        a67.setTransformationMethod(new Password());
        a5.setPositiveButton(getResources().getString(R.string.i6), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a, int i) {
                try {
                    PretendModeFragment.this.c(a67.getText().toString());
                } catch (NoSuchStringToReturn l2) {
                    l2.printStackTrace();
                }
                C10.a(PretendModeFragment.this.getActivity(), "com.mrepol742.webvium.activity.alias.PRE", 1);
                C10.a(PretendModeFragment.this.getActivity(), "com.mrepol742.webvium.activity.alias.MAY", 2);
                AwesomeToast.a(PretendModeFragment.this.getActivity(), PretendModeFragment.this.getActivity().getResources().getString(R.string.j31), 0);
                spe.setChecked(true);
                a.dismiss();
            }
        });
        a5.setNegativeButton(getResources().getString(R.string.i7), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a, int i) {
                spe.setChecked(false);
                a.dismiss();

            }
        });

        final AlertDialog d = a5.create();
        Objects.requireNonNull(d.getWindow()).setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        d.show();
        d.getButton(AlertDialog.BUTTON_POSITIVE).setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return (event.getFlags() & MotionEvent.FLAG_WINDOW_IS_OBSCURED) != 0;
            }
        });
        d.getButton(AlertDialog.BUTTON_NEGATIVE).setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return (event.getFlags() & MotionEvent.FLAG_WINDOW_IS_OBSCURED) != 0;
            }
        });
        final Button okButton = d.getButton(AlertDialog.BUTTON_POSITIVE);
        a67.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (e15.getText().toString().length() >= 4) {
                    if (a67.getText().toString().length() >= 4) {
                        okButton.setEnabled(a67.getText().toString().equals(e15.getText().toString()));
                    } else {
                        okButton.setEnabled(false);
                    }
                } else {
                    okButton.setEnabled(false);
                }
            }


        });
        e15.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (e15.getText().toString().length() >= 4) {
                    if (a67.getText().toString().length() >= 4) {
                        okButton.setEnabled(a67.getText().toString().equals(e15.getText().toString()));
                    } else {
                        okButton.setEnabled(false);
                    }
                } else {
                    okButton.setEnabled(false);
                }
            }


        });
        d.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
    }

    private void c(String a1) throws NoSuchStringToReturn {
        SharedPreferences a = getActivity().getSharedPreferences("a", 0);

        SharedPreferences.Editor b5 = a.edit();
        b5.putString("gsJsGsKSIgPes", Hash.a("SHA-512", a1));
        b5.apply();

    }

    @SuppressLint("ClickableViewAccessibility")
    private void d() {

        AlertDialog.Builder a5 = new AlertDialog.Builder(getActivity());
        LayoutInflater a6 = LayoutInflater.from(getActivity());
        View a7 = a6.inflate(R.layout.r, null);
        a5.setCancelable(false);
        a5.setTitle(getActivity().getResources().getString(R.string.j32));
        a5.setView(a7);
        final EDIT e15 = a7.findViewById(R.id.e14);

        e15.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return (event.getFlags() & MotionEvent.FLAG_WINDOW_IS_OBSCURED) != 0;
            }
        });

        int a15 = Resources.getColor(getActivity(), R.color.c);
        int a16 = Resources.getColor(getActivity(), R.color.b);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        if (!sp.getBoolean("autoUpdate", false)) {

            e15.setTextColor(a15);

        } else {

            e15.setTextColor(a16);
        }
        e15.setHint(getActivity().getResources().getString(R.string.j29));

        e15.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);

        e15.setTransformationMethod(new Password());
        a5.setPositiveButton(getResources().getString(R.string.i6), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a, int i) {
                try {
                    PretendModeFragment.this.h(e15.getText().toString());
                } catch (NoSuchStringToReturn l2) {
                    l2.printStackTrace();
                }
                a.dismiss();

            }
        });
        a5.setNegativeButton(getResources().getString(R.string.i7), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a, int i) {
                spe.setChecked(false);
                a.dismiss();
            }
        });

        final AlertDialog d = a5.create();
        Objects.requireNonNull(d.getWindow()).setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        d.show();
        d.getButton(AlertDialog.BUTTON_POSITIVE).setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return (event.getFlags() & MotionEvent.FLAG_WINDOW_IS_OBSCURED) != 0;
            }
        });
        d.getButton(AlertDialog.BUTTON_NEGATIVE).setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return (event.getFlags() & MotionEvent.FLAG_WINDOW_IS_OBSCURED) != 0;
            }
        });
        final Button okButton = d.getButton(AlertDialog.BUTTON_POSITIVE);
        e15.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                okButton.setEnabled(e15.getText().toString().length() >= 4);
            }


        });
        d.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);

    }

    @SuppressLint("ClickableViewAccessibility")
    private void e() {
        AlertDialog.Builder a5 = new AlertDialog.Builder(getActivity());
        LayoutInflater a6 = LayoutInflater.from(getActivity());
        View a7 = a6.inflate(R.layout.t, null);
        a5.setCancelable(false);
        a5.setTitle(getActivity().getResources().getString(R.string.j33));
        a5.setView(a7);

        final EDIT e16 = a7.findViewById(R.id.e16);

        e16.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return (event.getFlags() & MotionEvent.FLAG_WINDOW_IS_OBSCURED) != 0;
            }
        });
        int a15 = Resources.getColor(getActivity(), R.color.c);
        int a16 = Resources.getColor(getActivity(), R.color.b);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        if (!sp.getBoolean("autoUpdate", false)) {

            e16.setTextColor(a15);

        } else {

            e16.setTextColor(a16);
        }
        e16.setHint(getActivity().getResources().getString(R.string.j29));
        e16.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);

        e16.setTransformationMethod(new Password());
        a5.setPositiveButton(getResources().getString(R.string.i6), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a, int i) {
                try {
                    PretendModeFragment.this.g(e16.getText().toString());
                } catch (NoSuchStringToReturn l2) {
                    l2.printStackTrace();
                }
                a.dismiss();

            }
        });
        a5.setNegativeButton(getResources().getString(R.string.i7), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a, int i) {
                spe.setChecked(true);
                a.dismiss();
            }
        });

        final AlertDialog d = a5.create();

        Objects.requireNonNull(d.getWindow()).setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        d.show();
        d.getButton(AlertDialog.BUTTON_POSITIVE).setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return (event.getFlags() & MotionEvent.FLAG_WINDOW_IS_OBSCURED) != 0;
            }
        });
        d.getButton(AlertDialog.BUTTON_NEGATIVE).setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return (event.getFlags() & MotionEvent.FLAG_WINDOW_IS_OBSCURED) != 0;
            }
        });
        final Button okButton = d.getButton(AlertDialog.BUTTON_POSITIVE);
        e16.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                okButton.setEnabled(e16.getText().toString().length() >= 4);
            }


        });
        d.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);

    }

    private void h(String a1) throws NoSuchStringToReturn {
        SharedPreferences a = getActivity().getSharedPreferences("a", 0);
        String c727 = a.getString("gsJsGsKSIgPes", "");
        if (c727 != null) {
            if (Hash.a("SHA-512", a1).equals(c727)) {

                spe.setChecked(true);
                C10.a(getActivity(), "com.mrepol742.webvium.activity.alias.PRE", 1);
                String[] sg28 = new String[]{"com.DROID_MJ.a21.b0", "com.DROID_MJ.a21.b1", "com.mrepol742.webvium.activity.alias.MAY", "com.DROID_MJ.a21.b3", "com.DROID_MJ.a21.b4", "com.DROID_MJ.a21.b5", "com.DROID_MJ.a21.b6", "com.DROID_MJ.a21.b7", "com.DROID_MJ.a21.b8", "com.DROID_MJ.a21.b9", "com.DROID_MJ.a21.b10", "com.DROID_MJ.a21.b11", "com.DROID_MJ.a21.b12", "com.DROID_MJ.a21.b13", "com.DROID_MJ.a21.b14", "com.DROID_MJ.a21.b15", "com.DROID_MJ.a21.b16", "com.DROID_MJ.a21.b17", "com.DROID_MJ.a21.b18", "com.DROID_MJ.a21.b19"};
                for (String s : sg28) {
                    C10.a(getActivity(), s, 2);
                }
            } else {
                spe.setChecked(false);

                AwesomeToast.a(getActivity(), getActivity().getResources().getString(R.string.j34), 0);
            }

        }
    }

    private void g(String a1) throws NoSuchStringToReturn {
        SharedPreferences a = getActivity().getSharedPreferences("a", 0);
        String c727 = a.getString("gsJsGsKSIgPes", "");
        if (c727 != null) {
            if (Hash.a("SHA-512", a1).equals(c727)) {
                C10.a(getActivity(), "com.mrepol742.webvium.activity.alias.PRE", 2);
                C10.a(getActivity(), "com.mrepol742.webvium.activity.alias.MAY", 1);
                spe.setChecked(false);

            } else {

                spe.setChecked(true);
                AwesomeToast.a(getActivity(), getActivity().getResources().getString(R.string.j34), 0);
            }

        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void i() {
        AlertDialog.Builder a5 = new AlertDialog.Builder(getActivity());
        LayoutInflater a6 = LayoutInflater.from(getActivity());
        View a7 = a6.inflate(R.layout.c1, null);
        a5.setCancelable(true);
        a5.setTitle(getActivity().getResources().getString(R.string.k27));
        a5.setView(a7);
        final EDIT e15 = a7.findViewById(R.id.l13);
        final EDIT a67 = a7.findViewById(R.id.l14);
        final EDIT l15 = a7.findViewById(R.id.l15);
        e15.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return (event.getFlags() & MotionEvent.FLAG_WINDOW_IS_OBSCURED) != 0;
            }
        });
        a67.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return (event.getFlags() & MotionEvent.FLAG_WINDOW_IS_OBSCURED) != 0;
            }
        });
        l15.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return (event.getFlags() & MotionEvent.FLAG_WINDOW_IS_OBSCURED) != 0;
            }
        });
        int c = Resources.getColor(getActivity(), R.color.c);
        int d1 = Resources.getColor(getActivity(), R.color.b);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        if (!sp.getBoolean("autoUpdate", false)) {
            a67.setTextColor(c);
            e15.setTextColor(c);
            l15.setTextColor(c);
        } else {
            a67.setTextColor(d1);
            e15.setTextColor(d1);
            l15.setTextColor(d1);
        }
        e15.setHint(getActivity().getResources().getString(R.string.k28));
        a67.setHint(getActivity().getResources().getString(R.string.k29));
        l15.setHint(getActivity().getResources().getString(R.string.k30));
        e15.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        a67.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        l15.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        e15.setTransformationMethod(new Password());
        a67.setTransformationMethod(new Password());
        l15.setTransformationMethod(new Password());
        a5.setPositiveButton(getResources().getString(R.string.i6), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a, int i) {
                try {
                    PretendModeFragment.this.j(e15.getText().toString(), l15.getText().toString());
                } catch (NoSuchStringToReturn l2) {
                    l2.printStackTrace();
                }

                a.dismiss();
            }
        });
        a5.setNegativeButton(getResources().getString(R.string.i7), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a, int i) {
                a.dismiss();
            }
        });
        final AlertDialog d = a5.create();
        Objects.requireNonNull(d.getWindow()).setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        d.show();
        d.getButton(AlertDialog.BUTTON_POSITIVE).setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return (event.getFlags() & MotionEvent.FLAG_WINDOW_IS_OBSCURED) != 0;
            }
        });
        d.getButton(AlertDialog.BUTTON_NEGATIVE).setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return (event.getFlags() & MotionEvent.FLAG_WINDOW_IS_OBSCURED) != 0;
            }
        });
        final Button okButton = d.getButton(AlertDialog.BUTTON_POSITIVE);
        a67.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (e15.getText().toString().length() >= 4) {
                    if (a67.getText().toString().length() >= 4 && !a67.getText().toString().equals(e15.getText().toString())) {
                        if (l15.getText().toString().length() >= 4 && !l15.getText().toString().equals(e15.getText().toString())) {
                            okButton.setEnabled(a67.getText().toString().equals(l15.getText().toString()));
                        } else {
                            okButton.setEnabled(false);
                        }
                    } else {
                        okButton.setEnabled(false);
                    }
                } else {
                    okButton.setEnabled(false);
                }
            }


        });
        e15.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (e15.getText().toString().length() >= 4) {
                    if (a67.getText().toString().length() >= 4 && !a67.getText().toString().equals(e15.getText().toString())) {
                        if (l15.getText().toString().length() >= 4 && !l15.getText().toString().equals(e15.getText().toString())) {
                            okButton.setEnabled(a67.getText().toString().equals(l15.getText().toString()));
                        } else {
                            okButton.setEnabled(false);
                        }
                    } else {
                        okButton.setEnabled(false);
                    }
                } else {
                    okButton.setEnabled(false);
                }
            }


        });
        l15.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (e15.getText().toString().length() >= 4) {
                    if (a67.getText().toString().length() >= 4 && !a67.getText().toString().equals(e15.getText().toString())) {
                        if (l15.getText().toString().length() >= 4 && !l15.getText().toString().equals(e15.getText().toString())) {
                            okButton.setEnabled(a67.getText().toString().equals(l15.getText().toString()));
                        } else {
                            okButton.setEnabled(false);
                        }
                    } else {
                        okButton.setEnabled(false);
                    }
                } else {
                    okButton.setEnabled(false);
                }
            }


        });
        d.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
    }

    private void j(String a1, String a2) throws NoSuchStringToReturn {
        SharedPreferences a = getActivity().getSharedPreferences("a", 0);
        String c727 = a.getString("gsJsGsKSIgPes", "");
        if (c727 != null) {
            if (Hash.a("SHA-512", a1).equals(c727)) {
                SharedPreferences.Editor b5 = a.edit();
                b5.putString("gsJsGsKSIgPes", Hash.a("SHA-512", a2));
                b5.apply();
                AwesomeToast.a(getActivity(), getActivity().getResources().getString(R.string.k31), 0);
            } else {
                AwesomeToast.a(getActivity(), getActivity().getResources().getString(R.string.k36), 0);
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void k() {
        AlertDialog.Builder a5 = new AlertDialog.Builder(getActivity());
        LayoutInflater a6 = LayoutInflater.from(getActivity());
        View a7 = a6.inflate(R.layout.t, null);
        a5.setCancelable(true);
        a5.setTitle(getActivity().getResources().getString(R.string.k32));
        a5.setView(a7);

        final EDIT e16 = a7.findViewById(R.id.e16);


        int a15 = Resources.getColor(getActivity(), R.color.c);
        int a16 = Resources.getColor(getActivity(), R.color.b);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        if (!sp.getBoolean("autoUpdate", false)) {

            e16.setTextColor(a15);

        } else {

            e16.setTextColor(a16);
        }
        e16.setHint(getActivity().getResources().getString(R.string.g6));
        e16.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);

        e16.setTransformationMethod(new Password());
        a5.setPositiveButton(getResources().getString(R.string.i6), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a, int i) {
                try {
                    PretendModeFragment.this.l(e16.getText().toString());
                } catch (NoSuchStringToReturn l2) {
                    l2.printStackTrace();
                }
                a.dismiss();

            }
        });
        a5.setNegativeButton(getResources().getString(R.string.i7), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a, int i) {
                a.dismiss();
            }
        });

        final AlertDialog d = a5.create();

        Objects.requireNonNull(d.getWindow()).setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        d.show();
        d.getButton(AlertDialog.BUTTON_POSITIVE).setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return (event.getFlags() & MotionEvent.FLAG_WINDOW_IS_OBSCURED) != 0;
            }
        });
        d.getButton(AlertDialog.BUTTON_NEGATIVE).setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return (event.getFlags() & MotionEvent.FLAG_WINDOW_IS_OBSCURED) != 0;
            }
        });
        final Button okButton = d.getButton(AlertDialog.BUTTON_POSITIVE);
        e16.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                okButton.setEnabled(e16.getText().toString().length() >= 4);
            }


        });

        d.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);

    }

    private void l(String a1) throws NoSuchStringToReturn {
        SharedPreferences a = getActivity().getSharedPreferences("a", 0);
        String c727 = a.getString("gsJsGsKSIgPes", "");
        if (c727 != null) {
            if (Hash.a("SHA-512", a1).equals(c727)) {

                SharedPreferences.Editor b5 = a.edit();
                b5.clear();
                b5.apply();
                AwesomeToast.a(getActivity(), getActivity().getResources().getString(R.string.k33), 0);
                spe.setChecked(false);
                C10.a(getActivity(), "com.mrepol742.webvium.activity.alias.PRE", 2);
                C10.a(getActivity(), "com.mrepol742.webvium.activity.alias.MAY", 1);
            } else {

                AwesomeToast.a(getActivity(), getActivity().getResources().getString(R.string.k36), 0);
            }

        }
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