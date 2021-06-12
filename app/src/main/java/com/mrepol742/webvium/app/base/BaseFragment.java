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

package com.mrepol742.webvium.app.base;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

@SuppressWarnings("deprecation")
public class BaseFragment extends Fragment {

    private SharedPreferences sharedPreferences;
    private SharedPreferences exclusive;

    public SharedPreferences a221() {
        if (sharedPreferences == null) {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        }
        return sharedPreferences;
    }

    public String a222(String sg) {
        if (exclusive == null) {
            exclusive = getActivity().getSharedPreferences("wv", 0);
        }
        return exclusive.getString(sg, "");
    }

    public int a223(String sg) {
        if (exclusive == null) {
            exclusive = getActivity().getSharedPreferences("wv", 0);
        }
        return exclusive.getInt(sg, 0);
    }

    public boolean a224(String sg, boolean def) {
        if (exclusive == null) {
            exclusive = getActivity().getSharedPreferences("wv", 0);
        }
        return exclusive.getBoolean(sg, def);
    }

    public void a225(String sg, boolean def) {
        if (exclusive == null) {
            exclusive = getActivity().getSharedPreferences("wv", 0);
        }
        SharedPreferences.Editor e = exclusive.edit();
        e.putBoolean(sg, def);
        e.apply();
    }
}
