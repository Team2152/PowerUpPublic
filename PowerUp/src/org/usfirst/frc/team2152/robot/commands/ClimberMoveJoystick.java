package org.usfirst.frc.team2152.robot.commands;

import org.usfirst.frc.team2152.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimberMoveJoystick extends Command {

	public ClimberMoveJoystick() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.climberSubsystem);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (Robot.m_oi.driverXbox.getRawButton(7)) {
			Robot.climberSubsystem.setSpeed(0.75);
		} else if (Math.abs(Robot.m_oi.operatorXbox.getRawAxis(5)) - 0.1 >= 0.1) {
			Robot.climberSubsystem.setSpeed(-Robot.m_oi.operatorXbox.getRawAxis(5));
		} else {
			Robot.climberSubsystem.setSpeed(0);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.climberSubsystem.setSpeed(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.climberSubsystem.setSpeed(0);
	}
}
