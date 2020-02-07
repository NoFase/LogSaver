package ru.siberian.huawei.LogSaver.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.siberian.huawei.LogSaver.external.AbrOfServers;
import ru.siberian.huawei.LogSaver.managment.FilterBd;
import ru.siberian.huawei.LogSaver.service.LinesOfTableForTDIMU;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class TableController {

    @GetMapping("tables/defaultTable")
    public String main(Map<String, Object> model){
        model.put("some", "List TDM tables");
        List<String> abrServers = new AbrOfServers().getAbrServers();
        model.put("abrServers", abrServers);

        List<List<String>> lll = new ArrayList<>();
        List<String> lineOfTables = new ArrayList<>();

        String lineOfTable = "";
        for (int i = 0; i < 63; i++) {
            lineOfTables.add(new LinesOfTableForTDIMU(i, 1, "Huawei").creatingLineOfTableForTDIMU());
        }

        lll.add(lineOfTables);
        lll.add(lineOfTables);
        lll.add(lineOfTables);
        lll.add(lineOfTables);
        lll.add(lineOfTables);

        model.put("lll", lll);
        model.put("tables", lineOfTables);
        return "tables/defaultTable";
    }

//    @PostMapping
//    public String filter(@RequestParam String abrCity, Map<String, Object> model){
//        List<String> abrServers = new AbrOfServers().getAbrServers();
//        model.put("abrServers", abrServers);
//        model.put("some", "List TDM tables");
//        return "tables/defaultTable";
//    }

}
