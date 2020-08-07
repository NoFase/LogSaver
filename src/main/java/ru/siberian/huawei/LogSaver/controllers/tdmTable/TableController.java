package ru.siberian.huawei.LogSaver.controllers.tdmTable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.siberian.huawei.LogSaver.entity.TDMIU.TableForSTM;
import ru.siberian.huawei.LogSaver.external.AbrOfServers;
import ru.siberian.huawei.LogSaver.managment.LoadingDataForTDM;
import ru.siberian.huawei.LogSaver.repository.TableRepository;
import ru.siberian.huawei.LogSaver.service.KLM;
import ru.siberian.huawei.LogSaver.service.LinesOfTableForTDIMU;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class TableController {
    @Autowired
    public TableRepository tableRepository;

    private Map<String, Object> model;

    @GetMapping("tables/defaultTable")
    public String main(Map<String, Object> model){
        this.model = model;
//        model.put("some", "List TDM tables");
        tableFilling();
        return "tables/defaultTable";
    }

    @PostMapping("tables/tableEditing")
    public String tableEditing(@RequestParam int numberOfSTM, @RequestParam int numberOfE1, @RequestParam int startTid,
                  @RequestParam String startTs, @RequestParam String endTs, @RequestParam String startCic,  @RequestParam String endCic,
                               @RequestParam String numberOfTrunk,  @RequestParam String projectNumber,
                               @RequestParam String nameOfTrunk, Map<String, Object> model){
        this.model = model;
        int trunkNumber;
        String typeOfSTM = "huawei";
        String klm = new KLM(typeOfSTM, numberOfE1).getKlm();

        TableForSTM tableForSTM =  new TableForSTM("SRT", numberOfSTM, numberOfE1, startTid, startTid + 31, klm, startTs, endTs, startCic, endCic, numberOfTrunk,
                projectNumber, nameOfTrunk);
        System.out.println(tableForSTM);
        tableRepository.save(tableForSTM);
        tableFilling();
        return "tables/defaultTable";
    }


    private void tableFilling(){
        model.put("some", "List TDM tables");
        List<String> abrServers = new AbrOfServers().getAbrServers();
        model.put("abrServers", abrServers);

        List<List<String>> lll = new ArrayList<>();
        List<String> lineOfTables = new ArrayList<>();
        new LoadingDataForTDM();

//        System.out.println("TableController - main - check1");

        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 63; i++) {
                lineOfTables.add(new LinesOfTableForTDIMU(i, j, "Huawei").creatingLineOfTableForTDIMU());
            }
            lll.add(lineOfTables);
        }

        model.put("lll", lll);
        model.put("tables", lineOfTables);
    }

}
