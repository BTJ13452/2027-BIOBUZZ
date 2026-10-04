package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.robot.Drive;
import org.firstinspires.ftc.teamcode.robot.Intake;

public class BTJTeleOp extends OpMode {

    final int START_HEADING = 0;
    final boolean START_FIELDO = false;

    Drive drive;
    Intake intake;


    @Override
    public void init() {
        drive = new Drive(hardwareMap, START_HEADING, START_FIELDO);
        intake = new Intake(hardwareMap);
    }

    @Override
    public void loop() {
        drive.drive(gamepad1.right_stick_x, -gamepad1.right_stick_y, gamepad1.left_stick_x);

        if (gamepad1.rightStickButtonWasPressed()) {
            if (drive.isFildoOn())
                drive.cancelFieldo();
            else
                drive.activateFieldo();
        }


        if (gamepad1.xWasPressed()) {
            if (intake.isActive())
                intake.deactivate();
            else
                intake.activate();
        }

    }
}
