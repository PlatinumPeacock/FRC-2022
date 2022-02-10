// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LimeLight;
import edu.wpi.first.networktables.NetworkTableInstance;
//test
import frc.robot.subsystems.RotateShooter;
import frc.robot.subsystems.Shooter;


public class limeLightRun extends CommandBase {
  LimeLight limeLight;
  RotateShooter rotateShooter;
  Shooter shooter;
  /** Creates a new limeLightRun. */
  public limeLightRun(LimeLight l, RotateShooter rs, Shooter s) {
    limeLight = l;
    rotateShooter = rs;
    addRequirements(rotateShooter);
    shooter = s;
    addRequirements(shooter);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);
  }
  
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute(){
  NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);

  // won't turn right toward a target and light doesn't turn off when button is released

  if(NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0) == 1)
  {
    while(NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0) < -7)
    {
      rotateShooter.rotateShooterHead(-0.1);
      if(NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0) > -7)
      {
        rotateShooter.stop();
      }
    }
    
    while(NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0) > 7)
    {
      rotateShooter.rotateShooterHead(0.1);
      if(NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0) < 7)
      {
        rotateShooter.stop();
      }
    }


    if(NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0) >= -7 && NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0) <= 7)
    {
      shooter.shootBall(1);
    }
    
     
  }
  
  
}
  
  
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
    //shooter.shootBall(0);
    //rotateShooter.rotateShooterHead(0); 

    shooter.stop();
    rotateShooter.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}