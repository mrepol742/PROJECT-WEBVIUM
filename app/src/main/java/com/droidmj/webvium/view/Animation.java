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

package com.droidmj.webvium.view;

import android.content.Context;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.droidmj.webvium.annotation.release.Keep;
import com.droidmj.webvium.telemetry.DiagnosticData;

public class Animation {
    @Keep
    private Animation() {
    }

    public static void animate(Context a, int b, final View c) {
        try {
            android.view.animation.Animation d = AnimationUtils.loadAnimation(a, b);
            d.setAnimationListener(new android.view.animation.Animation.AnimationListener() {

                @Override
                public void onAnimationStart(android.view.animation.Animation animation) {
                    c.setLayerType(View.LAYER_TYPE_HARDWARE, null);
                }

                @Override
                public void onAnimationEnd(android.view.animation.Animation animation) {
                    c.setLayerType(View.LAYER_TYPE_NONE, null);
                }

                @Override
                public void onAnimationRepeat(android.view.animation.Animation animation) {
                    c.post(() -> c.setLayerType(View.LAYER_TYPE_NONE, null));
                }
            });
            c.startAnimation(d);
        } catch (Exception ex) {
            DiagnosticData.a(ex);
        }
    }

}