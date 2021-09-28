package ru.siberian.huawei.LogSaver.managment.services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CleanerFolder {
    private List<File> list = new ArrayList<>();

    public CleanerFolder() throws InterruptedException {
        searchFileInFolder("D:/XMLTemporary/");
        Thread.sleep(2000);
        for (File file: list){
            deletingFiles(file);
        }
    }

    private void searchFileInFolder(String folderPath) {
        File dir = new File(folderPath); //path указывает на директорию
        for (File file : dir.listFiles()) {
            if (file.isFile())
                list.add(file);
        }
        System.out.println(list.toString());
    }

    private void deletingFiles(File file){
        String fileName = file.getName();
        if (file.delete()) System.out.println("Файл " + fileName + " был удален.");
    }
}
