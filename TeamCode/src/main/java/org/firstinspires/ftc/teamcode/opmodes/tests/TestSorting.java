package org.firstinspires.ftc.teamcode.opmodes.tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.robot.Sorting;

@TeleOp
public class TestSorting extends OpMode {

    Sorting sorting;

    @Override
    public void init() {
        sorting = new Sorting(hardwareMap);
    }

    @Override
    public void loop() {
        if (gamepad1.aWasPressed()) {
            sorting.rightArm.activate();
        }
        if (gamepad1.aWasPressed()) {
            sorting.rightArm.deactivate();
        }

        if (gamepad1.xWasPressed()) {
            sorting.leftArm.activate();
        }

        if (gamepad1.yWasPressed()) {
            sorting.leftArm.deactivate();
        }

        telemetry.addLine("Right Arm:");

        telemetry.addData("Is Active", sorting.rightArm.isActive());

        telemetry.addLine("Left Arm:");

        telemetry.addData("Is Active", sorting.leftArm.isActive());

        telemetry.addLine(sorting.toString());
        telemetry.update();
    }
}
