package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

public abstract class Drive {

    final String FRONT_LEFT_WHEEL_NAME = "Front left";
    final String FRONT_RIGHT_WHEEL_NAME = "Front right";
    final String BACK_LEFT_WHEEL_NAME = "Back left";
    final String BACK_RIGHT_WHEEL_NAME = "Back right";

    final DcMotorSimple.Direction FRONT_LEFT_WHEEL_DIRECTION = DcMotorSimple.Direction.FORWARD;
    final DcMotorSimple.Direction FRONT_RIGHT_WHEEL_DIRECTION = DcMotorSimple.Direction.REVERSE;
    final DcMotorSimple.Direction BACK_LEFT_WHEEL_DIRECTION = DcMotorSimple.Direction.FORWARD;
    final DcMotorSimple.Direction BACK_RIGHT_WHEEL_DIRECTION = DcMotorSimple.Direction.REVERSE;

    final double ROTATION_SENSITIVITY = 1;

    boolean isFieldoOn;

    public void deactivateFieldo() {
        isFieldoOn = false;
    }

    public void activateFieldo() {
        isFieldoOn = true;
    }

    public boolean isFildoOn() {
        return isFieldoOn;
    }


    public abstract void drive(double x, double y, double r);
    public abstract void resetFieldo();

}
