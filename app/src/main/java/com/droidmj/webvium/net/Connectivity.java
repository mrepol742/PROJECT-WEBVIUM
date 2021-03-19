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

package com.droidmj.webvium.net;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;

import com.droidmj.webvium.annotation.Development;
import com.droidmj.webvium.annotation.Unused;
import com.droidmj.webvium.annotation.release.Keep;

public class Connectivity {
    @Keep
    private Connectivity() {
    }

    public static boolean isThereAnyInternetConnection(Context a) {
        ConnectivityManager cm = (ConnectivityManager) a.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= 23) {
            NetworkCapabilities nc = cm.getNetworkCapabilities(cm.getActiveNetwork());

            if (Build.VERSION.SDK_INT >= 26) {
                return !e(nc);
            }
            return !d(nc);
        }
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni != null) {
            return ni.getType() != ConnectivityManager.TYPE_WIFI && ni.getType() != ConnectivityManager.TYPE_MOBILE && ni.getType() != ConnectivityManager.TYPE_ETHERNET && ni.getType() != ConnectivityManager.TYPE_WIMAX;
        }
        return true;
    }

    public static boolean isAirplaneMode(Context a) {
        return Settings.Global.getInt(a.getContentResolver(), Settings.Global.AIRPLANE_MODE_ON, 0) == 0;
    }

    @SuppressWarnings("EmptyMethod")
    @Development
    public static void c() {
      /*   ConnectivityManager cm = (ConnectivityManager) a.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
         NetworkCapabilities nc = cm.getNetworkCapabilities(cm.getActiveNetwork());
         String[] bn = new String[]{};
         if (nc != null) {
               bn[0] = nc.hasTransport(NetworkCapabilities.TRANSPORT_VPN);
               bn[1] = nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR);
               bn[2] = nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI);
               bn[3] = nc.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET);
               bn[4] = nc.hasTransport(NetworkCapabilities.NET_CAPABILITY_CAPTIVE_PORTAL);
               bn[5] = nc.hasTransport(NetworkCapabilities.NET_CAPABILITY_CBS);
               bn[6] = nc.hasTransport(NetworkCapabilities.NET_CAPABILITY_DUN);
               bn[7] = nc.hasTransport(NetworkCapabilities.NET_CAPABILITY_EIMS);
               bn[8] = nc.hasTransport(NetworkCapabilities.NET_CAPABILITY_FOREGROUND);
               bn[9] = nc.hasTransport(NetworkCapabilities.NET_CAPABILITY_FOTA);
               bn[10] = nc.hasTransport(NetworkCapabilities.NET_CAPABILITY_IA);
               bn[11] = nc.hasTransport(NetworkCapabilities.NET_CAPABILITY_IMS);
               bn[12] = nc.hasTransport(NetworkCapabilities.NET_CAPABILITY_INTERNET);
               bn[13] = nc.hasTransport(NetworkCapabilities.NET_CAPABILITY_MCX);
               bn[14] = nc.hasTransport(NetworkCapabilities.NET_CAPABILITY_MMS);
               bn[15] = nc.hasTransport(NetworkCapabilities.NET_CAPABILITY_NOT_CONGESTED);
               bn[16] = nc.hasTransport(NetworkCapabilities.NET_CAPABILITY_NOT_METERED);
               bn[17] = nc.hasTransport(NetworkCapabilities.NET_CAPABILITY_NOT_RESTRICTED);
               bn[18] = nc.hasTransport(NetworkCapabilities.NET_CAPABILITY_NOT_ROAMING);
               bn[19] = nc.hasTransport(NetworkCapabilities.NET_CAPABILITY_NOT_SUSPENDED);
          


        }
        return bn;*/
    }

    private static boolean d(NetworkCapabilities nc) {
        if (nc != null) {
            return nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || nc.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) || nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI);
        }
        return false;
    }

    @TargetApi(26)
    private static boolean e(NetworkCapabilities nc) {
        if (nc != null) {
            return nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || nc.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) || nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI_AWARE);
        }
        return false;
    }

    public static boolean isRestrictBackground(Context aq) {
        ConnectivityManager cm = (ConnectivityManager) aq.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= 24) {
            int a = cm.getRestrictBackgroundStatus();
            if (a == ConnectivityManager.RESTRICT_BACKGROUND_STATUS_DISABLED || a == ConnectivityManager.RESTRICT_BACKGROUND_STATUS_WHITELISTED) {
                return false;
            } else if (a == ConnectivityManager.RESTRICT_BACKGROUND_STATUS_ENABLED) {
                return true;
            }
        }
        return true;
    }

    @Unused
    public static boolean isMetered(Context aq) {
        ConnectivityManager cm = (ConnectivityManager) aq.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.isActiveNetworkMetered();
    }
}