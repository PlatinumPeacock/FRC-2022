// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;



//import edu.wpi.first.wpilibj.Joystick;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {
//PWMS for wiring team
	public static final int LEFT_FRONT = 8;
	public static final int RIGHT_FRONT = 7;
	//public static final int RIGHT_BACK = 7;
	//public static final int LEFT_BACK = 8;
	public static final int SHOOTER = 1;
	public static final int SHOOTER2 = 0;
	public static final int SHOOTERFEEDER = 9;
    public static final int SHOOTERFEEDER2 = 3;
	public static final int ROTATER = 2;
	public static final int INTAKE = 6;
	public static final int ELEVATOR = 5;
    public static final int ELEVATOR2 = 4;

	//Analogue
	public static final int RANGE_FINDER = 0;

	//Axis
	public static final int XBOX_LEFT_Y_AXIS = 1;//go into driver station click on USB icon and check for y and x axis
	public static final int XBOX_RIGHT_Y_AXIS = 5;
	public static final int RIGHT_TRIGGER = 3;
    public static final double DRIVETRAINSPEED = 1.0;
	public static final double DRIVE_FORWARD_TIME = 4.0;
	public static final double AUTONOMOUS_SPEED = 0.2;
	public final static Joystick JOYSTICK1 =  new Joystick(0);
	public static final int JOYSTICK11 = 0; //the port numbers for robot container
	public final static Joystick JOYSTICK2 =  new Joystick(1);
	public static final int JOYSTICK22 = 1; //the port numbers for robot container
	public static final int JOYSTICK3 = 2;
	

	public static final double SHOOTER_SPEED = 1.0;
	public static final double ROTATER_SPEED = 0.15;
	public static final double INTAKE_SPEED = 1.0;
	//public static final int CAMERA_RES_X = 320;  //width
	//public static final int CAMERA_RES_Y = 240; //height
	public static final double AUTO_SHOOT_TIME = 2.0;
	public static final double SETPOINT_FORWARD = 3.0;
	public static final double SHOOTERFEEDER_SPEED = 0;
    public static final double ELEVATOR_SPEED = 1.0;
	public static final double  UPDATE_FREQUENCY   = 15;     // miliseconds (maybe change to microseconds)
    public static final double  RECORDING_DURATION   = 10;      // seconds
    public static final String  SAVE_FILE_EXTENSION   = ".lmao";// extension for files that robotArrays are saved in
    public static final String  SAVE_FILE_PATH        = "/home/lvuser/";// path on the roborio to keep robotArray files( thanks for ruining the cool looking code )
    public static final String  SAVE_FILE_NAME        = "test"; // name of the file to make or read
    public static final boolean PRINT_DEBUG_INFO      = true;   // true to print info about the recoring and playback
    public static final boolean VERBOSE_DEBUG_PRINT   = true;   // true to print a lot of in depth info about recording and playback
    public static final boolean SHOULD_RECORD         = true;   // if false will block the "start recording" method
    public static final boolean INTERPOLATE_VALUES    = true;   // should data retreived between updates be interpolated (a method of constructing new data points between two given data points)
  



   
	
	
	

	
	
	
	
	
}
