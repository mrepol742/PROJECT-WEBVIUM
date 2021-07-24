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

package com.mrepol742.webvium.app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;

import com.mrepol742.webvium.annotation.Keep;

public class Resources {
    @Keep
    private Resources() {
    }

    public static Drawable getDrawable(Context a, int i) {
        return a.getDrawable(i);
    }

    @SuppressWarnings("deprecation")
    public static int getColor(Context a, int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            return a.getColor(i);
        }
        return a.getResources().getColor(i);
    }

    public static Bitmap getBitmapFromResource(Context context, int res) {
        Drawable drawable = getDrawable(context, res);
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static Drawable toDrawable(int i, float[] fl, int j) {
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(i);
        shape.setCornerRadii(fl);
        shape.setColor(j);
        return shape;
    }

    public static boolean isColorDark(int color) {
        double darkness = 1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255;
        return !(darkness < 0.5);
    }

}