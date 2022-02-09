// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

//import edu.wpi.cscore.UsbCamera;
//import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.AutoShoot;
import frc.robot.commands.AutonomousOne;
//import frc.robot.commands.AutonomousOne;
//import frc.robot.commands.AutonomousTwo;
import frc.robot.commands.DriveForwardTimed;
import frc.robot.commands.DriveToDistance;
import frc.robot.commands.DriveWithJoysticksTrial;
import frc.robot.commands.IntakeBall;
import frc.robot.commands.RotateHeadLeft;
import frc.robot.commands.RotateShooterHead;
import frc.robot.commands.ShootBall;
//import frc.robot.commands.limeLightRun;
import frc.robot.subsystems.DriveTrainTrial;
import frc.robot.subsystems.Intake;
//import frc.robot.subsystems.LimeLight;
import frc.robot.subsystems.RotateShooter;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
//import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 * 
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  
  private final DriveTrainTrial driveTrain;
  private final DriveWithJoysticksTrial driveWithJoystick;
  private final DriveForwardTimed driveForwardTimed;
  private final DriveToDistance driveToDistance;
  public static Joystick driverJoystick1;
  public static Joystick driverJoystick2;
  public static XboxController driverJoystick3;

  private final Shooter shooter;
  private final ShootBall shootBall;
  private final AutoShoot autoShoot;

  private final RotateShooter rotateShooter;
  private final RotateShooterHead rotateShooterHead;

  private final Intake intake;
  private final IntakeBall intakeBall;

  //private final LimeLight limeLight;
  //private final limeLightRun limeLightRun;

  private final AutonomousOne autonomousOne;
  //private final AutonomousTwo autonomousTwo;

 SendableChooser<Command> chooser = new SendableChooser<>();
  

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
  driveTrain = new DriveTrainTrial();
  driveWithJoystick = new DriveWithJoysticksTrial(driveTrain);
  driveTrain.setDefaultCommand(driveWithJoystick);

  driveForwardTimed = new DriveForwardTimed(driveTrain);
  driveForwardTimed.addRequirements(driveTrain);

  driveToDistance = new DriveToDistance(driveTrain);
  driveToDistance.addRequirements(driveTrain);

  driverJoystick1 = new Joystick(Constants.JOYSTICK11);
  driverJoystick2 = new Joystick(Constants.JOYSTICK22);
  driverJoystick3 = new XboxController(Constants.JOYSTICK3);

  shooter = new Shooter();
  shootBall = new ShootBall(shooter);
  shootBall.addRequirements(shooter);

  rotateShooter = new RotateShooter();
  rotateShooterHead = new RotateShooterHead(rotateShooter);
  rotateShooterHead.addRequirements(rotateShooter);
  

  autoShoot = new AutoShoot(shooter);
  autoShoot.addRequirements(shooter);

  intake = new Intake();
  intakeBall = new IntakeBall(intake);
  intakeBall.addRequirements(intake);
  intake.setDefaultCommand(intakeBall);

  //limeLight = new LimeLight();
  //limeLightRun = new limeLightRun(limeLight);

  autonomousOne = new AutonomousOne(driveTrain, shooter);
  //autonomousTwo = new AutonomousTwo(driveTrain, shooter);

  //Add choices as options here
 //chooser.addOption("Autonomous Two", autonomousTwo);
  //Default option
  chooser.setDefaultOption("Autonomous One", autonomousOne);
 //chooser.setDefaultOption("Autonomous One", autonomousOne);
  //Add choice so smart dashboard
  SmartDashboard.putData("Autonomous", chooser);

  //Initialize camera
  //UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
  //camera.setResolution(Constants.CAMERA_RES_X, Constants.CAMERA_RES_Y);

    // Configure the button bindings
    configureButtonBindings();
  }


  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link Joystick}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //add other buttons
      JoystickButton shootButton = new JoystickButton(driverJoystick3, XboxController.Button.kA.value);
      shootButton.whileHeld(new ShootBall(shooter));

      JoystickButton rotaterButtonRight = new JoystickButton(driverJoystick3, XboxController.Button.kBumperRight.value);
      rotaterButtonRight.whileHeld(new RotateShooterHead(rotateShooter));

      JoystickButton rotaterButtonLeft = new JoystickButton(driverJoystick3, XboxController.Button.kBumperLeft.value);
      rotaterButtonLeft.whileHeld(new RotateHeadLeft(rotateShooter)); //The left bumper will rotate the rotater in the opposite direction, this hasn't been made yet.

      //JoystickButton driveButton = new JoystickButton(driverJoystick3, XboxController.Button.kBumperRight.value);
      //driveButton.whileHeld(new DriveToDistance(driveTrain));

     //JoystickButton driveTimedButton= new JoystickButton(driverJoystick3, XboxController.Button.kBumperLeft.value);
     //driveTimedButton.whenPressed(new DriveForwardTimed(driveTrain));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
     
    //An ExampleCommand will run in autonomous
  return chooser.getSelected();
  }
}
