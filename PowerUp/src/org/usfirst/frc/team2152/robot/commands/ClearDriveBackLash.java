package org.usfirst.frc.team2152.robot.commands;

import org.usfirst.frc.team2152.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClearDriveBackLash extends Command {
	private Timer timer;
	public ClearDriveBackLash() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveTrainSubsystem);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		timer = new Timer();
		timer.reset();
		timer.start();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.driveTrainSubsystem.arcadeDrive(0.15, 0);

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if( timer.get() >= 0.5){
			return true;
		} else {
		return false;
		}
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.driveTrainSubsystem.tankDrive(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.driveTrainSubsystem.tankDrive(0, 0);
	}
}
