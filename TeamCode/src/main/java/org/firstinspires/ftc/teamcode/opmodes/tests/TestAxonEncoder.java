package org.firstinspires.ftc.teamcode.opmodes.tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.util.AxonCRServo;

@Disabled
@TeleOp
public class TestAxonEncoder extends OpMode {
    AxonCRServo axon;




    @Override
    public void init() {
        axon = new AxonCRServo(hardwareMap, "axon test", "encoder test");
        axon.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    @Override
    public void start() {

    }

    @Override
    public void loop() {
        if (gamepad1.aWasPressed()) {
            axon.setPower(-0.2);
        }

        if ((gamepad1.bWasPressed())) {
            axon.setPower(0);
        }

        axon.update();
        telemetry.addData("angle", axon.getAngle());
        telemetry.addData("angle", axon.getTotalAngle());
        telemetry.update();
    }
}
