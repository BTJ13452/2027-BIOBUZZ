package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.BTJConfiguration;
import org.firstinspires.ftc.teamcode.util.BTJDirections;
import org.firstinspires.ftc.teamcode.util.BTJLimeLight;
import org.firstinspires.ftc.teamcode.util.BasicSystem;

public class Shooter implements BasicSystem {

    final double MOTORS_POWER = 1;
    final double COEFFICIENT = 2000;
    final double EXPONENT = 1;
    final static double KP = 0;
    public static double KI = 0;
    final static double KD = 0;
    final static double KF = 0;
    final double FLOWER_SERVO_RIGHT_SHOOTER_POSITION = 1;
    final double FLOWER_SERVO_RIGHT_DEFAULT_POSITION = 0;
    final double FLOWER_SERVO_LEFT_SHOOTER_POSITION = 1;
    final double FLOWER_SERVO_LEFT_DEFAULT_POSITION = 0;

    PIDFCoefficients pidfCoefficients;

    DcMotorEx shooterMotor1;
    DcMotorEx shooterMotor2;
    Servo flowerServoRight;
    Servo flowerServoLeft;

    public Shooter(HardwareMap hardwareMap) {
        shooterMotor1 = hardwareMap.get(DcMotorEx.class, BTJConfiguration.SHOOTER_MOTOR1_NAME);
        shooterMotor2 = hardwareMap.get(DcMotorEx.class, BTJConfiguration.SHOOTER_MOTOR2_NAME);

        shooterMotor1.setDirection(BTJDirections.SHOOTER_MOTOR1_DIRECTION);
        shooterMotor2.setDirection(BTJDirections.SHOOTER_MOTOR2_DIRECTION);

        shooterMotor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        shooterMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        pidfCoefficients = new PIDFCoefficients(KP, KD, KI, KF);

//        flowerServoRight=hardwareMap.servo.get(BTJConfiguration.FLOWER_SHOOTER_SERVO_RIGHT_NAME);
//        flowerServoRight=hardwareMap.servo.get(BTJConfiguration.FLOWER_SHOOTER_SERVO_LEFT_NAME);
//        flowerServoRight.setDirection(BTJDirections.FLOWER_SHOOTER_SERVO_RIGHT_DIRECTION);
//        flowerServoRight.setDirection(BTJDirections.FLOWER_SHOOTER_SERVO_LEFT_DIRECTION);
//        stopAimingToShooter();
    }

    public void runByPidf() {
        shooterMotor1.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
        shooterMotor2.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);

    }

    public double shooterByDistance() {
        return COEFFICIENT * Math.pow(EXPONENT, BTJLimeLight.getDistance());
    }

    public void aimToFlower(){
        flowerServoRight.setPosition(FLOWER_SERVO_RIGHT_SHOOTER_POSITION);
        flowerServoLeft.setPosition(FLOWER_SERVO_LEFT_SHOOTER_POSITION);
    }

    public void stopAimingToShooter(){
        flowerServoRight.setPosition(FLOWER_SERVO_RIGHT_DEFAULT_POSITION);
        flowerServoLeft.setPosition(FLOWER_SERVO_LEFT_DEFAULT_POSITION);
    }


    @Override
    public void activate() {
        shooterMotor1.setPower(MOTORS_POWER);
        shooterMotor2.setPower(MOTORS_POWER);
    }


    @Override
    public void deactivate() {
        shooterMotor1.setPower(0);
        shooterMotor2.setPower(0);
    }

    @Override
    public boolean isActive() {
        return shooterMotor1.getPower() != 0;
    }

    @Override
    public void emergencyStop() {
        shooterMotor1.setPower(0);
        shooterMotor2.setPower(0);
    }

    @Override
    public String toString() {
        return "Shooter motor power: " + shooterMotor1.getPower() + "\n" +
                "Shooter motor2 power: " + shooterMotor2.getPower() + "\n" +
                "PIDF Coefficients: " + pidfCoefficients.toString();

    }
}