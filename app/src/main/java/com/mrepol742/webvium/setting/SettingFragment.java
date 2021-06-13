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
import android.widget.ImageView;
import android.widget.ListView;

import com.mrepol742.webvium.FEED;
import com.mrepol742.webvium.LOCK;
import com.mrepol742.webvium.MANG;
import com.mrepol742.webvium.R;
import com.mrepol742.webvium.SETT;
import com.mrepol742.webvium.app.base.BaseFragment;
import com.mrepol742.webvium.content.Intents;
import com.mrepol742.webvium.content.Package;
import com.mrepol742.webvium.util.Base64;
import com.mrepol742.webvium.util.Hardware;
import com.mrepol742.webvium.view.Animation;

public class SettingFragment extends BaseFragment {

    private ImageView iv, iv0;
    private boolean bn;

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 345) {
            if (resultCode == Activity.RESULT_OK) {
                if (!bn)
                    Intents.e(getActivity(), "search", SETT.FRAGMENT_PRIVACY, SETT.class);

            }
        }
        if (requestCode == 346) {
            if (resultCode == Activity.RESULT_OK) {
                if (!bn)
                    Intents.e(getActivity(), "search", SETT.FRAGMENT_DATABASE, SETT.class);
            }
        }
        if (requestCode == 456) {
            if (resultCode == Activity.RESULT_OK) {
                if (!bn)
                    Intents.e(getActivity(), "search", SETT.FRAGMENT_ADVANCED, SETT.class);
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
        a31.setOnItemClickListener((a1, b1, c, d) -> {
            if (bn) {
                b(c);
            } else {
                a(c);
            }
        });
        if (bn) {
            b(0);
        } else {
            iv = v.findViewById(R.id.m2);
            iv0 = v.findViewById(R.id.m3);
            iv.setBackgroundResource(R.drawable.c6);
            iv0.setBackgroundResource(R.drawable.c6);
            iv.setOnClickListener(view -> e());
            iv0.setOnClickListener(view -> Intents.a(getActivity(), FEED.class));
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

    private void a(int a) {

        switch (a) {

            case 0:
                Intents.e(getActivity(), "search", SETT.FRAGMENT_GENERAL, SETT.class);
                break;
            case 1:
                Intents.e(getActivity(), "search", SETT.FRAGMENT_INTERFACE, SETT.class);
                break;
            case 2:
                Intents.e(getActivity(), "search", SETT.FRAGMENT_SEARCH, SETT.class);
                break;
            case 3:
                Intents.e(getActivity(), "search", SETT.FRAGMENT_DOWNLOAD, SETT.class);
                break;
            case 4:
                if (a221().getBoolean("lockWn99", false)) {
                    Intent it = new Intent(getActivity(), LOCK.class);
                    startActivityForResult(it, 345);
                } else {
                    Intents.e(getActivity(), "search", SETT.FRAGMENT_PRIVACY, SETT.class);
                }
                break;
            case 5:
                Intents.e(getActivity(), "search", SETT.FRAGMENT_ACCESSIBILITY, SETT.class);
                break;
            case 6:
                if (a221().getBoolean("lockWn99", false)) {
                    Intent it = new Intent(getActivity(), LOCK.class);
                    startActivityForResult(it, 456);
                } else {
                    Intents.e(getActivity(), "search", SETT.FRAGMENT_ADVANCED, SETT.class);
                }
                break;
            case 7:
                Intents.e(getActivity(), "search", SETT.FRAGMENT_VIDEO, SETT.class);
                break;
            case 8:
                Intents.e(getActivity(), "search", SETT.FRAGMENT_TOOL, SETT.class);
                break;
            case 9:
                Intents.a(getActivity(), MANG.class);
                break;

            case 10:
                Intents.e(getActivity(), "search", SETT.FRAGMENT_EXPERIMENTAL, SETT.class);
                break;
        }
    }

    private void b(int a) {
        int i = R.id.i6;
        switch (a) {

            case 0:
                as(i, new GeneralFragment());
                // h18.setText(getString(R.string.a));
                break;
            case 1:
                as(i, new InterfaceFragment());
                // h18.setText(getString(R.string.o10));
                break;
            case 2:
                as(i, new SearchFragment());
                // h18.setText(getString(R.string.e));
                break;
            case 3:
                as(i, new DownloadFragment());
                // h18.setText(getString(R.string.f));
                break;
            case 4:
                if (a221().getBoolean("lockWn99", false)) {
                    Intent it = new Intent(getActivity(), LOCK.class);
                    startActivityForResult(it, 345);
                } else {
                    as(i, new PrivacyFragment());
                    // h18.setText(getString(R.string.b));
                }
                break;
            case 5:
                as(i, new AccessibilityFragment());
                //h18.setText(getString(R.string.g));
                break;
            case 6:
                if (a221().getBoolean("lockWn99", false)) {
                    Intent it = new Intent(getActivity(), LOCK.class);
                    startActivityForResult(it, 456);
                } else {
                    as(i, new AdvancedFragment());
                    // h18.setText(getString(R.string.c));
                }
                break;
            case 7:
                as(i, new VideoFragment());
                //  h18.setText(getString(R.string.i));
                break;
            case 8:
                as(i, new ToolFragment());
                //  h18.setText(getString(R.string.h10));
                break;
            case 9:
                Intents.a(getActivity(), MANG.class);
                break;

            case 10:
                as(i, new ExperimentalFragment());
                // h18.setText(getString(R.string.t7));
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

    private void e() {
        Intent b = new Intent("android.intent.action.SEND");
        b.setType("text/plain");
        b.putExtra("android.intent.extra.TEXT", String.format(getString(R.string.f33), Base64.decode("aHR0cHM6Ly9tcmVwb2w3NDIuZ2l0aHViLmlvL1BST0pFQ1QtV0VCVklVTS8")));
        startActivity(Intent.createChooser(b, String.format(getString(R.string.l8), "\"" + Package.c() + "\"")));
    }


}