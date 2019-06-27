package ru.siberian.huawei.LogSaver.external;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;

public class ListOfServers {
    private HashMap<String, String> servers = new HashMap<>();

    public ListOfServers() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(Paths.get("").toAbsolutePath().toString() + "/serversList.txt"));
            while (br.ready()){
                String[] line = br.readLine().split("\\s+");
//          index 0 - Ip Addres, index 1 - ABR
                servers.put(line[0].trim(), line[1].trim().toUpperCase());
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("Не найден файл serversList.txt. Файл ищется по адресу: "
             + Paths.get("").toAbsolutePath().toString());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Проблемы с чтением файла serversList.txt");
            e.printStackTrace();
        }
    }

    public HashMap<String, String> getServers() {
        return servers;
    }
}
