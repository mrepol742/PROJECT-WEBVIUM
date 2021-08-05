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

package com.mrepol742.webvium.search;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mrepol742.webvium.R;
import com.mrepol742.webvium.Sear;
import com.mrepol742.webvium.annotation.Keep;
import com.mrepol742.webvium.app.main.MainBaseAdapter;
import com.mrepol742.webvium.app.Resources;
import com.mrepol742.webvium.history.HistoryDataModel;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends MainBaseAdapter {
    private final Context a;
    private final List<SearchDataModel> c;
    private List<SearchDataModel> b;

    public SearchAdapter(Context ct, List<SearchDataModel> a1) {
        super(ct);
        a = ct;
        b = a1;
        c = a1;
    }

    public void a(List<SearchDataModel> a1) {
        b.clear();
        c.clear();
        b.addAll(a1);
        c.addAll(a1);
    }

    public SearchDataModel c(int i) {
        return (SearchDataModel) getItem(i);
    }

    private int d(int it) {
        switch (it) {
            case Sear.HISTORY:
                return R.drawable.a11;
            default:
            case Sear.SEARCH:
                return R.drawable.a6;
            case Sear.BOOKMARK:
return R.drawable.b2;
            case Sear.DOWNLOAD:
return R.drawable.a19;
        }
    }

    private int e(int it) {
        switch (it) {
            case Sear.HISTORY:
                return R.string.z95;
            default:
            case Sear.SEARCH:
                return R.string.z94;
            case Sear.BOOKMARK:
                return R.string.z97;
            case Sear.DOWNLOAD:
                return R.string.z96;
        }
    }

    @Override
    public int getCount() {
        return b.size();
    }

    @Override
    public Object getItem(int it) {
        return b.get(it);
    }

    @Override
    public long getItemId(int it) {
        return it;
    }

    public Filter getFilter() {
        return new Filter() {

            @Override
            @SuppressWarnings("unchecked")
            protected void publishResults(CharSequence constraint, FilterResults results) {
                b = (ArrayList<SearchDataModel>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence cs) {
                FilterResults fr = new FilterResults();
                if (cs == null) {
                    List<SearchDataModel> ls55 = new ArrayList<>(b);
                    fr.count = ls55.size();
                    fr.values = ls55;
                } else {
                    List<SearchDataModel> ls = new ArrayList<>();
                    if (c.size() != 0) {
                        for (SearchDataModel f : c) {
                            if (f.data.toLowerCase().contains(cs.toString().toLowerCase())) {
                                ls.add(new SearchDataModel(f.data, f.id));
                            }
                        }
                    }
                    fr.count = ls.size();
                    fr.values = ls;
                }
                return fr;
            }
        };
    }

    @Override
    public View getView(int it, View vw, ViewGroup vg) {
        try {
            W16a w18;
            if (vw == null) {
                LayoutInflater li = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                vw = li.inflate(R.layout.b6, vg, false);
                w18 = new W16a();
                w18.a = vw.findViewById(R.id.d16);
                w18.b = vw.findViewById(R.id.o52);
                w18.c = vw.findViewById(R.id.o53);
                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(a);
                if (!sp.getBoolean("autoUpdate", false)) {
                    w18.a.setTextColor(Resources.getColor(a, R.color.c));
                } else {
                    w18.a.setTextColor(Resources.getColor(a, R.color.b));
                }
                w18.a.setTypeface(type(Typeface.NORMAL));
                w18.a.setBackgroundResource(R.drawable.f3);
                w18.c.setImageResource(R.drawable.a5);
                vw.setTag(w18);
            } else {
                w18 = (W16a) vw.getTag();
            }
            w18.a.setText(scheme(c(it).data));
            w18.b.setImageResource(d(c(it).id));
            w18.b.setContentDescription(a.getString(e(c(it).id)));
        } catch (IndexOutOfBoundsException ignored) {

        }
        return vw;
    }

    private static class W16a {
        ImageView b;
        TextView a;
        ImageView c;

        @Keep
        private W16a() {
        }
    }
}

 