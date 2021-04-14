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
