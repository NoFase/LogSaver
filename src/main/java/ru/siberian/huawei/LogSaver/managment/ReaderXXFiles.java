package ru.siberian.huawei.LogSaver.managment;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ReaderXXFiles {
    private String fileName;

    public ReaderXXFiles(String fileName) {
        this.fileName = fileName;
    }

    public ArrayList<String> reading() {
        ArrayList<String> result = new ArrayList<>();
//        String result= "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(Paths.get("").toAbsolutePath().toString() + "/src/main/resources/include/" + fileName + ".xx"));
            while (br.ready()){
                result.add(br.readLine());
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("Не найден файл " + fileName + ".xx Файл ищется по адресу: "
                    + Paths.get("").toAbsolutePath().toString());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Проблемы с чтением файла " + fileName + ".xx");
            e.printStackTrace();
        }
        return result;
    }
}
