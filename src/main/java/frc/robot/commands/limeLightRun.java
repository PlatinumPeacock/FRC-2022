// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LimeLight;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrainTrial;
import frc.robot.subsystems.RotateShooter;
import frc.robot.subsystems.Shooter;
//test imports
import edu.wpi.first.wpilibj.Joystick;


public class limeLightRun extends CommandBase {
  LimeLight limeLight;
  RotateShooter rotateShooter;
  Shooter shooter;
  DriveTrainTrial driveTrain;

  //testing joystick inputs
  Joystick joyStick1;
  Joystick joyStick2;

  //limelight variables
  double tx;
  double ty;
  double ta;
  double tv;
  double distance;

  /** Creates a new limeLightRun. */
  public limeLightRun(LimeLight l, RotateShooter rs, Shooter s, DriveTrainTrial d, Joystick js1, Joystick js2) 
  {
    limeLight = l;
    rotateShooter = rs;
    addRequirements(rotateShooter);
    shooter = s;
    addRequirements(shooter);
    driveTrain = d;
    addRequirements(driveTrain);

    //testing joysticks (continued)
    joyStick1 = js1;
    joyStick2 = js2;

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
  
  updateLimeLightTracking();
  
  //testing joystick code
/* ***SAVE*** ***WORKING CODE***
  while(distance > 15)
  {
    driveTrain.driveForward(-0.5);
    updateLimeLightTracking();
  }
  driveTrain.stop();
  while(distance < 10)
  {
    driveTrain.driveForward(0.5);
    updateLimeLightTracking();
  }
  driveTrain.stop();
*/
  driveTrain.driveWithJoysticks(Constants.DRIVETRAINSPEED);
  while(tx < 0)
  {
    driveTrain.driveWithJoysticks(Constants.DRIVETRAINSPEED);
    rotateShooter.rotateShooterHead(-0.09);
    if(tx > -7)
    {
      rotateShooter.stop();
      break;
    }
    updateLimeLightTracking();
  }

  while(tx > 0)
  {
    driveTrain.driveWithJoysticks(Constants.DRIVETRAINSPEED);
    rotateShooter.rotateShooterHead(0.09);
    if(tx < 7)
    {
      rotateShooter.stop();
      break;
    }
    updateLimeLightTracking();
  }

}

  
  
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
    //shooter.shootBall(0);
    //rotateShooter.rotateShooterHead(0); 

    shooter.stop();
    rotateShooter.stop();
    driveTrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  //updates limelight info
  public void updateLimeLightTracking(){
    tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
    tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
    ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
    ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
    distance = ((26/3)-2.75) / Math.tan(Math.toRadians(30 + ty));
  }
}