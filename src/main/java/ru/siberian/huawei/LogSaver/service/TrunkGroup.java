package ru.siberian.huawei.LogSaver.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrunkGroup {

    private String name;
    private int number;
    private List<Integer> tkcs = new ArrayList<>();
    private List<Integer> tids = new ArrayList<>();
    private List<Integer> cics = new ArrayList<>();

    public TrunkGroup(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public void addTkcs(int tkc) {
        tkcs.add(tkc);
    }

    public void addTids(int tid) {
        tids.add(tid);
    }

    public void addCics(int cic) {
        cics.add(cic);
    }

    public int getMaxTks() {
        checkingFullE1();
        return tkcs.get(tkcs.size()-1);
    }

    public int getMinTks() {
        checkingFullE1();
        return tkcs.get(0);
    }

    public int getMaxTid() {
        checkingFullE1();
        return tids.get(tids.size()-1);
    }

    public int getMinTid() {
        checkingFullE1();
        return tids.get(0);
    }

    private void checkingFullE1(){
        Arrays.sort(tkcs.toArray());
        while (tkcs.size() < 32) {
            tkcs.add(tkcs.get(tkcs.size()-1) + 1);
        }
        Arrays.sort(tids.toArray());
        while (tids.size() < 32) {
            tids.add(tids.get(tids.size()-1) + 1);
        }
    }
}
