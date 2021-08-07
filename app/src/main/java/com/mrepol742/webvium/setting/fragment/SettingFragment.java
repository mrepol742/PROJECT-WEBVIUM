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

// SETTINGS FRAGMENT PART 0 over 1 (def== mail)

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.mrepol742.webvium.Lock;
import com.mrepol742.webvium.Mana;
import com.mrepol742.webvium.R;
import com.mrepol742.webvium.Sett;
import com.mrepol742.webvium.app.base.BaseFragment;
import com.mrepol742.webvium.app.Intents;
import com.mrepol742.webvium.app.Package;
import com.mrepol742.webvium.security.Base64;
import com.mrepol742.webvium.setting.SettingAdapter;
import com.mrepol742.webvium.util.Hardware;
import com.mrepol742.webvium.util.Animation;

public class SettingFragment extends BaseFragment {

    private static final int[] header1 = {
            R.string.a,
            R.string.o10,
            R.string.e,
            R.string.f,
            R.string.b,
            R.string.g,
            R.string.c,
            R.string.i,
            R.string.h10,
            R.string.l14,
            R.string.y69
    };
    private static final int[] summary1 = {
            R.string.t8,
            R.string.t9,
            R.string.t10,
            R.string.t11,
            R.string.t12,
            R.string.t13,
            R.string.t14,
            R.string.t15,
            R.string.t16,
            R.string.f16,
            R.string.t19,
            R.string.y70
    };
    private ImageView iv, iv0;
    private boolean bn;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 345) {
            if (resultCode == Activity.RESULT_OK) {
                if (!bn)
                    Intents.e(getActivity(), "search", Sett.FRAGMENT_PRIVACY, Sett.class);

            }
        }
        if (requestCode == 346) {
            if (resultCode == Activity.RESULT_OK) {
                if (!bn)
                    Intents.e(getActivity(), "search", Sett.FRAGMENT_DATABASE, Sett.class);
            }
        }
        if (requestCode == 456) {
            if (resultCode == Activity.RESULT_OK) {
                if (!bn)
                    Intents.e(getActivity(), "search", Sett.FRAGMENT_ADVANCED, Sett.class);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v;
        bn = Hardware.isTablet(getActivity());
        if (bn) {
            v = inflater.inflate(R.layout.a24, container, false);
        } else {
            v = inflater.inflate(R.layout.a23, container, false);
        }
        ListView a31 = v.findViewById(R.id.a3);
        SettingAdapter aa;
        aa = new SettingAdapter(getActivity(), header1, summary1);
        a31.setAdapter(aa);
        a31.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a1, View b1, int c, long d) {
                if (bn) {
                    SettingFragment.this.b(c);
                } else {
                    switch (c) {
                        case 0:
                            Intents.e(getActivity(), "search", Sett.FRAGMENT_GENERAL, Sett.class);
                            break;
                        case 1:
                            Intents.e(getActivity(), "search", Sett.FRAGMENT_INTERFACE, Sett.class);
                            break;
                        case 2:
                            Intents.e(getActivity(), "search", Sett.FRAGMENT_SEARCH, Sett.class);
                            break;
                        case 3:
                            Intents.e(getActivity(), "search", Sett.FRAGMENT_DOWNLOAD, Sett.class);
                            break;
                        case 4:
                            if (a221().getBoolean("lockWn99", false)) {
                                Intent it = new Intent(getActivity(), Lock.class);
                                startActivityForResult(it, 345);
                            } else {
                                Intents.e(getActivity(), "search", Sett.FRAGMENT_PRIVACY, Sett.class);
                            }
                            break;
                        case 5:
                            Intents.e(getActivity(), "search", Sett.FRAGMENT_ACCESSIBILITY, Sett.class);
                            break;
                        case 6:
                            if (a221().getBoolean("lockWn99", false)) {
                                Intent it = new Intent(getActivity(), Lock.class);
                                startActivityForResult(it, 456);
                            } else {
                                Intents.e(getActivity(), "search", Sett.FRAGMENT_ADVANCED, Sett.class);
                            }
                            break;
                        case 7:
                            Intents.e(getActivity(), "search", Sett.FRAGMENT_VIDEO, Sett.class);
                            break;
                        case 8:
                            Intents.e(getActivity(), "search", Sett.FRAGMENT_TOOL, Sett.class);
                            break;
                        case 9:
                            Intents.a(getActivity(), Mana.class);
                            break;

                        case 10:
                            Intents.e(getActivity(), "search", Sett.FRAGMENT_EXPERIMENTAL, Sett.class);
                            break;
                    }
                }
            }
        });
        if (bn) {
            b(0);
        } else {
            iv = v.findViewById(R.id.m2);
            iv0 = v.findViewById(R.id.m3);
            iv.setBackgroundResource(R.drawable.c6);
            iv0.setBackgroundResource(R.drawable.c6);
            iv.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent b = new Intent("android.intent.action.SEND");
                    b.setType("text/plain");
                    b.putExtra("android.intent.extra.TEXT", String.format(getString(R.string.f33), Base64.decode("aHR0cHM6Ly9tcmVwb2w3NDIuZ2l0aHViLmlvL1BST0pFQ1QtV0VCVklVTS8")));
                    startActivity(Intent.createChooser(b, String.format(getString(R.string.l8), "\"" + Package.c() + "\"")));
                }
            });
            iv0.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent intent = new Intent("android.intent.action.SEND");
                    intent.putExtra("android.intent.extra.EMAIL", new String[]{getString(R.string.dev_mail)});
                    intent.putExtra("android.intent.extra.SUBJECT", getString(R.string.z69));
                    intent.putExtra("android.intent.extra.TEXT", getString(R.string.z66));
                    intent.putExtra("android.intent.extra.CC", getString(R.string.dev_mail));
                    intent.setType("text/html");
                    intent.setPackage("com.google.android.gm");
                    startActivity(Intent.createChooser(intent, getString(R.string.z65)));
                }
            });
            iv.setImageResource(R.drawable.c7);
            iv0.setImageResource(R.drawable.c8);
        }
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!bn) {
            Animation.animate(getActivity(), R.anim.i, iv);
            Animation.animate(getActivity(), R.anim.i, iv0);
        }
    }

    private void b(int a) {
        int i = R.id.i6;
        switch (a) {
            case 0:
                as(i, new GeneralFragment());
                break;
            case 1:
                as(i, new InterfaceFragment());
                break;
            case 2:
                as(i, new SearchFragment());
                break;
            case 3:
                as(i, new DownloadFragment());
                break;
            case 4:
                if (a221().getBoolean("lockWn99", false)) {
                    Intent it = new Intent(getActivity(), Lock.class);
                    startActivityForResult(it, 345);
                } else {
                    as(i, new PrivacyFragment());
                }
                break;
            case 5:
                as(i, new AccessibilityFragment());
                break;
            case 6:
                if (a221().getBoolean("lockWn99", false)) {
                    Intent it = new Intent(getActivity(), Lock.class);
                    startActivityForResult(it, 456);
                } else {
                    as(i, new AdvancedFragment());
                }
                break;
            case 7:
                as(i, new VideoFragment());
                break;
            case 8:
                as(i, new ToolFragment());
                break;
            case 9:
                Intents.a(getActivity(), Mana.class);
                break;
            case 10:
                as(i, new DevelopmentFragment());
                break;
        }
    }

    public void as(int i, Fragment s) {
        String a = s.getClass().getName();
        FragmentManager fm = getFragmentManager();
        boolean bn = fm.popBackStackImmediate(a, 0);
        if (!bn) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(i, s);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.addToBackStack(a);
            ft.commit();
        }
    }
}