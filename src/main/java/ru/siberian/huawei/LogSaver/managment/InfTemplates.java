package ru.siberian.huawei.LogSaver.managment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class InfTemplates {

    private final Logger LOGGER = LoggerFactory.getLogger(InfTemplates.class);
    public static long LONGDAYBYSECONDS;
    public static int hour;
    public static int minutes;
    public static int plusDays;
    {
    //        выгрузка данных из файла настроек
        ReaderXXFiles reader = new ReaderXXFiles("\\services\\inf.ini");
        ArrayList<String> inf = reader.reading();
            for (String lineOfInf: inf){
            if (lineOfInf.contains("=")) {
                String[] dataOfInf = lineOfInf.split("=");
                switch (dataOfInf[0]) {
                    case "LONGDAYBYSECONDS":
                        LONGDAYBYSECONDS = Integer.parseInt(dataOfInf[1]);
                        LOGGER.info("\t\t\tLONGDAYBYSECONDS\t" + LONGDAYBYSECONDS);
//                        System.out.println("\t\t\tLONGDAYBYSECONDS\t" + LONGDAYBYSECONDS);
                        break;
                    case "HOUR":
                        hour = Integer.parseInt(dataOfInf[1]);
                        LOGGER.info("\t\t\thour\t" + hour);
                        break;
                    case "MINUTES":
                        minutes = Integer.parseInt(dataOfInf[1]);
                        LOGGER.info("\t\t\tminutes\t" + minutes);
                        break;
                    case "PLUSDAYS":
                        plusDays = Integer.parseInt(dataOfInf[1]);
                        LOGGER.info("\t\t\tPlusDays\t" + plusDays);
                        break;
            }
        }
    }

    }
}
