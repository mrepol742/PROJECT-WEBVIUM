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

package com.mrepol742.webvium.tab;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mrepol742.webvium.R;
import com.mrepol742.webvium.annotation.Keep;
import com.mrepol742.webvium.app.NoSuchItemToGet;
import com.mrepol742.webvium.app.NoSuchSpannableStringBuilderToReturn;
import com.mrepol742.webvium.app.WebViews;
import com.mrepol742.webvium.app.main.MainBaseAdapter;
import com.mrepol742.webvium.content.Resources;

import java.util.ArrayList;

public class NewTabAdapter extends MainBaseAdapter {
    private final Context a;
    private final ArrayList<NewTabDataModel> ws;
    private final SharedPreferences sp;

    public NewTabAdapter(Context ct, ArrayList<NewTabDataModel> ws) {
        super(ct);
        this.a = ct;
        this.ws = ws;
        this.sp = PreferenceManager.getDefaultSharedPreferences(ct);
    }

    @Override
    public int getCount() {
        synchronized (this.ws) {
            return this.ws.size();
        }
    }

    @Override
    public Object getItem(int it) {
        synchronized (this.ws) {
            return this.ws.get(it);
        }
    }

    @Override
    public long getItemId(int it) {
        return it;
    }

    @Override
    public View getView(int i, View view, ViewGroup vg) {
        try {
            AN w17;
            if (view == null) {
                LayoutInflater li = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = li.inflate(R.layout.a14, vg, false);
                w17 = new AN();
                w17.a = view.findViewById(R.id.o42);
                w17.b = view.findViewById(R.id.o43);
                w17.c = view.findViewById(R.id.o44);
                if (!this.sp.getBoolean("autoUpdate", false)) {
                    w17.b.setTextColor(Resources.getColor(a, R.color.c));
                    w17.c.setTextColor(Resources.getColor(a, R.color.c));
                } else {
                    w17.b.setTextColor(Resources.getColor(a, R.color.b));
                    w17.c.setTextColor(Resources.getColor(a, R.color.b));
                }
                w17.b.setTypeface(type(Typeface.NORMAL));
                w17.c.setTypeface(type(Typeface.NORMAL));
                view.setTag(w17);
            } else {
                w17 = (AN) view.getTag();
            }
            String title = ws.get(i).title;
            Bitmap bit = ws.get(i).favicon;
            if (title.equals("webvium://newtab")) {
                w17.a.setImageResource(R.drawable.a22);
                w17.c.setText(a.getString(R.string.z58));
            } else if (title.equals("webvium://closealltab")) {
                w17.a.setImageResource(R.drawable.a25);
                w17.c.setText(a.getString(R.string.z64));
            } else {
                w17.c.setText(title);
            }
            if (bit != null) {
                w17.a.setImageBitmap(bit);
            }
        } catch (IndexOutOfBoundsException ignored) {

        }
        return view;
    }

    private static class AN {
        ImageView a;
        TextView b;
        TextView c;

        @Keep
        private AN() {
        }
    }
}
