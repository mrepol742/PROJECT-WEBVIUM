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
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mrepol742.webvium.app.main.MainBaseActivity;
import com.mrepol742.webvium.app.Resources;
import com.mrepol742.webvium.app.StorageDirectory;
import com.mrepol742.webvium.app.Vibrator;
import com.mrepol742.webvium.security.SHA;
import com.mrepol742.webvium.util.BitmapCache;
import com.mrepol742.webvium.util.AwesomeToast;

import java.io.File;
import java.util.Objects;

/*
 * @LockActivity
 */
public class Lock extends MainBaseActivity implements View.OnClickListener, View.OnLongClickListener {
    private static final int[] ids = {
            R.id.i9,
            R.id.j1,
            R.id.i11,
            R.id.i12,
            R.id.i13,
            R.id.i14,
            R.id.i15,
            R.id.i16,
            R.id.i17,
            R.id.i18,
            R.id.i19,
            R.id.i20
    };
    private TextView tv1;
    private StringBuilder sb = new StringBuilder();
    private LinearLayout ll;

    @Override
    @SuppressLint("ClickableViewAccessibility")
    protected void onCreate(Bundle a) {
        super.onCreate(a);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            a225(R.layout.b14);
        } else {
            a225(R.layout.b13);
        }
        tv1 = findViewById(R.id.i10);
        ll = findViewById(R.id.i7);
        tv1.setTypeface(type(Typeface.BOLD));
        int f = Resources.getColor(this, R.color.c);
        int g = Resources.getColor(this, R.color.b);
        tv1.setTextColor(f);
        for (int it2 : ids) {
            if (it2 == R.id.i9) {
                ImageView iw1 = findViewById(it2);
                iw1.setImageResource(R.drawable.b27);
                iw1.setOnClickListener(this);
                iw1.setOnLongClickListener(this);
                iw1.setOnTouchListener(new View.OnTouchListener() {

                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return (event.getFlags() & MotionEvent.FLAG_WINDOW_IS_OBSCURED) != 0;
                    }
                });
            } else if (it2 == R.id.j1) {
                ImageView j1 = findViewById(it2);
                j1.setImageResource(R.drawable.b28);
                j1.setOnClickListener(this);
                j1.setOnTouchListener(new View.OnTouchListener() {

                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return (event.getFlags() & MotionEvent.FLAG_WINDOW_IS_OBSCURED) != 0;
                    }
                });
                j1.setOnLongClickListener(this);
            } else {
                TextView tv = findViewById(it2);
                tv.setTextColor(g);
                tv.setBackgroundResource(R.drawable.a26);
                tv.setOnClickListener(this);
                tv.setOnLongClickListener(this);
                tv.setOnTouchListener(new View.OnTouchListener() {

                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return (event.getFlags() & MotionEvent.FLAG_WINDOW_IS_OBSCURED) != 0;
                    }
                });
                tv.setTypeface(type(Typeface.BOLD));
                tv.setText(a(it2));
            }

            final File fe = new File(StorageDirectory.getBackground(this));
            if (a221().getBoolean("webviumB", false) && fe.exists()) {
                Runnable p155 = new Runnable() {

                    @Override
                    public void run() {
                        final Bitmap bp = BitmapCache.getInstance().a(StorageDirectory.getBackground(Lock.this));
                        Lock.this.runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                ll.setBackground(new BitmapDrawable(Lock.this.getResources(), bp));
                            }
                        });
                    }
                };
                new Thread(p155).start();
            } else {
                if (!a221().getBoolean("autoUpdate", false)) {
                    ll.setBackgroundColor(Resources.getColor(this, R.color.b));
                } else {
                    ll.setBackgroundColor(Resources.getColor(this, R.color.n));
                }
            }
        }
    }

    private String a(int i) {
        if (i == R.id.i11) {
            return getString(R.string.m11);
        } else if (i == R.id.i12) {
            return getString(R.string.m12);
        } else if (i == R.id.i13) {
            return getString(R.string.m13);
        } else if (i == R.id.i14) {
            return getString(R.string.m14);
        } else if (i == R.id.i15) {
            return getString(R.string.m15);
        } else if (i == R.id.i16) {
            return getString(R.string.m16);
        } else if (i == R.id.i17) {
            return getString(R.string.m17);
        } else if (i == R.id.i18) {
            return getString(R.string.m18);
        } else if (i == R.id.i19) {
            return getString(R.string.m19);
        }
        return getString(R.string.m20);
    }

    private void b(String a) {
        if (a.length() == 0) {
            return;
        }
        SharedPreferences sp = getSharedPreferences("a", 0);
        String sg1 = sp.getString("ajGjbduTwibdi", "");
        if (Objects.requireNonNull(sg1).equals(SHA.a("SHA-512", a))) {
            Intent resultIntent = new Intent();
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
            overridePendingTransition(R.anim.f, R.anim.b);
            Vibrator.vibrate(this, 100);
        } else {
            AwesomeToast.c(this, getString(R.string.c40));
            Animation am76 = AnimationUtils.loadAnimation(this, R.anim.a);
            ll.startAnimation(am76);
            tv1.setText("");
            Vibrator.vibrate(this, 200);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Intent resultIntent = new Intent();
        setResult(Activity.RESULT_CANCELED, resultIntent);
        finish();
        return super.onKeyDown(keyCode, event);

    }

    private void d() {
        String a2 = sb.toString();
        if (a2.length() == 0) {
            return;
        }
        SharedPreferences sp3 = getSharedPreferences("a", 0);
        String sg1 = sp3.getString("ajGjbduTwibdi", "");
        if (Objects.requireNonNull(sg1).equals(SHA.a("SHA-512", a2))) {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("a", "a");
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
            finishAndRemoveTask();
            overridePendingTransition(R.anim.f, R.anim.b);
            Vibrator.vibrate(this, 100);
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.i11) {
            tv1.append("*");
            sb.append(getString(R.string.m11));
            d();
        } else if (id == R.id.i12) {
            tv1.append("*");
            sb.append(getString(R.string.m12));
            d();
        } else if (id == R.id.i13) {
            tv1.append("*");
            sb.append(getString(R.string.m13));
            d();
        } else if (id == R.id.i14) {
            tv1.append("*");
            sb.append(getString(R.string.m14));
            d();
        } else if (id == R.id.i15) {
            tv1.append("*");
            sb.append(getString(R.string.m15));
            d();
        } else if (id == R.id.i16) {
            tv1.append("*");
            sb.append(getString(R.string.m16));
            d();
        } else if (id == R.id.i17) {
            tv1.append("*");
            sb.append(getString(R.string.m17));
            d();
        } else if (id == R.id.i18) {
            tv1.append("*");
            sb.append(getString(R.string.m18));
            d();
        } else if (id == R.id.i19) {
            tv1.append("*");
            sb.append(getString(R.string.m19));
            d();
        } else if (id == R.id.i20) {
            tv1.append("*");
            sb.append(getString(R.string.m20));
            d();
        } else if (id == R.id.j1) {
            b(sb.toString());
        } else if (id == R.id.i9) {
            String sg5 = tv1.getText().toString();
            String temp = sb.toString();
            String ab = temp.substring(0, temp.length() - 1);
            String ab1 = sg5.substring(0, sg5.length() - 1);
            tv1.setText(ab1);
            sb = new StringBuilder(ab);
        }
    }

    @Override
    public boolean onLongClick(View view) {
        int id = view.getId();
        if (id == R.id.i11) {
            tv1.append("*");
            sb.append(getString(R.string.m11));
            d();
            return true;
        } else if (id == R.id.i12) {
            tv1.append("*");
            sb.append(getString(R.string.m12));
            d();
            return true;
        } else if (id == R.id.i13) {
            tv1.append("*");
            sb.append(getString(R.string.m13));
            d();
            return true;
        } else if (id == R.id.i14) {
            tv1.append("*");
            sb.append(getString(R.string.m14));
            d();
            return true;
        } else if (id == R.id.i15) {
            tv1.append("*");
            sb.append(getString(R.string.m15));
            d();
            return true;
        } else if (id == R.id.i16) {
            tv1.append("*");
            sb.append(getString(R.string.m16));
            d();
            return true;
        } else if (id == R.id.i17) {
            tv1.append("*");
            sb.append(getString(R.string.m17));
            d();
            return true;
        } else if (id == R.id.i18) {
            tv1.append("*");
            sb.append(getString(R.string.m18));
            d();
            return true;
        } else if (id == R.id.i19) {
            tv1.append("*");
            sb.append(getString(R.string.m19));
            d();
            return true;
        } else if (id == R.id.i20) {
            tv1.append("*");
            sb.append(getString(R.string.m20));
            d();
            return true;
        } else if (id == R.id.j1) {
            b(sb.toString());
            return true;
        } else if (id == R.id.i9) {
            tv1.setText("");
            sb = new StringBuilder();
            return true;
        }
        return false;
    }

}
