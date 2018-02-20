package org.usfirst.frc.team2152.robot.commands;

import org.usfirst.frc.team2152.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CubeIntakeMove extends Command {

	double cubeIntakeSpeed = 0;
	double cubeExpelSpeed = 0;
	double cubeRotateRightSpeed = 0;
	double cubeRotateLeftSpeed = 0;
	int cubeButtonAid = 1;
	int cubeButtonBid = 2;
	int cubeButtonXid = 3;
	int cubeButtonYid = 4;
	int cubeButtonBumpLid = 5;
	int cubeButtonBumpRid = 6;
	Joystick joystick;

	/**
	 * This command controls the cube intake and the solenoid. you can intake in
	 * and out and clamp via joystick. To see the joystick controls refer to the
	 * joystick spreadsheet.
	 * 
	 * @param cubeIntakeSpeed
	 *            Sets intake speed
	 * @param cubeExpelSpeed
	 *            Sets expel speed
	 * @param cubeRotateRightSpeed
	 *            Sets right rotation speed
	 * @param cubeRotateLeftSpeed
	 *            Sets left rotation speed
	 * @param joystick
	 */
	public CubeIntakeMove(double cubeIntakeSpeed, double cubeExpelSpeed, double cubeRotateRightSpeed,
			double cubeRotateLeftSpeed, Joystick joystick) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.cubeIntakeSubsystem);
		this.cubeIntakeSpeed = cubeIntakeSpeed;
		this.cubeExpelSpeed = cubeExpelSpeed;
		this.cubeRotateRightSpeed = cubeRotateRightSpeed;
		this.cubeRotateLeftSpeed = cubeRotateLeftSpeed;
		this.joystick = joystick;

	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		// When both inner sensors show NO cube presense then
		// check then joystick buttons and act on them if they are pressed
		if (Robot.cubeIntakeSubsystem.cubeDetectInRight() == false
				&& Robot.cubeIntakeSubsystem.cubeDetectInLeft() == false) {

			// Using else if so that only one button is usable at a time in the
			// following priority: A Y X B
			// A button intakes until inner sensors are triggered
			if (joystick.getRawButton(cubeButtonAid) == true) {
				Robot.cubeIntakeSubsystem.cubeIntakeMove(cubeIntakeSpeed);
			} else if (joystick.getRawButton(cubeButtonYid) == true) {
				Robot.cubeIntakeSubsystem.cubeExpelMove(cubeExpelSpeed);
			} else if (joystick.getRawButton(cubeButtonXid) == true) {
				Robot.cubeIntakeSubsystem.cubeRotateLeft(cubeRotateLeftSpeed);
			} else if (joystick.getRawButton(cubeButtonBid) == true) {
				Robot.cubeIntakeSubsystem.cubeRotateRight(cubeRotateRightSpeed);
			} else {
				Robot.cubeIntakeSubsystem.cubeIntakeMove(0);
				Robot.cubeIntakeSubsystem.cubeExpelMove(0);
				Robot.cubeIntakeSubsystem.cubeRotateLeft(0);
				Robot.cubeIntakeSubsystem.cubeRotateRight(0);
			}

		} else {
			// If BOTH INNER sensors are triggered then stop all intake motors
			// Sensors, with this code configuration, must be installed very
			// close to the back of the manipulator
			Robot.cubeIntakeSubsystem.cubeIntakeMove(0);
			Robot.cubeIntakeSubsystem.cubeExpelMove(0);
			Robot.cubeIntakeSubsystem.cubeRotateLeft(0);
			Robot.cubeIntakeSubsystem.cubeRotateRight(0);
		}

		// If BOTH INNER sensors are triggered then it will allow the user to expel the cube
		// Sensors, with this code configuration, must be installed very
		// close to the back of the manipulator
		if (Robot.cubeIntakeSubsystem.cubeDetectInRight() == true
				&& Robot.cubeIntakeSubsystem.cubeDetectInLeft() == true) {
			if (joystick.getRawButton(cubeButtonYid) == true) {
				Robot.cubeIntakeSubsystem.cubeExpelMove(cubeExpelSpeed);
			} else {
				Robot.cubeIntakeSubsystem.cubeExpelMove(0);
			}

		}

		// If BOTH OUTER sensors are triggered then the solenoid will actuate and will close the clamp
		// They have to placed in order to see enough of the cube
		if (Robot.cubeIntakeSubsystem.cubeDetectOutRight() == true
				&& Robot.cubeIntakeSubsystem.cubeDetectOutLeft() == true) {
			Robot.cubeIntakeSubsystem.cubeSolenoidClose();
		}

		//This is the manual override for the solenoid clamp 
		//Checks for a button press and will open and close the clamp
		if (joystick.getRawButton(cubeButtonBumpLid) == true) {
			Robot.cubeIntakeSubsystem.cubeSolenoidOpen();
		} else if (joystick.getRawButton(cubeButtonBumpRid) == true) {
			Robot.cubeIntakeSubsystem.cubeSolenoidClose();
		}

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		// This is the default command for the CubeIntake Subsystem. Must return
		// false.
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.cubeIntakeSubsystem.cubeIntakeMove(0);
		Robot.cubeIntakeSubsystem.cubeExpelMove(0);
		Robot.cubeIntakeSubsystem.cubeRotateLeft(0);
		Robot.cubeIntakeSubsystem.cubeRotateRight(0);
	}
}
