/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.cameraserver.CameraServer;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


/**
 * This is a demo program showing the use of the DifferentialDrive class.
 * Runs the motors with arcade steering.
 */
public class Robot extends TimedRobot {
  private  CANSparkMax m_leftFrontMotor = new CANSparkMax(3, MotorType.kBrushless);
  private  CANSparkMax m_leftBackMotor = new CANSparkMax(4, MotorType.kBrushless);
  private  CANSparkMax m_rightFrontMotor = new CANSparkMax(1, MotorType.kBrushless);
  private  CANSparkMax m_rightBackMotor = new CANSparkMax(2, MotorType.kBrushless);
  
  private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_leftFrontMotor, m_rightFrontMotor);
  private final Joystick m_stick = new Joystick(0);

  @Override
  public void robotInit() {
    m_rightBackMotor.follow(m_rightFrontMotor);
    m_leftBackMotor.follow(m_leftFrontMotor);
    m_leftFrontMotor.setInverted(true);
    m_leftBackMotor.setInverted(true); 
    CameraServer.getInstance().startAutomaticCapture();
  }

  @Override
  public void teleopPeriodic() {
    // Drive with arcade drive.
    // That means that the Y axis drives forward
    // and backward, and the X turns left and right.
    //
    // JCA 20200106: Multiply getX() by -1 so that the robot turns in the same
    // direction as the joystick
    m_robotDrive.arcadeDrive((m_stick.getX()*-1*0.5), (m_stick.getY()*0.5));
  }
}