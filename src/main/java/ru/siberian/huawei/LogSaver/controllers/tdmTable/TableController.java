package ru.siberian.huawei.LogSaver.controllers.tdmTable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.siberian.huawei.LogSaver.entity.TDMIU.TableForSTM;
import ru.siberian.huawei.LogSaver.external.AbrOfServers;
import ru.siberian.huawei.LogSaver.external.ListOfServers;
import ru.siberian.huawei.LogSaver.manageXML.SAXMyParser;
import ru.siberian.huawei.LogSaver.manageXML.clasess.tg.TG_SOFTX3000;
import ru.siberian.huawei.LogSaver.manageXML.clasess.tkc.TKC_SOFTX3000;
import ru.siberian.huawei.LogSaver.managment.DirScanner;
import ru.siberian.huawei.LogSaver.repository.TableRepository;
import ru.siberian.huawei.LogSaver.service.KLM;
import ru.siberian.huawei.LogSaver.service.LinesOfTableForTDIMU;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TableController {
    private int maxTid;

    @Autowired
    public TableRepository tableRepository;

    private final Logger LOGGER = LoggerFactory.getLogger(TableController.class);
    private String abrCity;
    private ListOfServers listOfServers = new ListOfServers();

    @GetMapping("tables/defaultTable")
    public String main(Map<String, Object> model){
        model.put("some", "Просмотр таблиц TDIMU");
        model.put("abrServers", AbrOfServers.abrServers);
//        LOGGER.info("\t\tStart controller, by method main - " + AbrOfServers.abrServers);
        return "tables/defaultTable";
    }

    @PostMapping("tables/choose")
    public String choose(@RequestParam String abrCity, Map<String, Object> model){
        this.abrCity = abrCity;


        model.put("abrServers", AbrOfServers.abrServers);
        HashMap<String, TKC_SOFTX3000> tkc_softx3000HashMap = new HashMap<>();
        HashMap<String, TG_SOFTX3000> tg_softx3000HashMap = new HashMap<>();
        //поиск имени файла по аббревиатуре
        String fileName = new DirScanner(abrCity).finding();
        if (fileName.equalsIgnoreCase("empty") || fileName == null || fileName == "") {
            model.put("some", "Данных по выбранному городу нет");
        } else {
            model.put("some", "Выбран город " + abrCity);
            //запускаем парсер XML, указав имя файла, найденного в DirScanner
            SAXMyParser saxMyParser = new SAXMyParser("/XMLTemporary/" + fileName);
            maxTid = 0;
            for(TKC_SOFTX3000 tkc: saxMyParser.getTkcSoftx3000s()){
                if (Integer.parseInt(tkc.getTID()) > maxTid) maxTid = Integer.parseInt(tkc.getTID());
                tkc_softx3000HashMap.put(tkc.getTID(), tkc);
            }

            for (TG_SOFTX3000 tg: saxMyParser.getTgSoftx3000s()){
                tg_softx3000HashMap.put(tg.getTG(), tg);
            }
        }

        tableFilling(model, tkc_softx3000HashMap, tg_softx3000HashMap);
//        LOGGER.info("\t\tStart controller, by method choose");
        String ipServer = ListOfServers.servers.entrySet()
                .stream()
                .filter(pair -> pair.getValue().equals(abrCity))
                .findFirst()
                .orElse(null)
                .getKey();//выгрузка IP по выбранному имени города

        return "tables/defaultTable";
    }

    @PostMapping("tables/tableEditing")
    public String tableEditing(@RequestParam int numberOfSTM, @RequestParam int numberOfE1, @RequestParam int startTid,
                  @RequestParam String startTs, @RequestParam String endTs, @RequestParam String startCic,  @RequestParam String endCic,
                               @RequestParam String numberOfTrunk,  @RequestParam String projectNumber,
                               @RequestParam String nameOfTrunk, Map<String, Object> model){
        int trunkNumber;
        if (abrCity == null) {
            model.put("some", "Необходимо выбрать город для редактирования");
        } else {
            model.put("some", "Была отредактирована таблица в городе: " + abrCity);
            String typeOfSTM = "huawei";
            String klm = new KLM(typeOfSTM, numberOfE1).getKlm();

            TableForSTM tableForSTM = new TableForSTM("SRT", numberOfSTM, numberOfE1, startTid, startTid + 31, klm, startTs, endTs, startCic, endCic, numberOfTrunk,
                    projectNumber, nameOfTrunk);
            tableRepository.save(tableForSTM);
        }
        LOGGER.info("\t\tStart controller, by method tableEditing");
        return "tables/defaultTable";
    }

    private Map<String, Object> tableFilling(Map<String, Object> model, HashMap<String, TKC_SOFTX3000> tkc, HashMap<String, TG_SOFTX3000> tgs){
        model.put("abrServers", AbrOfServers.abrServers);

        List<List<String>> lll = new ArrayList<>();
        List<String> lineOfTables = new ArrayList<>();

//        for (Map.Entry e: tgs.entrySet()) {
//            LOGGER.info(e.getKey().toString() + " - " + e.getValue().toString());
//        }

//        for (Map.Entry e : tkc.entrySet()){
//            TKC_SOFTX3000 tt = (TKC_SOFTX3000)e.getValue();
//            if (tt.getTG().equalsIgnoreCase("28"))
//                System.out.println(tt.toString());
//
//        }

        for (int j = 0; j < (maxTid/(32*63) + (maxTid % (32*63) > 0 ? 1 : 0)); j++) { // высчитываем количество STM и обходим их все
            for (int i = 0; i < 64; i++) {
                lineOfTables.add(new LinesOfTableForTDIMU(i, j, "Huawei", tkc, tgs).creatingLineOfTableForTDIMU());
            }
        }

        model.put("tables", lineOfTables);
        return model;
    }
}
