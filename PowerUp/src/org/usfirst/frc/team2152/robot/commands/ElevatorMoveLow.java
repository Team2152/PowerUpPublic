package org.usfirst.frc.team2152.robot.commands;

import org.usfirst.frc.team2152.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorMoveLow extends Command {

	double lowerSpeed = 0;

	public ElevatorMoveLow(double lowerSpeed) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.elevatorSubsystem);
		this.lowerSpeed = lowerSpeed;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		Robot.elevatorSubsystem.setElevatorLowerSpeed(lowerSpeed);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {

		if (Robot.elevatorSubsystem.getElevatorMinHeight() == true) {
			return true;
		}

		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.elevatorSubsystem.setElevatorStop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.elevatorSubsystem.setElevatorStop();
	}
}