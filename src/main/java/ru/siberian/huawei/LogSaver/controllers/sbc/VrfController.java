package ru.siberian.huawei.LogSaver.controllers.sbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.siberian.huawei.LogSaver.repository.VrfRepository;

import java.util.Map;

@Controller
public class VrfController {

    @Autowired
    private VrfRepository repository;

    @GetMapping("sbc/vrf")
    public String main(Map<String, Object> model){
        model.put("some", "Список всех заведенных VRF.");
        model.put("data", repository.findAll());
        return "sbc/vrf";
    }
}