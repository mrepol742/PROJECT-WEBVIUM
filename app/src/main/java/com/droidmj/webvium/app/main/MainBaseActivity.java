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

package com.droidmj.webvium.app.main;

import android.os.Bundle;

import com.droidmj.webvium.app.BuildConfiguration;
import com.droidmj.webvium.app.base.BaseActivity;

public class MainBaseActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle be) {
        theme(T_MAIN);
        super.onCreate(be);
    }
}
