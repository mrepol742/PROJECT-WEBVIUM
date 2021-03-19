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

package com.droidmj.webvium.app;

import android.webkit.GeolocationPermissions;

public class GeolocationDataModel {
    public final String a;
    public final GeolocationPermissions.Callback b;

    public GeolocationDataModel(String a, GeolocationPermissions.Callback b) {
        this.a = a;
        this.b = b;
    }
}
