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

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.mrepol742.webvium.app.base.BaseActivity;
import com.mrepol742.webvium.app.ActivityState;
import com.mrepol742.webvium.app.Resources;
import com.mrepol742.webvium.util.AwesomeToast;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLDecoder;

/*
 * @BackupActivity
 */

public class Back0 extends BaseActivity {


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 742 && resultCode == RESULT_OK && data.getData() != null) {
            try {
                a(getContentResolver().openInputStream(data.getData()));
            } catch (Exception en) {
                AwesomeToast.c(Back0.this, getString(R.string.z21));
                en.printStackTrace();
                finishAndRemoveTask();
            }
        } else {
            finishAndRemoveTask();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        onNewIntent(getIntent());
    }

    protected void a(InputStream is) {
        try {
            // TODO: Check here what kind of db is in the file
        } catch (Exception en) {
            AwesomeToast.c(Back0.this, getString(R.string.z21));
            en.printStackTrace();
            finishAndRemoveTask();
        }
    }

    private void c(final Object mp) {
        final AlertDialog.Builder a = new AlertDialog.Builder(this);
        a.setCancelable(false);
        a.setTitle(getString(R.string.t6));
        a.setMessage(R.string.b39);
        a.setPositiveButton(getString(R.string.i6), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a12, int intetg) {
                try {
                   SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(Back0.this);
                   SharedPreferences.Editor spe = sp.edit();
                   spe.clear();

                   // TODO: import the settings
                   spe.apply();
                    ActivityState.changedTo(Back0.this, "com.mrepol742.webvium.activity.alias.PRE", PackageManager.COMPONENT_ENABLED_STATE_ENABLED);
                    ActivityState.changedTo(Back0.this, "com.mrepol742.webvium.activity.alias.MAY", PackageManager.COMPONENT_ENABLED_STATE_DISABLED);
                    Back0.this.getSharedPreferences("a", 0).edit().putString("ajGjbduTwibdi", "").putString("gsJsGsKSIgPes", "").apply();
                    Back0.this.g(Back0.this.getString(R.string.b27));
                    Back0.this.t();
                } catch (Exception en) {
                    en.printStackTrace();
                    Back0.this.d(Back0.this.getString(R.string.b28));
                }
                a12.dismiss();
                Back0.this.finishAndRemoveTask();
            }
        });
        a.setNegativeButton(getString(R.string.i7), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a1, int intetg) {
                a1.dismiss();
                Back0.this.finishAndRemoveTask();
            }
        });
        a.create().show();
    }

    private void d(String a) {
        AwesomeToast.c(this, a);
    }

    private void e(final Object mp) {
        final AlertDialog.Builder a = new AlertDialog.Builder(this);
        a.setCancelable(false);
        a.setTitle(getString(R.string.t6));
        a.setMessage(getString(R.string.c21));
        a.setPositiveButton(getString(R.string.i6), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a12, int intetg) {
                try {

                    // TODO: import search db
                    Back0.this.g(Back0.this.getString(R.string.b27));
                } catch (Exception en) {
                    en.printStackTrace();
                    Back0.this.d(Back0.this.getString(R.string.b28));
                }
                a12.dismiss();
                Back0.this.finishAndRemoveTask();
            }
        });
        a.setNegativeButton(getString(R.string.i7), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a1, int intetg) {
                a1.dismiss();
                Back0.this.finishAndRemoveTask();
            }
        });
        a.create().show();
    }

    private void f(final Object mp) {
        final AlertDialog.Builder a = new AlertDialog.Builder(this);
        a.setCancelable(false);
        a.setTitle(getString(R.string.t6));
        a.setMessage(getString(R.string.z35));
        a.setPositiveButton(getString(R.string.i6), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a12, int intetg) {
                try {

                    // TODO: import download db
                    Back0.this.g(Back0.this.getString(R.string.b27));
                } catch (Exception en) {
                    en.printStackTrace();
                    Back0.this.d(Back0.this.getString(R.string.b28));
                }
                a12.dismiss();
                Back0.this.finishAndRemoveTask();
            }
        });
        a.setNegativeButton(getString(R.string.i7), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a1, int intetg) {
                a1.dismiss();
                Back0.this.finishAndRemoveTask();
            }
        });
        a.create().show();
    }

    private void g(String a) {
        AwesomeToast.b(this, a);
    }

    private void i(final Object mp) {
        final AlertDialog.Builder a = new AlertDialog.Builder(this);
        a.setCancelable(false);
        a.setTitle(getString(R.string.t6));
        a.setMessage(getString(R.string.d34));
        a.setPositiveButton(getString(R.string.i6), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a12, int intetg) {
                try {

                    // TODO: import bookmark db
                    Back0.this.g(Back0.this.getString(R.string.b27));
                } catch (Exception en) {
                    en.printStackTrace();
                    Back0.this.d(Back0.this.getString(R.string.b28));
                }
                a12.dismiss();
                Back0.this.finishAndRemoveTask();
            }
        });
        a.setNegativeButton(getString(R.string.i7), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a1, int intetg) {
                a1.dismiss();
                Back0.this.finishAndRemoveTask();
            }
        });
        a.create().show();
    }

    private void j(final Object mp) {
        final AlertDialog.Builder a = new AlertDialog.Builder(this);
        a.setCancelable(false);
        a.setTitle(getString(R.string.t6));
        a.setMessage(getString(R.string.d33));
        a.setPositiveButton(getString(R.string.i6), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a12, int intetg) {
                try {

                    // TODO: import history db
                    Back0.this.g(Back0.this.getString(R.string.b27));
                } catch (Exception en) {
                    Back0.this.d(Back0.this.getString(R.string.b28));
                }
                a12.dismiss();
                Back0.this.finishAndRemoveTask();
            }
        });
        a.setNegativeButton(getString(R.string.i7), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a1, int intetg) {
                a1.dismiss();
                Back0.this.finishAndRemoveTask();
            }
        });
        a.create().show();
    }


    private void t() {
        AlertDialog.Builder c = new AlertDialog.Builder(this);
        LayoutInflater d = LayoutInflater.from(this);
        View e = d.inflate(R.layout.a12, null);
        c.setCancelable(false);
        c.setView(e);
        TextView f = e.findViewById(R.id.g1);
        f.setText(getString(R.string.o1));
        if (!a221().getBoolean("autoUpdate", false)) {
            f.setTextColor(Resources.getColor(this, R.color.c));
        } else {
            f.setTextColor(Resources.getColor(this, R.color.b));
        }
        AlertDialog j5 = c.create();
        O5 timer = new O5(2000, 2000, j5);
        timer.start();
        j5.show();
    }

    protected void onNewIntent(Intent a) {
        try {
            String action = a.getAction();
            String data = a.getDataString();
            String as = a.getStringExtra("a");
            if (as != null) {
                a.removeExtra("a");
                Intent d = new Intent(Intent.ACTION_GET_CONTENT);
                d.setType("*/*");
                d.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(Intent.createChooser(d, getString(R.string.a26)), 742);
            } else if (action.equals(Intent.ACTION_VIEW) && data != null) {
                try {
                    AwesomeToast.c(Back0.this, getString(R.string.z21));
                    a(new FileInputStream(URLDecoder.decode(data.replace("file://", ""), "UTF-8")));
                } catch (Exception en) {
                    AwesomeToast.c(Back0.this, getString(R.string.z21));
                    en.printStackTrace();
                    finishAndRemoveTask();
                }
            }
            a.replaceExtras(new Bundle());
            a.setAction("");
            a.setData(null);
            a.setFlags(0);
        } catch (Exception ex) {
            ex.printStackTrace();
            finishAndRemoveTask();
        }
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
            finishAndRemoveTask();
            SharedPreferences c56 = getSharedPreferences("wv", 0);
            Intent it = new Intent(Back0.this, Webv.class);
            it.putExtra("value", c56.getString("MyURL", ""));
            it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(it);
        }
    }
}