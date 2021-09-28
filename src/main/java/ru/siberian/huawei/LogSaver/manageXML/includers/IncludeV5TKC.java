package ru.siberian.huawei.LogSaver.manageXML.includers;

import org.xml.sax.Attributes;
import ru.siberian.huawei.LogSaver.manageXML.clasess.tkc.V5TKC_SOFTX3000;

public class IncludeV5TKC {
    public IncludeV5TKC(V5TKC_SOFTX3000 tg, Attributes attributes) {
        switch (attributes.getValue(attributes.getQName(0))) {
            case ("EEN"):
                tg.setEEN(attributes.getValue(attributes.getQName(1)));
                break;
            case ("MN"):
                tg.setMN(attributes.getValue(attributes.getQName(1)));
                break;
            case ("SEN"):
                tg.setSEN(attributes.getValue(attributes.getQName(1)));
                int startSC = Integer.parseInt(attributes.getValue(attributes.getQName(1)));
                startSC = startSC * 32;
                tg.setSC(String.valueOf(startSC));
                break;
            case ("EN"):
                tg.setEN(attributes.getValue(attributes.getQName(1)));
                int endSC = Integer.parseInt(attributes.getValue(attributes.getQName(1)));
                endSC = endSC * 32 + 31;
                tg.setEC(String.valueOf(endSC));
                break;
            case ("LNKS"):
                tg.setLNKS(attributes.getValue(attributes.getQName(1)));
                break;
            case ("MGEID"):
                tg.setMGEID(attributes.getValue(attributes.getQName(1)));
                break;
            case ("NLNKID"):
                tg.setNLNKID(attributes.getValue(attributes.getQName(1)));
                break;
            case ("NTGID"):
                tg.setNTGID(attributes.getValue(attributes.getQName(1)));
                break;
            case ("NTID"):
                tg.setNTID(attributes.getValue(attributes.getQName(1)));
                tg.setTID(attributes.getValue(attributes.getQName(1)));
                break;
            case ("SLNKID"):
                tg.setSLNKID(attributes.getValue(attributes.getQName(1)));
                break;
            case ("SMGID"):
                tg.setSMGEID(attributes.getValue(attributes.getQName(1)));
                break;
            case ("TGID"):
                tg.setTGID(attributes.getValue(attributes.getQName(1)));
                tg.setTG(attributes.getValue(attributes.getQName(1)));
                break;

        }
    }
}
