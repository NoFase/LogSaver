package ru.siberian.huawei.LogSaver.managment.services;


import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class FTPManager {
    private final Logger LOGGER = LoggerFactory.getLogger(FTPManager.class);

    private FTPClient ftpClient;
    private String remoteFileName;
    private File downloadFile;

    private int port = 21;
    private String serverIp;
    private String serverName;
    private String userName;
    private String pass;
    private String path;


    public FTPManager(String serverIp, String serverName, String userName, String pass, String path) {
        this.serverIp = serverIp;
        this.serverName = serverName;
        this.userName = userName;
        this.pass = pass;
        this.path = path;

        connectToFTP();
        listFilesFromFTP();
        uploadFromFTP();
    }

    public String getRemoteFileName() {
        return remoteFileName;
    }

    public File getDownloadFile() {
        return downloadFile;
    }

    public void uploadFromFTP() {

        if (remoteFileName != null) {
            File downloadFile = new File("/XMLTemporary/" + remoteFileName);
            try (OutputStream os = new BufferedOutputStream(new FileOutputStream(downloadFile))){
                boolean status = ftpClient.retrieveFile(remoteFileName, os);
                LOGGER.info("status = " + status);
                LOGGER.info("reply = " + ftpClient.getReplyString());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    LOGGER.info("We disconnected from server: " + serverIp);
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void download() throws IOException {
//    private void download(String fileURL, String destinationDirectory,String name) throws IOException {
        String fileURL = remoteFileName;
        String destinationDirectory = "/XMLTemporary/";
        // File name that is being downloaded
        String downloadedFileName = remoteFileName;
        // Open connection to the file
        URL url = new URL(fileURL);

        InputStream is = url.openStream();
        // Stream to the destionation file
        FileOutputStream fos = new FileOutputStream(destinationDirectory + "/" + downloadedFileName);
        BufferedOutputStream bos = new BufferedOutputStream(fos );

        // Read bytes from URL to the local file
        byte[] buffer = new byte[4096];
        int bytesRead = 0;

        while ((bytesRead = is.read(buffer)) != -1) {
            bos.write(buffer, 0, bytesRead);
        }

        bos.flush();
        // Close destination stream
        bos.close();
        // Close URL stream
        is.close();
    }


    public void connectToFTP(){
        ftpClient = new FTPClient();
        try {
            ftpClient.connect(serverIp, port);
            showServerReply(ftpClient);
            int replyCode = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)){
                LOGGER.info("Operation failed. Server " + serverIp + " reply code: " + replyCode);
                return;
            }
            boolean success = ftpClient.login(userName, pass);
            showServerReply(ftpClient);
            if (!success){
                LOGGER.info("Could not login to the server: " + serverIp);
                return;
            }

            success = ftpClient.changeWorkingDirectory(path);
            showServerReply(ftpClient);
            if(!success) {
                LOGGER.info("Some problem with changing directory on FTP server: " + serverIp);
            }
            //Эти настройки нужны, чтобы сменить тип данных на удаленном FTP сервере с текстовых на битовые
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.setFileTransferMode(FTP.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();

        } catch (IOException e) {
            LOGGER.info(e.toString());
        }
    }

    private void listFilesFromFTP(){
        try {
            FTPFile[] files = ftpClient.listFiles();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            for (FTPFile file : files){
                String details = file.getName();
                if (details.contains(serverName)) {
                    remoteFileName = details;
                    details += "\t\t" + file.getSize();
                    details += "\t\t" + dateFormat.format(file.getTimestamp().getTime());
                    LOGGER.info("Find file: " + details);
                }
            }
        } catch (IOException e){
            LOGGER.info(e.toString());
        }
    }

    public void disconnect(){
        try {
            ftpClient.logout();
            ftpClient.disconnect();
        } catch (IOException e) {
            LOGGER.info("Any problem with disconnect from server: " + serverIp);
        }
    }


    private void showServerReply(FTPClient ftpClient){
        String[] replies = ftpClient.getReplyStrings();
        if (replies != null && replies.length > 0){
            for (String aReply : replies){
                LOGGER.info("SERVER: " + serverIp + " : " + aReply);
            }
        }
    }
}
