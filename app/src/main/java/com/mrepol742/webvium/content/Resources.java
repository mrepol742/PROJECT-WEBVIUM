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

package com.mrepol742.webvium.content;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;

import com.mrepol742.webvium.annotation.release.Keep;

public class Resources {
    @Keep
    private Resources() {
    }

    public static Drawable a(Context a, int i) {
        return a.getDrawable(i);
    }

    @SuppressWarnings("deprecation")
    public static int b(Context a, int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            return a.getColor(i);
        }
        return a.getResources().getColor(i);
    }

    public static Bitmap c(Context context, int res) {
        Drawable drawable = a(context, res);
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static Boolean d(Context ct, int i) {
        return ct.getResources().getBoolean(i);
    }

    public static int e(Context ct, int i) {
        return ct.getResources().getInteger(i);
    }


    public static Drawable f(int i, float[] fl, int j) {
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(i);
        shape.setCornerRadii(fl);
        shape.setColor(j);
        return shape;
    }

}