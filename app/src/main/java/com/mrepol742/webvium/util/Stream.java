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

package com.mrepol742.webvium.util;

import android.net.Uri;

import com.mrepol742.webvium.annotation.Keep;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class Stream {
    @Keep
    private Stream() {
    }

    public static String a(String url, String problem, String main) {
        try {
            Uri b = Uri.parse(url);
            InetAddress c = InetAddress.getByName(b.getHost());
            return String.format(main, c.getHostName(),
                    c.getHostAddress(),
                    c.hashCode(),
                    c.getCanonicalHostName(),
                    c.isAnyLocalAddress(),
                    c.isLinkLocalAddress(),
                    c.isLoopbackAddress(),
                    c.isMCGlobal(),
                    c.isMCLinkLocal(),
                    c.isMCNodeLocal(),
                    c.isMCOrgLocal(),
                    c.isMCSiteLocal(),
                    c.isMulticastAddress());
        } catch (Exception ex) {
            ex.printStackTrace();
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
            ex.printStackTrace();
        }
        return problem;
    }

    public static String c(String url, String problem) {
        try {
            InetAddress c = InetAddress.getByName(Uri.parse(url).getHost());
            return c.getHostAddress();
        } catch (Exception ex) {
            ex.printStackTrace();
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
            ex.printStackTrace();
        }
        return problem;
    }

    public static void e(String a) {
        try {
            new URL(a).openConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
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
            ex.printStackTrace();
        }
        return problem;
    }

    public static int g(String url) {
        try {
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
                nfe.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public static String h(String problem) {
        try {
            InetAddress ia = InetAddress.getLocalHost();
            return ia.getHostName();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return problem;
    }

    public static int i(String url) {
        try {
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
                nfe.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Integer.parseInt("0");
    }
}