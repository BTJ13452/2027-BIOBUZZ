package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.util.BTJSystem;

public class BTJRobot{
    final boolean START_FIELDO = false;

    public Drive drive;
    public Intake intake;
    public Shooter shooter;

    BTJSystem[] systems = new BTJSystem[]{drive,intake,shooter};

    public BTJRobot(HardwareMap hardwareMap, double startHeading){
        drive = new RegularDrive(hardwareMap, startHeading, START_FIELDO);
//        intake = new Intake(hardwareMap);
//        shooter = new Shooter(hardwareMap);
    }

    public void emergencyStop (){
        for (int i=0;i<systems.length;i++){
            systems[i].emergencyStop();
        }
    }


}
