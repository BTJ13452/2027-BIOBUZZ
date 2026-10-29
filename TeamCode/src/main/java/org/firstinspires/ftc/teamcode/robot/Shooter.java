package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.AxonServo;
import org.firstinspires.ftc.teamcode.util.BTJConfiguration;
import org.firstinspires.ftc.teamcode.util.BTJDirections;
import org.firstinspires.ftc.teamcode.util.BTJLimeLight;
import org.firstinspires.ftc.teamcode.util.BasicSystem;
import org.firstinspires.ftc.teamcode.util.ScoringElement;

public class Shooter implements BasicSystem {

    final double MOTORS_POWER = 1;
    final double RIGHT_PISTON_POSITION_FOR_POLLEN = 1;
    final double LEFT_PISTON_POSITION_FOR_POLLEN = 1;
    final double RIGHT_PISTON_POSITION_FOR_NECTAR = 0;
    final double LEFT_PISTON_POSITION_FOR_NECTAR = 0;
    final double COEFFICIENT = 2000;
    final double EXPONENT = 1;
    final static double KP = 0;
    public static double KI = 0;
    final static double KD = 0;
    final static double KF = 0;

    PIDFCoefficients pidfCoefficients;

    public ShooterToFlower shooterToFlower;

    DcMotorEx shooterMotor1;
    DcMotorEx shooterMotor2;

    Servo servoPistonLeft;
    Servo servoPistonRight;


    public Shooter(HardwareMap hardwareMap) {
        shooterMotor1 = hardwareMap.get(DcMotorEx.class, BTJConfiguration.SHOOTER_MOTOR1_NAME);
        shooterMotor2 = hardwareMap.get(DcMotorEx.class, BTJConfiguration.SHOOTER_MOTOR2_NAME);

        shooterMotor1.setDirection(BTJDirections.SHOOTER_MOTOR1_DIRECTION);
        shooterMotor2.setDirection(BTJDirections.SHOOTER_MOTOR2_DIRECTION);

        shooterMotor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        shooterMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        servoPistonLeft = hardwareMap.servo.get(BTJConfiguration.PISTON_SERVO_LEFT_NAME);
        servoPistonRight = hardwareMap.servo.get(BTJConfiguration.PISTON_SERVO_RIGHT_NAME);

        servoPistonLeft.setDirection(BTJDirections.PISTON_SERVO_LEFT_DIRECTION);
        servoPistonRight.setDirection(BTJDirections.PISTON_SERVO_RIGHT_DIRECTION);

        pidfCoefficients = new PIDFCoefficients(KP, KD, KI, KF);

//        shooterToFlower = new ShooterToFlower(hardwareMap);
    }

    public void setShooterSize(ScoringElement scoringElement) {
        if (scoringElement == ScoringElement.POLLEN) {
            servoPistonLeft.setPosition(LEFT_PISTON_POSITION_FOR_POLLEN);
            servoPistonRight.setPosition(RIGHT_PISTON_POSITION_FOR_POLLEN);
        } else {
            servoPistonLeft.setPosition(LEFT_PISTON_POSITION_FOR_NECTAR);
            servoPistonRight.setPosition(RIGHT_PISTON_POSITION_FOR_NECTAR);
        }
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
                "PIDF Coefficients: " + pidfCoefficients.toString() + "\n" +
                "Shooter to flower: " + "\n" +
                shooterToFlower.toString();
    }


    public class ShooterToFlower implements BasicSystem {

        final double FLOWER_SERVO_RIGHT_SHOOTER_POSITION = 1;
        final double FLOWER_SERVO_RIGHT_DEFAULT_POSITION = 0;
        final double FLOWER_SERVO_LEFT_SHOOTER_POSITION = 1;
        final double FLOWER_SERVO_LEFT_DEFAULT_POSITION = 0;

        AxonServo flowerServoRight;
        AxonServo flowerServoLeft;

        public ShooterToFlower(HardwareMap hardwareMap) {
            flowerServoRight = new AxonServo(hardwareMap, BTJConfiguration.FLOWER_SHOOTER_SERVO_RIGHT_NAME);
            flowerServoLeft = new AxonServo(hardwareMap, BTJConfiguration.FLOWER_SHOOTER_SERVO_LEFT_NAME);
            flowerServoRight.setDirection(BTJDirections.FLOWER_SHOOTER_SERVO_RIGHT_DIRECTION);
            flowerServoLeft.setDirection(BTJDirections.FLOWER_SHOOTER_SERVO_LEFT_DIRECTION);
            deactivate();
        }

        @Override
        public void activate() {
            flowerServoRight.setPosition(FLOWER_SERVO_RIGHT_SHOOTER_POSITION);
            flowerServoLeft.setPosition(FLOWER_SERVO_LEFT_SHOOTER_POSITION);
        }

        @Override
        public void deactivate() {
            flowerServoRight.setPosition(FLOWER_SERVO_RIGHT_DEFAULT_POSITION);
            flowerServoLeft.setPosition(FLOWER_SERVO_LEFT_DEFAULT_POSITION);
        }

        @Override
        public boolean isActive() {
            return flowerServoRight.getTargetPosition() == FLOWER_SERVO_RIGHT_SHOOTER_POSITION;

        }

        @Override
        public String toString() {
            return "Is active: " + isActive();

        }
    }
}