/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2152.robot;

import org.usfirst.frc.team2152.robot.subsystems.CubeIntake;
import org.usfirst.frc.team2152.robot.subsystems.CubeMove;
import org.usfirst.frc.team2152.robot.auto.BaselineCenter;
import org.usfirst.frc.team2152.robot.auto.BaselineLeft;
import org.usfirst.frc.team2152.robot.auto.BaselineRight;
import org.usfirst.frc.team2152.robot.auto.ScaleLeft;
import org.usfirst.frc.team2152.robot.auto.ScaleRight;
import org.usfirst.frc.team2152.robot.auto.SwitchCenter;
import org.usfirst.frc.team2152.robot.auto.SwitchLeft;
import org.usfirst.frc.team2152.robot.auto.SwitchRight;
import org.usfirst.frc.team2152.robot.auto.TestAuto;
import org.usfirst.frc.team2152.robot.commands.PreCannedTurn;
import org.usfirst.frc.team2152.robot.network.OdroidsCameraSettings;
import org.usfirst.frc.team2152.robot.network.UDPHandler;
import org.usfirst.frc.team2152.robot.network.UDPReceiver;
import org.usfirst.frc.team2152.robot.network.Vars;
import org.usfirst.frc.team2152.robot.subsystems.Dashboard;
import org.usfirst.frc.team2152.robot.subsystems.DriveTrain;

import org.usfirst.frc.team2152.robot.subsystems.LED;

import org.usfirst.frc.team2152.robot.subsystems.Elevator;
import org.usfirst.frc.team2152.robot.subsystems.EncoderSendSystem;
import org.usfirst.frc.team2152.robot.subsystems.NavX;
import org.usfirst.frc.team2152.robot.subsystems.TimeSyncSystem;
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
	public static final OdroidsCameraSettings cameras = new OdroidsCameraSettings();
	public static OI m_oi;
	public static Log m_logger;
	public static Dashboard powerUpDashboard = new Dashboard();
	public static String PLATE_ASSIGNMENT;
	public static final NavX navxSubsystem = new NavX();
	public static final Gain driveTrainJoystickGain = new Gain(Gain.PCT_75, Gain.DEFAULT_DEADBAND);
	public static final CubeIntake cubeIntakeSubsystem = new CubeIntake();
	public static final CubeMove cubeMoveSubsystem = new CubeMove();
	public static final DriveTrain driveTrainSubsystem = new DriveTrain();

	public static final LED ledSubsystem = new LED();
	public static final UDPHandler udp = new UDPHandler();
	private UDPReceiver timeReceiver = new UDPReceiver(UDPReceiver.UDP_PORT2);
	private UDPReceiver udpReceiver = new UDPReceiver(UDPReceiver.UDP_PORT);

	public static final Elevator elevatorSubsystem = new Elevator();
	
	public static TimeSyncSystem timeSync = new TimeSyncSystem();
	public static EncoderSendSystem encoderSendSystem = new EncoderSendSystem(11111, 1); //what does this do? Which port?

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
		
		try {
			timeReceiver.setListener((data) -> { 
				if(data[0] == 3) {
					timeSync.startTimeSync(data[1]);
				}
			});
			timeReceiver.start();
			encoderSendSystem.start();
			
			udpReceiver.setListener(udp);
			udpReceiver.start();
		} 
		catch (Exception e) {

		}
		
		cameras.start();

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
		m_chooser.addObject("Scale Left", new ScaleLeft());
		m_chooser.addObject("Scale Right", new ScaleRight());
		//m_chooser.addObject("TestAuto", new TestAuto());

		
		powerUpDashboard.putPositions();
		powerUpDashboard.putRecording();
		//POSITION = SmartDashboard.getString("Position", "");

		cameras.setToDisabledMode();
		
		powerUpDashboard.putElevatorStatus(Robot.elevatorSubsystem.getElevatorMaxHeight(), Robot.elevatorSubsystem.getElevatorMinHeight());
		
		//Robot.driveTrainSubsystem.setBreakMode(true);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		cameras.setToDisabledMode();
		powerUpDashboard.putPlateAssignment(DriverStation.getInstance().getGameSpecificMessage());
		//Robot.driveTrainSubsystem.setBreakMode(false);

	}

	@Override
	public void disabledPeriodic() {
		powerUpDashboard.putPlateAssignment(DriverStation.getInstance().getGameSpecificMessage());


		powerUpDashboard.putEncoderData(Robot.driveTrainSubsystem.getLSensorPosition(),
				Robot.driveTrainSubsystem.getRSensorPosition());
		Scheduler.getInstance().run();
		cameras.setToDisabledMode();
		
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		//Robot.driveTrainSubsystem.setBreakMode(true);
		Robot.powerUpDashboard.putPlateAssignment(DriverStation.getInstance().getGameSpecificMessage());
		cameras.startRecording();

		m_chooser.addDefault("No Auto", null);
		m_chooser.addObject("BaseLine Left", new BaselineLeft());
		m_chooser.addObject("BaseLine Right", new BaselineRight());
		m_chooser.addObject("BaseLine Center", new BaselineCenter());
		m_chooser.addObject("Switch Left", new SwitchLeft());
		m_chooser.addObject("Switch Right", new SwitchRight());
		m_chooser.addObject("Switch Center", new SwitchCenter());
		m_chooser.addObject("Scale Left", new ScaleLeft());
		m_chooser.addObject("Scale Right", new ScaleRight());
		//m_chooser.addObject("TestAuto", new TestAuto());
		
		// Plate assignment used to determine auto routine
		//powerUpDashboard.putPlateAssignment(DriverStation.getInstance().getGameSpecificMessage());
		m_autonomousCommand = m_chooser.getSelected();
		cameras.sendGameData("Plate:" + PLATE_ASSIGNMENT);
		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
		
		cameras.setToAutoMode();

	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		SmartDashboard.putNumber("Navx Angle", Robot.navxSubsystem.getAngle());
		powerUpDashboard.putUDP(udpReceiver.isRunning());
		powerUpDashboard.putCubeVision(udp.getValue(Vars.Cube.Double.XAngle), udp.getValue(Vars.Cube.Double.Distance));
		cameras.setToAutoMode();
		Scheduler.getInstance().run();
		powerUpDashboard.putCubeMoveStatus(Robot.cubeMoveSubsystem.isHighPosition(), Robot.cubeMoveSubsystem.isLowPosition());
	}

	@Override
	public void teleopInit() {
		//Robot.driveTrainSubsystem.setBreakMode(true);
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		cameras.setToTeleMode();
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
		//powerUpDashboard.putPlateAssignment(DriverStation.getInstance().getGameSpecificMessage());
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		powerUpDashboard.putUDP(udpReceiver.isRunning());
		SmartDashboard.putNumber("Navx Angle", Robot.navxSubsystem.getAngle());
		powerUpDashboard.putEncoderData(Robot.driveTrainSubsystem.getLSensorPosition(),Robot.driveTrainSubsystem.getRSensorPosition());
		Scheduler.getInstance().run();
		
		powerUpDashboard.putCubeMoveStatus(Robot.cubeMoveSubsystem.isHighPosition(), Robot.cubeMoveSubsystem.isLowPosition());
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
