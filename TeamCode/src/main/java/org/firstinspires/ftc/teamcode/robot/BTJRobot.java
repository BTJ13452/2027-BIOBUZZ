package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.util.AllianceColor;
import org.firstinspires.ftc.teamcode.util.BTJSystem;
import org.firstinspires.ftc.teamcode.util.Point;

public class BTJRobot implements BTJSystem {
    final boolean START_FIELDO = false;

    final Point BlueTargetLeft = new Point(84.31, 47.51);
    final Point BlueTargetRight = new Point(84.11, 86.82);
    final Point RedTargetLeft = new Point(57.98, 87.60);
    final Point RedTargetRight = new Point(57.78, 53.41);

    final Point targetLeft;
    final Point targetRight;

    public Drive drive;
    public Intake intake;
    public Shooter shooter;

    public Turret turret;

    BTJSystem[] systems = new BTJSystem[]{drive, intake, shooter, turret};

    public BTJRobot(HardwareMap hardwareMap, double startHeading, AllianceColor allianceColor) {
        drive = new RegularDrive(hardwareMap, startHeading, START_FIELDO);
        intake = new Intake(hardwareMap);
//        shooter = new Shooter(hardwareMap);

        if (allianceColor == AllianceColor.BLUE) {
            targetLeft = BlueTargetLeft;
            targetRight = BlueTargetRight;
        } else {
            targetLeft = RedTargetLeft;
            targetRight = RedTargetRight;
        }

    }

    public double calculateTargetAngle(double robotX, double robotY, double robotHeading) {


        double distanceLeft = Math.hypot(targetLeft.getX() - robotX, targetLeft.getY() - robotY);


        double distanceRight = Math.hypot(targetRight.getX() - robotX, targetRight.getY() - robotY);

        Point targetPoint;

        if (distanceLeft < distanceRight) {
            targetPoint = targetLeft;
        } else {
            targetPoint = targetRight;
        }

        double deltaX = targetPoint.getX() - robotX;
        double deltaY = targetPoint.getY() - robotY;
        double angleToTarget = Math.atan2(deltaY, deltaX);
        double targetAngle = angleToTarget - robotHeading;

        targetAngle = Math.atan2(Math.sin(targetAngle), Math.cos(targetAngle));

        return targetAngle;
    }

    public void aimTurret(double robotX, double robotY, double robotHeading) {

        double targetAngle = calculateTargetAngle(robotX, robotY, robotHeading);

        turret.setAngle(targetAngle);
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
            output += ("\n" + systems[i].getClass() + ": \n" + systems[i].toString() + "\n");
        }
        return output;
    }

    @Override
    public void update() {
        for (int i = 0; i < systems.length; i++) {
            systems[i].update();
        }
    }


}
