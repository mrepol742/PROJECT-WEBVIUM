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

package com.droidmj.webvium.io;

import com.droidmj.webvium.annotation.release.Keep;
import com.droidmj.webvium.app.BuildConfiguration;
import com.droidmj.webvium.telemetry.DiagnosticData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;

public class Files {

    public static final String n = "\n";
    public static final String br = "<br>";

    @Keep
    private Files() {
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
            if (a.mkdir()) {
                if (BuildConfiguration.isDevelopment) {
                    DiagnosticData.a("MKDIR = " + a);
                }
                return true;
            }
        }
        return false;
    }

    public static void createNewFolder(String a) {
        Thread thread = new Thread(() -> {
            createNewFolder(new java.io.File(a));
        });
        thread.start();
    }

    public static void delete(String a) {
        Thread thread = new Thread(() -> {
            delete(new java.io.File(a));
        });
        thread.start();
    }

    public static boolean delete(java.io.File fe) {
        if (fe.exists()) {
            if (fe.delete()) {
                if (BuildConfiguration.isDevelopment) {
                    DiagnosticData.a("DELETE = " + fe);
                }
                return true;
            }
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
        } catch (Exception ignored) {

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
                if (BuildConfiguration.isDevelopment) {
                    DiagnosticData.a("READ ONLY = " + bn + " FILE = " + location);
                }
            }
            return true;
        } catch (Exception exception) {
            DiagnosticData.a(exception);
        }
        return false;
    }

    public static void write(String location, String data, boolean readOnly) {
        Thread thread = new Thread(() -> {
            write(new java.io.File(location), data, readOnly);
        });
        thread.start();
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
                    if (BuildConfiguration.isDevelopment) {
                        DiagnosticData.a("READ ONLY = " + bn + " FILE = " + fe2);
                    }
                }
                return true;
            }
            return false;
        } catch (Exception exception) {
            DiagnosticData.a(exception);
        }
        return false;
    }

    public static void copy(String sg, String sg0, boolean readOnly) {
        Runnable re = new Runnable() {
            public void run() {
                copy(new File(sg), new File(sg0), readOnly);
            }
        };
        new Thread(re).start();
    }
}