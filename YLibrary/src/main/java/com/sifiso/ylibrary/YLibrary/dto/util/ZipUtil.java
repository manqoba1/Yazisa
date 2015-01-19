package com.sifiso.ylibrary.YLibrary.dto.util;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;

/**
 * Created by aubreyM on 2014/08/10.
 */
public class ZipUtil {
    static final int BUFFER = 2048;

    public static boolean unzip(File zippedFile, File unpackedFile) {
        Log.d("ZipU", "staring unzip");
        try {
            BufferedOutputStream dest = null;
            FileInputStream fis = new
                    FileInputStream(zippedFile);
            ZipInputStream zis = new
                    ZipInputStream(new BufferedInputStream(fis));
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                System.out.println("-----------------------Extracting: " + entry);
                int count;
                byte data[] = new byte[BUFFER];
                // write the files to the disk
                FileOutputStream fos = new
                        FileOutputStream(unpackedFile);
                dest = new
                        BufferedOutputStream(fos, BUFFER);
                while ((count = zis.read(data, 0, BUFFER))
                        != -1) {
                    dest.write(data, 0, count);
                }
                dest.flush();
                dest.close();
            }
            zis.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static String unpack(File zippedFile, File unpackedFile) {
        long start = System.currentTimeMillis();
        FileInputStream is;
        ZipInputStream zis;
        String content = null;
        try {
            is = new FileInputStream(zippedFile);
            zis = new ZipInputStream(new BufferedInputStream(is));
            ZipEntry ze;
            byte[] buffer = new byte[1024];
            int count;

            while ((ze = zis.getNextEntry()) != null) {
                FileOutputStream fout = new FileOutputStream(unpackedFile);
                while ((count = zis.read(buffer)) != -1) {
                    fout.write(buffer, 0, count);
                }

                fout.close();
                zis.closeEntry();
            }
            zis.close();
            content = new Scanner(unpackedFile).useDelimiter("\\A").next();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        long end = System.currentTimeMillis();
        System.out.println("###----> Unpack zip zippedFile, elapsed ms: " + (end - start));
        return content;
    }

    public static String unzipedString(ByteBuffer bb) throws ZipException {
        long start = System.currentTimeMillis();
        ZipInputStream zip;
        byte[] zippedBytes = bb.array();
        byte[] unZippedBytes = new byte[BUFFER];
        System.out.println("###----> pack zip zippedFile, capacity: " + bb.capacity());
        StringBuilder string = new StringBuilder();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bb.array());
        int count = 0;
        GZIPInputStream gzipI = null;
        try {
            gzipI = new GZIPInputStream(inputStream, BUFFER);

            while ((count = gzipI.read(unZippedBytes)) != -1) {
                string.append(new String(unZippedBytes, 0, count));
            }

            System.out.println("###----> Unpack zip zippedFile, Length: " + string.length());
        } catch (Exception e) {
            Log.e(LOG, "######## Unable to unpack the zip file", e);
            throw new ZipException();
        } finally {
            try {
                gzipI.close();
                inputStream.close();
            } catch (IOException e) {
                Log.e(LOG, "######## I/O InputStream problem ", e);
            }
        }
        long end = System.currentTimeMillis();
        Log.w(LOG, "######## The time taken to unzip is in ms: " + getElapsed(start, end));
        if (string.equals(null)) throw new ZipException();
        return string.toString();
    }

    private static double getElapsed(long start, long end) {
        BigDecimal d = new BigDecimal(end - start).divide(new BigDecimal(1000));
        return d.doubleValue();
    }

    public static String LOG = ZipUtil.class.getSimpleName();
}
