package org.firstinspires.ftc.teamcode.robot;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.pedropathing.follower.Follower;
import com.pedropathing.follower.ManualDrive;
import com.pedropathing.math.Pose;

import org.firstinspires.ftc.teamcode.robot.pedro.Constants;

public class PPDrive extends Drive {


    private Follower follower;


    public PPDrive() {
        follower = Constants.create(hardwareMap);

    }

    @Override
    public void drive(double x, double y, double r) {
        if (isFieldoOn) {
            ManualDrive.driveOrHold(follower, ManualDrive.fieldCentric(x, y, r, follower.pose().heading()));
        } else {
            ManualDrive.driveOrHold(follower, x, y, r);
        }
        follower.update();
    }

    @Override
    public void resetFieldo() {
        follower.setHeading(0);
    }
}
