package org.usfirst.frc.team2152.robot.commands;

import org.usfirst.frc.team2152.robot.ControllerMap;
import org.usfirst.frc.team2152.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class ElevatorMove extends Command {

	private Joystick joy1;
	private double lowerSpeed;
	private int leftTrigger = 2;
	private int rightTrigger = 3;

	/*
	 * create everything you will need to move by controls this will be for the
	 * basic movement if you want to do fancier things(ie setHeight) you will have
	 * to make a seperate class
	 */
	public ElevatorMove(double lowerSpeed, Joystick joy1) {
		requires(Robot.elevatorSubsystem);
		this.lowerSpeed = lowerSpeed;
		this.joy1 = joy1;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double inputValue = joy1.getRawAxis(ControllerMap.elevatorMoveAxis);
		inputValue *= 1;
		if (Robot.elevatorSubsystem.getElevatorMinHeight()) {
			Robot.elevatorSubsystem.resetEleEncoder();
		}
		if (inputValue <= -0.1 && Robot.elevatorSubsystem.getElevatorMaxHeight() == false) {
			Robot.elevatorSubsystem.setElevatorRaiseSpeed(-inputValue);
		} else if (inputValue >= -0.1 && Robot.elevatorSubsystem.getElevatorMinHeight() == false) {
			Robot.elevatorSubsystem.setElevatorLowerSpeed(inputValue);
		} else {
			Robot.elevatorSubsystem.setElevatorStop();
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
		Robot.elevatorSubsystem.setElevatorStop();
	}
}
