package ru.siberian.huawei.LogSaver.controllers.sbc;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.GetMapping;
        import ru.siberian.huawei.LogSaver.repository.IrtRepository;

        import java.util.Map;

@Controller
public class IrtController {

    @Autowired
    private IrtRepository repository;

    @GetMapping("sbc/irt")
    public String main(Map<String, Object> model){
        model.put("some", "Список всех заведенных IRT.");
        model.put("data", repository.findAll());
//        while (repository.findAll().iterator().hasNext()){
//            System.out.println(repository.findAll().iterator().next().getOfcNames());
//        }
        return "sbc/irt";
    }
}
