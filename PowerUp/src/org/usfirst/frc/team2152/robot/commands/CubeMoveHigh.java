package org.usfirst.frc.team2152.robot.commands;

import org.usfirst.frc.team2152.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CubeMoveHigh extends Command {

	private double cubeRaiseSpeed;
	private Timer watchdog;
	
	public CubeMoveHigh(double cubeRaiseSpeed) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.cubeMoveSubsystem);
		this.cubeRaiseSpeed = cubeRaiseSpeed;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		watchdog = new Timer();
		watchdog.reset();
		watchdog.start();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(Robot.cubeMoveSubsystem.isHighPosition() == false ){
			Robot.cubeMoveSubsystem.setCubeRaiseSpeed(cubeRaiseSpeed);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (Robot.cubeMoveSubsystem.isHighPosition() == true || watchdog.get()>= 1) {
			return true;
		} else {
			return false;
		}
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.cubeMoveSubsystem.setCubeRaiseSpeed(0);	
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.cubeMoveSubsystem.setCubeRaiseSpeed(0);
	}
}
