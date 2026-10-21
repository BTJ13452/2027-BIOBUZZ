package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.util.BTJSystem;

public class BTJRobot implements BTJSystem{
    final boolean START_FIELDO = false;

    public Drive drive;
    public Intake intake;
    public Shooter shooter;

    BTJSystem[] systems = new BTJSystem[]{drive, intake, shooter};

    public BTJRobot(HardwareMap hardwareMap, double startHeading) {
        drive = new RegularDrive(hardwareMap, startHeading, START_FIELDO);
        intake = new Intake(hardwareMap);
//        shooter = new Shooter(hardwareMap);
        }

    @Override
    public void emergencyStop() {
        for (int i = 0; i < systems.length; i++) {
            systems[i].emergencyStop();
        }
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < systems.length; i++) {
            output += ("\n" + systems[i].getClass() + ": \n" + systems[i].toString() +"\n");
        }
        return output;
    }

    @Override
    public void update(){
        for (int i = 0; i < systems.length; i++){
            systems[i].update();
        }
    }


}
