package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

public class BTJDirections {

    public static final DcMotorSimple.Direction FRONT_LEFT_WHEEL_DIRECTION = DcMotorSimple.Direction.REVERSE;
    public static final DcMotorSimple.Direction FRONT_RIGHT_WHEEL_DIRECTION = DcMotorSimple.Direction.FORWARD;
    public static final DcMotorSimple.Direction BACK_LEFT_WHEEL_DIRECTION = DcMotorSimple.Direction.REVERSE;
    public static final DcMotorSimple.Direction BACK_RIGHT_WHEEL_DIRECTION = DcMotorSimple.Direction.FORWARD;
    public static final DcMotorSimple.Direction INTAKE_MOTOR_LEFT_DIRECTION = DcMotorSimple.Direction.REVERSE;
    public static final DcMotorSimple.Direction INTAKE_MOTOR_RIGHT_DIRECTION = DcMotorSimple.Direction.FORWARD;
    public static final DcMotorEx.Direction SHOOTER_MOTOR1_DIRECTION = DcMotorEx.Direction.FORWARD;
    public static final DcMotorEx.Direction SHOOTER_MOTOR2_DIRECTION = DcMotorEx.Direction.FORWARD;

    public static final Servo.Direction FLOWER_INTAKE_SERVO_RIGHT_DIRECTION = Servo.Direction.REVERSE;
    public static final Servo.Direction FLOWER_INTAKE_SERVO_LEFT_DIRECTION = Servo.Direction.FORWARD;
    public static final Servo.Direction FLOWER_SHOOTER_SERVO_RIGHT_DIRECTION = Servo.Direction.FORWARD;
    public static final Servo.Direction FLOWER_SHOOTER_SERVO_LEFT_DIRECTION = Servo.Direction.FORWARD;
    public static final Servo.Direction PISTON_SERVO_RIGHT_DIRECTION = Servo.Direction.FORWARD;
    public static final Servo.Direction PISTON_SERVO_LEFT_DIRECTION = Servo.Direction.FORWARD;
    public static final DcMotorSimple.Direction TURRET_SERVO_DIRECTION = DcMotorSimple.Direction.FORWARD;

}
