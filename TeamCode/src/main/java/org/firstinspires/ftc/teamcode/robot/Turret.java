package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.util.AxonServo;
import org.firstinspires.ftc.teamcode.util.BTJConfiguration;
import org.firstinspires.ftc.teamcode.util.BTJDirections;
import org.firstinspires.ftc.teamcode.util.BTJPID;
import org.firstinspires.ftc.teamcode.util.BTJSystem;

public class Turret implements BTJSystem {

    final double KP = 1;
    final double KI = 0;
    final double KD = 0;

    final double MAX_ANGLE = 180;

    final double MIN_ANGLE = -180;
    final double GEAR_RATIO = 2.75;
    AxonServo axonServo;
    double targetAngle;
    BTJPID pid;

    boolean isStopped;


    public Turret(HardwareMap hardwareMap) {
        axonServo = new AxonServo(hardwareMap, BTJConfiguration.TURRET_SERVO_NAME, BTJConfiguration.TURRET_ENCODER_NAME);
        axonServo.setDirection(BTJDirections.TURRET_SERVO_DIRECTION);
        targetAngle = 0;
        pid = new BTJPID(KP, KI, KD);
        isStopped = false;
    }

    public void setAngle(double radians) {
        if (radians > MAX_ANGLE || radians < MIN_ANGLE)
            throw (new IllegalArgumentException("radiance = " + radians + " should be between " + MIN_ANGLE + " and " + MAX_ANGLE));

        targetAngle = radians;
    }

    @Override
    public void emergencyStop() {
        axonServo.setPower(0);
        isStopped = true;
    }

    @Override
    public void update() {
        if (!isStopped)
            axonServo.setPower(pid.power(targetAngle * GEAR_RATIO - axonServo.getTotalAngle()));
    }

    @Override
    public String toString() {
        return "Target angle: " + targetAngle + "\n" +
                "Servo power: " + axonServo.getPower() + "\n" +
                "PID: " + pid.toString();
    }
}

