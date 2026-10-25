package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.CRServoImpl;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.hardware.ServoImpl;

public class AxonServo extends ServoImpl {

    double targetPosition;


    public AxonServo(HardwareMap hardwareMap, String servoName) {
        super(hardwareMap.servo.get(servoName).getController(), hardwareMap.servo.get(servoName).getPortNumber());
    }

    public double getTargetPosition() {
        return targetPosition;
    }

    @Override
    public synchronized void setPosition(double position) {
        super.setPosition(position);

        targetPosition = position;
    }
}
