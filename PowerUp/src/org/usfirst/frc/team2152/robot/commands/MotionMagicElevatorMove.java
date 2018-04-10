package org.usfirst.frc.team2152.robot.commands;

import org.usfirst.frc.team2152.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MotionMagicElevatorMove extends Command {
	private Joystick joy1;
	private double lowerSpeed;

	public MotionMagicElevatorMove(double lowerSpeed, Joystick joy1) {
		requires(Robot.elevatorSubsystem);
		this.lowerSpeed = lowerSpeed;
		this.joy1 = joy1;
		requires(Robot.elevatorSubsystem);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (Math.abs(joy1.getRawAxis(1)) > 0.05) {
			double targetPos = -joy1.getRawAxis(1) * 409.6;
			// Robot.elevatorSubsystem.goToHeight(targetPos);
			System.out.println("Enc pos: " + Robot.elevatorSubsystem.getEncoder() + " Enc setpoint: " + targetPos
					+ " Joystick Pos: " + -joy1.getRawAxis(1));
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
