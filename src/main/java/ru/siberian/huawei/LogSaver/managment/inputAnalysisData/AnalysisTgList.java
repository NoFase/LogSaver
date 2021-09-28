package ru.siberian.huawei.LogSaver.managment.inputAnalysisData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.siberian.huawei.LogSaver.entity.TDMIU.TG;

public class AnalysisTgList extends Analysis{
    private final Logger LOGGER = LoggerFactory.getLogger(AnalysisTgList.class);
    private String massifOfLine[];

    public TG findingTGs(String line){
        TG tg = null;
//        LOGGER.info(line);
        massifOfLine = line.split("\\s+");
        if (line.contains("ISUP")) {
            tg = new TG(Integer.parseInt(massifOfLine[1]), massifOfLine[6], massifOfLine[2]);
//            LOGGER.info(tg.toString());
        }
        else if (line.contains("V5") || line.contains("PRA") || line.contains("SIP") || line.contains("R2")) {
            if (!line.contains("V5 Trunk Group Info")) {
//                LOGGER.info(" ----->" + line);
//            LOGGER.info(" ----->" + massifOfLine.toString());
                tg = new TG(Integer.parseInt(massifOfLine[1]), massifOfLine[2], massifOfLine[3]);
//                LOGGER.info(tg.toString());
            }
        }
        return tg;
    }
}
