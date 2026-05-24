package com.example.onthi_240503;

public class GlobalUtils {
    public static int A = 46;
    public static int B = 21;

    public static float ceil(float value, int places) {

        double scale = Math.pow(10, places);

        return (float) (Math.ceil(value * scale) / scale);
    }
}
