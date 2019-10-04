package ru.siberian.huawei.LogSaver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.siberian.huawei.LogSaver.controllers.states.SeacherSBCParameter;
import ru.siberian.huawei.LogSaver.entity.sbc.Iaddr;
import ru.siberian.huawei.LogSaver.repository.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
//@RequestMapping(value = "/SE2900")
public class Se2900Controller {

    @Autowired
    private IaddrRepository iaddrRepository;
    @Autowired
    private IofcRepository iofcRepository;
    @Autowired
    private IrtRepository irtRepository;
    @Autowired
    private IsiptgRepository isiptgRepository;
    @Autowired
    private VrfRepository vrfRepository;
    @Autowired
    private OneSRepository oneSRepository;
    @Autowired
    private CacplcsetRepository cacplcsetRepository;
    @Autowired
    private CaccallplcRepository caccallplcRepository;

//    private CrudRepository crudRepository;

    private Iterable<Object> iterable = null;

    @GetMapping("/SE2900")
    public String main(Map<String, Object> model){
        model.put("some", "Работа с базой данных SBC!!!");
        return "se2900";
    }

    @PostMapping("findAllIp")
    public String findAllIp(@RequestParam String sign, @RequestParam String typeOfSearch, Map<String, Object> model) {
        int count = 0;
        model.put("some", "Выбран поиск по " + typeOfSearch);
        if (sign.trim().length() > 0) {
            model.put("data", findingRepository(typeOfSearch).findAllById(Collections.singleton(sign)));
        }
            else {
                iterable = findingRepository(typeOfSearch).findAll();
//            for (Object o: iterable){
//                model.put("data", count++ + o.toString());
//            }
            model.put("data", iterable);
        }

        return "se2900";
    }

    private CrudRepository findingRepository(String typeOfSearch) {
        CrudRepository crudRepository = null;
        if (typeOfSearch.equals("Iaddr")) crudRepository = iaddrRepository;
        else if (typeOfSearch.equals("Iofc")) crudRepository = iofcRepository;
        else if (typeOfSearch.equals("Irt")) crudRepository = irtRepository;
        else if (typeOfSearch.equals("Isiptg")) crudRepository = isiptgRepository;
        else if (typeOfSearch.equals("VRF")) crudRepository = vrfRepository;
        else if (typeOfSearch.equals("1c")) crudRepository = oneSRepository;
        else if (typeOfSearch.equals("Caccllplc")) crudRepository = caccallplcRepository;
        else if (typeOfSearch.equals("Cacplset")) crudRepository = cacplcsetRepository;
        return crudRepository;
    }

}
