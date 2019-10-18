package ru.siberian.huawei.LogSaver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.siberian.huawei.LogSaver.repository.IrtRepository;

import java.util.Map;

@Controller
public class IrtController {

    @Autowired
    private IrtRepository irtRepository;

    @GetMapping("sbc/irt")
    public String main(Map<String, Object> model){
        model.put("some", "Список всех заведенных VRF.");
        model.put("data", irtRepository.findAll());
        return "sbc/irt";
    }
}
