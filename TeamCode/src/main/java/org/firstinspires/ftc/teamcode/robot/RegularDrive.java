package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class RegularDrive extends Drive {


    IMU imu;

    DcMotor motorFrontLeft;
    DcMotor motorFrontRight;
    DcMotor motorBackLeft;
    DcMotor motorBackRight;

    double heading;


    public RegularDrive(HardwareMap hardwareMap, double heading, boolean isFildoOn) {
        motorFrontLeft = hardwareMap.dcMotor.get(BTJConfiguration.FRONT_LEFT_WHEEL_NAME);
        motorFrontRight = hardwareMap.dcMotor.get(BTJConfiguration.FRONT_RIGHT_WHEEL_NAME);
        motorBackLeft = hardwareMap.dcMotor.get(BTJConfiguration.BACK_LEFT_WHEEL_NAME);
        motorBackRight = hardwareMap.dcMotor.get(BTJConfiguration.BACK_RIGHT_WHEEL_NAME);

        motorFrontLeft.setDirection(BTJDirections.FRONT_LEFT_WHEEL_DIRECTION);
        motorFrontRight.setDirection(BTJDirections.FRONT_RIGHT_WHEEL_DIRECTION);
        motorBackLeft.setDirection(BTJDirections.BACK_LEFT_WHEEL_DIRECTION);
        motorBackRight.setDirection(BTJDirections.BACK_RIGHT_WHEEL_DIRECTION);

        motorFrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorFrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorBackLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorBackRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        imu = hardwareMap.get(IMU.class, "imu");

        imu.initialize(new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                RevHubOrientationOnRobot.UsbFacingDirection.UP)));
        resetFieldo();

        this.isFieldoOn = isFildoOn;

        this.heading = (heading / 180) * Math.PI;
    }

    @Override
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

    @Override
    public void resetFieldo() {
        imu.resetYaw();
        heading = 0;

    }
}
