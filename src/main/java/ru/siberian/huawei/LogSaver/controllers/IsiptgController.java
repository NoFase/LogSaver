package ru.siberian.huawei.LogSaver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.siberian.huawei.LogSaver.repository.IsiptgRepository;

import java.util.Map;

@Controller
public class IsiptgController {

    @Autowired
    private IsiptgRepository repository;

    @GetMapping("sbc/isiptg")
    public String main(Map<String, Object> model){
        model.put("some", "Список всех заведенных Isiptg.");
        model.put("data", repository.findAll());
        return "sbc/isiptg";
    }
}
