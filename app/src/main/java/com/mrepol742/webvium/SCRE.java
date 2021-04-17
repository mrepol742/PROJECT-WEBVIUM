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

package com.mrepol742.webvium;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.print.PrintManager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.mrepol742.webvium.app.Notifications;
import com.mrepol742.webvium.app.base.BaseActivity;
import com.mrepol742.webvium.app.main.MainNotification;
import com.mrepol742.webvium.app.main.MainWebView;
import com.mrepol742.webvium.content.Intents;
import com.mrepol742.webvium.content.Package;
import com.mrepol742.webvium.content.Resources;
import com.mrepol742.webvium.io.Files;
import com.mrepol742.webvium.io.StorageDirectory;
import com.mrepol742.webvium.telemetry.DiagnosticData;
import com.mrepol742.webvium.view.Animation;

// @Class Screenshot
public class SCRE extends BaseActivity {
    private final String st = "/" + Package.c() + "/Screenshot/";
    private TextView h18;
    private ImageView h19;
    private int on;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle a) {
        theme(T_DEFAULT);
        super.onCreate(a);
        a225(R.layout.a7);
        Toolbar h17 = findViewById(R.id.h17);
        h18 = findViewById(R.id.h18);
        h19 = findViewById(R.id.h19);
        h18.setTypeface(type(Typeface.BOLD));
        setActionBar(h17);
        h17.setElevation(5);
        int d = Resources.b(this, R.color.c);
        int e = Resources.b(this, R.color.b);
        ActionBar ab = getActionBar();
        if (ab != null) {
            // ab.setDisplayHomeAsUpEnabled(true);
            // ab.setDisplayShowHomeEnabled(false);
            ab.setDisplayShowTitleEnabled(false);
        }
        if (!a221().getBoolean("autoUpdate", false)) {
            h18.setTextColor(d);
        } else {
            h18.setTextColor(e);
        }
        h17.setBackgroundResource(R.drawable.p);
        h17.setNavigationIcon(R.drawable.a2);
        h17.setNavigationOnClickListener(view -> finishAndRemoveTask());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        iv = findViewById(R.id.i1);
        h19.setElevation(5);
        iv.setImageResource(R.drawable.c7);
        iv.setBackgroundResource(R.drawable.c6);
        iv.setOnClickListener(view -> b(h18.getText().toString()));
    }

    @Override
    public boolean onKeyUp(int a, KeyEvent b) {
        if (a == 4 && b.isTracking() && !b.isCanceled()) {
            finishAndRemoveTask();
            return true;
        }
        return false;
    }


    @Override
    @SuppressLint("AlwaysShowAction")
    public boolean onCreateOptionsMenu(Menu a) {

        if (on == Configuration.ORIENTATION_LANDSCAPE) {
            a.add(0, 0, 0, getString(R.string.a13)).setIcon(Resources.a(this, R.drawable.b10)).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            a.add(0, 1, 0, getString(R.string.a14)).setIcon(Resources.a(this, R.drawable.c4)).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        } else {
            a.add(0, 0, 0, getString(R.string.a13));
            a.add(0, 1, 0, getString(R.string.a14));
        }
        a.add(0, 2, 0, getString(R.string.w3));
        a.add(0, 3, 0, getString(R.string.h3));

        return super.onCreateOptionsMenu(a);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem a) {
        switch (a.getItemId()) {

            case 0:
                d(h18.getText().toString());
                return true;
            case 1:
                e();
                return true;
            case 2:
                f(h18.getText().toString());
                return true;
            case 3:
                f(h18.getText().toString());
                Intents.e(this, "search", SETT.FRAGMENT_TOOL, SETT.class);

                return true;
            default:
                return super.onOptionsItemSelected(a);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        onNewIntent(getIntent());
        Animation.animate(this, R.anim.i, iv);
    }

    private void b(String a) {
        Intent f = new Intent(Intent.ACTION_SEND);
        f.setType("image/*");
        f.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" +
                StorageDirectory.a() + st + a));
        String c = getString(R.string.l8);
        String d = c.replaceAll("%a", "\"" + a + "\"");
        startActivity(Intent.createChooser(f, d));
    }

    private void d(final String b) {
        final AlertDialog.Builder a = new AlertDialog.Builder(this);


        a.setCancelable(true);
        a.setTitle(getString(R.string.k1));
        String c = getString(R.string.l11);
        String d = c.replaceAll("%a", "\"" + b + "\"");
        a.setMessage(d);
        a.setPositiveButton(getString(R.string.i6), (a12, intetg) -> {

            Files.delete(st + b);
            finish();
            MainNotification.a(SCRE.this, Notifications.b);
        });
        a.setNegativeButton(getString(R.string.i7), (a1, intetg) -> a1.dismiss());
        a.create().show();
    }

    private void e() {
        try {
            final AlertDialog.Builder a = new AlertDialog.Builder(this);
            a.setCancelable(true);
/*
        Files b67 = new Files(st+b);
        Files[] cj = b67.listFiles();
        long lg = cj.length;
        String sg2 = Formatter.formatFileSize(getApplicationContext(), lg);
       */
            a.setTitle(getString(R.string.k1));
            a.setMessage("This feature is not available for now... ");
            a.setPositiveButton("Close", (a1, intetg) -> a1.dismiss());
            a.create().show();
        } catch (Exception ex) {
            DiagnosticData.a(ex);
        }
    }

    private void f(String a) {
        MainWebView w4 = new MainWebView(this);
        w4.loadDataWithBaseURL(null, "<!DOCTYPE html><html><head><title></title></head><body><img src=\"file://" + StorageDirectory.getWebviumDir() + "/Screenshot/" + a + "\"/></body></html>", "text/html", "UTF-8", null);
        PrintManager aa = (PrintManager) getSystemService(Context.PRINT_SERVICE);
        aa.print(a, w4.createPrintDocumentAdapter(a), null);
        w4.destroy();

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
            final String b = a.getStringExtra("a56hj");
            if (b != null) {
                String c = b.replaceAll(StorageDirectory.a() + st, "");
                Runnable p = () -> {
                    final Bitmap bp = BitmapFactory.decodeFile(b);
                    runOnUiThread(() -> h19.setImageBitmap(bp));
                };
                new Thread(p).start();
                h18.setText(c);
                a.removeExtra("a56hj");
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