package ru.siberian.huawei.LogSaver.manageXML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class DOMxml {
    private final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    private DocumentBuilder builder;
    private final File xmlFile;

    public DOMxml(String fileName) {

        xmlFile = new File(fileName);

//        if (xmlFile.exists()) updating();
//        else System.out.println("no");
        creatingNewFile();

    }

    private void updating() {
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            Element element = doc.getDocumentElement();
//            element.appendChild(getServer(doc, "Cheboksary", "10.100.130.4", "login2", "pass2"));

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            // для красивого вывода в консоль
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);

            //печатаем в консоль или файл
            StreamResult console = new StreamResult(System.out);
            StreamResult file = new StreamResult(xmlFile);

            //записываем данные
            transformer.transform(source, console);
            transformer.transform(source, file);
            // обновляем значения
            //updateElementValue(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void creatingNewFile() {
        try {
            builder = factory.newDocumentBuilder();
            // создаем пустой объект Document, в котором будем
            // создавать наш xml-файл
            Document doc = builder.newDocument();
            // создаем корневой элемент
            Element rootElement =
                    doc.createElementNS("https://myXMLParser.com/servers", "Servers");
            // добавляем корневой элемент в объект Document
            doc.appendChild(rootElement);
            // добавляем первый дочерний элемент к корневому
            rootElement.appendChild(getServer(doc, "Saratov", "10.124.125.4", "SRT", "login1", "pass1", "10.10.11.11",
                    "loginFTP", "12133ff34", "/src/doc/dd", "Saratov_SX"));
            //добавляем второй дочерний элемент к корневому
            rootElement.appendChild(getServer(doc, "Orenburg", "10.130.130.4", "ORB", "login2", "pass2", "100.100.101.101",
                    "loginFTP1", "dsafdsafwee", "/src/doc/dd/bin", "Orenburg_SX"));

            Element secondElement = doc.createElement("STMs");
            rootElement.appendChild(secondElement);

            secondElement.appendChild(getSTM(doc, "Saratov", "E63", "0", "1", "2", "0", "2047"));
            secondElement.appendChild(getSTM(doc, "Saratov", "E63", "1", "1", "3", "2048", "4095"));

            //создаем объект TransformerFactory для печати в консоль
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            // для красивого вывода в консоль
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);

            //печатаем в консоль или файл
            StreamResult console = new StreamResult(System.out);
            StreamResult file = new StreamResult(xmlFile);

            //записываем данные
            transformer.transform(source, console);
            transformer.transform(source, file);
            System.out.printf("Создание XML файла закончено");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // метод для создания нового узла XML-файла
    private Node getServer(Document doc,
                           String serverName,
                           String ip,
                           String abr,
                           String loginSx,
                           String passSx,
                           String ipFtp,
                           String loginFtp,
                           String passFtp,
                           String pathFtp,
                           String idForFtp) {
        Element server = doc.createElement("Server");
        server.setAttribute("serverName", serverName);

        server.appendChild(getServerElement(doc, server, "ipSx", ip));
        server.appendChild(getServerElement(doc, server, "abr", abr));
        server.appendChild(getServerElement(doc, server, "loginSx", loginSx));
        server.appendChild(getServerElement(doc, server, "passSx", passSx));
        server.appendChild(getServerElement(doc, server, "ipFtp", ipFtp));
        server.appendChild(getServerElement(doc, server, "loginFtp", loginFtp));
        server.appendChild(getServerElement(doc, server, "passFtp", passFtp));
        server.appendChild(getServerElement(doc, server, "pathFtp", pathFtp));
        server.appendChild(getServerElement(doc, server, "idForFtp", idForFtp));
        return server;
    }

    private Node getSTM(Document doc,
                        String serverName,
                        String bName,
                        String bNumber,
                        String fn,
                        String sn,
                        String st,
                        String et){
        Element elementSTM = doc.createElement("STM");
        elementSTM.setAttribute("server_STM", serverName);

        elementSTM.appendChild(getServerElement(doc, elementSTM, "BName", bName));
        elementSTM.appendChild(getServerElement(doc, elementSTM, "BNumber", bNumber));
        elementSTM.appendChild(getServerElement(doc, elementSTM, "FN", fn));
        elementSTM.appendChild(getServerElement(doc, elementSTM, "SN", sn));
        elementSTM.appendChild(getServerElement(doc, elementSTM, "ST", st));
        elementSTM.appendChild(getServerElement(doc, elementSTM, "ET", et));
        return elementSTM;
    }


    // утилитный метод для создание нового узла XML-файла
    private Node getServerElement(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }
}

