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

import com.mrepol742.webvium.annotation.Keep;
import com.mrepol742.webvium.app.UnsupportedActions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Zip {
    @Keep
    private Zip() {
    }

    public static boolean compress(String filePath, String i) {
        try {
            File file = new File(filePath);
            FileOutputStream fos = new FileOutputStream(i);
            ZipOutputStream zos = new ZipOutputStream(fos);
            zos.putNextEntry(new ZipEntry(file.getName()));
            byte[] bytes = getBytes(file);
            zos.write(bytes, 0, bytes.length);
            zos.closeEntry();
            zos.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private static byte[] getBytes(File file) throws UnsupportedActions, IOException {
        try (RandomAccessFile f = new RandomAccessFile(file, "r")) {
            long longLength = f.length();
            int length = (int) longLength;
            if (length != longLength)
                throw new UnsupportedActions();
            byte[] data = new byte[length];
            f.readFully(data);
            return data;
        }
    }

    public static boolean decompress(ZipInputStream zos, File file) {
        try {
            byte[] buffer = new byte[1024];
            ZipEntry ze = zos.getNextEntry();
            while (ze != null) {
                FileOutputStream fos = new FileOutputStream(file);
                int len;
                while ((len = zos.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                ze = zos.getNextEntry();
            }
            zos.closeEntry();
            zos.close();
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static boolean decompress(String filePath, String i) throws FileNotFoundException {
        return decompress(new ZipInputStream(new FileInputStream(filePath)), new File(i));
    }

    public static boolean decompress(InputStream is, String path) {
        return decompress(new ZipInputStream(is), new File(path));
    }
}