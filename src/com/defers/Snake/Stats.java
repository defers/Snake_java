package com.defers.Snake;

public class Stats {

    private static int seedsQuantity = 0;
    public static int level = 1;
    public static double currentSpeed = Settings.SPEED;

    public static void setSeedsQuantity(int _seedsQuantity) {

        seedsQuantity = _seedsQuantity;

    }

    public static int getSeedsQuantity() {

        return seedsQuantity;

    }

}
