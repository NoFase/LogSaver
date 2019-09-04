package ru.siberian.huawei.LogSaver.external;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AbrOfServers {
    private List<String> abrServers;
    public AbrOfServers() {
        ListOfServers listOfServers = new ListOfServers();
        abrServers = new ArrayList<>();
        for(Map.Entry<String, String> entry: listOfServers.getServers().entrySet()){
            abrServers.add(entry.getValue());
        }
    }

    public List<String> getAbrServers() {
        return abrServers;
    }
}
