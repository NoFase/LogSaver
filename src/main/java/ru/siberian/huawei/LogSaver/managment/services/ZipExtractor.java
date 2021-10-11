package ru.siberian.huawei.LogSaver.managment.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.util.zip.GZIPInputStream;

public class ZipExtractor {

    private final Logger LOGGER = LoggerFactory.getLogger(ZipExtractor.class);

    public void decompressGzipFile(String gzipFile, String newFile) {
        if (gzipFile.contains(".gz")) {
            try {
                FileInputStream fis = new FileInputStream(gzipFile);
                GZIPInputStream gis = new GZIPInputStream(fis);
                FileOutputStream fos = new FileOutputStream(newFile);
                byte[] buffer = new byte[1024];
                int len;
                while ((len = gis.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                gis.close();
            } catch (IOException e) {
                LOGGER.info("Extracted file: " + gzipFile);
                LOGGER.info(e.toString());
            }
        }
    }
}
