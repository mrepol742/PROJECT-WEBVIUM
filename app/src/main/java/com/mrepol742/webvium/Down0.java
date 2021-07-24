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

package com.mrepol742.webvium;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.mrepol742.webvium.util.AwesomeToast;

import java.util.Objects;

/*
 * @DownloadBroadcastReceiver
 */
public class Down0 extends BroadcastReceiver {

    @Override
    public void onReceive(Context a, Intent b) {
        if (Objects.requireNonNull(b.getAction()).equals("android.intent.action.DOWNLOAD_NOTIFICATION_CLICKED")) {
            AwesomeToast.a(a, "DOWNLOAD_NOTIFICATION_CLICKED", 0);
        } else if (Objects.requireNonNull(b.getAction()).equals("android.intent.action.VIEW_DOWNLOADS")) {
            AwesomeToast.a(a, "VIEW_DOWNLOADS", 0);
        } else if (Objects.requireNonNull(b.getAction()).equals("android.intent.action.DOWNLOAD_COMPLETE")) {
            AwesomeToast.a(a, "DOWNLOAD_COMPLETE", 0);
        }
    }
}
