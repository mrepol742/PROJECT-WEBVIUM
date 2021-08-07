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

package com.mrepol742.webvium.bookmark;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mrepol742.webvium.R;
import com.mrepol742.webvium.annotation.Keep;
import com.mrepol742.webvium.app.main.MainBaseAdapter;
import com.mrepol742.webvium.app.Resources;

import java.util.List;
import java.util.Random;

public class BookmarkAdapter extends MainBaseAdapter {
    private static final int[] background = {
            R.drawable.g3,
            R.drawable.g4,
            R.drawable.g5,
            R.drawable.g6
    };
    private final Context a;
    private final List<String> b;
    private final Random rm;


    public BookmarkAdapter(Context ct, List<String> al) {
        super(ct);
        a = ct;
        b = al;
        rm = new Random();
    }

    public void a(List<String> a1) {
        synchronized (b) {
            b.clear();
            b.addAll(a1);
        }
    }

    @Override
    public int getCount() {
        return b.size();
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
            W3a w20;
            if (e == null) {
                LayoutInflater ll = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                e = ll.inflate(R.layout.w, vg, false);
                w20 = new W3a();
                w20.tv = e.findViewById(R.id.f1);
                w20.ll = e.findViewById(R.id.o23);
                w20.tv.setCompoundDrawablePadding(20);
                w20.tv.setTextColor(Resources.getColor(a, R.color.b));
                w20.tv.setCompoundDrawablesWithIntrinsicBounds(null, Resources.getDrawable(a, R.drawable.a9), null, null);
                w20.tv.setTypeface(type(Typeface.BOLD));
                e.setTag(w20);
            } else {
                w20 = (W3a) e.getTag();
            }
            w20.tv.setText(b.get(it));
            w20.ll.setBackgroundResource(background[rm.nextInt(background.length)]);
            w20.ll.setElevation(5);

        } catch (IndexOutOfBoundsException ignored) {

        }
        return e;
    }

    private static class W3a {
        TextView tv;
        LinearLayout ll;

        @Keep
        private W3a() {
        }
    }
}



