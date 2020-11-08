package com.gridnine.testing;

public class ArriveBeforeDepartureTime implements Rule{
    private static ArriveBeforeDepartureTime instance;

    private ArriveBeforeDepartureTime(){}

    public static ArriveBeforeDepartureTime getInstance() {
        if (instance == null) {
            instance = new ArriveBeforeDepartureTime();
        }
        return instance;
    }
}
