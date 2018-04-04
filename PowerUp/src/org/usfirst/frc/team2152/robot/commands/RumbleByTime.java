package org.usfirst.frc.team2152.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RumbleByTime extends Command {
	private Joystick joystick;
	private RumbleType type;
	private double intensity = 0;
	private double seconds = 0;
	private Timer timer;
    public RumbleByTime(Joystick joystick,RumbleType type, double intensity, double seconds) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.joystick = joystick;
    	this.type = type;
    	if(intensity > 1){
    		intensity = 1;
    	} else if( intensity < 0){
    		intensity = 0;
    	} else {
    		this.intensity = 0;
    	}
    	this.seconds = seconds;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer = new Timer();
    	timer.reset();
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	joystick.setRumble(type , intensity);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(timer.get() > seconds){
    		return true;
    	} else {
        return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	joystick.setRumble(type, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	joystick.setRumble(type, 0);
    }
}
