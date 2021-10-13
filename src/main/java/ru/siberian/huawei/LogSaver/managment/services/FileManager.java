package ru.siberian.huawei.LogSaver.managment.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import ru.siberian.huawei.LogSaver.managment.DirScanner;
import ru.siberian.huawei.LogSaver.managment.FTPServersForUpload;
import ru.siberian.huawei.LogSaver.managment.LoadersDataForXML;

import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;
import java.nio.file.Paths;

@Configuration

public class FileManager {
    private final Logger LOGGER = LoggerFactory.getLogger(FileManager.class);
    public static final String PATH = Paths.get("").toAbsolutePath().toString() + "/XMLTemporary/";

    @Scheduled(cron = "0 0 5 * * ?") // SEC MIN HOUR DAY_of_the_month MONTH DAY_of_the_week
    public void FileManager() {
        new CleanerFolder(PATH);

        for (FTPServersForUpload ftp : LoadersDataForXML.ftpServersForUploads){
            FTPManager manager = new FTPManager(
                    ftp.getIp(),
                    ftp.getNameServer(),
                    ftp.getLogin(),
                    ftp.getPassword(),
                    ftp.getPath());
        }

        for (File file : new DirScanner().searchFileInFolder()) {
            String fileName = PATH + file.getName();
            if (fileName.contains(".xml.gz") && !fileName.equalsIgnoreCase("pom.xml")) {
                new ZipExtractor().decompressGzipFile(fileName, fileName.substring(0, fileName.length() - 3));
                file.delete();
            }
        }
    }
}
