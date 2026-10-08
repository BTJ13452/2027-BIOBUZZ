package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class BTJRobot {
    final boolean START_FIELDO = false;

    public Drive drive;
    public Intake intake;
    public Shooter shooter;

    public BTJRobot(HardwareMap hardwareMap, double startHeading){
        drive = new RegularDrive(hardwareMap, startHeading, START_FIELDO);
//        intake = new Intake(hardwareMap);
//        shooter = new Shooter(hardwareMap);
    }


}
