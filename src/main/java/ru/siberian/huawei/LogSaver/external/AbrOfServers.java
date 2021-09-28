package ru.siberian.huawei.LogSaver.external;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class AbrOfServers {
    public static List<String> abrServers;
    public AbrOfServers() {
        abrServers = new ArrayList<>();
        for(Map.Entry<String, String> entry: ListOfServers.servers.entrySet()){
            abrServers.add(entry.getValue());
        }
    }

    public List<String> getAbrServers() {
        return abrServers;
    }
}
