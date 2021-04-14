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

package com.mrepol742.webvium.download;

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
import com.mrepol742.webvium.app.NoSuchItemToGet;
import com.mrepol742.webvium.app.main.MainBaseAdapter;
import com.mrepol742.webvium.content.Resources;

import java.util.ArrayList;

public class DownloadAdapter extends MainBaseAdapter {
    private final Context a;
    private final ArrayList<DownloadArrayDataModel> downloadArrayDataModel;

    public DownloadAdapter(Context ct, ArrayList<DownloadArrayDataModel> downloadArrayDataModel) {
        super(ct);
        this.downloadArrayDataModel = downloadArrayDataModel;
        a = ct;
    }

    public void a(ArrayList<DownloadArrayDataModel> w) {
        synchronized (this.downloadArrayDataModel) {
            downloadArrayDataModel.clear();
            downloadArrayDataModel.addAll(w);
        }
    }

    public DownloadArrayDataModel b(int i) throws NoSuchItemToGet {
        DownloadArrayDataModel sync = (DownloadArrayDataModel) getItem(i);
        if (sync == null) {
            throw new NoSuchItemToGet();
        }
        return sync;
    }

    @Override
    public int getCount() {
        synchronized (this.downloadArrayDataModel) {
            return this.downloadArrayDataModel.size();
        }
    }

    @Override
    public Object getItem(int it) {
        synchronized (this.downloadArrayDataModel) {
            return this.downloadArrayDataModel.get(it);
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
                w20.e = e.findViewById(R.id.b13);
                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(a);
                if (!sp.getBoolean("autoUpdate", false)) {
                    w20.a.setTextColor(Resources.b(a, R.color.c));
                    w20.b.setTextColor(Resources.b(a, R.color.c));
                    w20.d.setTextColor(Resources.b(a, R.color.c));
                    w20.e.setTextColor(Resources.b(a, R.color.c));
                    w20.c.setBackgroundResource(R.drawable.v);
                } else {
                    w20.a.setTextColor(Resources.b(a, R.color.b));
                    w20.b.setTextColor(Resources.b(a, R.color.b));
                    w20.d.setTextColor(Resources.b(a, R.color.b));
                    w20.e.setTextColor(Resources.b(a, R.color.b));
                    w20.c.setBackgroundResource(R.drawable.y);
                }
                w20.a.setTypeface(type(Typeface.BOLD));
                w20.b.setTypeface(type(Typeface.NORMAL));
                w20.d.setTypeface(type(Typeface.NORMAL));
                w20.e.setTypeface(type(Typeface.NORMAL));
                e.setTag(w20);
            } else {
                w20 = (W12a) e.getTag();
            }
            w20.a.setText(b(it).b);
            w20.b.setText(b(it).d);
            w20.c.setImageResource(b(it).e4);
            w20.d.setText(b(it).f);
            w20.e.setText(b(it).g);
        } catch (IndexOutOfBoundsException | NoSuchItemToGet ignored) {

        }
        return e;
    }

    private static class W12a {
        TextView a;
        TextView b;
        ImageView c;
        TextView d;
        TextView e;
        @Keep
        private W12a() {
        }
    }

}



