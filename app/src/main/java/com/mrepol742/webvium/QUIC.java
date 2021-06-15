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

package com.mrepol742.webvium;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;

import com.mrepol742.webvium.app.Notifications;
import com.mrepol742.webvium.app.main.MainNotification;
import com.mrepol742.webvium.app.main.MainService;
import com.mrepol742.webvium.content.Resources;

// @Class QuickSearch
public class QUIC extends MainService {

    @Override
    public int onStartCommand(Intent a1, int c555, int c) {
        MainNotification.b(this, getString(R.string.l33), getString(R.string.y18));
        android.app.Notification.Builder m = Notifications.a(this, getString(R.string.l33));
        m.setSmallIcon(R.drawable.a18);
        m.setContentTitle(getString(R.string.l33));
        m.setContentText(getString(R.string.n39));
        m.setOngoing(true);
        m.setColor(Resources.getColor(this, R.color.a));
        m.setAutoCancel(false);
        Intent j11 = new Intent(this, SEAR.class);

        PendingIntent k567 = PendingIntent.getActivity(this, 0, j11, PendingIntent.FLAG_UPDATE_CURRENT);
        m.setContentIntent(k567);
        if (Build.VERSION.SDK_INT <= 26) {
            m.setPriority(android.app.Notification.PRIORITY_LOW);
        }
        m.setVisibility(android.app.Notification.VISIBILITY_PUBLIC);
        m.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.b));
        NotificationManager nmc = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nmc.notify(Notifications.d, m.build());
        s1();
        return super.onStartCommand(a1, c555, c);
    }

}