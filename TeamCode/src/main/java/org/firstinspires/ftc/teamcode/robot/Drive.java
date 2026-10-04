package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class Drive {

    final double ROTATION_SENSITIVITY = 1;

    IMU imu;

    DcMotor motorFrontLeft;
    DcMotor motorFrontRight;
    DcMotor motorBackLeft;
    DcMotor motorBackRight;

    double heading;

    boolean isFieldoOn;

    public Drive(HardwareMap hardwareMap, double heading, boolean isFildoOn) {
        motorFrontLeft = hardwareMap.dcMotor.get("Front left");
        motorFrontRight = hardwareMap.dcMotor.get("Front right");
        motorBackLeft = hardwareMap.dcMotor.get("Back left");
        motorBackRight = hardwareMap.dcMotor.get("Back right");

        motorFrontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        motorFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBackLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        motorBackRight.setDirection(DcMotorSimple.Direction.REVERSE);

        motorFrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorFrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorBackLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorBackRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        imu = hardwareMap.get(IMU.class, "imu");

        imu.initialize(new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                RevHubOrientationOnRobot.UsbFacingDirection.UP)));
        resetIMU();

        this.isFieldoOn = isFildoOn;

        this.heading = (heading / 180) * Math.PI;
    }

    public void drive(double x, double y, double r) {
        r *= ROTATION_SENSITIVITY;


        if (isFieldoOn) {
            double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS) + heading;

            double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
            double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

            x = rotX;
            y = rotY;
        }

        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(r), 1);
        double leftFrontPower = (y + x + r) / denominator;
        double leftBackPower = (y - x + r) / denominator;
        double rightFrontPower = (y - x - r) / denominator;
        double rightBackPower = (y + x - r) / denominator;

        motorFrontLeft.setPower(leftFrontPower);
        motorBackLeft.setPower(leftBackPower);
        motorFrontRight.setPower(rightFrontPower);
        motorBackRight.setPower(rightBackPower);
    }

    public void resetIMU() {
        imu.resetYaw();
        heading = 0;
    }

    public void cancelFieldo() {
        isFieldoOn = false;
    }

    public void activateFieldo() {
        isFieldoOn = true;
    }

    public boolean isFildoOn() {
        return isFieldoOn;
    }
}
