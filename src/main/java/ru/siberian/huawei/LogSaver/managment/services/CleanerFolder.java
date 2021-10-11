package ru.siberian.huawei.LogSaver.managment.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
/*
    Подчистка файлов в папке в которую ежедневно будут выкачиваться бэкапы с коммутатаорв
 */
public class CleanerFolder {
    private final Logger LOGGER = LoggerFactory.getLogger(CleanerFolder.class);
    private List<File> list = new ArrayList<>();


    public CleanerFolder() {
        searchFileInFolder("/XMLTemporary/");
        if (list.size() > 0) {
//            list.stream().forEach(s -> deletingFiles(s));
            for (File file : list) {
                if (file.getName().contains(".xml")
                        || file.getName().contains(".gz")
                        && !file.getName().equalsIgnoreCase("pom.xml"))
                    file.delete();
            }
        }
    }
//  для частных случаев удаления файлов
    public CleanerFolder(File file) {
        deletingFiles(file);
    }

    /*
        поиск всех файлов в указанной папке
         */
    private void searchFileInFolder(String folderPath) {
        File dir = new File(folderPath); //path указывает на директорию
        for (File file : dir.listFiles()) {
            if (file.isFile())
                list.add(file);
        }
    }

    /*
    удаление указанного файла
     */
    private void deletingFiles(File file){
        String fileName = file.getName();
        if (file.delete()) LOGGER.info("Файл " + fileName + " был удален.");
        else LOGGER.info("Не удалось найти файл: " + fileName);
    }
}
