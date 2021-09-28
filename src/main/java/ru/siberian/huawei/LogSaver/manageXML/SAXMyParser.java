package ru.siberian.huawei.LogSaver.manageXML;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;
import ru.siberian.huawei.LogSaver.manageXML.clasess.tg.*;
import ru.siberian.huawei.LogSaver.manageXML.clasess.tkc.*;
import ru.siberian.huawei.LogSaver.manageXML.includers.*;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;

public class SAXMyParser {
    private final Logger LOGGER = LoggerFactory.getLogger(SAXMyParser.class);

    final String fileName = "GExport_SoftX3000_NNovgorod_10.141.139.7_20210913031516.xml";
//    final String fileName = "short.xml";
    private ArrayList<TKC_SOFTX3000> tkcSoftx3000s = new ArrayList<>();
    private ArrayList<TG_SOFTX3000> tgSoftx3000s = new ArrayList<>();

    public ArrayList<TKC_SOFTX3000> getTkcSoftx3000s() {
        return tkcSoftx3000s;
    }

    public ArrayList<TG_SOFTX3000> getTgSoftx3000s() {
        return tgSoftx3000s;
    }

    DefaultHandler handler = new DefaultHandler() {
        boolean tagOn = false;
        boolean tagObjectOn = false;
        TKC_SOFTX3000 tkcSoftx3000;
        TG_SOFTX3000 tgSoftx3000;
        private int classType;

        //Start work with TAG
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equalsIgnoreCase("class")) {
                switch (attributes.getValue("name")){
                    case ("N7TKC_SOFTX3000"):
                        classType = 1;
                        tagOn = true;
                        break;
                    case ("PRATKC_SOFTX3000"):
                        classType = 2;
                        tagOn = true;
                        break;
                    case ("V5TKC_SOFTX3000"):
                        classType = 3;
                        tagOn = true;
                        break;
                    case ("SOSMTKC_SOFTX3000"):
                        classType = 4;
                        tagOn = true;
                        break;
                    case ("SOSMTG_SOFTX3000"):
                        classType = 5;
                        tagOn = true;
                        break;
                    case ("PRATG_SOFTX3000"):
                        classType = 6;
                        tagOn = true;
                        break;
                    case ("N7TG_SOFTX3000"):
                        classType = 7;
                        System.out.println("CLASS N7");
                        tagOn = true;
                        break;
                    case ("SIPTG_SOFTX3000"):
                        classType = 8;
                        tagOn = true;
                        break;
                }
            }
            if (qName.equalsIgnoreCase("object") && tagOn) {
                tkcSoftx3000 = null;
                tgSoftx3000 = null;
                switch (classType){
                    case 1:
                        TKC_SOFTX3000 n7TKC_softx3000 = new N7TKC_SOFTX3000();
                        tkcSoftx3000 = n7TKC_softx3000;
                        tkcSoftx3000s.add(tkcSoftx3000);
                        break;
                    case 2:
                        TKC_SOFTX3000 pratkc_softx3000 = new PRATKC_SOFTX3000();
                        tkcSoftx3000 = pratkc_softx3000;
                        tkcSoftx3000s.add(tkcSoftx3000);
                        break;
                    case 3:
                        TKC_SOFTX3000 v5TKC_softx3000 = new V5TKC_SOFTX3000();
                        tkcSoftx3000 = v5TKC_softx3000;
                        tkcSoftx3000s.add(tkcSoftx3000);
                        break;
                    case 4:
                        TKC_SOFTX3000 sosmtkc_softx3000 = new SOSMTKC_SOFTX3000();
                        tkcSoftx3000 = sosmtkc_softx3000;
                        tkcSoftx3000s.add(tkcSoftx3000);
                        break;
                    case 5:
                        TG_SOFTX3000 sosmtTg_softx3000 = new SOSMTG_SOFTX3000();
                        tgSoftx3000 = sosmtTg_softx3000;
                        tgSoftx3000s.add(tgSoftx3000);
                        break;
                    case 6:
                        TG_SOFTX3000 praTg_softx3000 = new PRATG_SOFTX3000();
                        tgSoftx3000 = praTg_softx3000;
                        tgSoftx3000s.add(tgSoftx3000);
                        break;
                    case 7:
                        TG_SOFTX3000 n7TG_softx3000 = new N7TG_SOFTX3000();
                        tgSoftx3000 = n7TG_softx3000;
                        tgSoftx3000s.add(tgSoftx3000);
                        break;
                    case 8:
                        TG_SOFTX3000 sipTg_softx3000 = new SIPTG_SOFTX3000();
                        tgSoftx3000 = sipTg_softx3000;
                        tgSoftx3000s.add(tgSoftx3000);
                        break;
                }
                tagObjectOn = true;
            }
            if (qName.equalsIgnoreCase("parameter")  && tagOn && tagObjectOn) {
                switch (classType){
                    case 1:
                        new IncudeN7TKC((N7TKC_SOFTX3000) tkcSoftx3000, attributes);
                        break;
                    case 2:
                        new IncludePRATKC((PRATKC_SOFTX3000) tkcSoftx3000, attributes);
                        break;
                    case 3:
                        new IncludeV5TKC((V5TKC_SOFTX3000) tkcSoftx3000, attributes);
                        break;
                    case 4:
                        new IncludeSOSMTKC((SOSMTKC_SOFTX3000) tkcSoftx3000, attributes);
                        break;
                    case 5:
                        new IncludeTG(tgSoftx3000, attributes);
                        break;
                    case 6:
                        new IncludeTG(tgSoftx3000, attributes);
                        break;
                    case 7:
                        new IncludeTG(tgSoftx3000, attributes);
                        break;
                    case 8:
                        new IncludeTG(tgSoftx3000, attributes);
                        break;
                }
            }
        }



        //read text inside TAG
        @Override
        public void characters(char ch[], int start, int length) throws SAXException {

        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
            if (qName.equalsIgnoreCase("class")) tagOn = false;
            if (qName.equalsIgnoreCase("object")) tagObjectOn = false;
            //проработать закрытие тегов через флаги
        }

        @Override
        public void startDocument() throws SAXException {
            LOGGER.info("Начало разбора документа!");// далее необходимо добавить название фвйла который разбирается
        }

        @Override
        public void endDocument() throws SAXException {
            LOGGER.info("Разбор документа завершен!");
        }
        @Override
        public void startPrefixMapping (String prefix, String uri)
                throws SAXException
        {
            System.out.println(prefix + " - " + uri);
        }
    };

    public SAXMyParser() {
        try {
            SAXParserFactory factory;
            factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            //start analyse XML-document
            saxParser.parse(fileName, handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

