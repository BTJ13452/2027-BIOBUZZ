package org.firstinspires.ftc.teamcode.util;

public interface BTJSystem {

      public void emergencyStop();
      public default void update(){};
      @Override
      public String toString();
}
