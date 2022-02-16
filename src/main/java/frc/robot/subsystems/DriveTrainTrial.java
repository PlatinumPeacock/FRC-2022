// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
//import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PWMVictorSPX; //OG
//import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
//import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrainTrial extends SubsystemBase {
   //public static SpeedController leftFront; //OG
   //public static SpeedController rightFront; //OG
   //public static SpeedController leftBack; //OG
   //public static SpeedController rightBack; //OG

    public static SpeedController leftFront;
    public static SpeedController rightFront;
  
  SpeedControllerGroup leftMotors;
  SpeedControllerGroup rightMotors;
  DifferentialDrive drive;
  private final AnalogInput rangeFinder;

  /** Creates a new DriveTrainTrial. */
  public DriveTrainTrial() {
    leftFront = new PWMVictorSPX(Constants.LEFT_FRONT); // OG
    leftFront.setInverted(true);
    rightFront = new PWMVictorSPX(Constants.RIGHT_FRONT);
    rightFront.setInverted(true);
/*
    leftFront = new WPI_VictorSPX(Constants.LEFT_FRONT);
    leftFront.setInverted(true);
    leftBack = new  WPI_VictorSPX(Constants.LEFT_BACK);
    leftBack.setInverted(true);
    rightFront = new  WPI_VictorSPX(Constants.RIGHT_FRONT);
    rightFront.setInverted(true);
    rightBack = new  WPI_VictorSPX(Constants.RIGHT_BACK);
    rightBack.setInverted(true);
    */

    //leftMotors = new SpeedControllerGroup(leftFront);
    //rightMotors = new SpeedControllerGroup(rightFront);

    drive = new DifferentialDrive(leftFront, rightFront);
    //drive.setExpiration(0.1);
    //drive.setMaxOutput(0.7);
    //drive = new DifferentialDrive(leftMotors, rightMotors);
   // drive.setMotorSafety(false);
    // private final Joystick m_stick = new Joystick(0);
    // private final Joystick x_stick = new Joystick(1);
    
    // drive = new DifferentialDrive(leftMotors, rightMotors); our code

    rangeFinder = new AnalogInput(Constants.RANGE_FINDER);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void driveWithJoysticks(double speed) {
    drive.tankDrive(Constants.JOYSTICK1.getY() * speed, Constants.JOYSTICK2.getY() * speed);
    // drive.tankDrive((JOYSTICK1.getY() * speed), (JOYSTICK2.getY() * speed));
  }


  public void driveForward(double speed)
  {
    drive.tankDrive(speed, speed);
  }

  public boolean driveToDistance(double setPointDistance, double speed)
  {
   while(rangeFinder.getAverageVoltage() > setPointDistance)
   {
    driveForward(speed);
   }
   return true;
  }

  public void stop()
  {
    drive.stopMotor();
  }
}
