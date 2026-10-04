package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.robot.Drive;

public class BTJTeleOp extends OpMode {

    final int START_HEADING = 0;
    final boolean START_FIELDO = false;

    Drive drive;


    @Override
    public void init() {
        drive = new Drive(hardwareMap, START_HEADING, START_FIELDO);
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
    }
}
