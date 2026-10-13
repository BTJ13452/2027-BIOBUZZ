package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;

import org.firstinspires.ftc.teamcode.util.BTJConfiguration;

public class BTJLimeLight {

    static Limelight3A limelight;
    static LLResult llResult;


    public BTJLimeLight(HardwareMap hardwareMap) {
        limelight = hardwareMap.get(Limelight3A.class, BTJConfiguration.LIMELIGHT);
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

    public static double getDistance() {
         if (llResult != null) {
            llResult = limelight.getLatestResult();
           return llResult.getTa();
        }
         return 0;
    }

    public static double getAngele() {
        if (llResult != null) {
            llResult = limelight.getLatestResult();
            return llResult.getTx();
        }
        return 0;
    }

}
