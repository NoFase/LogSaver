package ru.siberian.huawei.LogSaver.external;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListOfExceptions {
    private List<String> commandsExceptions = new ArrayList<>();
    private String fileName = "exceptionsList.txt";

    public ListOfExceptions(String fileName) {
        this.fileName = fileName;
        listCreating();
    }

    public ListOfExceptions() {
        listCreating();
    }

    private void listCreating(){
        try {
            BufferedReader br = new BufferedReader(new FileReader(Paths.get("").toAbsolutePath().toString() + "/" + fileName));
            while (br.ready()){
                commandsExceptions = Arrays.asList(br.readLine().split("\\s{2,}"));
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("Не найден файл " + fileName + ". Файл ищется по адресу: "
                    + Paths.get("").toAbsolutePath().toString());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Проблемы с чтением файла " + fileName);
            e.printStackTrace();
        }
    }


    public List<String> getCommandsExceptions() {
        return commandsExceptions;
    }
}
