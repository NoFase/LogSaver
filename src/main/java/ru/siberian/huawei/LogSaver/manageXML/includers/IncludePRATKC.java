package ru.siberian.huawei.LogSaver.manageXML.includers;

import org.xml.sax.Attributes;
import ru.siberian.huawei.LogSaver.manageXML.clasess.tkc.PRATKC_SOFTX3000;

public class IncludePRATKC {
    public IncludePRATKC(PRATKC_SOFTX3000 tg, Attributes attributes) {
        switch (attributes.getValue(attributes.getQName(0))) {
            case ("MN"):
                tg.setMN(attributes.getValue(attributes.getQName(1)));
                break;
            case ("SC"):
                tg.setSC(attributes.getValue(attributes.getQName(1)));
                break;
            case ("CS"):
                tg.setCS(attributes.getValue(attributes.getQName(1)));
                break;
            case ("EC"):
                tg.setEC(attributes.getValue(attributes.getQName(1)));
                break;
            case ("TG"):
                tg.setTG(attributes.getValue(attributes.getQName(1)));
                break;
            case ("TID"):
                tg.setTID(attributes.getValue(attributes.getQName(1)));
                break;
            case ("TIDPFX"):
                tg.setTIDPFX(attributes.getValue(attributes.getQName(1)));
                break;
        }
    }
}
