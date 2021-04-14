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

package com.mrepol742.webvium;

import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import com.mrepol742.webvium.app.BuildConfiguration;
import com.mrepol742.webvium.app.base.BaseActivity;
import com.mrepol742.webvium.content.Intents;
import com.mrepol742.webvium.content.Package;
import com.mrepol742.webvium.content.Resources;
import com.mrepol742.webvium.setting.AboutFragment;
import com.mrepol742.webvium.setting.AccessibilityFragment;
import com.mrepol742.webvium.setting.AdvancedFragment;
import com.mrepol742.webvium.setting.DatabaseFragment;
import com.mrepol742.webvium.setting.DownloadFragment;
import com.mrepol742.webvium.setting.ExperimentalFragment;
import com.mrepol742.webvium.setting.GeneralFragment;
import com.mrepol742.webvium.setting.InterfaceFragment;
import com.mrepol742.webvium.setting.PretendModeFragment;
import com.mrepol742.webvium.setting.PrivacyFragment;
import com.mrepol742.webvium.setting.SearchFragment;
import com.mrepol742.webvium.setting.SecurityLockFragment;
import com.mrepol742.webvium.setting.ToolFragment;
import com.mrepol742.webvium.setting.VideoFragment;
import com.mrepol742.webvium.telemetry.DiagnosticData;
import com.mrepol742.webvium.util.Base64;

import java.util.Objects;

// @Class SettingLauncher
public class SETT extends BaseActivity {

    private TextView h18;
    public static final int FRAGMENT_TOOL = 1;
    public static final int FRAGMENT_GENERAL = 2;
    public static final int FRAGMENT_VIDEO = 3;
    public static final int FRAGMENT_SEARCH = 4;
    public static final int FRAGMENT_DOWNLOAD = 5;
    public static final int FRAGMENT_INTERFACE = 6;
    public static final int FRAGMENT_PRIVACY = 7;
    public static final int FRAGMENT_ADVANCED = 8;
    public static final int FRAGMENT_ACCESSIBILITY = 9;
    public static final int FRAGMENT_ABOUT = 10;
    public static final int FRAGMENT_EXPERIMENTAL = 11;
    public static final int FRAGMENT_DATABASE = 12;
    public static final int FRAGMENT_SECURITY_LOCK = 13;
    public static final int FRAGMENT_PRETEND_MODE = 14;
    public static final int FRAGMENT_DEFAULT = 742;

    @Override
    protected void onCreate(Bundle a) {
        theme(T_DEFAULT);
        super.onCreate(a);

        a225(R.layout.a9);
        Toolbar h17 = findViewById(R.id.c3);
        h18 = findViewById(R.id.c4);
        TextView k8 = findViewById(R.id.c5);
        h18.setTypeface(type(Typeface.BOLD));
        k8.setTypeface(type(Typeface.BOLD));
        setActionBar(h17);
        h17.setElevation(5);
        k8.setText(getString(R.string.l13));

        ActionBar ab = getActionBar();
        if (ab != null) {
            // ab.setDisplayHomeAsUpEnabled(true);
            // ab.setDisplayShowHomeEnabled(false);
            ab.setDisplayShowTitleEnabled(false);
        }

        if (!a221().getBoolean("autoUpdate", false)) {
            h18.setTextColor(Resources.b(this, R.color.c));
            k8.setTextColor(Resources.b(this, R.color.c));
        } else {
            h18.setTextColor(Resources.b(this, R.color.b));
            k8.setTextColor(Resources.b(this, R.color.b));
        }
        h17.setBackgroundResource(R.drawable.p);
        h17.setNavigationIcon(R.drawable.a2);
        h17.setNavigationOnClickListener(view -> finish());
        if (a224("webDa", false)) {
            k8.setVisibility(View.VISIBLE);
        } else {
            k8.setVisibility(View.GONE);
        }
        onNewIntent(getIntent());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent a = getIntent();
        if (Objects.equals(a.getAction(), Intent.ACTION_MAIN) && a.getCategories().contains("android.intent.category.NOTIFICATION_PREFERENCES")) {
            as(R.id.i6, new GeneralFragment());
            h18.setText(getString(R.string.a));
            a.replaceExtras(new Bundle());
            a.setAction("");
            a.setData(null);
            a.setFlags(0);
        }
    }

    @Override
    public boolean onKeyDown(int a5, KeyEvent b) {
        if (b.getAction() == KeyEvent.ACTION_DOWN && a5 == KeyEvent.KEYCODE_BACK) {
            as1();
            return true;
        }
        return false;
    }

    @Override
    protected void onNewIntent(Intent a) {
        try {
            if (Objects.equals(a.getAction(), Intent.ACTION_MAIN) && a.getCategories().contains("android.intent.category.NOTIFICATION_PREFERENCES")) {
                return;
            }
            int val = a.getIntExtra("search", FRAGMENT_DEFAULT);
            if (val == FRAGMENT_DEFAULT) {
                finishAndRemoveTask();
            }
            int i = R.id.i6;
            switch (val) {
                case FRAGMENT_TOOL:
                    as(i, new ToolFragment());
                    h18.setText(getString(R.string.h10));
                    break;
                case FRAGMENT_GENERAL:
                    as(i, new GeneralFragment());
                    h18.setText(getString(R.string.a));
                    break;
                case FRAGMENT_VIDEO:
                    as(i, new VideoFragment());
                    h18.setText(getString(R.string.i));
                    break;
                case FRAGMENT_SEARCH:
                    as(i, new SearchFragment());
                    h18.setText(getString(R.string.e));
                    break;
                case FRAGMENT_DOWNLOAD:
                    as(i, new DownloadFragment());
                    h18.setText(getString(R.string.f));
                    break;
                case FRAGMENT_INTERFACE:
                    as(i, new InterfaceFragment());
                    h18.setText(getString(R.string.o10));
                    break;
                case FRAGMENT_PRIVACY:
                    as(i, new PrivacyFragment());
                    h18.setText(getString(R.string.b));
                    break;
                case FRAGMENT_ADVANCED:
                    as(i, new AdvancedFragment());
                    h18.setText(getString(R.string.c));
                    break;
                case FRAGMENT_ACCESSIBILITY:
                    as(i, new AccessibilityFragment());
                    h18.setText(getString(R.string.g));
                    break;
                case FRAGMENT_ABOUT:
                    as(i, new AboutFragment());
                    h18.setText(getString(R.string.l));
                    break;
                case FRAGMENT_EXPERIMENTAL:
                    as(i, new ExperimentalFragment());
                    h18.setText(getString(R.string.t7));
                    break;
                case FRAGMENT_DATABASE:
                    as(i, new DatabaseFragment());
                    h18.setText(getString(R.string.t6));
                    break;
                case FRAGMENT_SECURITY_LOCK:
                    as(i, new SecurityLockFragment());
                    h18.setText(getString(R.string.l10));
                    break;
                case FRAGMENT_PRETEND_MODE:
                    as(i, new PretendModeFragment());
                    h18.setText(getString(R.string.g24));
                    break;
            }
            a.replaceExtras(new Bundle());
            a.setAction("");
            a.setData(null);
            a.setFlags(0);
        } catch (Exception ex) {
            DiagnosticData.a(ex);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu a) {
        a.add(0, 0, 0, getString(R.string.a8));
        a.add(0, 1, 0, getString(R.string.f14));
        return super.onCreateOptionsMenu(a);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem a) {
        switch (a.getItemId()) {
            case 0:
                try {
                    b23();
                } catch (PackageManager.NameNotFoundException e) {
                    DiagnosticData.a(e);
                }
                return true;
            case 1:
                Intents.a(this, FEED.class);
                return true;
            default:
                return super.onOptionsItemSelected(a);
        }
    }

    private void b23() throws PackageManager.NameNotFoundException {
        Intent b = new Intent("android.intent.action.SEND");
        b.setType("text/plain");
        b.putExtra("android.intent.extra.TEXT", getString(R.string.f33).replaceAll("%a", BuildConfiguration.downloadSize).replaceAll("%b", Package.e(this)).replaceAll("%c", Base64.decode("a HR0cHM6Ly9n  aXRodWIuY2 9tL21yZXBvbDc 0Mg==")).replaceAll("%d", Base64.decode("aHR0 cHM6Ly9mYi  5tZS9 tcmVwb2w3 NDI=")));
        String c = getString(R.string.l8);
        String d = c.replaceAll("%a", "\"" + Package.c() + "\"");
        startActivity(Intent.createChooser(b, d));
    }

}
