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

package com.mrepol742.webvium.app.main;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.mrepol742.webvium.util.cache.FontCache;

public class MainBaseAdapter extends BaseAdapter implements View.OnTouchListener, View.OnDragListener {
    private final FontCache U7;

    public MainBaseAdapter(Context ct) {
        U7 = FontCache.getInstance(ct.getApplicationContext());
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

                return true;
            case DragEvent.ACTION_DRAG_ENTERED:
                return true;
            case DragEvent.ACTION_DRAG_EXITED:
                return true;
            case DragEvent.ACTION_DROP:

                return true;
            case DragEvent.ACTION_DRAG_ENDED:
                return true;
            default:
                return false;
        }
    }


    public Typeface type(int i) {
        return U7.a(i);
    }
}