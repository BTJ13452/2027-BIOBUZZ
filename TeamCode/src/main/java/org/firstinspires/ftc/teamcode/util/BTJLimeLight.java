package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;

public class BTJLimeLight {

    static Limelight3A limelight;
    static LLResult llResult;


    public BTJLimeLight(HardwareMap hardwareMap, AllianceColor allianceColor) {
        limelight = hardwareMap.get(Limelight3A.class, BTJConfiguration.LIMELIGHT);
        limeLightStart(allianceColor.getValue());
    }


    public static void limeLightStart(int PipeLine) {
        limelight.start();
        limelight.pipelineSwitch(PipeLine);
    }

    public static void limeLightStop() {
        limelight.stop();
    }


    public static double getDistance() {
        llResult = limelight.getLatestResult();
        if (llResult != null) {
            return llResult.getTa();
        }
        return 0;
    }

    public static double getAngel() {
        llResult = limelight.getLatestResult();
        if (llResult != null) {
            return llResult.getTx();
        }
        return 0;
    }

}
