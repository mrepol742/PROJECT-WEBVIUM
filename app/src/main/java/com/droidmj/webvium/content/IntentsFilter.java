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

package com.droidmj.webvium.content;

import android.content.IntentFilter;

import com.droidmj.webvium.app.BuildConfiguration;
import com.droidmj.webvium.telemetry.DiagnosticData;

public class IntentsFilter extends IntentFilter {
    public IntentsFilter() {
    }

    public IntentsFilter(String ac) {
        super(ac);
        if (BuildConfiguration.Application.isDevelopment) {
            DiagnosticData.a(ac);
        }
    }

    public void act(String sg) {
        addAction(sg);
        if (BuildConfiguration.Application.isDevelopment) {
            DiagnosticData.a(sg);
        }
    }

    public void cat(String sg) {
        addCategory(sg);
        if (BuildConfiguration.Application.isDevelopment) {
            DiagnosticData.a(sg);
        }
    }

}
