package org.firstinspires.ftc.teamcode.opmodes.tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.BTJConfiguration;

@Disabled
@TeleOp
public class TestAxonEncoder extends OpMode {

    AnalogInput encoder;
    CRServo axon;


    @Override
    public void init() {
        encoder = hardwareMap.analogInput.get("encoder test");
        axon = hardwareMap.crservo.get("axon test");
    }

    @Override
    public void start() {

    }

    @Override
    public void loop() {
        if (gamepad1.aWasPressed()) {
            axon.setPower(0.2);
        }

        if ((gamepad1.bWasPressed())) {
            axon.setPower(0);
        }

        telemetry.addData("v", encoder.getVoltage());
        telemetry.update();
    }
}
