package ru.siberian.huawei.LogSaver.managment;

import ru.siberian.huawei.LogSaver.entity.SbcLogMessages;
import ru.siberian.huawei.LogSaver.statics.ClassOfStatics;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ru.siberian.huawei.LogSaver.LogSaverApplication.sbcLogMessagesRepository;

public class ParserValueFromSbc {

    private String inputValue;
    private String cityName;

    public ParserValueFromSbc(String inputValue, String cityName) throws ParseException {
        this.inputValue = inputValue;
        this.cityName = cityName;

        if (valueFiltering(inputValue))findingData(inputValue);
    }

    //filter for skip lines without pay load
    private Boolean valueFiltering(String line){
        for (String string : ClassOfStatics.listOfExceptionsForSbc) {
            if (line.contains(string)) {
                return false;
            }
        }
        return true;
    }

    private void findingData (String line) throws ParseException {
        String regex = "^\\s[0-9]{6,}\\s{2,}\\w{3,}\\s{2,}(.*)"; // For finding line started with ID number
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault());
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        SbcLogMessages logMessages = null;

        if (matcher.find()){
            String[] messages = line.split("\\s{2,}"); // Split line to component, ignoring single space
            if (messages.length == 11) {
                try {
                    logMessages = new SbcLogMessages(
//                            Long.parseLong(messages[0].trim()),     // id
                            messages[1],                            // typeOfInterface
                            messages[2],                            // operator
                            messages[3],                            // ip
                            format.parse(messages[4]),              // beginTime
                            format.parse(messages[5]),              // endTime
                            messages[6],                            // commandName
                            Integer.parseInt(messages[7].trim()),   // numberOfMe
                            messages[8],                            // result
                            messages[9],                            // info
                            messages[10],                           // description
                            cityName)                               // cityName
                    ;
//                    System.out.println(logMessages.toString() + "\t" + line);
                    if (logMessages != null) sbcLogMessagesRepository.save(logMessages);
//                    else System.out.println("---------->logMessages = null");
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
