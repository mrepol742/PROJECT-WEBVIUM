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

package com.mrepol742.webvium.util.cache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.LruCache;

public class BitmapCache {
    private static BitmapCache U6;
    private LruCache<String, Bitmap> cac;

    private BitmapCache() {
        if (cac == null) {
            cac = new LruCache<>(32);
        }
    }

    public static BitmapCache getInstance() {
        if (U6 == null) {
            U6 = new BitmapCache();
        }
        return U6;
    }

    public Bitmap a(String sg) {
        Bitmap bp = cac.get(sg);
        if (bp == null) {
            bp = BitmapFactory.decodeFile(sg);
            cac.put(sg, bp);
        }
        return bp;
    }

    public void b(String sg) {
        cac.put(sg, BitmapFactory.decodeFile(sg));
    }

}
