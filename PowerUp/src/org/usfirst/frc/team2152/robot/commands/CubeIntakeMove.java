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

		if (Robot.cubeIntakeSubsystem.cubeDetectInRight() == false && Robot.cubeIntakeSubsystem.cubeDetectInLeft() == false) {

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

		} else{
			Robot.cubeIntakeSubsystem.cubeIntakeMove(0);
			Robot.cubeIntakeSubsystem.cubeExpelMove(0);
			Robot.cubeIntakeSubsystem.cubeRotateLeft(0);
			Robot.cubeIntakeSubsystem.cubeRotateRight(0);
		}
	}

	
    // Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
