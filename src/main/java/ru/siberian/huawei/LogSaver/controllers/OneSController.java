package ru.siberian.huawei.LogSaver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.siberian.huawei.LogSaver.repository.OneSRepository;

import java.util.Map;

@Controller
//@RequestMapping(value = "/sbc/ones")
public class OneSController {

    @Autowired
    private OneSRepository repository;

    @GetMapping("sbc/ones")
    public String main(Map<String, Object> model){
        model.put("some", "Список всех заведенных 1с.");
        model.put("data", repository.findAll());
        return "sbc/ones";
    }
}
