package org.usfirst.frc.team2152.robot.commands;

import org.usfirst.frc.team2152.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CubeIntakeSensor extends Command {

	private double intakeSpeed = 0;

	public CubeIntakeSensor(double intakeSpeed) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.cubeIntakeSubsystem);
		this.intakeSpeed = intakeSpeed;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.cubeIntakeSubsystem.cubeIntakeSensor(intakeSpeed);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.cubeIntakeSubsystem.cubeDetectIn();
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
