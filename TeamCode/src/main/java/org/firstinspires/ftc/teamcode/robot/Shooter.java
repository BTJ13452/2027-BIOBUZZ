package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.teamcode.util.BTJConfiguration;
import org.firstinspires.ftc.teamcode.util.BTJDirections;
import org.firstinspires.ftc.teamcode.util.BTJLimeLight;

public class Shooter implements BasicSystem {

    final double MOTORS_POWER = 1;
    final double COEFFICIENT = 2000;
    final double EXPONENT = 1;
    final static double KP = 0;
    public static double KI = 0;
    final static double KD = 0;
    final static double KF = 0;

    PIDFCoefficients pidfCoefficients;

    DcMotorEx shooterMotor1;
    DcMotorEx shooterMotor2;

    public Shooter(HardwareMap hardwareMap) {
        shooterMotor1 = hardwareMap.get(DcMotorEx.class, BTJConfiguration.SHOOTER_MOTOR1_NAME);
        shooterMotor2 = hardwareMap.get(DcMotorEx.class, BTJConfiguration.SHOOTER_MOTOR2_NAME);

        shooterMotor1.setDirection(BTJDirections.SHOOTER_MOTOR1_DIRECTION);
        shooterMotor2.setDirection(BTJDirections.SHOOTER_MOTOR2_DIRECTION);

        shooterMotor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        shooterMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        pidfCoefficients = new PIDFCoefficients(KP, KD, KI, KF);

    }

    public void runByPidf() {
        shooterMotor1.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
        shooterMotor2.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);

    }

    public double shooterByDistance() {
        return COEFFICIENT * Math.pow(EXPONENT, BTJLimeLight.getDistance());

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
    public void eStop() {
        shooterMotor1.setPower(0);
        shooterMotor2.setPower(0);
    }

    @Override
    public boolean isActive() {
        return shooterMotor1.getPower() != 0;
    }

}