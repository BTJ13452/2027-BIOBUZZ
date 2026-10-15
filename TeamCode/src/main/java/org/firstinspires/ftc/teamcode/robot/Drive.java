package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.util.BTJSystem;

public abstract class Drive implements BTJSystem {


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

    @Override
    public abstract String toString();

}
