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

package com.droidmj.webvium.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.droidmj.webvium.R;
import com.droidmj.webvium.annotation.release.Keep;
import com.droidmj.webvium.content.Resources;
import com.droidmj.webvium.util.cache.FontCache;

public class Toast {
    @Keep
    private Toast() {
    }

    public static void a(Context a, String b55, int c5) {
        View b = View.inflate(a, R.layout.h, null);
        TextView tv = b.findViewById(R.id.b14);
        tv.setTextColor(Resources.b(a, R.color.c));
        tv.setMaxLines(2);
        tv.setBackgroundResource(R.drawable.e11);
        FontCache u7 = FontCache.getInstance(a.getApplicationContext());
        tv.setTypeface(u7.a(Typeface.NORMAL));
        tv.setText(b55);
        android.widget.Toast d = new android.widget.Toast(a);
        d.setDuration(c5);
        d.setView(b);
        d.show();
    }

    public static void a(Context a, String b) {
        a(a, b, android.widget.Toast.LENGTH_SHORT);
    }

    // success
    public static void b(Context a, String b55) {
        View b = View.inflate(a, R.layout.h, null);
        TextView tv = b.findViewById(R.id.b14);
        tv.setTextColor(Resources.b(a, R.color.b));
        tv.setBackgroundResource(R.drawable.o);
        FontCache u7 = FontCache.getInstance(a.getApplicationContext());
        tv.setTypeface(u7.a(Typeface.NORMAL));
        tv.setText(b55);
        tv.setMaxLines(2);
        android.widget.Toast d = new android.widget.Toast(a);
        d.setDuration(android.widget.Toast.LENGTH_SHORT);
        d.setView(b);
        d.show();
    }

    // failed
    public static void c(Context a, String bgg) {
        View b = View.inflate(a, R.layout.h, null);
        TextView tv = b.findViewById(R.id.b14);
        tv.setTextColor(Resources.b(a, R.color.b));
        tv.setBackgroundResource(R.drawable.n);
        FontCache u7 = FontCache.getInstance(a.getApplicationContext());
        tv.setTypeface(u7.a(Typeface.NORMAL));
        tv.setText(bgg);
        tv.setMaxLines(2);
        android.widget.Toast d = new android.widget.Toast(a);
        d.setDuration(android.widget.Toast.LENGTH_SHORT);
        d.setView(b);
        d.show();
    }

    public static void d(Context a, String b55) {
        View b = View.inflate(a, R.layout.h, null);
        TextView tv = b.findViewById(R.id.b14);
        tv.setTextColor(Resources.b(a, R.color.b));
        tv.setBackgroundResource(R.drawable.o);
        FontCache u7 = FontCache.getInstance(a.getApplicationContext());
        tv.setTypeface(u7.a(Typeface.NORMAL));
        tv.setText(b55);
        android.widget.Toast d = new android.widget.Toast(a);
        d.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        d.setDuration(android.widget.Toast.LENGTH_SHORT);
        d.setView(b);
        d.show();
    }

    // failed
    public static void e(Context a, String bgg, int bb) {
        View b = View.inflate(a, R.layout.h, null);
        TextView tv = b.findViewById(R.id.b14);
        tv.setTextColor(Resources.b(a, R.color.b));
        tv.setBackgroundResource(R.drawable.n);
        FontCache u7 = FontCache.getInstance(a.getApplicationContext());
        tv.setTypeface(u7.a(Typeface.NORMAL));
        tv.setText(bgg);
        tv.setMaxLines(2);
        android.widget.Toast d = new android.widget.Toast(a);
        d.setDuration(bb);
        d.setView(b);
        d.show();

    }


}