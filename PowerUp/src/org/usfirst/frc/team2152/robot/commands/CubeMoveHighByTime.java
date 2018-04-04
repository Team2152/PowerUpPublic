package org.usfirst.frc.team2152.robot.commands;

import org.usfirst.frc.team2152.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CubeMoveHighByTime extends Command {

	private double cubeRaiseSpeed;
	private double time;
	private Timer timer;
	public CubeMoveHighByTime(double cubeRaiseSpeed,double time) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.cubeMoveSubsystem);
		this.cubeRaiseSpeed = cubeRaiseSpeed;
		this.time = time;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		timer = new Timer();
		timer.reset();
		timer.start();
		
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(Robot.cubeMoveSubsystem.isHighPosition() == false ){
			Robot.cubeMoveSubsystem.setCubeRaiseSpeed(cubeRaiseSpeed);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (Robot.cubeMoveSubsystem.isHighPosition() == true || timer.get() >= time) {
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
