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

package com.droidmj.webvium.util;

import android.net.Uri;
import android.os.NetworkOnMainThreadException;

import com.droidmj.webvium.annotation.release.Keep;
import com.droidmj.webvium.os.StrictMode;
import com.droidmj.webvium.telemetry.DiagnosticData;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLHandshakeException;

public class Stream {
    @Keep
    private Stream() {
    }

    public static String a(String url, String problem, String main) {
        try {
            Uri b = Uri.parse(url);
            InetAddress c = InetAddress.getByName(b.getHost());
            String d = c.getHostName();
            int e = c.hashCode();
            boolean f = c.isAnyLocalAddress();
            boolean g = c.isLinkLocalAddress();
            boolean h = c.isLoopbackAddress();
            boolean i = c.isMCGlobal();
            boolean j = c.isMCLinkLocal();
            boolean k = c.isMCNodeLocal();
            boolean l = c.isMCOrgLocal();
            boolean m = c.isMCSiteLocal();
            boolean n = c.isMulticastAddress();
            String o = c.getCanonicalHostName();
            String p = c.getHostAddress();
            return main.replace("%a", d)
                    .replace("%b", p)
                    .replace("%c", Integer.toString(e))
                    .replace("%d", o)
                    .replace("%e", Boolean.toString(f))
                    .replace("%f", Boolean.toString(g))
                    .replace("%g", Boolean.toString(h))
                    .replace("%h", Boolean.toString(i))
                    .replace("%i", Boolean.toString(j))
                    .replace("%j", Boolean.toString(k))
                    .replace("%k", Boolean.toString(l))
                    .replace("%l", Boolean.toString(m))
                    .replace("%m", Boolean.toString(n));
        } catch (Exception ex) {
            DiagnosticData.a(ex.getClass().getName());
        }

        return problem;
    }

    // local ip address
// Html.b(Stream.b( getString(R.string.c33), getString(R.string.g26)));
    public static String b(String problem) {
        try {
            InetAddress ia = InetAddress.getLocalHost();
            return ia.getHostAddress();
        } catch (Exception ex) {
            DiagnosticData.a(ex.getClass().getName());
        }
        return problem;
    }

    public static String c(String url, String problem) {
        try {
            StrictMode.a();
            InetAddress c = InetAddress.getByName(Uri.parse(url).getHost());
            return c.getHostAddress();
        } catch (Exception ex) {
            DiagnosticData.a(ex.getClass().getName());
        }
        return problem;
    }

    // BitmapCache.d(url, getString(R.string.c33), getString(R.string.h35), getString(R.string.h36));
    public static String d(String url5, String problem) {
        try {
            StringBuilder sb0 = new StringBuilder();
            URL url = new URL(url5);
            URLConnection uc = url.openConnection();
            Map<String, List<String>> map = uc.getHeaderFields();
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                sb0.append("<b>");
                sb0.append(entry.getKey());
                sb0.append(": ");
                sb0.append("</b>");
                sb0.append(entry.getValue().toString());
                sb0.append("<br>");
            }
            return sb0.toString().replaceAll("\\[", "").replaceAll("]", "");
        } catch (Exception ex) {
            DiagnosticData.a(ex.getClass().getName());
        }
        return problem;
    }

    public static void e(String a) {
        try {
            StrictMode.a();
            new URL(a).openConnection();
        } catch (Exception ex) {
            DiagnosticData.a(ex.getClass().getName());
        }
    }

    public static String f(String url, String problem) {
        try {
            if (url.startsWith("file://") || url.startsWith("content://")) {
                InputStream is = new FileInputStream(url);
                StringBuilder contentBuilder = new StringBuilder();
                Reader rr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(rr);
                String sCurrentLine;
                while ((sCurrentLine = br.readLine()) != null) {
                    contentBuilder.append(sCurrentLine).append("\n");
                }
                br.close();
                is.close();
                rr.close();

                return contentBuilder.toString();
            } else {
                URL u = new URL(url);
                URLConnection is = u.openConnection();
                InputStream inputStream = is.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader c = new BufferedReader(inputStreamReader);
                String tf5;
                StringBuilder cd = new StringBuilder();
                while ((tf5 = c.readLine()) != null) {
                    cd.append(tf5).append("\n");
                }
                c.close();
                inputStreamReader.close();
                inputStream.close();
                return cd.toString();
            }
        } catch (Exception ex) {
            DiagnosticData.a(ex.getClass().getName());
        }
        return problem;
    }

    public static int g(String url) {
        try {
            StrictMode.a();
            URL u = new URL(url);
            URLConnection is = u.openConnection();
            InputStream inputStream = is.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader c = new BufferedReader(inputStreamReader);
            String tf5;
            StringBuilder sb = new StringBuilder();
            while ((tf5 = c.readLine()) != null) {
                sb.append(tf5.trim());
            }
            c.close();
            inputStreamReader.close();
            inputStream.close();
            try {
                return Integer.parseInt(sb.toString());
            } catch (NumberFormatException nfe) {
                DiagnosticData.a(nfe);
            }
        } catch (Exception ex) {
            DiagnosticData.a(ex.getClass().getName());
        }
        return 0;
    }

    public static String h(String problem) {
        try {
            InetAddress ia = InetAddress.getLocalHost();
            return ia.getHostName();
        } catch (Exception ex) {
            DiagnosticData.a(ex.getClass().getName());
        }
        return problem;
    }

    public static int i(String url) {
        try {
            StrictMode.a();
            URL u = new URL(url);
            URLConnection is = u.openConnection();
            InputStream inputStream = is.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader c = new BufferedReader(inputStreamReader);
            String tf5;
            StringBuilder sb = new StringBuilder();
            while ((tf5 = c.readLine()) != null) {
                sb.append(tf5.trim());
            }
            c.close();
            inputStreamReader.close();
            inputStream.close();
            try {
                return Integer.parseInt(sb.toString());
            } catch (NumberFormatException nfe) {
                DiagnosticData.a(nfe);
            }
        } catch (Exception ex) {
            DiagnosticData.a(ex.getClass().getName());
        }
        return Integer.parseInt("742");
    }
}