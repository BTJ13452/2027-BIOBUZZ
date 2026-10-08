package org.firstinspires.ftc.teamcode.util;

public class BTJPID {
    double kp, ki, kd;
    double lastRunTime = -1;
    double lastDeltaX;
    double integral = 0;

    public BTJPID(double kp, double ki, double kd) {
        this.kp = kp;
        this.ki = ki;
        this.kd = kd;
    }

    public double power(double deltaX, double runTime) {
        double deltaT = lastRunTime == -1 ? 0 : runTime - lastRunTime;
        lastRunTime = runTime;
        integral += deltaT * deltaX;

        double derivative = deltaT == 0 ? 0 : (deltaX - lastDeltaX) / deltaT;
        lastDeltaX = deltaX;

        double power = kp * deltaX + ki * integral + kd * derivative;
        power = Math.max(-1,Math.min(power,1));
        return power;
    }
}
