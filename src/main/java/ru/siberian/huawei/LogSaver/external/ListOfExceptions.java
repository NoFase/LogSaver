package ru.siberian.huawei.LogSaver.external;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ListOfExceptions {
    private List<String> commandsExceptions = new ArrayList<>();

    public ListOfExceptions() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(Paths.get("").toAbsolutePath().toString() + "/exceptionsList.txt"));
            while (br.ready()){
                commandsExceptions.add(br.readLine());
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("Не найден файл exceptionsList.txt. Файл ищется по адресу: "
                    + Paths.get("").toAbsolutePath().toString());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Проблемы с чтением файла exceptionsList.txt");
            e.printStackTrace();
        }
    }

    public List<String> getCommandsExceptions() {
        return commandsExceptions;
    }
}
