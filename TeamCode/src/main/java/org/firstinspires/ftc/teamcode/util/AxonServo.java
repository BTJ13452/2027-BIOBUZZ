package org.firstinspires.ftc.teamcode.util;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServoImpl;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class AxonServo extends CRServoImpl {
    final double RADIANS_PER_VOLT = 2 * Math.PI / 3.3;
    final double RADIANS_PER_ROTATION = 2 * Math.PI;
    final double ANGLE_WRAP_THRESHOLD = Math.PI;

    AnalogInput encoder;

    double lastAngle = 0;
    int rotationCount = 0;

    public AxonServo(HardwareMap hardwareMap, String servoName, String encoderName) {
        super(hardwareMap.crservo.get(servoName).getController(), hardwareMap.crservo.get(servoName).getPortNumber());
        encoder = hardwareMap.analogInput.get(encoderName);
    }

    public void setEncoder(HardwareMap hardwareMap, String encoderName) {
        encoder = hardwareMap.analogInput.get(encoderName);
        this.getPower();
    }

    public double getAngel() {
        return encoder.getVoltage() * RADIANS_PER_VOLT;
    }

    public void update() {
        double currentAngle = getAngel();
        if (lastAngle - currentAngle > ANGLE_WRAP_THRESHOLD)
            rotationCount++;
        if (currentAngle - lastAngle > ANGLE_WRAP_THRESHOLD)
            rotationCount--;

        lastAngle = currentAngle;
    }

    public double getTotalAngle() {
        return rotationCount * RADIANS_PER_ROTATION + getAngel();
    }


}
