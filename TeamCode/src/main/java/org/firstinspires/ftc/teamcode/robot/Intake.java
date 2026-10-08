package org.firstinspires.ftc.teamcode.robot;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake implements BasicSystem{

    final Direction MOTOR_DIRECTION = Direction.REVERSE;
    final double MOTOR_POWER = 1;


    DcMotor motor;

    public Intake(HardwareMap hardwareMap) {
        motor = hardwareMap.dcMotor.get(BTJconfigorasion.MOTOR_NAME);

        motor.setDirection(MOTOR_DIRECTION);

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
