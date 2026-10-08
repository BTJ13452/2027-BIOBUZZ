package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

public abstract class Drive {


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
