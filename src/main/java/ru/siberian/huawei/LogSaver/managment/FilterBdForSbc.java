package ru.siberian.huawei.LogSaver.managment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.siberian.huawei.LogSaver.LogSaverApplication;
import ru.siberian.huawei.LogSaver.entity.SbcLogMessages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


//@Component
public class FilterBdForSbc {

    private final Logger LOGGER = LoggerFactory.getLogger(FilterBdForSbc.class);

    private String abr;
    private String beginTime;
    private String endTime;
    private String sign;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    private List<SbcLogMessages> messagesList = new ArrayList<>();
    private List<String> search = new ArrayList<>();

    public FilterBdForSbc(String abr, String beginTime, String endTime, String sign) {
        this.abr = abr;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.sign = sign;
    }

    public FilterBdForSbc() {
    }

    public List<String> getSearch(){
        try {
            if (abr.length() > 1 ){
                if (beginTime.length() > 1 && endTime.length() > 1){
                    if (sign.length() > 1) {
                        messagesList = LogSaverApplication.sbcLogMessagesRepository.findAny(abr, format.parse(beginTime), format.parse(endTime), sign);
//                        LOGGER.info("[1]\t" + abr + "\t" + beginTime + "\t" + endTime + "\t" + sign);
                    } else {
                        messagesList = LogSaverApplication.sbcLogMessagesRepository.findWithoutCommand(abr, format.parse(beginTime), format.parse(endTime));
//                        LOGGER.info("[2]\t" + abr + "\t" + beginTime + "\t" + endTime + "\t" + sign);
                    }
                } else {
                    if (sign.length() > 1) {
                        messagesList = LogSaverApplication.sbcLogMessagesRepository.findCityCommand(abr, sign);
//                        LOGGER.info("[3]\t" + abr + "\t" + beginTime + "\t" + endTime + "\t" + sign);
                    } else {
                        messagesList = LogSaverApplication.sbcLogMessagesRepository.findCity(abr);
//                        LOGGER.info("[4]\t" + abr + "\t" + beginTime + "\t" + endTime + "\t" + sign);
                    }
                }
            }

        for (SbcLogMessages message : messagesList){
            search.add(message.getCityName() +
                    "\t|\t" + message.getOperator() +
                    "\t|\t" + message.getIp() +
                    "\t|\t" + message.getBeginTime() +
                    "\t|\t" + message.getEndTime() +
                    "\t|\t" + message.getCommandName() +
                    "\t|\t" + message.getResult() +
                    "\t|\t" + message.getInfo() +
                    "\t|\t" + message.getDescription() +
                    "\n"
            );
        }
        } catch (NullPointerException | ParseException e){
            e.printStackTrace();
        }

        return search;
    }
}
