package org.firstinspires.ftc.teamcode.robot;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.BTJConfiguration;
import org.firstinspires.ftc.teamcode.util.BTJDirections;
import org.firstinspires.ftc.teamcode.util.BasicSystem;

public class Intake implements BasicSystem {
    final double MOTOR_POWER = 1;
    final double FLOWER_SERVO_RIGHT_INTAKE_POSITION = 1;
    final double FLOWER_SERVO_RIGHT_DEFAULT_POSITION = 0;
    final double FLOWER_SERVO_LEFT_INTAKE_POSITION = 1;
    final double FLOWER_SERVO_LEFT_DEFAULT_POSITION = 0;

    DcMotor motor;
    Servo flowerServoRight;
    Servo flowerServoLeft;

    public Intake(HardwareMap hardwareMap) {
        motor = hardwareMap.dcMotor.get(BTJConfiguration.INTAKE_MOTOR_NAME);
        motor.setDirection(BTJDirections.INTAKE_MOTOR_DIRECTION);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        flowerServoRight=hardwareMap.servo.get(BTJConfiguration.FLOWER_INTAKE_SERVO_RIGHT_NAME);
        flowerServoLeft=hardwareMap.servo.get(BTJConfiguration.FLOWER_INTAKE_SERVO_LEFT_NAME);
        flowerServoRight.setDirection(BTJDirections.FLOWER_INTAKE_SERVO_RIGHT_DIRECTION);
        flowerServoLeft.setDirection(BTJDirections.FLOWER_INTAKE_SERVO_LEFT_DIRECTION);
        stopIntakeFromFlower();
    }

    @Override
    public void activate() {
        motor.setPower(MOTOR_POWER);
    }

    @Override
    public void deactivate() {
        motor.setPower(0);
    }

    public void intakeFromFlower(){
        flowerServoRight.setPosition(FLOWER_SERVO_RIGHT_INTAKE_POSITION);
        flowerServoLeft.setPosition(FLOWER_SERVO_LEFT_INTAKE_POSITION);
        activate();
    }

    public void stopIntakeFromFlower(){
        flowerServoRight.setPosition(FLOWER_SERVO_RIGHT_DEFAULT_POSITION);
        flowerServoLeft.setPosition(FLOWER_SERVO_LEFT_DEFAULT_POSITION);
    }




    @Override
    public boolean isActive() {
        return Math.abs(MOTOR_POWER - motor.getPower()) < 1e-9;
    }

    @Override
    public void emergencyStop() {
        motor.setPower(0);
    }

    @Override
    public String toString() {
        return "Motor power: " + motor.getPower() + "\n";
    }
}
