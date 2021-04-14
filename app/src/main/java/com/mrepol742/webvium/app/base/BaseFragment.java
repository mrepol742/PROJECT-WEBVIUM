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

package com.mrepol742.webvium.app.base;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

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
            exclusive = getActivity().getSharedPreferences("wv,", 0);
        }
        return exclusive.getString(sg, "");
    }

    public int a223(String sg) {
        if (exclusive == null) {
            exclusive = getActivity().getSharedPreferences("wv,", 0);
        }
        return exclusive.getInt(sg, 0);
    }

    public boolean a224(String sg, boolean def) {
        if (exclusive == null) {
            exclusive = getActivity().getSharedPreferences("wv,", 0);
        }
        return exclusive.getBoolean(sg, def);
    }

    public void a225(String sg, boolean def) {
        if (exclusive == null) {
            exclusive = getActivity().getSharedPreferences("wv,", 0);
        }
        SharedPreferences.Editor e = exclusive.edit();
        e.putBoolean(sg, def);
        e.apply();
    }
}
