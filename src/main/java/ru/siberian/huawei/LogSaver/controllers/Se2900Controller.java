package ru.siberian.huawei.LogSaver.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping(value = "/SE2900")
public class Se2900Controller {

    @GetMapping
    public String main(Map<String, Object> model){
        model.put("some", "Работа с базой данных SBC!!!");
        return "main";
    }

    @PostMapping
    protected String findAllIaddrs(Map<String, Object> model) {
        model.put("some", "Работа с базой данных SBC!");
        return "search";
    }
}
