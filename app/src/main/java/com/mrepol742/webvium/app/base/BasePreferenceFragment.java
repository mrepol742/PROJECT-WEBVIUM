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

package com.mrepol742.webvium.app.base;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

@SuppressWarnings("deprecation")
public class BasePreferenceFragment extends PreferenceFragment {

    private SharedPreferences sp;

    @Override
    public void onCreate(Bundle b1) {
        super.onCreate(b1);
    }


    public void a5(int i) {
        addPreferencesFromResource(i);
    }

    public SharedPreferences a221() {
        if (sp == null) {
            sp = PreferenceManager.getDefaultSharedPreferences(getActivity());

        }
        return sp;
    }
}
