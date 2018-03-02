package org.usfirst.frc.team2152.robot.commands;

import org.usfirst.frc.team2152.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CubeIntakeCheck extends Command {
	private double intakeSpeed;
	private Timer watchDogTimer = new Timer();
	public CubeIntakeCheck(double intakeSpeed) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.cubeIntakeSubsystem);
		this.intakeSpeed = intakeSpeed;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		watchDogTimer.reset();
		watchDogTimer.start();

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.cubeIntakeSubsystem.cubeIntakeMove(intakeSpeed);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if(Robot.cubeIntakeSubsystem.cubeDetectIn() == true || watchDogTimer.get() >= 3) {
			return true;
		} else {
			return false;
		}
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.cubeIntakeSubsystem.cubeIntakeMove(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.cubeIntakeSubsystem.cubeIntakeMove(0);
	}
}
