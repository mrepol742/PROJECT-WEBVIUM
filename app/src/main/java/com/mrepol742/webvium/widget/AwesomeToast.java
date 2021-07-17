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

package com.mrepol742.webvium.widget;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.util.LruCache;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.mrepol742.webvium.R;
import com.mrepol742.webvium.annotation.Keep;
import com.mrepol742.webvium.content.Resources;
import com.mrepol742.webvium.io.StorageDirectory;

import java.io.File;

public class AwesomeToast {
    private static final String MAVEN_PRO = "classes";
    private static Typeface type;
    private static LruCache<Integer, Typeface> cac;
    private static final int PRIMARY_CACHE = 99;

    @Keep
    private AwesomeToast() {
    }

    public static void a(Context a, String b55, int c5) {
        View b = View.inflate(a, R.layout.h, null);
        TextView tv = b.findViewById(R.id.b14);
        tv.setTextColor(Resources.getColor(a, R.color.c));
        tv.setMaxLines(2);
        tv.setBackgroundResource(R.drawable.e11);
        tv.setTypeface(type(a.getApplicationContext()));
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
        tv.setTextColor(Resources.getColor(a, R.color.b));
        tv.setBackgroundResource(R.drawable.o);
        tv.setTypeface(type(a.getApplicationContext()));
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
        tv.setTextColor(Resources.getColor(a, R.color.b));
        tv.setBackgroundResource(R.drawable.n);
        tv.setTypeface(type(a.getApplicationContext()));
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
        tv.setTextColor(Resources.getColor(a, R.color.b));
        tv.setBackgroundResource(R.drawable.o);
        tv.setTypeface(type(a.getApplicationContext()));
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
        tv.setTextColor(Resources.getColor(a, R.color.b));
        tv.setBackgroundResource(R.drawable.n);
        tv.setTypeface(type(a.getApplicationContext()));
        tv.setText(bgg);
        tv.setMaxLines(2);
        android.widget.Toast d = new android.widget.Toast(a);
        d.setDuration(bb);
        d.setView(b);
        d.show();

    }

    // success
    public static void f(Context a, String b55, int i) {
        View b = View.inflate(a, R.layout.h, null);
        TextView tv = b.findViewById(R.id.b14);
        tv.setTextColor(Resources.getColor(a, R.color.b));
        tv.setBackgroundResource(R.drawable.o);
        tv.setTypeface(type(a.getApplicationContext()));
        tv.setText(b55);
        tv.setMaxLines(2);
        android.widget.Toast d = new android.widget.Toast(a);
        d.setDuration(i);
        d.setView(b);
        d.show();
    }

    private static Typeface type(Context ct) {
        if (type == null) {
            type = Typeface.createFromAsset(ct.getAssets(), MAVEN_PRO);
        }
        if (cac == null) {
            cac = new LruCache<>(32);
        }
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(ct);
        if (sp.getBoolean("cFNT", false)) {
            File fe = new File(StorageDirectory.Fonts.getPrimaryFont(ct));
            if (fe.exists()) {
                Typeface bp0 = cac.get(PRIMARY_CACHE);
                if (bp0 == null) {
                    bp0 = Typeface.createFromFile(fe);
                    cac.put(PRIMARY_CACHE, bp0);
                }
                return bp0;
            }
        }
        Typeface bp = cac.get(Typeface.NORMAL);
        if (bp == null) {
            bp = Typeface.create(type, Typeface.NORMAL);
            cac.put(Typeface.NORMAL, bp);
        }
        return bp;
    }


}