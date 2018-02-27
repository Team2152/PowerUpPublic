package org.usfirst.frc.team2152.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Delay extends Command {
	private Timer timer = new Timer();
	private double seconds = 0;
    public Delay(double seconds) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.seconds = seconds;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (timer.get() >= seconds){
    		return true;
    	} else {
        return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	timer.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	timer.stop();
    }
}
