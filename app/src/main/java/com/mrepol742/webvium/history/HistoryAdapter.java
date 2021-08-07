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
import android.graphics.Typeface;
import android.preference.PreferenceManager;
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
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HistoryAdapter extends MainBaseAdapter {
    private final Context a;
    private final List<HistoryDataModel> w3;
    private final SharedPreferences sp;
    private final SimpleDateFormat day;
    private final SimpleDateFormat month;
    private final SimpleDateFormat year;


    public HistoryAdapter(Context ct, List<HistoryDataModel> w3) {
        super(ct);
        this.a = ct;
        this.w3 = w3;
        this.sp = PreferenceManager.getDefaultSharedPreferences(ct);
        this.day = new SimpleDateFormat("dd", Locale.US);
        this.month = new SimpleDateFormat("MM", Locale.US);
        this.year = new SimpleDateFormat("yyyy", Locale.US);
    }

    public void a(List<HistoryDataModel> w3) {
        synchronized (this.w3) {
            this.w3.clear();
            this.w3.addAll(w3);
        }
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
            w17.a.setText(c(it).ls);
            w17.c.setImageResource(icon(c(it).ls0));
            w17.b.setText(scheme(c(it).ls0), TextView.BufferType.SPANNABLE);
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




