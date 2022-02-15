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

  //limelight variables
  double tx;
  double ty;
  double ta;
  double tv;

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

  /*
  if(NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0) == 1)
  {
    UpdateLimeLightTracking();

    while(tx < -7)
    {
      rotateShooter.rotateShooterHead(-0.07);
      UpdateLimeLightTracking();
    }
    
    while(tx > 7)
    {
      rotateShooter.rotateShooterHead(0.07);
      UpdateLimeLightTracking();    
    }

    if(tx > 7 && tx < -7)
    {
      rotateShooter.stop();
    }

    if(tx >= -7 && tx <= 7)
    {
      shooter.shootBall(1);
    }
    
     
  }
  */
  UpdateLimeLightTracking();
  while(tx < 0)
  {
    rotateShooter.rotateShooterHead(-0.09);
    if(tx > -7)
    {
      rotateShooter.stop();
      break;
    }
    UpdateLimeLightTracking();
  }

  while(tx > 0)
  {
    rotateShooter.rotateShooterHead(0.09);
    if(tx < 7)
    {
      rotateShooter.stop();
      break;
    }
    UpdateLimeLightTracking();
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

  //updates limelight info
  public void UpdateLimeLightTracking(){
    tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
    tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
    ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
    ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
  }
}