package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServoImpl;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class AxonCRServo extends CRServoImpl {
    final double RADIANS_PER_ROTATION = 2 * Math.PI;
    final double RADIANS_PER_VOLT = RADIANS_PER_ROTATION / 3.3;
    final double ANGLE_WRAP_THRESHOLD = Math.PI;

    AnalogInput encoder;

    double lastAngle = 0;
    int rotationCount = 0;

    public AxonCRServo(HardwareMap hardwareMap, String servoName, String encoderName) {
        super(hardwareMap.crservo.get(servoName).getController(), hardwareMap.crservo.get(servoName).getPortNumber());
        encoder = hardwareMap.analogInput.get(encoderName);
    }

    public double getAngle() {
        if (getDirection()==Direction.FORWARD){
            return RADIANS_PER_ROTATION - encoder.getVoltage() * RADIANS_PER_VOLT;
        }
        else {
            return encoder.getVoltage() * RADIANS_PER_VOLT;
        }
    }

    public void update() {
        double currentAngle = getAngle();
        if (lastAngle - currentAngle > ANGLE_WRAP_THRESHOLD)
            rotationCount++;
        if (currentAngle - lastAngle > ANGLE_WRAP_THRESHOLD)
            rotationCount--;

        lastAngle = currentAngle;
    }

    public double getTotalAngle() {
        return rotationCount * RADIANS_PER_ROTATION + getAngle();
    }


}
