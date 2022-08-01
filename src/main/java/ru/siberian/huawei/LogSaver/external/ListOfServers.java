package ru.siberian.huawei.LogSaver.external;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.siberian.huawei.LogSaver.managment.InfTemplates;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;

@Repository
public class ListOfServers {
    private final Logger LOGGER = LoggerFactory.getLogger(ListOfServers.class);
    public static HashMap<String, String> servers = new HashMap<>(); // for SoftX3000
    public static HashMap<String, String> serversSbc = new HashMap<>(); //for SBC SE2900

    public ListOfServers() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(Paths.get("").toAbsolutePath().toString() + "/serversList.txt"));
            while (br.ready()){
                String[] line = br.readLine().split("\\s+");
//          index 0 - Ip Addres, index 1 - ABR
                if(line[2].trim().contains("SX"))
                    servers.put(line[0].trim(), line[1].trim().toUpperCase());
                else if (line[2].trim().contains("SBC")){
                    serversSbc.put(line[0].trim(), line[1].trim());
                }

            }
            br.close();
            LOGGER.info("\tRead " + servers.size() + " servers from the serversList");

        } catch (FileNotFoundException e) {
            LOGGER.warn("\tDoes not find the serversList.txt. The file was find in path: "
             + Paths.get("").toAbsolutePath().toString());
            e.printStackTrace();
        } catch (IOException e) {
            LOGGER.warn("\tWe are have some problem with reading the serversList.txt");
            e.printStackTrace();
        }
    }

    public HashMap<String, String> getServers() {
        return servers;
    }
}
