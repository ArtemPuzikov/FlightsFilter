package com.gridnine.testing;

public class DepartureBeforeCurrentTime implements Rule{
    private static DepartureBeforeCurrentTime instance;

    private DepartureBeforeCurrentTime(){}

    public static DepartureBeforeCurrentTime getInstance() {
        if (instance == null) {
            instance = new DepartureBeforeCurrentTime();
        }
        return instance;
    }
}
