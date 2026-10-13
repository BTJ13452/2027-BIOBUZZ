package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import org.firstinspires.ftc.teamcode.util.BTJConfiguration;
import org.firstinspires.ftc.teamcode.util.BTJDirections;
import org.firstinspires.ftc.teamcode.util.BTJPID;

public class Shooter implements BasicSystem{

    final double MOTORS_POWER = 1;
    public static double kp = 80;
    public static double kd = -5;
    public static double ki = 0;
    public static double kf = 14.5;
    DcMotorEx motor1;
    DcMotorEx motor2;

    public Shooter(HardwareMap hardwareMap) {
        motor1 = hardwareMap.get(DcMotorEx.class, BTJConfiguration.SHOOTER_MOTOR1_NAME);
        motor2 = hardwareMap.get(DcMotorEx.class,BTJConfiguration.SHOOTER_MOTOR2_NAME);

        motor1.setDirection(BTJDirections.SHOOTER_MOTOR1_DIRECTION);
        motor2.setDirection(BTJDirections.SHOOTER_MOTOR2_DIRECTION);

        motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(kp, ki, kd, kf);
        motor1.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER,new PIDFCoefficients());
        motor2.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER,new PIDFCoefficients());

    }

    public void runByPidf(){
        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(kp,ki,kd,kf);
        motor1.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER,new PIDFCoefficients());
        motor2.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER,new PIDFCoefficients());


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