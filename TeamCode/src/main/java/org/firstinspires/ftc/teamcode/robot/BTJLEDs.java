package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.LED;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.BTJConfiguration;
import org.firstinspires.ftc.teamcode.util.BTJSystem;

public class BTJLEDs implements BTJSystem {

    final double VALUE_FOR_GREEN = 0.500;
    final double VALUE_FOR_RED = 0.277;
    final double VALUE_FOR_OFF = 0.0;
    Servo leftLED;
    Servo rightLED;


    public BTJLEDs(HardwareMap hardwareMap) {
        leftLED = hardwareMap.servo.get(BTJConfiguration.LEFT_LED);
        rightLED = hardwareMap.servo.get(BTJConfiguration.RIGHT_LED);
    }

    public void green() {
        leftLED.setPosition(VALUE_FOR_GREEN);
        rightLED.setPosition(VALUE_FOR_GREEN);
    }

    public void red() {
        leftLED.setPosition(VALUE_FOR_RED);
        rightLED.setPosition(VALUE_FOR_RED);
    }


    public void off() {
        leftLED.setPosition(VALUE_FOR_OFF);
        rightLED.setPosition(VALUE_FOR_OFF);

    }

    @Override
    public void emergencyStop() {

    }

    @Override
    public String toString() {
        return "";
    }
}
