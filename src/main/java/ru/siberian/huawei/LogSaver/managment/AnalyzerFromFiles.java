package ru.siberian.huawei.LogSaver.managment;

import ru.siberian.huawei.LogSaver.entity.Messages;
import ru.siberian.huawei.LogSaver.external.ListOfExceptions;

import java.util.List;

import static ru.siberian.huawei.LogSaver.LogSaverApplication.repos;

public class AnalyzerFromFiles {
    private List<String> exceptions;
    private String cityName;

    public AnalyzerFromFiles(String fileName) {
        exceptions = new ListOfExceptions().getCommandsExceptions();
        cityName = fileName.substring(fileName.length()-7, fileName.length()-4);
    }
    public void checking(String line){
        int count = 0;
        boolean check = false;
        while (count < exceptions.size()){
            String compare = exceptions.get(count);
            if (line.contains(compare)) {
                check = true;
            }
            count++;
        }

        if (!check) {
            String[] parts = line.split("\\t");
            repos.save(new Messages(cityName,
                    new MyDate().convertingStringToDate(parts[0] + " " + parts[1]),
                    parts[2], parts[4], parts[5]));
        }
    }
}
