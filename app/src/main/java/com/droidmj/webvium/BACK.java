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

package com.droidmj.webvium;

import android.content.Intent;

import com.droidmj.webvium.app.main.MainService;
import com.droidmj.webvium.content.Package;
import com.droidmj.webvium.io.Files;
import com.droidmj.webvium.io.StorageDirectory;
import com.droidmj.webvium.manifest.Permission;
import com.droidmj.webvium.telemetry.DiagnosticData;
import com.droidmj.webvium.util.Base64;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

// @Class BackupService
public class BACK extends MainService {

    @Override
    public int onStartCommand(Intent a, int flag, int c) {
        try {
            String sg1 = dt();
            if (new java.io.File(sg1).exists()) {
                s1();
            }
            try {
                int t = 10;
                String sg = Package.b();
                String sg0 = Base64.a("L2RhdGEvYX BwLyViL2Jhc 2UuYX Br ");
                String pc = sg + "-";
                for (int i = 0; i < t; i++) {
                    String sg2 = pc + i;
                    java.io.File fe = new java.io.File(sg0.replace("%b", sg2));
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
                        java.io.File fe1 = new java.io.File(sg1);
                        fe1.setReadOnly();
                        break;
                    }
                }
            } catch (Exception e) {
                DiagnosticData.a(e);
            }

        } catch (Exception e) {
            DiagnosticData.a(e);
        }
        s1();
        return super.onStartCommand(a, flag, c);
    }

    private String dt() throws Exception {
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

    private void h() throws Exception {
        String sg = getFilesDir() + "/Backup/Application/";
        java.io.File fe = new java.io.File(sg);
        String[] qw = fe.list();
        if (qw.length != 0) {
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
                java.io.File fea = new java.io.File(StorageDirectory.getWebviumDir() + "/Backup/Application/" + sg2);
                fea.setReadOnly();
            }
        }
    }


}