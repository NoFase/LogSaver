package ru.siberian.huawei.LogSaver.controllers.sbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.siberian.huawei.LogSaver.repository.CacplcsetRepository;

import java.util.Map;

@Controller
public class CacplcsetController {
    @Autowired
    private CacplcsetRepository repository;

    @GetMapping("sbc/cacplcset")
    public String main(Map<String, Object> model){
        model.put("some", "Список всех заведенных CACPLCSET.");
        model.put("data", repository.findAll());
        return "sbc/cacplcset";
    }
}
