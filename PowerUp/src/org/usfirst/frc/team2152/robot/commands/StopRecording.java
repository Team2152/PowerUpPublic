package org.usfirst.frc.team2152.robot.commands;

import org.usfirst.frc.team2152.robot.network.OdroidsCameraSettings;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StopRecording extends Command {
	public static final OdroidsCameraSettings cameras = new OdroidsCameraSettings();
	
    public StopRecording() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("recording stopped.");
    	cameras.shouldRecord(false);
    	//cameras.updateRecordStatus();
    	cameras.stopRecording();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
