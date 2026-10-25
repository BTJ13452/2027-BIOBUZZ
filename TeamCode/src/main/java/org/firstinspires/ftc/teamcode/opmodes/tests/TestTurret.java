package org.firstinspires.ftc.teamcode.opmodes.tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.robot.Turret;

@Disabled
@TeleOp
public class TestTurret extends OpMode {
    Turret turret;

    @Override
    public void init() {
        turret = new Turret(hardwareMap);
        turret.setAngle(Math.PI);
    }


    @Override
    public void start() {

    }

    @Override
    public void loop() {
        if (gamepad1.aWasPressed()) {
            turret.setAngle(Math.PI);
        }

        if (gamepad1.xWasPressed()) {
            turret.setAngle(Math.PI/2);
        }

        if (gamepad1.yWasPressed()) {
            turret.setAngle((Math.PI*3)/2);
        }

        if ((gamepad1.bWasPressed())) {
            turret.setAngle(0);
        }

        turret.update();

    }
}



