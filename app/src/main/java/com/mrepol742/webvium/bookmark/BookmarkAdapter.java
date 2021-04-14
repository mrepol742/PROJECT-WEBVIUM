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

package com.mrepol742.webvium.bookmark;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mrepol742.webvium.R;
import com.mrepol742.webvium.annotation.release.Keep;
import com.mrepol742.webvium.app.main.MainBaseAdapter;
import com.mrepol742.webvium.content.Resources;

import java.util.ArrayList;
import java.util.Random;

public class BookmarkAdapter extends MainBaseAdapter {
    private static final int[] background = {
            R.drawable.g3,
            R.drawable.g4,
            R.drawable.g5,
            R.drawable.g6
    };
    private final Context a;
    private final ArrayList<String> b;
    private final Random rm;


    public BookmarkAdapter(Context ct, ArrayList<String> al) {
        super(ct);
        a = ct;
        b = al;
        rm = new Random();
    }

    public void a(ArrayList<String> a1) {
        b.clear();
        b.addAll(a1);
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
                w20.tv.setTextColor(Resources.b(a, R.color.b));
                w20.tv.setCompoundDrawablesWithIntrinsicBounds(null, Resources.a(a, R.drawable.a9), null, null);
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



