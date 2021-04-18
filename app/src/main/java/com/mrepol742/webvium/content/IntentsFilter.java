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

package com.mrepol742.webvium.content;

import android.content.IntentFilter;

import com.mrepol742.webvium.app.BuildConfiguration;
import com.mrepol742.webvium.telemetry.DiagnosticData;

public class IntentsFilter extends IntentFilter {
    public IntentsFilter() {
    }

    public IntentsFilter(String ac) {
        super(ac);
        if (BuildConfiguration.isDevelopment) {
            DiagnosticData.a(ac);
        }
    }

    public void act(String sg) {
        addAction(sg);
        if (BuildConfiguration.isDevelopment) {
            DiagnosticData.a(sg);
        }
    }

    public void cat(String sg) {
        addCategory(sg);
        if (BuildConfiguration.isDevelopment) {
            DiagnosticData.a(sg);
        }
    }

}
