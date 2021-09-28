package ru.siberian.huawei.LogSaver.analysFile;

import ru.siberian.huawei.LogSaver.process.TsForTable;

public class TdmAnalyser {

    String input;

    public TdmAnalyser(String input) {
        this.input = input;
    }

    public TsForTable filer(){
        String[] line = input.split("\\s+");
        TsForTable tsForTable = null;

        if (input.length() > 20 && (line[1].length()==2 && line[2].contains(":")) || (line[0].length()==2 && line[1].contains(":")) ) {
            int n = (line[0] == " ") ? 0 : -1; // для проверки если исходные данные начинаются не с пустой строки
            String cityName = "CHB";
            int tid = findingTid(line[3 + n]);
            int numberOfSTM = tid / 2048;
            int numberOfE1 = ((numberOfSTM == 0) ? tid /32 : (tid / numberOfSTM) /32);
            int ts = Integer.parseInt(line[5 + n]);
            int cic = Integer.parseInt(line[6 + n]);
            int trunkNumber = Integer.parseInt(line[7 + n]);
            String trunkName = checkingTrunkName(line, n);
            String typeOfTrunk = line[line.length - 1];
//            System.out.println(new TsForTable(cityName, numberOfSTM, numberOfE1, tid, ts, cic, trunkNumber, trunkName, typeOfTrunk).toString());
            return new TsForTable(cityName, numberOfSTM, numberOfE1, tid, ts, cic, trunkNumber, trunkName, typeOfTrunk);
        }
        return tsForTable;
    }

    private int findingTid(String s) {
        return Integer.parseInt((s == null || s.length() == 0) ? "" : (s.substring(1)));
    }

    private String checkingTrunkName(String[] line, int n){
        if (line.length == 9) return line[8 + n];
        String trunkName = "";
        for (int i = 8; i < line.length-1; i++) {
            trunkName += line[i];
        }
        return trunkName;
    }
}
