package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class BTJDirections {

    public static final DcMotorEx.Direction FRONT_LEFT_WHEEL_DIRECTION = DcMotorSimple.Direction.REVERSE;
    public static final DcMotorEx.Direction FRONT_RIGHT_WHEEL_DIRECTION = DcMotorSimple.Direction.FORWARD;
    public static final DcMotorEx.Direction BACK_LEFT_WHEEL_DIRECTION = DcMotorSimple.Direction.REVERSE;
    public static final DcMotorEx.Direction BACK_RIGHT_WHEEL_DIRECTION = DcMotorSimple.Direction.FORWARD;

    public static final DcMotorEx.Direction INTAKE_MOTOR_DIRECTION = DcMotorSimple.Direction.REVERSE;
    public static final DcMotorEx.Direction SHOOTER_MOTOR1_DIRECTION = DcMotorSimple.Direction.REVERSE;
    public static final DcMotorSimple.Direction SHOOTER_MOTOR2_DIRECTION = DcMotorSimple.Direction.FORWARD;

}
