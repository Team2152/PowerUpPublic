package org.usfirst.frc.team2152.robot.commands;

import org.usfirst.frc.team2152.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetCubeIntake extends Command {

	private double cubeSpeed = 0;
	private double time = 0;
	private Timer timer = new Timer();
	
    public SetCubeIntake(double cubeSpeed, double time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.cubeIntakeSubsystem);
    	this.cubeSpeed   = cubeSpeed;
    	this.time = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.reset();
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// Send a positive to shoot and a negative to intake
    	Robot.cubeIntakeSubsystem.cubeSetMoveSpeed(cubeSpeed);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if( timer.get() >= time){
    	return true;	
    	} else {
      	return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	timer.stop();
    	Robot.cubeIntakeSubsystem.cubeSetMoveSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
