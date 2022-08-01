package ru.siberian.huawei.LogSaver.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.siberian.huawei.LogSaver.external.ListOfServers;
import ru.siberian.huawei.LogSaver.managment.FilterBdForSbc;

import java.util.*;

@Controller
public class SbcController {
    private final Logger LOGGER = LoggerFactory.getLogger(SbcController.class);

    @GetMapping("/SE2900")
    public String main(Map<String, Object> model){
        List<String> abrServers = new ArrayList<>();
        for (Map.Entry<String, String> servers : ListOfServers.serversSbc.entrySet()){
            abrServers.add(servers.getValue());
        }
        model.put("abrServers", abrServers);
        model.put("some", "Поиск логов на SE2900");
        return "SE2900";
    }
    @PostMapping("filterSBC")
    public String filterSBC(@RequestParam String sign, @RequestParam String abrCity, @RequestParam String dateStart,
                         @RequestParam String dateEnd, Map<String, Object> model){
//        LOGGER.info("Сработал метод filterSBC");
        List<String> searcher = null;

            searcher = new FilterBdForSbc(abrCity, dateStart, dateEnd, sign).getSearch();
        List<String> abrServers = new ArrayList<>();
        for (Map.Entry<String, String> servers : ListOfServers.serversSbc.entrySet()){
            abrServers.add(servers.getValue());
        }
        model.put("abrServers", abrServers);

        model.put("message", searcher);
        model.put("some", "Поиск логов на SE2900");
        return "searchSbc";
    }
}
