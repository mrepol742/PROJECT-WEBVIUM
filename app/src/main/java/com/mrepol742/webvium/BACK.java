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

package com.mrepol742.webvium;

import android.content.Intent;
import android.content.pm.PackageManager;

import com.mrepol742.webvium.app.main.MainService;
import com.mrepol742.webvium.content.Package;
import com.mrepol742.webvium.io.Files;
import com.mrepol742.webvium.io.StorageDirectory;
import com.mrepol742.webvium.manifest.Permission;
import com.mrepol742.webvium.util.Base64;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Objects;

// @Class BackupService
public class BACK extends MainService {

    @Override
    public int onStartCommand(Intent a, int flag, int c) {
        s1();
        Runnable runnable = () -> {
            try {
                String sg1 = dt();
                if (new java.io.File(sg1).exists()) {
                    s1();
                }
                try {
                    int t = 10;
                    String sg = Package.b();
                    String sg0 = Base64.decode("L2Rhd  GEvYXB   wLyUx      JHMvYm FzZS5hcGs");
                    String pc = sg + "-";
                    for (int i = 0; i < t; i++) {
                        String sg2 = pc + i;
                        java.io.File fe = new java.io.File(String.format(sg0, sg2));
                        if (fe.exists()) {
                            FileInputStream fos = new FileInputStream(fe.toString());
                            OutputStream d = new FileOutputStream(sg1);
                            byte[] e = new byte[1024];
                            int f;
                            while ((f = fos.read(e)) != -1) {
                                d.write(e, 0, f);
                            }
                            d.flush();
                            d.close();
                            fos.close();
                            break;
                        }
                    }
                } catch (Exception e) {
                   e.printStackTrace();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        new Thread(runnable).start();
        s1();
        return super.onStartCommand(a, flag, c);
    }

    private String dt() throws PackageManager.NameNotFoundException {
        if (!Permission.checkOnly(this, Permission.STORAGE)) {
            Files.createNewFolder(getFilesDir() + "/Backup");
            Files.createNewFolder(getFilesDir() + "/Backup/Application");
            return getFilesDir() + "/Backup/Application/Base_" + Package.e(this) + ".apk";
        }
        h();
        Files.createNewFolder(StorageDirectory.getWebviumDir() + "/Backup");
        Files.createNewFolder(StorageDirectory.getWebviumDir() + "/Backup/Application");
        return StorageDirectory.getWebviumDir() + "/Backup/Application/Base_" + Package.e(this) + ".apk";
    }

    private void h() {
        Runnable runnable = () -> {
            try {
                String sg = getFilesDir() + "/Backup/Application/";
                java.io.File fe = new java.io.File(sg);
                String[] qw = fe.list();
                if (Objects.requireNonNull(qw).length != 0) {
                    for (String sg2 : qw) {
                        FileInputStream fos = new FileInputStream(getFilesDir() + "/Backup/Application/" + sg2);
                        OutputStream d = new FileOutputStream(StorageDirectory.getWebviumDir() + "/Backup/Application/" + sg2);
                        byte[] e = new byte[1024];
                        int f;
                        while ((f = fos.read(e)) != -1) {
                            d.write(e, 0, f);
                        }
                        d.flush();
                        d.close();
                        fos.close();
                    }
                }
            } catch (Exception en) {
                en.printStackTrace();
            }
        };
        new Thread(runnable).start();
    }


}