// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Elevator extends SubsystemBase {
  PWMVictorSPX elevator;
  PWMVictorSPX elevator2;
  /** Creates a new Elevator. */
  public Elevator() {
    elevator = new PWMVictorSPX(Constants.ELEVATOR);
    elevator2 = new PWMVictorSPX(Constants.ELEVATOR2);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void elevatorUp(double speed)
  {
    elevator.set(speed * -1);
  }

  public void elevatorUpReverse(double speed)
  {
    elevator.set(speed);
  }

  public void elevatorHorizontal(double speed)
  {
    elevator2.set(speed
    );
  }

  public void elevatorHorizontalReverse(double speed)
  {
    elevator2.set(speed * -1);
  }

  public void elevatorBoth(double speed)
  {
    elevator.set(speed * -1);
    elevator2.set(speed);
  }

  public void elevatorBothReverse(double speed)
  {
    elevator.set(speed);
    elevator2.set(speed * -1);
  }

  public void stop()
  {
    elevator.set(0);
  }

  public void stop2()
  {
    elevator2.set(0);
  }

  public void stop3()
  {
    elevator.set(0);
    elevator2.set(0);
  }
}
