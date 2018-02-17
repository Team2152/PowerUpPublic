package org.usfirst.frc.team2152.robot.commands;


import org.usfirst.frc.team2152.robot.OI;
import org.usfirst.frc.team2152.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class Elevate extends Command{

	private double speed = 0;
	private Joystick joystick;
	
	/*
	 * create everything you will need to move by controls
	 * this will be for the basic movement
	 * if you want to do fancier things(ie setHeight) you will have to make a seperate class
	 */
	public Elevate(double speed, Joystick joystick) {
		requires(Robot.elevatorSubsystem);
		this.speed = speed;
		this.joystick = joystick;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(Robot.m_oi.driverXbox.getRawButton(1) == true){
			Robot.elevatorSubsystem.up(speed);
		}
		else if(Robot.m_oi.driverXbox.getRawButton(2) == true){
			Robot.elevatorSubsystem.down(speed);
		}
		else{
			Robot.elevatorSubsystem.stop();
			
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
