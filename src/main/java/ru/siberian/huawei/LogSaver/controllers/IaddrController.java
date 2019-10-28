package ru.siberian.huawei.LogSaver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.siberian.huawei.LogSaver.repository.IaddrRepository;
import ru.siberian.huawei.LogSaver.repository.IrtRepository;

import java.util.Map;

@Controller
public class IaddrController {
    @Autowired
    private IaddrRepository repository;

    @GetMapping("sbc/iaddr")
    public String main(Map<String, Object> model){
        model.put("some", "Список всех заведенных IADDR.");
        model.put("data", repository.findAll());
        return "sbc/iaddr";
    }
}
