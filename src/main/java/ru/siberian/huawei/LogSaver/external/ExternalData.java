package ru.siberian.huawei.LogSaver.external;

import java.util.HashMap;
import java.util.List;

public class ExternalData {
    private HashMap<String, String> servers;
    private List<String> exceptions;
    private List<String> exceptionsSbc;

    public HashMap<String, String> getServers() {
        return servers;
    }

    public List<String> getExceptions() {
        return exceptions;
    }

    public ExternalData() {
        ListOfServers listOfServers = new ListOfServers();
        ListOfExceptions listOfExceptions = new ListOfExceptions();
        ListOfExceptions listOfExceptionsSbc = new ListOfExceptions("filterSbc.txt");

        servers = ListOfServers.servers;
        exceptions = listOfExceptions.getCommandsExceptions();
        exceptionsSbc = listOfExceptionsSbc.getCommandsExceptions();
    }
}
