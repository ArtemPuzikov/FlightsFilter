package com.gridnine.testing;

public class MoreThanTwoHoursGround implements Rule{
    private static MoreThanTwoHoursGround instance;

    private MoreThanTwoHoursGround(){}

    public static MoreThanTwoHoursGround getInstance() {
        if (instance == null) {
            instance = new MoreThanTwoHoursGround();
        }
        return instance;
    }
}
