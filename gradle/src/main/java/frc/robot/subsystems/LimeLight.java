
package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
//import edu.wpi.first.wpilibj2.command.Command;
//import edu.wpi.first.wpilibj2.command.Subsystem;
//import frc.robot.subsystems.DriveTrainTrial;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class LimeLight extends TimedRobot{
  SpeedController leftFront1 = DriveTrainTrial.leftFront;
  SpeedController rightFront1 = DriveTrainTrial.rightFront;
  SpeedController leftBack1 = DriveTrainTrial.leftBack;
  SpeedController rightBack1 = DriveTrainTrial.rightBack;

  private SpeedControllerGroup m_LeftMotors = new SpeedControllerGroup(leftFront1, leftBack1);
  private SpeedControllerGroup m_RightMotors = new SpeedControllerGroup(rightFront1, rightBack1);
  private DifferentialDrive m_Drive = new DifferentialDrive(m_LeftMotors, m_RightMotors);

  // private Command m_autonomousCommand;

  // private RobotContainer m_robotContainer;

  private XboxController m_Controller = new XboxController(0);

  private boolean m_LimelightHasValidTarget = false;
  private double m_LimelightDriveCommand = 0.0;
  private double m_LimelightSteerCommand = 0.0;

  public void limeLightRun() {
    Update_Limelight_Tracking();

    double steer = m_Controller.getX(Hand.kRight);
    double drive = -m_Controller.getY(Hand.kLeft);
    boolean auto = m_Controller.getAButton();

    steer *= 0.70;
    drive *= 0.70;

    if (auto) {
      if (m_LimelightHasValidTarget) {
        m_Drive.arcadeDrive(m_LimelightDriveCommand, m_LimelightSteerCommand);
      } else {
        m_Drive.arcadeDrive(0.0, 0.0);
      }
    } else {
      m_Drive.arcadeDrive(drive, steer);
    }
  }

  public void Update_Limelight_Tracking() {
    // These numbers must be tuned for your Robot! Be careful!
    final double STEER_K = 0.03; // how hard to turn toward the target
    final double DRIVE_K = 0.25; // how hard to drive fwd toward the target
    final double DESIRED_TARGET_AREA = 13.0; // Area of the target when the robot reaches the wall
    final double MAX_DRIVE = 0.5; // Simple speed limit so we don't drive too fast

    double tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
    double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
    // double ty =
    // NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
    double ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);

    if (tv < 1.0) {
      m_LimelightHasValidTarget = false;
      m_LimelightDriveCommand = 0.0;
      m_LimelightSteerCommand = 0.0;
      return;
    }

    m_LimelightHasValidTarget = true;

    // Start with proportional steering
    double steer_cmd = tx * STEER_K;
    m_LimelightSteerCommand = steer_cmd;

    // try to drive forward until the target area reaches our desired area
    double drive_cmd = (DESIRED_TARGET_AREA - ta) * DRIVE_K;

    // don't let the robot drive too fast into the goal
    if (drive_cmd > MAX_DRIVE) {
      drive_cmd = MAX_DRIVE;
    }
    m_LimelightDriveCommand = drive_cmd;
  }

}
