package ru.siberian.huawei.LogSaver.statics;

import org.springframework.stereotype.Component;
import ru.siberian.huawei.LogSaver.entity.SbcLogMessages;
import ru.siberian.huawei.LogSaver.external.ListOfExceptions;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClassOfStatics {
    public static List<String> listOfExceptionsForSbc;
    public static List<SbcLogMessages> sbcLogMessagesList = new ArrayList<>();

    public ClassOfStatics() {
        listOfExceptionsForSbc = new ListOfExceptions("filterSbc.txt").getCommandsExceptions();
    }
}
