package ru.siberian.huawei.LogSaver.managment;

import ru.siberian.huawei.LogSaver.external.ListOfExceptions;

import java.util.List;

public class AnalyzerFromFiles {
    private List<String> exceptions;

    public AnalyzerFromFiles(String fileName, String line) {
        exceptions = new ListOfExceptions().getCommandsExceptions();

        String sityName = fileName.substring(fileName.length()-7);
        System.out.println(sityName);
        String[] parts = line.split("\\t");
        for (String l: parts) System.out.print(l + "#");
    }
    public void checking(String fileName, String line){
        String sityName = fileName.substring(fileName.length()-7);
        int count = 0;
        while (count < exceptions.size()){
            String compare = exceptions.get(count);
            if (line.contains(compare)) ;
        }
        String[] parts = line.split("\\t");
    }


}
