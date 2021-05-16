/*
 *
 * Copyright (c) 2021 Melvin Jones Repol (mrepol742.github.io). All rights reserved.
 *
 * License under the GNU General Public License, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.gnu.org/licenses/gpl-3.0.en.html
 *
 * Unless required by the applicable law or agreed in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mrepol742.webvium.view;

import android.content.Context;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.mrepol742.webvium.annotation.release.Keep;

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
            ex.printStackTrace();
        }
    }

}