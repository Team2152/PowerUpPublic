package org.usfirst.frc.team2152.robot.commands;

import org.usfirst.frc.team2152.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MotionMagicMoveTo extends Command {
	double targetIn;
	Timer timer;
	double targetPos;

	public MotionMagicMoveTo(double targetIn) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.elevatorSubsystem);
		this.targetIn = targetIn;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		timer = new Timer();
		timer.reset();
		timer.start();
		targetPos = Robot.elevatorSubsystem.convertToNativeUnits(targetIn);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.elevatorSubsystem.goToHeight(targetPos);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (Robot.m_oi.operatorXbox.getRawAxis(1) > 0.1 || timer.get() >= 5) {
			return true;
		} else {
			return false;
		}
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.elevatorSubsystem.goToHeight(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.elevatorSubsystem.goToHeight(0);

	}
}
