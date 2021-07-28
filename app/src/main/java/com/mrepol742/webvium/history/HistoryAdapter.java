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

package com.mrepol742.webvium.history;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Base64;
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

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class HistoryAdapter extends MainBaseAdapter {
    private final Context a;
    private final ArrayList<HistoryDataModel> w3;
    private final SharedPreferences sp;
    private final ForegroundColorSpan A;
    private final ForegroundColorSpan E;
    private final ForegroundColorSpan S;
    private final ForegroundColorSpan I;
    private final ForegroundColorSpan B;
    private final SimpleDateFormat day;
    private final SimpleDateFormat month;
    private final SimpleDateFormat year;


    public HistoryAdapter(Context ct, ArrayList<HistoryDataModel> w3) {
        super(ct);
        this.a = ct;
        this.w3 = w3;
        this.sp = PreferenceManager.getDefaultSharedPreferences(ct);
        this.day = new SimpleDateFormat("dd", Locale.US);
        this.month = new SimpleDateFormat("MM", Locale.US);
        this.year = new SimpleDateFormat("yyyy", Locale.US);
        this.A = new ForegroundColorSpan(Resources.getColor(ct, R.color.a));
        this.E = new ForegroundColorSpan(Resources.getColor(ct, R.color.e));
        this.S = new ForegroundColorSpan(Resources.getColor(ct, R.color.s));
        this.I = new ForegroundColorSpan(Resources.getColor(ct, R.color.i));
        this.B = new ForegroundColorSpan(Resources.getColor(ct, R.color.b));
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

    public static Bitmap d1(String b) {
        byte[] by = Base64.decode(b, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(by, 0, 0);
    }

    public void a(ArrayList<HistoryDataModel> w3) {
        synchronized (this.w3) {
            this.w3.clear();
            this.w3.addAll(w3);
        }
    }

    private SpannableString b(String url) {
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

    public HistoryDataModel c(int i) {
        return (HistoryDataModel) getItem(i);
    }

    @Override
    public int getCount() {
        synchronized (this.w3) {
            return this.w3.size();
        }
    }

    @Override
    public Object getItem(int it) {
        synchronized (this.w3) {
            return this.w3.get(it);
        }
    }

    @Override
    public long getItemId(int it) {
        return it;
    }

    @Override
    public View getView(int it, View e, ViewGroup vg) {
        try {
            AN w17;
            if (e == null) {
                LayoutInflater li = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                e = li.inflate(R.layout.a22, vg, false);
                w17 = new AN();

                w17.a = e.findViewById(R.id.e19);
                w17.b = e.findViewById(R.id.e20);
                w17.c = e.findViewById(R.id.e18);
                w17.d = e.findViewById(R.id.n29);
                if (!this.sp.getBoolean("autoUpdate", false)) {
                    w17.a.setTextColor(Resources.getColor(a, R.color.c));
                    w17.b.setTextColor(Resources.getColor(a, R.color.c));
                    w17.d.setTextColor(Resources.getColor(a, R.color.c));
                    w17.c.setBackgroundResource(R.drawable.v);
                } else {
                    w17.a.setTextColor(Resources.getColor(a, R.color.b));
                    w17.b.setTextColor(Resources.getColor(a, R.color.b));
                    w17.d.setTextColor(Resources.getColor(a, R.color.b));
                    w17.c.setBackgroundResource(R.drawable.y);
                }
                w17.a.setTypeface(type(Typeface.BOLD));
                w17.b.setTypeface(type(Typeface.NORMAL));
                w17.d.setTypeface(type(Typeface.NORMAL));
                e.setTag(w17);
            } else {
                w17 = (AN) e.getTag();
            }
           /* w17.changedTo.setText(U8.changedTo(c(it).ls));
            w17.c.setImageResource(c(it).ls1);
            w17.b.setText(b(U8.changedTo(c(it).ls0)), TextView.BufferType.SPANNABLE);
            w17.d.setText(c(it).ls2);*/
            w17.a.setText(c(it).ls);
            w17.c.setImageBitmap(d1(c(it).ls3));
            w17.b.setText(b(c(it).ls0), TextView.BufferType.SPANNABLE);
            Date date = new Date(c(it).ls2);
            String fiDate = day.format(date).replaceAll("^0*", "") + " " + DateUtil.format(Integer.parseInt(month.format(date))) + " " + year.format(date);
            w17.d.setText(fiDate);
        } catch (IndexOutOfBoundsException ignored) {

        }
        return e;
    }

    private static class AN {
        TextView a;
        TextView b;
        ImageView c;
        TextView d;

        @Keep
        private AN() {
        }
    }


}




