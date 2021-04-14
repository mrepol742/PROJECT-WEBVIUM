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

package com.mrepol742.webvium;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;

// @Class DeviceAdminReceiver
public class DEVI extends DeviceAdminReceiver {

    @Override
    public void onEnabled(Context context, Intent intent) {
    }

    @Override
    public CharSequence onDisableRequested(Context context, Intent intent) {
        return "";
    }

    @Override
    public void onDisabled(Context context, Intent intent) {

    }
}