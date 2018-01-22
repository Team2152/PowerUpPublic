package org.usfirst.frc.team2152.robot.commands;

import org.usfirst.frc.team2152.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TankDriveByTime extends Command {
	private double leftSpeed = 0;
	private double rightSpeed = 0;
	private double time = 0;
	Timer watchDogTimer;
    public TankDriveByTime(double leftSpeed, double rightSpeed, double time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrainSubsystem);
    	this.leftSpeed = leftSpeed;
    	this.rightSpeed = rightSpeed;
    	this.time = time;
    	watchDogTimer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	watchDogTimer.start();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrainSubsystem.tankDrive(leftSpeed, rightSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(watchDogTimer.get() > time){
    		return true;
    	} else {
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrainSubsystem.tankDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveTrainSubsystem.tankDrive(0, 0);
    }
}
