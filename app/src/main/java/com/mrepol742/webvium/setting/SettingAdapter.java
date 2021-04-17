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

package com.mrepol742.webvium.setting;

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
import com.mrepol742.webvium.annotation.release.Keep;
import com.mrepol742.webvium.app.main.MainBaseAdapter;
import com.mrepol742.webvium.content.Resources;

public class SettingAdapter extends MainBaseAdapter {
    private static final int[] drawables = {
            R.drawable.c17,
            R.drawable.c18,
            R.drawable.c19,
            R.drawable.c20,
            R.drawable.d1,
            R.drawable.d2,
            R.drawable.d3,
            R.drawable.d4,
            R.drawable.d5,
            R.drawable.e1,
            R.drawable.e12,
            R.drawable.d6,
            R.drawable.d20
    };
    private static final int[] header = {
            R.string.a,
            R.string.o10,
            R.string.e,
            R.string.f,
            R.string.b,
            R.string.g,
            R.string.c,
            R.string.i,
            R.string.h10,
            R.string.l14
    };
    private static final int[] summary = {
            R.string.t8,
            R.string.t9,
            R.string.t10,
            R.string.t11,
            R.string.t12,
            R.string.t13,
            R.string.t14,
            R.string.t15,
            R.string.t16,
            R.string.f16,
            R.string.t19
    };
    private final Context a;

    public SettingAdapter(Context ct) {
        super(ct);
        a = ct;
    }

    @Override
    public int getCount() {
        return header.length;
    }

    @Override
    public Object getItem(int it) {
        return it;
    }

    @Override
    public long getItemId(int it) {
        return it;
    }

    @Override
    public View getView(int it, View e, ViewGroup vg) {
        try {
            W15a w17;
            if (e == null) {
                LayoutInflater li = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                e = li.inflate(R.layout.b3, vg, false);
                w17 = new W15a();
                w17.a = e.findViewById(R.id.e19);
                w17.b = e.findViewById(R.id.e20);
                w17.c = e.findViewById(R.id.e18);
                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(a);
                if (!sp.getBoolean("autoUpdate", false)) {
                    w17.a.setTextColor(Resources.b(a, R.color.c));
                    w17.b.setTextColor(Resources.b(a, R.color.c));
                    w17.c.setBackgroundResource(R.drawable.v);
                } else {
                    w17.a.setTextColor(Resources.b(a, R.color.b));
                    w17.b.setTextColor(Resources.b(a, R.color.b));
                    w17.c.setBackgroundResource(R.drawable.y);
                }
                w17.a.setTypeface(type(Typeface.BOLD));
                w17.b.setTypeface(type(Typeface.NORMAL));
                e.setTag(w17);
            } else {
                w17 = (W15a) e.getTag();
            }
            w17.a.setText(header[it]);
            w17.c.setImageResource(drawables[it]);
            w17.b.setText(summary[it]);

        } catch (IndexOutOfBoundsException ignored) {

        }
        return e;
    }

    private static class W15a {
        TextView a;
        TextView b;
        ImageView c;
        @Keep
        private W15a() {
        }
    }

}




