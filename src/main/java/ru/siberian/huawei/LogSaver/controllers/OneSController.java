package ru.siberian.huawei.LogSaver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.siberian.huawei.LogSaver.repository.OneSRepository;

import java.util.Map;

@Controller
public class OneSController {

    @Autowired
    private OneSRepository oneSRepository;

//    @RequestMapping(value = "/sbc/ones.mustache")
    @GetMapping("sbc/ones")
    public String main(Map<String, Object> model){
        model.put("some", "Список всех заведенных 1с.");
        model.put("data", oneSRepository.findAll());
        return "sbc/ones";
    }
}
