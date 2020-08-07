package ru.siberian.huawei.LogSaver.controllers.tdmTable;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.siberian.huawei.LogSaver.external.AbrOfServers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class ChoiceCityForTdmController {


    @GetMapping("tables/choiceCityForTdm")
    public String main(Map<String, Object> model) throws IOException {
        model.put("some", "text only text");
        List<String> abrServers = new AbrOfServers().getAbrServers();
        model.put("abrServers", abrServers);
//        System.out.println(new ReaderXXFiles("head").reading());
        return "tables/choiceCityForTdm";
    }

    @PostMapping("tables/choose")
    public String choose(@RequestParam String abrCity, Map<String, Object> model){
        model.put("some", "Выбран город " + abrCity);
        return "tables/defaultTable";
    }
}
