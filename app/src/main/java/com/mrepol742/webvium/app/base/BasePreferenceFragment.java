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

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import com.mrepol742.webvium.app.BuildConfiguration;
import com.mrepol742.webvium.os.StrictMode;

@SuppressWarnings("deprecation")
public class BasePreferenceFragment extends PreferenceFragment {

    private SharedPreferences sp;

    @Override
    public void onCreate(Bundle b1) {
        if (BuildConfiguration.isDevelopment || (getActivity().getSharedPreferences("wv,", 0).getBoolean("webDa", false) && a221().getBoolean("stM12", false))) {
            StrictMode.b();
        }
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
