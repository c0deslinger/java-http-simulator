package com.exel.simulator.utils;

import java.util.UUID;

public class DataUtils {
    public static String generateTrxId() {
        return (UUID.randomUUID().toString().replaceAll("-",""))
                + (int) ((Math.random() * 8) + 1);
    }
}
