package ru.siberian.huawei.LogSaver.process;

import ru.siberian.huawei.LogSaver.entity.sbc.*;
import ru.siberian.huawei.LogSaver.external.LoadConfigSbc;

import java.util.*;

public class ConfiguratorCustomersOnSbc {
    private Map<String, CustomerConnectionOnSBC> customers= new HashMap<>();
    private final String ONES = "0000000000";

    public ConfiguratorCustomersOnSbc() {
        LoadConfigSbc loadConfigSbc = new LoadConfigSbc();
        loadConfigSbc.loadingConfigSbc();
        Map<String, Irt> irtMap = new HashMap<>();

//        STAGE 1
//      прогоняем все IRT
        for (Map.Entry<String, Irt> irt : loadConfigSbc.getIrts().entrySet()){
            if (irtMap.containsKey(irt.getKey())) continue;
            String customerName = irt.getKey();
            List<Iaddr> iaddrs = new ArrayList<>();
            List<String> iofcs;
            List<Iofc> customerIofc = new ArrayList<>();
            Map<String, Iofc> iofcMap = new HashMap<>();
            List<Isiptg> isiptgs = new ArrayList<>();
            List<Irt> customerIRT = new ArrayList<>();
//            проверка на то, что уже обошли данную IRT
            if (!irtMap.containsKey(irt.getKey())) irtMap.put(irt.getKey(), irt.getValue());
            List<OneS> oneSList = new ArrayList<>();
            oneSList.add(new OneS(ONES));
            customerIRT.add(loadConfigSbc.getIrts().get(irt.getKey()));
//         STAGE 2
//      записываем все названия IOFC привязанные к данной IRT
            iofcs = irt.getValue().getOfcNames();
//         STAGE 3
//      прогоняем все найденные IOFC
            for (String iofc: iofcs){
//              записываем в список все найденные IOFC
                iofcMap.put(iofc, loadConfigSbc.getIofcs().get(iofc));
                customerIofc.add(loadConfigSbc.getIofcs().get(iofc));
//         STAGE 4
//              создаем ISIPTG по найденному значению из IOFC
                Isiptg isiptg = loadConfigSbc.getIsiptgs().get(loadConfigSbc.getIofcs().get(iofc).getTg1Name());
                isiptgs.add(isiptg);
//         STAGE 4*
//              поиск IADDR для данного транка
                iaddrs.add(loadConfigSbc.getIaddrs().get(isiptg.getLaddrn()));
//         STAGE 5
//              задаем найденное значение в транке RNIT
                String rnit = isiptg.getRnit();
//         STAGE 6
//              поиск соответствия RNIT==SNOT по всем IRT
                for (Map.Entry<String, Irt> irtInternal : loadConfigSbc.getIrts().entrySet()){
//                  проверка на прохождение данного IRT (в теории после данной проверки проверка * будет нужна)
                    if (irtMap.containsKey(irtInternal.getKey())) continue;
//         STAGE 7
                    if (irtInternal.getValue().getSnot().equals(rnit)){
                        customerIRT.add(loadConfigSbc.getIrts().get(irtInternal.getKey()));
                        List<String> iofcInternals = loadConfigSbc.getIrts().get(irtInternal.getKey()).getOfcNames();
//         STAGE 8
                        for (String iofcInternal: iofcInternals){
                            iofcMap.put(iofc, loadConfigSbc.getIofcs().get(iofcInternal));
                            customerIofc.add(loadConfigSbc.getIofcs().get(iofcInternal));
//         STAGE 9
                            Isiptg isiptgInternal = loadConfigSbc.getIsiptgs().get(loadConfigSbc.getIofcs().get(iofcInternal).getTg1Name());
                            isiptgs.add(isiptgInternal);
//         STAGE 10
//                          поиск IADDR для данного транка
                            iaddrs.add(loadConfigSbc.getIaddrs().get(isiptgInternal.getLaddrn()));
                        }
//         STAGE 7*
//                      * если найденный IRT отсуствует в мапе, добавляем его туда
                        if (!irtMap.containsKey(irtInternal.getKey())) irtMap.put(irtInternal.getKey(), irtInternal.getValue());

                    }
                }
            }

            CustomerConnectionOnSBC customerConnectionOnSBC = new CustomerConnectionOnSBC(oneSList, iaddrs, isiptgs, customerIofc, customerIRT);

            customers.put(customerName, customerConnectionOnSBC);
        }


//        int count = 0;
//        for (Map.Entry<String, CustomerConnectionOnSBC> cus: customers.entrySet()){
//            System.out.println(count++ + "\t" + cus.getKey() + "\n" + cus.getValue().toString());
//        }
    }
}
