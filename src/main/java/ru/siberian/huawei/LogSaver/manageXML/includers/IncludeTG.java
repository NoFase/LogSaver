package ru.siberian.huawei.LogSaver.manageXML.includers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import ru.siberian.huawei.LogSaver.manageXML.clasess.tg.TG_SOFTX3000;

public class IncludeTG {
    private final Logger LOGGER = LoggerFactory.getLogger(IncludeTG.class);

    public IncludeTG(TG_SOFTX3000 tg, Attributes attributes) {
//        LOGGER.info(attributes.getValue("TG"));
        switch (attributes.getValue(attributes.getQName(0))) {
            case ("TG"):
                tg.setTG(attributes.getValue(attributes.getQName(1)));
                break;
            case ("TGN"):
                tg.setTGN(attributes.getValue(attributes.getQName(1)));
                break;
        }
    }
}
