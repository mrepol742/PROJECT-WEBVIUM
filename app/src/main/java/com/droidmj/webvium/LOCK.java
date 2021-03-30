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

package com.droidmj.webvium;

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

import com.droidmj.webvium.app.BuildConfiguration;
import com.droidmj.webvium.app.NoSuchObjectToReturn;
import com.droidmj.webvium.app.main.MainBaseActivity;
import com.droidmj.webvium.content.Resources;
import com.droidmj.webvium.io.Files;
import com.droidmj.webvium.io.StorageDirectory;
import com.droidmj.webvium.os.Vibrate;
import com.droidmj.webvium.security.Hash;
import com.droidmj.webvium.telemetry.DiagnosticData;
import com.droidmj.webvium.util.cache.BitmapCache;
import com.droidmj.webvium.widget.Toast;

import java.io.File;
import java.util.Objects;

// @Class Lock
public class LOCK extends MainBaseActivity implements View.OnClickListener, View.OnLongClickListener {
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
        int f = Resources.b(this, R.color.c);
        int g = Resources.b(this, R.color.b);
        tv1.setTextColor(f);
        for (int it2 : ids) {
            if (it2 == R.id.i9) {
                ImageView iw1 = findViewById(it2);
                iw1.setImageResource(R.drawable.b27);
                iw1.setOnClickListener(this);
                iw1.setOnLongClickListener(this);
                iw1.setOnTouchListener((v, event) -> (event.getFlags() & MotionEvent.FLAG_WINDOW_IS_OBSCURED) != 0);
            } else if (it2 == R.id.j1) {
                ImageView j1 = findViewById(it2);
                j1.setImageResource(R.drawable.b28);
                j1.setOnClickListener(this);
                j1.setOnTouchListener((v, event) -> (event.getFlags() & MotionEvent.FLAG_WINDOW_IS_OBSCURED) != 0);
                j1.setOnLongClickListener(this);
            } else {
                TextView tv = findViewById(it2);
                tv.setTextColor(g);
                tv.setBackgroundResource(R.drawable.a26);
                tv.setOnClickListener(this);
                tv.setOnLongClickListener(this);
                tv.setOnTouchListener((v, event) -> (event.getFlags() & MotionEvent.FLAG_WINDOW_IS_OBSCURED) != 0);
                tv.setTypeface(type(Typeface.BOLD));
                try {
                    tv.setText(a(it2));
                } catch (NoSuchObjectToReturn l1) {
                    DiagnosticData.a(l1);
                }
            }

            final File fe = new File(StorageDirectory.getBackground(this));
            if (a221().getBoolean("webviumB", false) && fe.exists()) {
                Runnable p155 = () -> {
                    Bitmap bp = BitmapCache.getInstance().a(StorageDirectory.getBackground(this));
                    runOnUiThread(() -> ll.setBackground(new BitmapDrawable(getResources(), bp)));
                };
                new Thread(p155).start();
            } else {
                if (!a221().getBoolean("autoUpdate", false)) {
                    ll.setBackgroundColor(Resources.b(this, R.color.b));
                } else {
                    ll.setBackgroundColor(Resources.b(this, R.color.n));
                }
            }
        }
    }

    private String a(int i) throws NoSuchObjectToReturn {
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
        } else if (i == R.id.i20) {
            return getString(R.string.m20);
        }
        throw new NoSuchObjectToReturn();
    }

    private void b(String a) throws NoSuchObjectToReturn {
        if (a.length() == 0) {
            return;
        }
        SharedPreferences sp = getSharedPreferences("a", 0);
        String sg1 = sp.getString("ajGjbduTwibdi", "");
        if (Objects.requireNonNull(sg1).equals(Hash.a("SHA-512", a))) {
            Intent resultIntent = new Intent();
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
            overridePendingTransition(R.anim.f, R.anim.b);
            Vibrate.a(this, 100);
        } else {
            Toast.c(this, getString(R.string.c40));
            Animation am76 = AnimationUtils.loadAnimation(this, R.anim.a);
            ll.startAnimation(am76);
            tv1.setText("");
            Vibrate.a(this, 200);
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
        if (Objects.requireNonNull(sg1).equals(Hash.a("SHA-512", a2))) {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("a", "a");
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
            finishAndRemoveTask();
            overridePendingTransition(R.anim.f, R.anim.b);
            Vibrate.a(this, 100);
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
            try {
                b(sb.toString());
            } catch (NoSuchObjectToReturn l2) {
                DiagnosticData.a(l2);
            }
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
            try {
                b(sb.toString());
            } catch (NoSuchObjectToReturn l2) {
                DiagnosticData.a(l2);
            }
            return true;
        } else if (id == R.id.i9) {
            tv1.setText("");
            sb = new StringBuilder();
            return true;
        }
        return false;
    }

}
