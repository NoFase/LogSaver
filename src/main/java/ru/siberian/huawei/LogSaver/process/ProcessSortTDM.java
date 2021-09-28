package ru.siberian.huawei.LogSaver.process;

import ru.siberian.huawei.LogSaver.analysFile.TdmAnalyser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProcessSortTDM {
    private ArrayList<String> lines;
    private HashMap<Integer, ArrayList> mapTg = new HashMap<>();

    public ProcessSortTDM(ArrayList<String> lines) {
        this.lines = lines;
        startSort();
    }

    private void startSort() {
        TsForTable ts;
        ArrayList<TsForTable> listOfTs;
        for (String line : lines) {//обход всех строк

            ts = new TdmAnalyser(line).filer(); // инициализация класса строки
//            System.out.println(ts.toString());
            if (ts != null) {// проверка на null для клсса строки
                if (mapTg.get(ts.getTrunkNumber()) != null) { // проверка на то, что в map существуеь уже транковая группа
                    listOfTs = mapTg.get(ts.getTrunkNumber());// достаем из map массив со строками
                    listOfTs.add(ts);// добавляем к массиву новую строку
                    mapTg.put(ts.getTrunkNumber(), listOfTs); // заводим новое значение массива к существующему транку в map
                } else {
                    listOfTs = new ArrayList<>(); // инициализируем массив
                    listOfTs.add(ts); // добавляем в массив строку
                    mapTg.put(ts.getTrunkNumber(), listOfTs);// создаем в map новый транк с соответсвущим ему массивом
                }
                listOfTs = null;// зануляем, чтобы на следуещем цикле не перерзаписывался
            }
        }
        for (Map.Entry key: mapTg.entrySet()){
//            System.out.println(key.getValue());
        }

    }


}
