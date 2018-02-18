/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2152.robot;

import org.usfirst.frc.team2152.robot.auto.BaselineCenter;
import org.usfirst.frc.team2152.robot.auto.BaselineLeft;
import org.usfirst.frc.team2152.robot.auto.BaselineRight;
import org.usfirst.frc.team2152.robot.auto.SwitchCenter;
import org.usfirst.frc.team2152.robot.auto.SwitchLeft;
import org.usfirst.frc.team2152.robot.auto.SwitchRight;
import org.usfirst.frc.team2152.robot.commands.PreCannedTurn;
import org.usfirst.frc.team2152.robot.subsystems.Dashboard;
import org.usfirst.frc.team2152.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2152.robot.subsystems.NavX;
import org.usfirst.frc.team2152.robot.utilities.Gain;
import org.usfirst.frc.team2152.robot.utilities.Log;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static OI m_oi;
	public static Log m_logger;
	public static Dashboard powerUpDashboard = new Dashboard();
	public static String PLATE_ASSIGNMENT;
	public static final NavX navxSubsystem = new NavX();
	public static final DriveTrain driveTrainSubsystem = new DriveTrain();
	public static final Gain driveTrainJoystickGain     = new Gain(Gain.PCT_75,Gain.XBOX_DEADBAND);

	
	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		m_oi = new OI();
		m_logger = new Log(true);


		// chooser.addObject("My Auto", new MyAutoCommand());
		
		SmartDashboard.putNumber("Auto Delay", 0);
		SmartDashboard.putNumber("Left Speed", 0);
		SmartDashboard.putNumber("Right Speed", 0);
		
		
		SmartDashboard.putData("Auto mode", m_chooser);
		
		
		m_chooser.addDefault("No Auto", null);
		m_chooser.addObject("BaseLine Left", new BaselineLeft());
		m_chooser.addObject("BaseLine Right", new BaselineRight());
		m_chooser.addObject("BaseLine Center", new BaselineCenter());
		m_chooser.addObject("Switch Left", new SwitchLeft());
		m_chooser.addObject("Switch Right", new SwitchRight());
		m_chooser.addObject("Switch Center", new SwitchCenter());
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		SmartDashboard.putNumber("Right 1 Current", Robot.driveTrainSubsystem.getCurrent(1));
		SmartDashboard.putNumber("Right 2 Current", Robot.driveTrainSubsystem.getCurrent(2));
		SmartDashboard.putNumber("Right 3 Current", Robot.driveTrainSubsystem.getCurrent(3));
		
		SmartDashboard.putNumber("AVG Right", (Robot.driveTrainSubsystem.getCurrent(1) + (Robot.driveTrainSubsystem.getCurrent(2) + (Robot.driveTrainSubsystem.getCurrent(3))/3)));
		
		SmartDashboard.putNumber("Left 1 Current", Robot.driveTrainSubsystem.getCurrent(4));
		SmartDashboard.putNumber("Left 2 Current", Robot.driveTrainSubsystem.getCurrent(5));
		SmartDashboard.putNumber("Left 3 Current", Robot.driveTrainSubsystem.getCurrent(6));
		
		SmartDashboard.putNumber("AVG Left", (Robot.driveTrainSubsystem.getCurrent(4) + (Robot.driveTrainSubsystem.getCurrent(5) + (Robot.driveTrainSubsystem.getCurrent(6))/3)));

		SmartDashboard.putNumber("R Pos", Robot.driveTrainSubsystem.getRSensorPosition());
		SmartDashboard.putNumber("L Pos", Robot.driveTrainSubsystem.getLSensorPosition());
		Scheduler.getInstance().run();

	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		//PLATE_ASSIGNMENT must be defined before autonomous is finalized for a match
		PLATE_ASSIGNMENT = DriverStation.getInstance().getGameSpecificMessage();
		powerUpDashboard.putPlateAssignment();
		m_autonomousCommand = m_chooser.getSelected();

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		
		SmartDashboard.putNumber("Navx Angle", Robot.navxSubsystem.getAngle());
		SmartDashboard.putNumber("Encoder Difference", Math.abs(Robot.driveTrainSubsystem.getRSensorPosition() - Robot.driveTrainSubsystem.getLSensorPosition()));
		SmartDashboard.putNumber("R Pos", Robot.driveTrainSubsystem.getRSensorPosition());
		SmartDashboard.putNumber("L Pos", Robot.driveTrainSubsystem.getLSensorPosition());
		Scheduler.getInstance().run();

	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
