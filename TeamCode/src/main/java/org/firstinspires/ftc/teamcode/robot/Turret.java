package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.util.AxonCRServo;
import org.firstinspires.ftc.teamcode.util.BTJConfiguration;
import org.firstinspires.ftc.teamcode.util.BTJDirections;
import org.firstinspires.ftc.teamcode.util.BTJPID;
import org.firstinspires.ftc.teamcode.util.BTJSystem;

public class Turret implements BTJSystem {
    final double KP = 0.08;
    final double KI = 0;
    final double KD = 0;
    final double MAX_ANGLE = Math.PI;
    final double MIN_ANGLE = -Math.PI;
    final double GEAR_RATIO = 2.75;
    AxonCRServo axonCRServo;
    double targetAngle;
    BTJPID pid;
    boolean isStopped;


    public Turret(HardwareMap hardwareMap) {
        axonCRServo = new AxonCRServo(hardwareMap, BTJConfiguration.TURRET_SERVO_NAME, BTJConfiguration.TURRET_ENCODER_NAME);
        axonCRServo.setDirection(BTJDirections.TURRET_SERVO_DIRECTION);
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
        axonCRServo.setPower(0);
        isStopped = true;
    }

    @Override
    public void update() {
        axonCRServo.update();
        if (!isStopped)
            axonCRServo.setPower(pid.power(targetAngle * GEAR_RATIO - axonCRServo.getTotalAngle()));
    }

    @Override
    public String toString() {
        return "Target angle: " + targetAngle + "\n" +
                "currentAngle" + axonCRServo.getTotalAngle() + "\n" +
                "Servo power: " + axonCRServo.getPower() + "\n" +
                "PID:  " + pid.toString();
    }
}
