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

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.mrepol742.webvium.app.Notifications;
import com.mrepol742.webvium.app.base.BaseActivity;
import com.mrepol742.webvium.app.main.MainNotification;
import com.mrepol742.webvium.app.main.MainWebView;
import com.mrepol742.webvium.app.Intents;
import com.mrepol742.webvium.app.Package;
import com.mrepol742.webvium.app.Resources;
import com.mrepol742.webvium.util.FileUtil;
import com.mrepol742.webvium.app.StorageDirectory;
import com.mrepol742.webvium.util.Animation;

/*
 * @ScreenshotActivity
 */
public class Scre extends BaseActivity {
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
        int d = Resources.getColor(this, R.color.c);
        int e = Resources.getColor(this, R.color.b);
        ActionBar ab = getActionBar();
        if (ab != null) {
            ab.setDisplayShowTitleEnabled(false);
        }
        if (!a221().getBoolean("autoUpdate", false)) {
            h18.setTextColor(d);
        } else {
            h18.setTextColor(e);
        }
        h17.setBackgroundResource(R.drawable.p);
        h17.setNavigationIcon(R.drawable.a2);
        h17.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Scre.this.finishAndRemoveTask();
            }
        });
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        iv = findViewById(R.id.i1);
        h19.setElevation(5);
        iv.setImageResource(R.drawable.c7);
        iv.setBackgroundResource(R.drawable.c6);
        iv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent f = new Intent(Intent.ACTION_SEND);
                f.setType("image/*");
                f.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" +
                        StorageDirectory.a() + st + h18.getText().toString()));
                startActivity(Intent.createChooser(f, String.format(getString(R.string.l8), "\"" + h18.getText().toString() + "\"")));

            }
        });
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
            a.add(0, 0, 0, getString(R.string.a13)).setIcon(Resources.getDrawable(this, R.drawable.b10)).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        } else {
            a.add(0, 0, 0, getString(R.string.a13));
        }
        a.add(0, 2, 0, getString(R.string.w3));
        a.add(0, 3, 0, getString(R.string.h3));
        return super.onCreateOptionsMenu(a);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem a1) {
        switch (a1.getItemId()) {
            case 0:
                AlertDialog.Builder a = new AlertDialog.Builder(this);
                a.setCancelable(true);
                a.setTitle(getString(R.string.k1));
                a.setMessage(String.format(getString(R.string.l11), "\"" + h18.getText().toString() + "\""));
                a.setPositiveButton(getString(R.string.i6), new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface a12, int intetg) {
                        FileUtil.delete(st + h18.getText().toString());
                        Scre.this.finish();
                        MainNotification.a(Scre.this, Notifications.b);
                    }
                });
                a.setNegativeButton(getString(R.string.i7), new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface a1, int intetg) {
                        a1.dismiss();
                    }
                });
                a.create().show();
                return true;
            case 2:
                MainWebView w4 = new MainWebView(this);
                String html = "<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "<title></title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "<img src=\"%1$s\"/>\n" +
                        "</body>\n" +
                        "</html>";
                w4.loadDataWithBaseURL(null, String.format(html, "file://" + StorageDirectory.getWebviumDir() + "/Screenshot/" + h18.getText().toString()), "text/html", w4.getTextEncoding(), null);
                PrintManager aa = (PrintManager) getSystemService(Context.PRINT_SERVICE);
                aa.print(h18.getText().toString(), w4.createPrintDocumentAdapter(h18.getText().toString()), null);
                w4.destroy();
                return true;
            case 3:
                Intents.e(this, "search", Sett.FRAGMENT_TOOL, Sett.class);
                return true;
            default:
                return super.onOptionsItemSelected(a1);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        onNewIntent(getIntent());
        Animation.animate(this, R.anim.i, iv);
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
                Runnable p = new Runnable() {

                    @Override
                    public void run() {
                        final Bitmap bp = BitmapFactory.decodeFile(b);
                        Scre.this.runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                h19.setImageBitmap(bp);
                            }
                        });
                    }
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
            ex.printStackTrace();
        }
    }
}