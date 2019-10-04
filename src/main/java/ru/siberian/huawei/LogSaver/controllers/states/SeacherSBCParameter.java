package ru.siberian.huawei.LogSaver.controllers.states;

public class SeacherSBCParameter {
    private String typeOfSearch;
    private Iterable<Object> iterable;

    public SeacherSBCParameter(String typeOfSearch) {
        this.typeOfSearch = typeOfSearch;
    }

    public Iterable<Object> getIterable() {
        return iterable;
    }
}
