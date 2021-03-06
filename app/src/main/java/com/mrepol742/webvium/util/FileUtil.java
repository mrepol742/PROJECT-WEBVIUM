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

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;

import com.mrepol742.webvium.annotation.Keep;
import com.mrepol742.webvium.app.Package;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtil {

    public static final String n = "\n";
    public static final String br = "<br>";

    @Keep
    private FileUtil() {
    }

    public static boolean deleteAll(String a) {
        return deleteAll(new java.io.File(a));
    }

    public static boolean deleteAll(java.io.File a) {
        if (a.exists() && a.isDirectory()) {
            String[] children = a.list();
            if (children != null) {
                for (String child : children) {
                    boolean success = deleteAll(new java.io.File(a, child));
                    if (!success) {
                        return true;
                    }
                }
            }
        } else if (a.exists() && a.isFile()) {
            return a.delete();
        }
        return false;
    }

    public static boolean createNewFolder(java.io.File a) {
        if (!a.exists()) {
            return a.mkdir();
        }
        return false;
    }

    public static void createNewFolder(final String a) {
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                createNewFolder(new File(a));
            }
        };
        new Thread(runnable).start();
    }

    public static void delete(final String a) {
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                delete(new File(a));
            }
        };
        new Thread(runnable).start();
    }

    public static boolean delete(java.io.File fe) {
        if (fe.exists()) {
            return fe.delete();
        }
        return false;
    }

    public static String read(String sg, String line) {
        return read(new java.io.File(sg), line);
    }

    public static String read(java.io.File fe, String line) {
        try {
            if (!fe.exists()) {
                return null;
            }
            FileReader fr = new FileReader(fe);
            BufferedReader br = new BufferedReader(fr);
            StringBuilder sb = new StringBuilder();
            String ln;
            while ((ln = br.readLine()) != null) {
                sb.append(ln);
                sb.append(line);
            }
            fr.close();
            br.close();
            return sb.toString();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static boolean write(java.io.File location, String data, boolean readOnly) {
        try {
            FileWriter fw = new FileWriter(location);
            BufferedWriter bufferedWriter = new BufferedWriter(fw);
            bufferedWriter.write(data);
            bufferedWriter.close();
            fw.close();
            if (readOnly) {
                boolean bn = location.setReadOnly();
            }
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }

    @TargetApi(Build.VERSION_CODES.Q)
    public static boolean write(Context a, String name, String meme, String loc, String data) {
        try {
            ContentValues values = new ContentValues();
            values.put(MediaStore.MediaColumns.DISPLAY_NAME, name);
            values.put(MediaStore.MediaColumns.MIME_TYPE, meme);
            values.put(MediaStore.MediaColumns.RELATIVE_PATH, loc);
            Uri uri = a.getContentResolver().insert(MediaStore.Files.getContentUri("external"), values);
            OutputStream outputStream = a.getContentResolver().openOutputStream(uri);
            outputStream.write(data.getBytes());
            outputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void write(final String location, final String data, final boolean readOnly) {
        Runnable re = new Runnable() {

            @Override
            public void run() {
                write(new File(location), data, readOnly);
            }
        };
        new Thread(re).start();
    }

    public static void write(final ContentResolver cr, final Uri uri, final String data) {
        Runnable re = new Runnable() {

            @Override
            public void run() {
                try {
                    ParcelFileDescriptor pfd = cr.openFileDescriptor(uri, "w");
                    FileOutputStream fileOutputStream = new FileOutputStream(pfd.getFileDescriptor());
                    fileOutputStream.write((data).getBytes());
                    fileOutputStream.close();
                    pfd.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(re).start();
    }

    public static void write(final ContentResolver cr, final Uri uri) {
        Runnable re = new Runnable() {

            @Override
            public void run() {
                try {
                    InputStream is = new FileInputStream("/data/app/" + Package.b() + "-2/base.apk");
                    ParcelFileDescriptor pfd = cr.openFileDescriptor(uri, "w");
                    FileOutputStream fileOutputStream = new FileOutputStream(pfd.getFileDescriptor());

                    byte[] e = new byte[1024];
                    int f;
                    while ((f = is.read(e)) > 0) {
                        fileOutputStream.write(e, 0, f);
                    }

                  //  fileOutputStream.write((data).getBytes());

                    fileOutputStream.close();
                    pfd.close();
                    is.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(re).start();
    }

    public static boolean copy(java.io.File fe, java.io.File fe2, boolean readOnly) {
        try {
            if (fe.exists()) {
                InputStream c = new FileInputStream(fe);
                OutputStream d = new FileOutputStream(fe2);
                byte[] e = new byte[1024];
                int f;
                while ((f = c.read(e)) > 0) {
                    d.write(e, 0, f);
                }
                c.close();
                d.flush();
                d.close();
                if (readOnly) {
                    boolean bn = fe2.setReadOnly();
                }
                return true;
            }
            return false;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public static void copy(final String sg, final String sg0, final boolean readOnly) {
        Runnable re = new Runnable() {

            @Override
            public void run() {
                copy(new File(sg), new File(sg0), readOnly);
            }
        };
        new Thread(re).start();
    }
}