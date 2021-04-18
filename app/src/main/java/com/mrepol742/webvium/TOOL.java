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

package com.mrepol742.webvium;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.net.Uri;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import com.mrepol742.webvium.app.base.BaseActivity;
import com.mrepol742.webvium.content.Clipboard;
import com.mrepol742.webvium.content.Intents;
import com.mrepol742.webvium.content.Package;
import com.mrepol742.webvium.content.Resources;
import com.mrepol742.webvium.io.Files;
import com.mrepol742.webvium.io.StorageDirectory;
import com.mrepol742.webvium.manifest.Permission;
import com.mrepol742.webvium.os.StrictMode;
import com.mrepol742.webvium.telemetry.DiagnosticData;
import com.mrepol742.webvium.text.Html;
import com.mrepol742.webvium.text.TextWatcher;
import com.mrepol742.webvium.util.Stream;
import com.mrepol742.webvium.util.U3;
import com.mrepol742.webvium.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

// @Class Tools
public class TOOL extends BaseActivity {

    public TextView k;
    public String sg;
    public String sg0;
    private int on;
    private TextView tv;
    private NfcAdapter nfc;

    @Override
    protected void onCreate(Bundle a) {
        theme(T_DEFAULT);
        super.onCreate(a);
        a225(R.layout.d);
        k = findViewById(R.id.l);
        tv = findViewById(R.id.c);
        Toolbar i = findViewById(R.id.k);
        k.setTypeface(type(Typeface.BOLD));
        tv.setTypeface(type(Typeface.NORMAL));
        int o = Resources.b(this, R.color.c);
        int p = Resources.b(this, R.color.b);

        k.setText(getString(R.string.j));
        if (!a221().getBoolean("autoUpdate", false)) {

            k.setTextColor(o);
            tv.setTextColor(o);

            tv.setBackgroundColor(Resources.b(this, R.color.p));
        } else {
            k.setTextColor(p);
            tv.setTextColor(p);

            tv.setBackgroundColor(Resources.b(this, R.color.m));
        }
        i.setBackgroundResource(R.drawable.p);
        setActionBar(i);
        i.setElevation(5);
        ActionBar ab = getActionBar();
        if (ab != null) {
            // ab.setDisplayHomeAsUpEnabled(true);
            // ab.setDisplayShowHomeEnabled(false);
            ab.setDisplayShowTitleEnabled(false);
        }
        i.setNavigationIcon(R.drawable.a2);
        i.setNavigationOnClickListener(view -> finish());
        nfc = NfcAdapter.getDefaultAdapter(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu a) {
        a.add(0, 0, 0, getString(R.string.a11)).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        a.add(0, 1, 0, getString(R.string.a12)).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        a.add(0, 2, 0, getString(R.string.u));
        a.add(0, 3, 0, getString(R.string.a8));
        a.add(0, 4, 0, getString(R.string.h3));
        return super.onCreateOptionsMenu(a);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu a) {
        MenuItem b1 = a.findItem(1);
        MenuItem c1 = a.findItem(0);
        MenuItem d1 = a.findItem(3);
        MenuItem e1 = a.findItem(2);
        b1.setIcon(Resources.a(this, R.drawable.i));
        c1.setIcon(Resources.a(this, R.drawable.g));
        if (on == Configuration.ORIENTATION_LANDSCAPE) {
            e1.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            d1.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            MenuItem f1 = a.findItem(4);
            f1.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            d1.setIcon(Resources.a(this, R.drawable.b15));
            e1.setIcon(Resources.a(this, R.drawable.b16));
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem a) {
        switch (a.getItemId()) {
            case 2:
                Clipboard.a(this, tv.getText().toString());
                h(getString(R.string.c27));
                return true;
            case 3:
                d();
                return true;

            case 0:
                if (Permission.check(this, Permission.STORAGE, 1)) {
                    f();
                }
                return true;
            case 1:
                b();
                return true;
            case 4:
                Intents.e(this, "search", SETT.FRAGMENT_TOOL, SETT.class);

                return true;
            default:
                return super.onOptionsItemSelected(a);
        }
    }

    @Override
    public boolean onKeyDown(int a, KeyEvent b1) {
        if (b1.getAction() == 0 && a == 4) {
            finish();
            return true;
        }
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        on = getResources().getConfiguration().orientation;
        invalidateOptionsMenu();
        onNewIntent(getIntent());
    }

    private void a(String jk) {
        final AlertDialog.Builder a = new AlertDialog.Builder(this);


        a.setCancelable(true);
        a.setTitle(getString(R.string.s26));
        a.setMessage(Html.b(jk));
        a.setPositiveButton(getString(R.string.u14), (a12, intetg) -> {
            Intent intent = new Intent();

            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", Package.b(), null);
            intent.setData(uri);
            startActivity(intent);
            a12.dismiss();
        });
        a.setNegativeButton(getString(R.string.i7), (a1, intetg) -> a1.dismiss());
        a.create().show();
    }

    private void b() {
        e(k.getText().toString() + ".html");
        Intent a = new Intent(Intent.ACTION_VIEW);
        a.setDataAndType(Uri.parse(StorageDirectory.getWebviumDir() + "/Source Code/" + k.getText().toString() + ".html"), "text/html");
        if (a.resolveActivity(getPackageManager()) != null) {
            startActivity(Intent.createChooser(a, getString(R.string.a26)));
        }
    }

    private void d() {
        Intent a = new Intent("android.intent.action.SEND");
        a.setType("text/plain");
        a.putExtra("android.intent.extra.TEXT", tv.getText().toString());
        startActivity(Intent.createChooser(a, String.format(getString(R.string.l8), "\"" + k.getText().toString() + "\"")));
    }

    public void e(String ko) {
        Files.createNewFolder(StorageDirectory.getWebviumDir() + "/Source Code");
        final String sg1 = tv.getText().toString();
        Runnable p15 = () -> {
            try {
                java.io.File a2 = new java.io.File(StorageDirectory.getWebviumDir() + "/Source Code/" + ko);
                if (!a2.exists()) {
                    if (a2.createNewFile()) {
                        FileOutputStream a3 = new FileOutputStream(a2);
                        OutputStreamWriter a4 = new OutputStreamWriter(a3);
                        a4.append(sg1);
                        a4.close();
                        a3.close();
                        runOnUiThread(() -> h(String.format(getString(R.string.c28), ko)));
                    }
                }
            } catch (FileNotFoundException a) {
                runOnUiThread(() -> Permission.check(TOOL.this, Permission.STORAGE, 1));
                DiagnosticData.a(a);
            } catch (IOException ie) {
                DiagnosticData.a(ie);
                runOnUiThread(() -> g(getString(R.string.b34)));
            }
        };
        new Thread(p15).start();
    }

    @SuppressLint("SetTextI18n")
    private void f() {
        AlertDialog.Builder a5 = new AlertDialog.Builder(this);
        LayoutInflater a6 = getLayoutInflater();
        View a7 = a6.inflate(R.layout.f, null);
        a5.setCancelable(true);
        a5.setTitle(getString(R.string.c29));
        a5.setView(a7);
        final EDIT a8 = a7.findViewById(R.id.b3);
        TextView f14 = a7.findViewById(R.id.f14);
        TextView f15 = a7.findViewById(R.id.f15);
        final TextView f17 = a7.findViewById(R.id.f17);
        int a15 = Resources.b(this, R.color.c);
        int a16 = Resources.b(this, R.color.b);
        int f = Resources.b(this, R.color.j);
        int g = Resources.b(this, R.color.k);
        if (!a221().getBoolean("autoUpdate", false)) {
            a8.setTextColor(a15);
            f14.setTextColor(a15);
            f15.setTextColor(a15);
            f17.setTextColor(a15);
            a8.setHintTextColor(f);
        } else {
            a8.setTextColor(a16);
            f14.setTextColor(a16);
            f15.setTextColor(a16);
            f17.setTextColor(a16);
            a8.setHintTextColor(g);
        }
        if (sg0 != null) {
            a8.setHint(Uri.parse(sg0).getHost() + ".html");
        } else {
            a8.setHint(Package.c() + ".html");
        }
        f14.setText(getString(R.string.c30));
        f15.setText(getString(R.string.c31));
        if (sg0 != null) {
            f17.setText(StorageDirectory.getWebviumDir() + "/Source Code/" + Uri.parse(sg0).getHost() + ".html");

        } else {

            f17.setText(StorageDirectory.getWebviumDir() + "/Source Code/" + Package.c() + ".html");
        }

        a5.setPositiveButton("Save", (a, intetg) -> {
            e(a8.getText().toString());
            a.dismiss();

        });
        a5.setNegativeButton(getString(R.string.i7), (a, intetg) -> a.dismiss());
        final AlertDialog d = a5.create();
        d.show();
        final Button okButton = d.getButton(AlertDialog.BUTTON_POSITIVE);
        a8.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String j78 = a8.getText().toString();
                f17.setText(StorageDirectory.getWebviumDir() + "/Source Code/" + j78);
                if (U3.b(j78)) {

                    okButton.setEnabled(false);
                } else {
                    java.io.File che = new java.io.File(StorageDirectory.getWebviumDir() + "/Source Code/" + j78);
                    if (che.exists()) {
                        okButton.setEnabled(false);
                        a8.setError(getString(R.string.u19));
                    } else {
                        okButton.setEnabled(true);
                    }
                }
            }
        });
        d.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
    }


    public void g(String a) {
        Toast.c(this, a);
    }

    public void h(String a) {
        Toast.b(this, a);
    }

    private void j(final String a) {
        tv.setText(getString(R.string.v13));
        Runnable p = () -> {
            StrictMode.a();
            final String cd = Stream.f(a, getString(R.string.c33));
            runOnUiThread(() -> tv.setText(Html.b(cd)));
        };
        new Thread(p).start();
        invalidateOptionsMenu();
    }

    @Override
    @TargetApi(Build.VERSION_CODES.M)
    public void onRequestPermissionsResult(int a, String[] b, int[] c) {
        super.onRequestPermissionsResult(a, b, c);
        if (a == 1) {
            if (c.length > 0 && c[0] == PackageManager.PERMISSION_GRANTED) {
                f();
            } else {

                if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    g(getString(R.string.u15));
                } else {
                    a(getString(R.string.u16));
                }

            }
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        on = newConfig.orientation;
        invalidateOptionsMenu();
    }

    @Override
    protected void onNewIntent(Intent a) {
        try {
            String sg = a.getStringExtra("value");
            String sg5 = a.getStringExtra("value0");
            String action = a.getAction();
            String data = a.getDataString();
            if (nfc != null) {
                if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
                    Parcelable[] rawMessages = a.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
                    if (rawMessages != null) {
                        //NdefMessage[] messages = new NdefMessage[rawMessages.length];
                        StringBuilder sb = new StringBuilder();
                        for (Parcelable rawMessage : rawMessages) {
                            //messages[i] = (NdefMessage) rawMessages[i];
                            sb.append(rawMessage);
                        }
                        j(sb.toString());
                    }
                }
            }

            if (sg != null && U3.b(sg)) {
                j(sg);
                sg0 = sg;
                k.setText(sg);
                a.removeExtra("value");
            } else if (sg5 != null && U3.b(sg5)) {
                sg0 = sg5;
                tv.setText(getString(R.string.v13));
                Runnable p15 = () -> {
                    final String temp = Stream.d(sg5, getString(R.string.c33));
                    runOnUiThread(() -> tv.setText(temp));
                };
                new Thread(p15).start();
                k.setText(sg5);
                a.removeExtra("value0");
            }
            if (action != null) {
                if (Intent.ACTION_VIEW.equals(action)) {
                    if (data != null) {
                        j(data);
                        sg0 = data;
                        k.setText(a.getStringExtra("qr"));
                        a.removeExtra("qr");
                    }
                }
            }
            a.replaceExtras(new Bundle());
            a.setAction("");
            a.setData(null);
            a.setFlags(0);
        } catch (Exception ex) {
            DiagnosticData.a(ex);
        }
    }
}
 