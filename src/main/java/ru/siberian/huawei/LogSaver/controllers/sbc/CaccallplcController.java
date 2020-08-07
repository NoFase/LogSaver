package ru.siberian.huawei.LogSaver.controllers.sbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.siberian.huawei.LogSaver.repository.CaccallplcRepository;

import java.util.Map;

@Controller
public class CaccallplcController {
    @Autowired
    private CaccallplcRepository repository;

    @GetMapping("sbc/caccllplc")
    public String main(Map<String, Object> model){
        model.put("some", "Список всех заведенных CACCLLPLC.");
        model.put("data", repository.findAll());
        return "sbc/caccllplc";
    }
}
