package org.usfirst.frc.team2152.robot.commands;

import org.usfirst.frc.team2152.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CubeExpel extends Command {

	private double expelSpeed = 0;
	private Joystick operatorJoystick;
	private Joystick driverJoystick;
	private int operatorButtonID;
	private int driverButtonID;

	public CubeExpel(double expelSpeed, int driverButtonID, int operatorButtonID, Joystick operatorJoystick, Joystick driverJoystick) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.cubeIntakeSubsystem);
		this.expelSpeed = expelSpeed;
		this.operatorJoystick = operatorJoystick;
		this.driverJoystick = driverJoystick;
		this.operatorButtonID = operatorButtonID;
		this.driverButtonID = driverButtonID;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.cubeIntakeSubsystem.cubeExpelMove(expelSpeed);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (operatorJoystick.getRawButton(operatorButtonID) == false
				&& driverJoystick.getRawButton(driverButtonID) == false) {
			return true;
		} else {
			return false;
		}

	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.cubeIntakeSubsystem.cubeExpelMove(0);

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.cubeIntakeSubsystem.cubeExpelMove(0);
	}
}
