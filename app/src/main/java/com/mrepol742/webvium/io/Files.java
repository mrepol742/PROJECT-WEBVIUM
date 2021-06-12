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

package com.mrepol742.webvium.io;

import com.mrepol742.webvium.annotation.Keep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;

@SuppressWarnings("ALL")
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
            return a.mkdir();
        }
        return false;
    }

    public static void createNewFolder(String a) {
       Runnable runnable = () -> createNewFolder(new File(a));
      new Thread(runnable).start();
    }

    public static void delete(String a) {
        Runnable runnable = () -> delete(new File(a));
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

    public static void write(String location, String data, boolean readOnly) {
        Runnable runnable = () -> write(new File(location), data, readOnly);
        new Thread(runnable).start();
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

    public static void copy(String sg, String sg0, boolean readOnly) {
        Runnable re = () -> copy(new File(sg), new File(sg0), readOnly);
        new Thread(re).start();
    }
}