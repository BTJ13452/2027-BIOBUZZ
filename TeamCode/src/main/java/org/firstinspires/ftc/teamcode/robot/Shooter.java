package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.util.BTJConfiguration;
import org.firstinspires.ftc.teamcode.util.BTJDirections;

public class Shooter implements BasicSystem{

    final double MOTORS_POWER = 1;
    DcMotor motor1;
    DcMotor motor2;

    public Shooter(HardwareMap hardwareMap) {
        motor1 = hardwareMap.dcMotor.get(BTJConfiguration.SHOOTER_MOTOR1_NAME);
        motor2 = hardwareMap.dcMotor.get(BTJConfiguration.SHOOTER_MOTOR2_NAME);

        motor1.setDirection(BTJDirections.MOTOR1_DIRECTION);
        motor2.setDirection(BTJDirections.MOTOR2_DIRECTION);

        motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void activate() {
        motor1.setPower(MOTORS_POWER);
        motor2.setPower(MOTORS_POWER);
    }


    @Override
    public void deactivate() {
        motor1.setPower(0);
        motor2.setPower(0);
    }

    @Override
    public void eStop() {
        motor1.setPower(0);
        motor2.setPower(0);
    }

    @Override
    public boolean isActive() {
        return motor1.getPower() != 0;
    }

}