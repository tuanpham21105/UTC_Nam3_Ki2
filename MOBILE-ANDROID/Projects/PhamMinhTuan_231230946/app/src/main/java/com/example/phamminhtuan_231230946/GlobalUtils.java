package com.example.phamminhtuan_231230946;

public class GlobalUtils {
    public static int A = 46;
    public static int B = 21;
    public static int C = 1;

    public static float ceil(float value, int places) {

        double scale = Math.pow(10, places);

        return (float) (Math.ceil(value * scale) / scale);
    }
}
