// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  PWMVictorSPX intake;

  /** Creates a new Intake. */
  public Intake() {
    intake = new PWMVictorSPX(Constants.INTAKE);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void intakeBall(XboxController controller, double speed)
  {
    intake.set(controller.getRawAxis(Constants.RIGHT_TRIGGER)*speed);
  }

  public void stop()
  {
    intake.set(0);
  }
}
