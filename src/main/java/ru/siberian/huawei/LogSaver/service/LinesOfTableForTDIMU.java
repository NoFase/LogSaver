package ru.siberian.huawei.LogSaver.service;

public class LinesOfTableForTDIMU {
    private int numberOfE1;
    private int numberOfSTM;
    private String typeOfSTM;

    public LinesOfTableForTDIMU(int numberOfE1, int numberOfSTM, String typeOfSTM) {
        this.numberOfE1 = numberOfE1;
        this.numberOfSTM = numberOfSTM;
        this.typeOfSTM = typeOfSTM;
    }

    public String creatingLineOfTableForTDIMU(){
        String string = "";
        int tkcStart = numberOfSTM * 64 *32 + numberOfE1 * 32;
        int tkcEnd = tkcStart + 31;
        string = "<tr>" +
                "<td>" + numberOfE1 + "</td>" +
                "<td>" + tkcStart + "</td>" +
                "<td>" + tkcEnd + "</td>"+
                "<td>" +
                new KLM(typeOfSTM, numberOfE1).getKlm() +
                "</td>" +
                "<td>0</td>" +
                "<td>31</td>" +
                "<td>0</td>" +
                "<td>31</td>" +
                "<td>1711059</td>" +
                "<td>Mango</td>"
        ;
        return string;
    }
}
