package org.usfirst.frc.team2152.robot.commands;

import org.usfirst.frc.team2152.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CubeMoveLow extends Command {

	private double cubeLowerSpeed;
	private Timer timer;

	public CubeMoveLow(double cubeLowerSpeed) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.cubeMoveSubsystem);
		this.cubeLowerSpeed = cubeLowerSpeed;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		timer = new Timer();
		timer.reset();
		timer.start();

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (Robot.cubeMoveSubsystem.isLowPosition() == false) {
			Robot.cubeMoveSubsystem.setCubeLowerSpeed(cubeLowerSpeed);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (Robot.cubeMoveSubsystem.isLowPosition() == true || timer.get() >= 1) {
			return true;
		} else {
			return false;
		}
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.cubeMoveSubsystem.setCubeLowerSpeed(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.cubeMoveSubsystem.setCubeLowerSpeed(0);
	}
}
