/*
 *
 * Copyright (c) 2021 Melvin Jones Repol (mrepol742.github.io). All rights reserved.
 *
 * License under the GNU General Public License, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain changedTo copy of the License at
 *
 *     https://www.gnu.org/licenses/gpl-3.0.en.html
 *
 * Unless required by the applicable law or agreed in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mrepol742.webvium;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import com.mrepol742.webvium.app.base.BaseActivity;
import com.mrepol742.webvium.app.Package;
import com.mrepol742.webvium.app.Resources;
import com.mrepol742.webvium.setting.fragment.AboutFragment;
import com.mrepol742.webvium.setting.fragment.AccessibilityFragment;
import com.mrepol742.webvium.setting.fragment.AdvancedFragment;
import com.mrepol742.webvium.setting.fragment.DatabaseFragment;
import com.mrepol742.webvium.setting.fragment.DownloadFragment;
import com.mrepol742.webvium.setting.fragment.DevelopmentFragment;
import com.mrepol742.webvium.setting.fragment.GeneralFragment;
import com.mrepol742.webvium.setting.fragment.InterfaceFragment;
import com.mrepol742.webvium.setting.fragment.PretendModeFragment;
import com.mrepol742.webvium.setting.fragment.PrivacyFragment;
import com.mrepol742.webvium.setting.fragment.SearchFragment;
import com.mrepol742.webvium.setting.fragment.SecurityLockFragment;
import com.mrepol742.webvium.setting.fragment.ToolFragment;
import com.mrepol742.webvium.setting.fragment.VideoFragment;
import com.mrepol742.webvium.security.Base64;

import java.util.Objects;

/*
 * @SettingActivityFragment
 */
public class Sett extends BaseActivity {

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
    private TextView h18;

    @Override
    protected void onCreate(Bundle a) {
        theme(T_DEFAULT);
        super.onCreate(a);
        a225(R.layout.a9);
        Toolbar h17 = findViewById(R.id.b7);
        h18 = findViewById(R.id.b8);
        h18.setTypeface(type(Typeface.BOLD));
        setActionBar(h17);
        h17.setElevation(5);
        ActionBar ab = getActionBar();
        if (ab != null) {
            ab.setDisplayShowTitleEnabled(false);
        }
        if (!a221().getBoolean("autoUpdate", false)) {
            h18.setTextColor(Resources.getColor(this, R.color.c));
        } else {
            h18.setTextColor(Resources.getColor(this, R.color.b));
        }
        h17.setBackgroundResource(R.drawable.p);
        h17.setNavigationIcon(R.drawable.a2);
        h17.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Sett.this.finish();
            }
        });
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
                    as(i, new DevelopmentFragment());
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
            ex.printStackTrace();
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
                b23();
                return true;
            case 1:
                Intent intent = new Intent("android.intent.action.SEND");
                intent.putExtra("android.intent.extra.EMAIL", new String[]{getString(R.string.dev_mail)});
                intent.putExtra("android.intent.extra.SUBJECT", getString(R.string.z69));
                intent.putExtra("android.intent.extra.TEXT", getString(R.string.z66));
                intent.putExtra("android.intent.extra.CC", getString(R.string.dev_mail));
                intent.setType("text/html");
                intent.setPackage("com.google.android.gm");
                startActivity(Intent.createChooser(intent, getString(R.string.z65)));
                return true;
            default:
                return super.onOptionsItemSelected(a);
        }
    }

    private void b23() {
        Intent b = new Intent("android.intent.action.SEND");
        b.setType("text/plain");
        b.putExtra("android.intent.extra.TEXT", String.format(getString(R.string.f33), Base64.decode("aHR0cHM6Ly9tcmVwb2w3NDIuZ2l0aHViLmlvL1BST0pFQ1QtV0VCVklVTS8")));
        startActivity(Intent.createChooser(b, String.format(getString(R.string.l8), "\"" + Package.c() + "\"")));
    }

}
