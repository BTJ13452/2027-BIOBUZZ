package org.firstinspires.ftc.teamcode.robot;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.util.BTJConfiguration;
import org.firstinspires.ftc.teamcode.util.BTJDirections;

public class Intake implements BasicSystem{


    final double MOTOR_POWER = 1;


    DcMotor motor;

    public Intake(HardwareMap hardwareMap) {
        motor = hardwareMap.dcMotor.get(BTJConfiguration.MOTOR_NAME);

        motor.setDirection(BTJDirections.MOTOR_DIRECTION);

        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void activate() {
        motor.setPower(MOTOR_POWER);
    }

    @Override
    public void deactivate() {
        motor.setPower(0);
    }

    @Override
    public void eStop() {
        motor.setPower(0);
    }

    @Override
    public boolean isActive() {
        return Math.abs(MOTOR_POWER - motor.getPower()) < 1e-9;
    }

}
