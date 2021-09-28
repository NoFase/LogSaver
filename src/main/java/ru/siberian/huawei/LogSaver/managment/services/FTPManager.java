package ru.siberian.huawei.LogSaver.managment.services;


import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class FTPManager {
    private FTPClient ftpClient;
    private String remoteFileName;

    public void downloadFromFTP() {
//        String fileName = "GExport_SoftX3000_Penza_10.141.85.228_20210923031621.xml.gz";
        if (remoteFileName != null) {
            File downloadFile = new File("/XMLTemporary/" + remoteFileName);
//            File downloadFile = new File(Paths.get("").toAbsolutePath().toString() + "/src/main/trash/" + "fileoutput.gz");
//            try (OutputStream os = new FileOutputStream(remoteFileName)) {
            try (OutputStream os = new BufferedOutputStream(new FileOutputStream(downloadFile))){
                boolean status = ftpClient.retrieveFile(remoteFileName, os);
                System.out.println("status = " + status);
                System.out.println("reply = " + ftpClient.getReplyString());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void connectToFTP(){
        String serverName = "10.128.153.150";
        int port = 21;
        String userName = "backup_user";
        String pass = "4gyN12Szt@";
        ftpClient = new FTPClient();
        try {
            ftpClient.connect(serverName, port);
            showServerReply(ftpClient);
            int replyCode = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)){
                System.out.println("Operation failed. Server reply code: " + replyCode);
                return;
            }
            boolean success = ftpClient.login(userName, pass);
            showServerReply(ftpClient);
            if (!success){
                System.out.println("Could not login to the server");
                return;
            }

            success = ftpClient.changeWorkingDirectory("/export/home/sysm/opt/oss/server/var/fileint/cm/GExport");
            showServerReply(ftpClient);
            if(success) {
                System.out.println("LOGGED IN SERVER");
            } else {
                System.out.println("somthing wrong");
            }
            listFilesFromFTP();
            downloadFromFTP();
            disconnect();

        } catch (IOException e) {

        }
    }

    private void listFilesFromFTP() throws IOException{
        FTPFile[] files = ftpClient.listFiles();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (FTPFile file : files){
            String details = file.getName();
            if (details.contains("SoftX3000_Penza")) {
                remoteFileName = details;
//            if (file.isDirectory()){
//                details = "[" + details + "]";
//            }
                details += "\t\t" + file.getSize();
                details += "\t\t" + dateFormat.format(file.getTimestamp().getTime());
                System.out.println(details);
            }
        }
    }

    public void disconnect(){
        try {
            ftpClient.logout();
            ftpClient.disconnect();
            System.out.println("Disconnect from FTP.");
        } catch (IOException e) {}
    }


    private void showServerReply(FTPClient ftpClient){
        String[] replies = ftpClient.getReplyStrings();
        if (replies != null && replies.length > 0){
            for (String aReply : replies){
                System.out.println("SERVER: " + aReply);
            }
        }
    }
}
