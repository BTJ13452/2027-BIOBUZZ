package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.LED;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.BTJConfiguration;
import org.firstinspires.ftc.teamcode.util.BTJSystem;

public class BTJLEDs implements BTJSystem {

    public static enum Color{
        OFF,
        GREEN,
        RED;

        public double getValue(){
            switch (this){
                case OFF:
                    return 0.0;
                case RED:
                    return 0.277;
                case GREEN:
                    return 0.500;
                default:
                    throw (new IllegalArgumentException());
            }
        }
    }


    Servo leftLED;
    Servo rightLED;


    public BTJLEDs(HardwareMap hardwareMap) {
        leftLED = hardwareMap.servo.get(BTJConfiguration.LEFT_LED);
        rightLED = hardwareMap.servo.get(BTJConfiguration.RIGHT_LED);
    }

    public void setColor(Color color){
        leftLED.setPosition(color.getValue());
        rightLED.setPosition(color.getValue());
    }

    @Override
    public String toString() {
        return "";
    }
}
