// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMTalonFX;
import edu.wpi.first.wpilibj.PWMVictorSPX;
//import edu.wpi.first.wpilibj.SpeedControllerGroup;
//import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
 PWMTalonFX shooter;
 PWMTalonFX shooter2Fx;
 PWMVictorSPX shooterFeeder;
 PWMVictorSPX shooterFeeder2; 
 double shooterSpeed = Constants.SHOOTER_SPEED;
 double shooterFeederSpeed = Constants.SHOOTERFEEDER_SPEED;
 //SpeedController shooter2Fx;
 //SpeedControllerGroup shooterMotors;

   //Creates a new Shooter. 
  public Shooter() {
    shooter = new PWMTalonFX(Constants.SHOOTER);
    shooter2Fx = new PWMTalonFX(Constants.SHOOTER2);
    shooterFeeder = new PWMVictorSPX(Constants.SHOOTERFEEDER);
    shooterFeeder2 = new PWMVictorSPX(Constants.SHOOTERFEEDER2);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void shootBall(double speed)
  {
    shooter.set(speed);
    shooter2Fx.set(speed * -1);
    shooterFeeder.set(speed);
    shooterFeeder2.set(speed);
  }
  
   public void slow(){
     for(double x = 1.0; x >= 0.0; x -= 0.001){
      shooter.set(x);
      shooter2Fx.set(x);
     }
     shooterFeeder.set(0);
     shooterFeeder2.set(0);
    }
  
 
  public void stop()
  {
    shooter.set(0);
    shooter2Fx.set(0);
    shooterFeeder.set(0);
    shooterFeeder2.set(0);
    //shooter.stopMotor();
  } 
  
}

