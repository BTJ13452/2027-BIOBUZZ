package org.firstinspires.ftc.teamcode.opmodes.tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robot.Intake;

@Disabled
@TeleOp
public class TestFlowerIntake extends OpMode {
    Intake intake;

    @Override
    public void init() {
         intake=new Intake(hardwareMap);
    }

    @Override
    public void loop() {
        if(gamepad1.aWasPressed()){
            intake.intakeFromFlower();
        }

        if (gamepad1.bWasPressed()) {
            intake.stopIntakeFromFlower();
        }

    }
}
