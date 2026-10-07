package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Shooter implements BasicSystem{
    final String MOTOR1_NAME = "";
    final String MOTOR2_NAME = "";
    final Direction MOTOR1_DIRECTION = Direction.REVERSE;
    final Direction MOTOR2_DIRECTION = Direction.FORWARD;
    final double MOTORS_POWER = 1;
    DcMotor motor1;
    DcMotor motor2;

    public Shooter(HardwareMap hardwareMap) {
        motor1 = hardwareMap.dcMotor.get(MOTOR1_NAME);
        motor2 = hardwareMap.dcMotor.get(MOTOR2_NAME);

        motor1.setDirection(MOTOR1_DIRECTION);
        motor2.setDirection(MOTOR2_DIRECTION);

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