package com.mixleylogic.infinitum;

import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;

public class VecMath {
    public static Vector2f getPitchAndYaw(Vector3d vec) {
        double fraction;
        double a;

        double yawRadians;
        if (vec.x != 0.0) {
            fraction = vec.z / vec.x;
            a = Math.atan(fraction);
            if (vec.z >= 0.0) {
                if (vec.x >= 0.0) {
                    yawRadians = a;
                } else {
                    yawRadians = Math.PI + a;
                }
            } else {
                if (vec.x >= 0.0) {
                    yawRadians = (2.0 * Math.PI) + a;
                } else {
                    yawRadians = Math.PI + a;
                }
            }
        }
        else {
            yawRadians = Math.PI / 2.0;
        }
        double yawDegrees = Math.toDegrees(yawRadians);

        Vector3d rotated = vec.rotateYaw((float) -yawRadians);

        double pitchRadians;
        if (vec.x != 0.0) {
            fraction = rotated.y / rotated.x;
            a = Math.atan(fraction);
            //double pitchRadians;
            if (rotated.y >= 0.0) {
                if (rotated.x >= 0.0) {
                    pitchRadians = a;
                } else {
                    pitchRadians = Math.PI + a;
                }
            } else {
                if (rotated.x >= 0.0) {
                    pitchRadians = (2.0 * Math.PI) + a;
                } else {
                    pitchRadians = Math.PI + a;
                }
            }
        }
        else {
            pitchRadians = Math.PI / 2.0;
        }
        double pitchDegrees = Math.toDegrees(pitchRadians);

        return new Vector2f((float)pitchDegrees, (float)yawDegrees);
    }
}
