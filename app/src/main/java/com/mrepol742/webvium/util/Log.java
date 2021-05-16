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

import android.content.Context;

import com.mrepol742.webvium.annotation.Development;
import com.mrepol742.webvium.annotation.Test;
import com.mrepol742.webvium.annotation.release.Keep;
import com.mrepol742.webvium.content.Package;
import com.mrepol742.webvium.io.StorageDirectory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Test
public class Log {

    private static final String st = " ~~~~~~~~~~~~~ ";
    private static final String sg = " /data/data/com.mrepol742.webvium/files/main.log";

    @Keep
    private Log() {
    }

    public static void a(Exception a1) {
        b(android.util.Log.getStackTraceString(a1), a1.getClass().getName());
    }

    @Development
    public static void a(String a) {
        b(a, "Log");
    }

    @Development
    private static void b(String log, String category) {
        Runnable p = () -> {
            try {
                File logFile = new File(sg);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM dd, yyyy", Locale.US);
                SimpleDateFormat simpleDateFormat0 = new SimpleDateFormat("h:mm:ss", Locale.US);
                if (!logFile.exists()) {
                    if (logFile.createNewFile()) {
                        FileOutputStream fileOutputStream = new FileOutputStream(logFile);
                        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                        outputStreamWriter.append(st)
                                .append(simpleDateFormat.format(new Date()))
                                .append(st)
                                .append("\n")
                                .append(simpleDateFormat0.format(new Date()))
                                .append(" || ")
                                .append(category)
                                .append(" || ")
                                .append(log);
                        outputStreamWriter.close();
                        fileOutputStream.close();
                    }
                } else {
                    SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("MM dd, yyyy", Locale.US);
                    FileWriter fileWriter = new FileWriter(logFile, true);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    if (simpleDateFormat1.format(logFile.lastModified()).equals(simpleDateFormat1.format(new Date()))) {
                        bufferedWriter.write("\n" +
                                simpleDateFormat0.format(new Date()) +
                                " || " +
                                category +
                                " || " +
                                log);
                    } else {
                        bufferedWriter.write("\n\n\n" +
                                st +
                                simpleDateFormat.format(new Date()) +
                                st +
                                "\n" +
                                simpleDateFormat0.format(new Date()) +
                                " || " +
                                category +
                                " || " +
                                log);
                    }
                    bufferedWriter.close();
                    fileWriter.close();
                }
            } catch (Exception ex) {
                android.util.Log.d(Package.b(), log);
            }
        };
        new Thread(p).start();
    }
}