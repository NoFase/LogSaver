package ru.siberian.huawei.LogSaver.managment;

import ru.siberian.huawei.LogSaver.entity.Messages;
import ru.siberian.huawei.LogSaver.entity.MessagesRepository;
import ru.siberian.huawei.LogSaver.network.TCPConnection;

import java.util.Date;
import java.util.List;

public class InputAnalyzer {
    private List<String> exceptions;
    private MessagesRepository repository;
    private String cityName;
    private String longLine = "";

    public InputAnalyzer(List<String> exceptions, MessagesRepository repository, String cityName) {
        this.exceptions = exceptions;
        this.repository = repository;
        this.cityName = cityName;
    }

    public void analysis(String line, TCPConnection tcpConnection){
        if (line.contains("logged in successfully")) tcpConnection.sendString("LST CMDLOG: QM=Important;");
        else if (line.contains("Number of results =")) tcpConnection.disconnect();
        else {
            int count = 0;
            while (count < exceptions.size()) {
                String compare = exceptions.get(count);
                if (line.contains("BAM")) longLine = line;
                else if (line.contains("Detailed operation command")) {
                    String lineCommand = line.substring(30);
                    String[] items = longLine.split("\\s+");
//      установить дату
                    if (longLine.contains(compare) || lineCommand.contains(compare)) count = exceptions.size();
                    if (count == exceptions.size() - 1) {
                        repository.save(new Messages(cityName, new Date(), items[2], items[3], lineCommand));
                    }
                }
                count++;
            }
        }
    }
}
