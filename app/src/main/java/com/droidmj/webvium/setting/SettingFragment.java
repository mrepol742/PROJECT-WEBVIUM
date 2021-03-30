/*
 *
 *
 *
 * DROID MJ Property || Confidential
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package com.droidmj.webvium.setting;

// SETTINGS FRAGMENT PART 0 over 1 (def== mail)

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.droidmj.webvium.BuildConfig;
import com.droidmj.webvium.FEED;
import com.droidmj.webvium.LOCK;
import com.droidmj.webvium.MANG;
import com.droidmj.webvium.R;
import com.droidmj.webvium.SETT;
import com.droidmj.webvium.app.BuildConfiguration;
import com.droidmj.webvium.app.base.BaseFragment;
import com.droidmj.webvium.content.Intents;
import com.droidmj.webvium.content.Package;
import com.droidmj.webvium.telemetry.DiagnosticData;
import com.droidmj.webvium.util.Base64;
import com.droidmj.webvium.util.Hardware;
import com.droidmj.webvium.view.Animation;

public class SettingFragment extends BaseFragment {

    private ImageView iv, iv0;
    private boolean bn;

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
        SettingAdapter aa = new SettingAdapter(getActivity());
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
            iv.setOnClickListener(view -> {
                try {
                    e();
                } catch (PackageManager.NameNotFoundException e) {
                    DiagnosticData.a(e);
                }
            });
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
                Intents.e(getActivity(), "search", SETT.FRAGMENT_DOWNLOAD, SETT.class);
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

    private void e() throws PackageManager.NameNotFoundException {
        Intent b = new Intent("android.intent.action.SEND");
        b.setType("text/plain");
        b.putExtra("android.intent.extra.TEXT", getString(R.string.f33).replaceAll("%a", BuildConfiguration.Application.downloadSize).replaceAll("%b", Package.e(getActivity())).replaceAll("%c", Base64.a("a HR0cHM6Ly9n  aXRodWIuY2 9tL21yZXBvbDc 0Mg==")).replaceAll("%d", Base64.a("aHR0 cHM6Ly9mYi  5tZS9 tcmVwb2w3 NDI=")));
        String c = getString(R.string.l8);
        String d = c.replace("%a", "\"" + Package.c() + "\"");
        startActivity(Intent.createChooser(b, d));
    }


}