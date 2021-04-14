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

package com.mrepol742.webvium.manage.space;

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

public class ManageSpaceAdapter extends MainBaseAdapter {
    private final Context a;
    private final ManageSpaceDataModel w;

    public ManageSpaceAdapter(Context ct, ManageSpaceDataModel w) {
        super(ct);
        this.w = w;
        a = ct;
    }

    public void a(ManageSpaceDataModel w) {
        this.w.a1.clear();
        this.w.a2.clear();
        this.w.a3.clear();
        this.w.a4.clear();
        this.w.a1.addAll(w.a1);
        this.w.a2.addAll(w.a2);
        this.w.a3.addAll(w.a3);
        this.w.a4.addAll(w.a4);
    }

    @Override
    public int getCount() {
        return w.a1.size();
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
            W19a w20;
            if (e == null) {
                LayoutInflater li = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                e = li.inflate(R.layout.a11, vg, false);
                w20 = new W19a();
                w20.a = e.findViewById(R.id.g7);
                w20.b = e.findViewById(R.id.g8);
                w20.c = e.findViewById(R.id.a12);
                w20.d = e.findViewById(R.id.a13);
                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(a);
                if (!sp.getBoolean("autoUpdate", false)) {
                    w20.a.setTextColor(Resources.b(a, R.color.c));
                    w20.b.setTextColor(Resources.b(a, R.color.c));
                    w20.d.setTextColor(Resources.b(a, R.color.c));
                    w20.c.setBackgroundResource(R.drawable.v);
                } else {
                    w20.a.setTextColor(Resources.b(a, R.color.b));
                    w20.b.setTextColor(Resources.b(a, R.color.b));
                    w20.d.setTextColor(Resources.b(a, R.color.b));
                    w20.c.setBackgroundResource(R.drawable.y);
                }


                w20.a.setTypeface(type(Typeface.BOLD));
                w20.b.setTypeface(type(Typeface.NORMAL));
                w20.d.setTypeface(type(Typeface.NORMAL));
                e.setTag(w20);
            } else {
                w20 = (W19a) e.getTag();
            }
            w20.a.setText(w.a1.get(it));
            w20.b.setText(w.a2.get(it));
            w20.c.setImageResource(w.a3.get(it));
            w20.d.setText(w.a4.get(it));
        } catch (IndexOutOfBoundsException ignored) {

        }
        return e;
    }

    private static class W19a {
        TextView a;
        TextView b;
        ImageView c;
        TextView d;
        @Keep
        private W19a() {
        }
    }

}



