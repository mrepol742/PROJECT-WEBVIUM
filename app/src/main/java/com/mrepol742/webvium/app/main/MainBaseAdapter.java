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

package com.mrepol742.webvium.app.main;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.LruCache;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.mrepol742.webvium.app.StorageDirectory;

import java.io.File;

public class MainBaseAdapter extends BaseAdapter implements View.OnTouchListener, View.OnDragListener {
    private static final String MAVEN_PRO = "classes";
    private static final int PRIMARY_CACHE = 99;
    private static final int SECONDARY_CACHE = 100;
    private final LruCache<Integer, Typeface> cac;
    private final Typeface main;
    private final SharedPreferences sp;
    private final String PRIMARY_FONT;
    private final String SECONDARY_FONT;

    public MainBaseAdapter(Context ct) {
        cac = new LruCache<>(32);
        sp = PreferenceManager.getDefaultSharedPreferences(ct);
        SECONDARY_FONT = StorageDirectory.Fonts.getSecondaryFont(ct);
        PRIMARY_FONT = StorageDirectory.Fonts.getPrimaryFont(ct);
        main = Typeface.createFromAsset(ct.getAssets(), MAVEN_PRO);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }

    protected void w44a(View v, float x, float y) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.leftMargin = Math.round(x);
        params.topMargin = Math.round(y);
        v.getRootView().setLayoutParams(params);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View view, MotionEvent event) {
        ClipData.Item item = new ClipData.Item(view.getTag().toString());
        String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
        ClipData data = new ClipData(view.getTag().toString(), mimeTypes, item);
        View.DragShadowBuilder ds = new View.DragShadowBuilder(view);
        if (Build.VERSION.SDK_INT >= 24) {
            view.startDragAndDrop(data, ds, view, 0);
        } else {
            view.startDrag(data, ds, view, 0);
        }
        w44a(view, view.getX(), view.getY());
        return true;
    }

    @Override
    public boolean onDrag(View view, DragEvent de) {
        switch (de.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
            case DragEvent.ACTION_DRAG_EXITED:
            case DragEvent.ACTION_DRAG_ENTERED:
            case DragEvent.ACTION_DROP:
            case DragEvent.ACTION_DRAG_ENDED:
                return true;
            default:
                return false;
        }
    }

    public Typeface type(int sg) {
        if (sp.getBoolean("cFNT", false)) {
            File fe = new File(PRIMARY_FONT);
            File fe1 = new File(SECONDARY_FONT);
            if (fe.exists()) {
                Typeface bp0 = cac.get(PRIMARY_CACHE);
                if (bp0 == null) {
                    bp0 = Typeface.createFromFile(fe);
                    cac.put(PRIMARY_CACHE, bp0);
                }
                return bp0;
            }
            if (fe1.exists()) {
                Typeface bp0 = cac.get(SECONDARY_CACHE);
                if (bp0 == null) {
                    bp0 = Typeface.createFromFile(fe);
                    cac.put(SECONDARY_CACHE, bp0);
                }
                return bp0;
            }
        }
        Typeface bp = cac.get(sg);
        if (bp == null) {
            bp = Typeface.create(main, sg);
            cac.put(sg, bp);
        }
        return bp;
    }
}