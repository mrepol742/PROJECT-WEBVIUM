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

package com.mrepol742.webvium.app;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import java.util.Random;

public class Notifications {
    public static final int DEFAULT = 10;
    public static final int FOREGROUND = 20;
    public static final int a = 1;
    public static final int b = 2;
    public static final int c = 3;
    public static final int d = 4;
    public static final int e = 5;
    public static final int f = 6;
    public static final int g = 7;
    public static final int h = 8;
    public static final int i = 9;

    public static Notification.Builder a(Context ct, String ch) {
        if (Build.VERSION.SDK_INT >= 26) {
            return new android.app.Notification.Builder(ct, ch);
        }
        return new android.app.Notification.Builder(ct);
    }

    public static void b(Context ct, Intent cn) {
        if (Build.VERSION.SDK_INT >= 26) {
            ct.startForegroundService(cn);
        } else {
            ct.startService(cn);
        }
    }

    public static int getRandomizeNotificationId(int a) {
        Random random = new Random();
        return a + random.nextInt(BuildConfiguration.NOTIFICATION_DEFAULT_ID_MULTIPLIER);

    }

}