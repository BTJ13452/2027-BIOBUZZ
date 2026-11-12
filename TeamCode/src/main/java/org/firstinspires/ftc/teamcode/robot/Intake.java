package org.firstinspires.ftc.teamcode.robot;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.util.AxonServo;
import org.firstinspires.ftc.teamcode.util.BTJConfiguration;
import org.firstinspires.ftc.teamcode.util.BTJDirections;
import org.firstinspires.ftc.teamcode.util.BasicSystem;

public class Intake implements BasicSystem {
    final double MOTOR_INTAKE_POWER = 1;
    final double MOTOR_EJECT_POWER = 1;

    DcMotor motorLeft;
    DcMotor motorRight;

    public IntakeFromFlower intakeFromFlower;


    public Intake(HardwareMap hardwareMap) {
        motorLeft = hardwareMap.dcMotor.get(BTJConfiguration.INTAKE_MOTOR_LEFT_NAME);
        motorRight = hardwareMap.dcMotor.get(BTJConfiguration.INTAKE_MOTOR_RIGHT_NAME);
        motorLeft.setDirection(BTJDirections.INTAKE_MOTOR_LEFT_DIRECTION);
        motorRight.setDirection(BTJDirections.INTAKE_MOTOR_RIGHT_DIRECTION);
        motorLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        intakeFromFlower = new IntakeFromFlower(hardwareMap);
    }

    @Override
    public void activate() {
        motorLeft.setPower(MOTOR_INTAKE_POWER);
        motorRight.setPower(MOTOR_INTAKE_POWER);
    }

    @Override
    public void deactivate() {
        motorLeft.setPower(0);
        motorRight.setPower(0);
    }

    public void eject() {
        motorLeft.setPower(MOTOR_EJECT_POWER);
        motorRight.setPower(MOTOR_EJECT_POWER);
    }

    @Override
    public boolean isActive() {
        return Math.abs(MOTOR_INTAKE_POWER - motorLeft.getPower()) < 1e-9 || Math.abs(MOTOR_INTAKE_POWER - motorRight.getPower()) < 1e-9;
    }

    @Override
    public void emergencyStop() {
        motorLeft.setPower(0);
        motorRight.setPower(0);
    }

    @Override
    public String toString() {
        return "Left motor power: " + motorLeft.getPower() + "\n" +
                "Right motor power: " + motorRight.getPower() + "\n" +
                "intakeFromFlower: " + "\n" +
                intakeFromFlower.toString();
    }

    public class IntakeFromFlower implements BasicSystem {
        final double FLOWER_SERVO_RIGHT_INTAKE_POSITION = 1;
        final double FLOWER_SERVO_LEFT_INTAKE_POSITION = 1;
        final double FLOWER_SERVO_RIGHT_DEFAULT_POSITION = 0.65;
        final double FLOWER_SERVO_LEFT_DEFAULT_POSITION = 0.65;
        final double FLOWER_SERVO_RIGHT_INIT_POSITION = 0.5;
        final double FLOWER_SERVO_LEFT_INIT_POSITION = 0.5;


        AxonServo flowerServoRight;
        AxonServo flowerServoLeft;

        public IntakeFromFlower(HardwareMap hardwareMap) {
            flowerServoRight = new AxonServo(hardwareMap, BTJConfiguration.FLOWER_INTAKE_SERVO_RIGHT_NAME);
            flowerServoLeft = new AxonServo(hardwareMap, BTJConfiguration.FLOWER_INTAKE_SERVO_LEFT_NAME);
            flowerServoRight.setDirection(BTJDirections.FLOWER_INTAKE_SERVO_RIGHT_DIRECTION);
            flowerServoLeft.setDirection(BTJDirections.FLOWER_INTAKE_SERVO_LEFT_DIRECTION);
            moveToInitPosition();
        }

        public void moveToInitPosition() {
            flowerServoRight.setPosition(FLOWER_SERVO_RIGHT_INIT_POSITION);
            flowerServoLeft.setPosition(FLOWER_SERVO_LEFT_INIT_POSITION);
        }

        @Override
        public void activate() {
            flowerServoRight.setPosition(FLOWER_SERVO_RIGHT_INTAKE_POSITION);
            flowerServoLeft.setPosition(FLOWER_SERVO_LEFT_INTAKE_POSITION);
        }

        @Override
        public void deactivate() {
            flowerServoRight.setPosition(FLOWER_SERVO_RIGHT_DEFAULT_POSITION);
            flowerServoLeft.setPosition(FLOWER_SERVO_LEFT_DEFAULT_POSITION);
        }

        @Override
        public boolean isActive() {
            return flowerServoRight.getTargetPosition() == FLOWER_SERVO_RIGHT_INTAKE_POSITION;
        }

        @Override
        public String toString() {
            return "Is active: " + isActive();
        }
    }
}
