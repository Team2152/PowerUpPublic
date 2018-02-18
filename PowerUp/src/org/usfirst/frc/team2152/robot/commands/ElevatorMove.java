package org.usfirst.frc.team2152.robot.commands;

import org.usfirst.frc.team2152.robot.OI;
import org.usfirst.frc.team2152.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class ElevatorMove extends Command {

	private double speed = 0;
	private Joystick joystick;
	private double raiseSpeed;
	private double lowerSpeed;

	/*
	 * create everything you will need to move by controls this will be for the
	 * basic movement if you want to do fancier things(ie setHeight) you will
	 * have to make a seperate class
	 */
	public ElevatorMove(double raiseSpeed, double lowerSpeed, Joystick joystick) {
		requires(Robot.elevatorSubsystem);
		this.raiseSpeed = raiseSpeed;
		this.lowerSpeed = lowerSpeed;
		this.joystick = joystick;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		if (Robot.m_oi.driverXbox.getRawButton(1) == true) {
			if (Robot.elevatorSubsystem.getElevatorHeight() == false) {
				Robot.elevatorSubsystem.setElevatorRaiseSpeed(raiseSpeed);
			} else if (Robot.elevatorSubsystem.getElevatorHeight() == true) {
				Robot.elevatorSubsystem.setElevatorLowerSpeed(0);
			}

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
		Robot.elevatorSubsystem.setElevatorLowerSpeed(0);
	}
}
