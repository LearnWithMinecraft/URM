package com.learnwithminecraft.urm.utils;

import cn.nukkit.math.Vector3;

public class VectorUtils {

    public static String vector3ToString(Vector3 vector3) {
        return String.format("%s:%s:%s", vector3.getX(), vector3.getY(), vector3.getZ());
    }

    public static Vector3 stringToVector3(String string) {
        String[] split = string.split(":");

        if (split.length < 3) {
            throw new RuntimeException("Could not load vector3 because need 3 params (x:y:z)");
        }

        double x = Double.parseDouble(split[0]);
        double y = Double.parseDouble(split[1]);
        double z = Double.parseDouble(split[2]);

        return new Vector3(x, y, z);
    }
}
