package org.firstinspires.ftc.teamcode.util;

public enum AllianceColor {
    BLUE,
    RED;

    public int getValue(){
        switch (this){
            case BLUE:
                return 0;
            case RED:
                return 1;
            default:
                throw (new IllegalArgumentException());
        }
    }
}
