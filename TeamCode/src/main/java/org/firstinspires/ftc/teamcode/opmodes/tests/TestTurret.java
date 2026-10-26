package org.firstinspires.ftc.teamcode.opmodes.tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robot.Turret;

@TeleOp
public class TestTurret extends OpMode {

    Turret turret;

    double targetAngle = 0;

    @Override
    public void init() {
        turret = new Turret(hardwareMap);
        turret.setAngle(targetAngle);
    }

    @Override
    public void loop() {

        if (gamepad1.aWasPressed()) {
            targetAngle = Math.PI;
        }

        if (gamepad1.xWasPressed()) {
            targetAngle = Math.PI / 2;
        }

        if (gamepad1.yWasPressed()) {
            targetAngle = -Math.PI / 2;
        }

        if (gamepad1.bWasPressed()) {
            targetAngle = 0;
        }

        turret.setAngle(targetAngle);
        turret.update();

        telemetry.addData("Target angle (rad)", targetAngle);

        telemetry.addData("Target angle (degrees)", Math.toDegrees(targetAngle));
        telemetry.addLine(turret.toString());

        telemetry.update();
    }
}


