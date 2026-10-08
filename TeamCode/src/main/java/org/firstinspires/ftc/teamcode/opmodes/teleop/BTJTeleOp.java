package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robot.BTJRobot;

@TeleOp
public class BTJTeleOp extends OpMode {

    final int START_HEADING = 0;

    BTJRobot robot;



    @Override
    public void init() {
        robot = new BTJRobot(hardwareMap, START_HEADING);
    }

    @Override
    public void loop() {
        robot.drive.drive(gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_stick_x);

        if (gamepad1.rightStickButtonWasPressed()) {
            if (robot.drive.isFildoOn())
                robot.drive.deactivateFieldo();
            else
                robot.drive.activateFieldo();
        }


        if (gamepad1.xWasPressed()) {
            if (robot.intake.isActive())
                robot.intake.deactivate();
            else
                robot.intake.activate();
        }


        if (gamepad1.bWasPressed()){
            if (robot.shooter.isActive())
                robot.shooter.deactivate();
            else
                robot.shooter.activate();
        }

    }
}
