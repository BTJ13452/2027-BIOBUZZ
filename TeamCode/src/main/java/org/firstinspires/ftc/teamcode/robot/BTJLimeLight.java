package org.firstinspires.ftc.teamcode.robot;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;

public class BTJLimeLight {

    static Limelight3A limelight;
    static LLResult llResult;


    public BTJLimeLight(HardwareMap hardwareMap) {
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
    }


    public static void limeLightStart(int PipeLine) {
        limelight.start();
        limelight.pipelineSwitch(PipeLine);
    }

    public static void limeLightStop() {
        limelight.stop();
    }

    public static void getLatestResult() {
        if (llResult != null) {
            llResult = limelight.getLatestResult();
        }
    }

    public static void getDistance() {
        if (llResult != null) {
            llResult = limelight.getLatestResult();
            llResult.getTa();
        }

    }

    public static void getAngele() {
        if (llResult != null) {
            llResult = limelight.getLatestResult();
            llResult.getTx();
        }

    }


}
