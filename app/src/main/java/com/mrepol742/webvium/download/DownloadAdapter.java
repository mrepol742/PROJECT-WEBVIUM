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

package com.mrepol742.webvium.download;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mrepol742.webvium.R;
import com.mrepol742.webvium.annotation.Keep;
import com.mrepol742.webvium.app.main.MainBaseAdapter;
import com.mrepol742.webvium.app.Resources;
import com.mrepol742.webvium.util.DateUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DownloadAdapter extends MainBaseAdapter {
    private final Context a;
    private final ArrayList<DownloadDataModel> al;
    private final SimpleDateFormat day;
    private final SimpleDateFormat month;
    private final SimpleDateFormat year;
    private final SharedPreferences sp;
    private final ForegroundColorSpan A;
    private final ForegroundColorSpan E;
    private final ForegroundColorSpan S;
    private final ForegroundColorSpan I;
    private final ForegroundColorSpan B;

    public DownloadAdapter(Context ct, ArrayList<DownloadDataModel> al) {
        super(ct);
        this.al = al;
        a = ct;
        this.A = new ForegroundColorSpan(Resources.getColor(ct, R.color.a));
        this.E = new ForegroundColorSpan(Resources.getColor(ct, R.color.e));
        this.S = new ForegroundColorSpan(Resources.getColor(ct, R.color.s));
        this.I = new ForegroundColorSpan(Resources.getColor(ct, R.color.i));
        this.B = new ForegroundColorSpan(Resources.getColor(ct, R.color.b));
        sp = PreferenceManager.getDefaultSharedPreferences(ct);
        this.day = new SimpleDateFormat("dd", Locale.US);
        this.month = new SimpleDateFormat("MM", Locale.US);
        this.year = new SimpleDateFormat("yyyy", Locale.US);
    }

    public void a(ArrayList<DownloadDataModel> w) {
        synchronized (this.al) {
            al.clear();
            al.addAll(w);
        }
    }

    public DownloadDataModel b(int i) {
        return (DownloadDataModel) getItem(i);
    }

    private SpannableString c(String url) {
        SpannableString ssb = new SpannableString(url);
        if (url.startsWith("https://")) {
            ssb.setSpan(this.A, 0, 8, 0);
        } else if (url.startsWith("http://")) {
            ssb.setSpan(this.E, 0, 7, 0);
        } else if (url.startsWith("file://")) {
            ssb.setSpan(this.S, 0, 7, 0);
        } else if (url.startsWith("content://") || url.startsWith("webvium://")) {
            ssb.setSpan(this.S, 0, 10, 0);
        } else {
            if (!this.sp.getBoolean("autoUpdate", false)) {
                ssb.setSpan(this.I, 0, url.length(), 0);
            } else {
                ssb.setSpan(this.B, 0, url.length(), 0);
            }
        }
        return ssb;
    }

    public static int d(String b) {
        if (b.startsWith("https://")) {
            return R.drawable.a15;
        } else if (b.startsWith("http://")) {
            return R.drawable.a16;
        } else if (b.startsWith("file://") || b.startsWith("content://")) {
            return R.drawable.a17;
        }
        return R.drawable.a8;
    }

    @Override
    public int getCount() {
        synchronized (this.al) {
            return this.al.size();
        }
    }

    @Override
    public Object getItem(int it) {
        synchronized (this.al) {
            return this.al.get(it);
        }
    }

    @Override
    public long getItemId(int it) {
        return it;
    }

    @Override
    public View getView(int it, View e, ViewGroup vg) {
        try {
            W12a w20;
            if (e == null) {
                LayoutInflater li = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                e = li.inflate(R.layout.b7, vg, false);
                w20 = new W12a();
                w20.a = e.findViewById(R.id.m15);
                w20.b = e.findViewById(R.id.b11);
                w20.c = e.findViewById(R.id.n20);
                w20.d = e.findViewById(R.id.b12);
                if (!sp.getBoolean("autoUpdate", false)) {
                    w20.a.setTextColor(Resources.getColor(a, R.color.c));
                    w20.b.setTextColor(Resources.getColor(a, R.color.c));
                    w20.d.setTextColor(Resources.getColor(a, R.color.c));
                    w20.c.setBackgroundResource(R.drawable.v);
                } else {
                    w20.a.setTextColor(Resources.getColor(a, R.color.b));
                    w20.b.setTextColor(Resources.getColor(a, R.color.b));
                    w20.d.setTextColor(Resources.getColor(a, R.color.b));
                    w20.c.setBackgroundResource(R.drawable.y);
                }
                w20.a.setTypeface(type(Typeface.BOLD));
                w20.b.setTypeface(type(Typeface.NORMAL));
                w20.d.setTypeface(type(Typeface.NORMAL));
                e.setTag(w20);
            } else {
                w20 = (W12a) e.getTag();
            }
            w20.a.setText(b(it).a);
            w20.b.setText(c(b(it).b));
            w20.c.setImageResource(d(b(it).b));
            Date date = new Date(b(it).d);
            String fiDate = day.format(date).replaceAll("^0*", "") + " " + DateUtil.format(Integer.parseInt(month.format(date))) + " " + year.format(date);
            w20.d.setText(b(it).c + " | " + fiDate);
        } catch (IndexOutOfBoundsException ignored) {

        }
        return e;
    }

    private static class W12a {
        TextView a;
        TextView b;
        ImageView c;
        TextView d;

        @Keep
        private W12a() {
        }
    }

}



