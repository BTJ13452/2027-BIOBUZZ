package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class Drive {



    final double ROTATION_SENSITIVITY = 1;

    final String FRONT_LEFT_WHEEL_NAME = "Front left";
    final String FRONT_RIGHT_WHEEL_NAME = "Front right";
    final String BACK_LEFT_WHEEL_NAME = "Back left";
    final String BACK_RIGHT_WHEEL_NAME = "Back right";

    final Direction FRONT_LEFT_WHEEL_DIRECTION = Direction.FORWARD;
    final Direction FRONT_RIGHT_WHEEL_DIRECTION = Direction.REVERSE;
    final Direction BACK_LEFT_WHEEL_DIRECTION = Direction.FORWARD;
    final Direction BACK_RIGHT_WHEEL_DIRECTION = Direction.REVERSE;


    IMU imu;

    DcMotor motorFrontLeft;
    DcMotor motorFrontRight;
    DcMotor motorBackLeft;
    DcMotor motorBackRight;

    double heading;

    boolean isFieldoOn;

    public Drive(HardwareMap hardwareMap, double heading, boolean isFildoOn) {
        motorFrontLeft = hardwareMap.dcMotor.get(FRONT_LEFT_WHEEL_NAME);
        motorFrontRight = hardwareMap.dcMotor.get(FRONT_RIGHT_WHEEL_NAME);
        motorBackLeft = hardwareMap.dcMotor.get(BACK_LEFT_WHEEL_NAME);
        motorBackRight = hardwareMap.dcMotor.get(BACK_RIGHT_WHEEL_NAME);

        motorFrontLeft.setDirection(FRONT_LEFT_WHEEL_DIRECTION);
        motorFrontRight.setDirection(FRONT_RIGHT_WHEEL_DIRECTION);
        motorBackLeft.setDirection(BACK_LEFT_WHEEL_DIRECTION);
        motorBackRight.setDirection(BACK_RIGHT_WHEEL_DIRECTION);

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

    public void deactivateFieldo() {
        isFieldoOn = false;
    }

    public void activateFieldo() {
        isFieldoOn = true;
    }

    public boolean isFildoOn() {
        return isFieldoOn;
    }
}
