package org.usfirst.frc.team2152.robot.commands;

import org.usfirst.frc.team2152.robot.OI;
import org.usfirst.frc.team2152.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class ElevatorMove extends Command {

	
	private Joystick joy1;
	private Joystick joy2;
	private double lowerSpeed;
	private int leftTrigger = 2;
	private int rightTrigger = 3;

	/*
	 * create everything you will need to move by controls this will be for the
	 * basic movement if you want to do fancier things(ie setHeight) you will
	 * have to make a seperate class
	 */
	public ElevatorMove( double lowerSpeed, Joystick joy1) {
		requires(Robot.elevatorSubsystem);
		this.lowerSpeed = lowerSpeed;
		this.joy1 = joy1;
		this.joy2 = joy2;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		if((joy1.getRawAxis(leftTrigger) >= .1 && Robot.elevatorSubsystem.getElevatorMaxHeight() == false)){
			Robot.elevatorSubsystem.setElevatorRaiseSpeed(Robot.m_oi.operatorXbox.getRawAxis(leftTrigger)*.6);
		}
		else if (Robot.elevatorSubsystem.getElevatorMaxHeight() == true) {
			Robot.elevatorSubsystem.setElevatorRaiseSpeed(0);
		} 
		else if (joy1.getRawAxis(rightTrigger) >= .1 && Robot.elevatorSubsystem.getElevatorMinHeight() == false) {
			Robot.elevatorSubsystem.setElevatorLowerSpeed(lowerSpeed);
		} 
		else if (Robot.elevatorSubsystem.getElevatorMinHeight() == true) {
			Robot.elevatorSubsystem.setElevatorLowerSpeed(0);
		} 
		else if (Robot.m_oi.operatorXbox.getRawAxis(leftTrigger) <= .1) {
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


