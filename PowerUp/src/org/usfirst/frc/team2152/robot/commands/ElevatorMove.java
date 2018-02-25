package org.usfirst.frc.team2152.robot.commands;

import org.usfirst.frc.team2152.robot.OI;
import org.usfirst.frc.team2152.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class ElevatorMove extends Command {

	
	private Joystick joystick;
	private double raiseSpeed;
	private double lowerSpeed;
	private int buttonAid = 1;
	private int buttonBid = 2;

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

		if (Robot.m_oi.driverXbox.getRawButton(buttonAid) == true ){//&& Robot.elevatorSubsystem.getElevatorMaxHeight() == false) {
			Robot.elevatorSubsystem.setElevatorRaiseSpeed(raiseSpeed);
		} 
//		else if (Robot.elevatorSubsystem.getElevatorMaxHeight() == true) {
//			Robot.elevatorSubsystem.setElevatorRaiseSpeed(0);
//		} 
		else if (Robot.m_oi.driverXbox.getRawButton(buttonBid) == true){ // && Robot.elevatorSubsystem.getElevatorMinHeight() == false) {
			Robot.elevatorSubsystem.setElevatorLowerSpeed(lowerSpeed);
		} 
//		else if (Robot.elevatorSubsystem.getElevatorMinHeight() == true) {
//			Robot.elevatorSubsystem.setElevatorLowerSpeed(0);
//		} 
		else if (Robot.m_oi.driverXbox.getRawButton(buttonAid) == false && Robot.m_oi.driverXbox.getRawButton(buttonBid) == false) {
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

