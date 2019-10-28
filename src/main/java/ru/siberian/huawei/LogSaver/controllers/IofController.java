package ru.siberian.huawei.LogSaver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.siberian.huawei.LogSaver.repository.IofcRepository;

import java.util.Map;

@Controller
public class IofController {

    @Autowired
    private IofcRepository repository;

    @GetMapping("sbc/iofc")
    public String main(Map<String, Object> model){
        model.put("some", "Список всех заведенных IOFC.");
        model.put("data", repository.findAll());
        return "sbc/iofc";
    }
}
