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

package com.mrepol742.webvium.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.mrepol742.webvium.R;
import com.mrepol742.webvium.annotation.release.Keep;
import com.mrepol742.webvium.content.Resources;
import com.mrepol742.webvium.util.cache.FontCache;

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