package org.usfirst.frc.team2152.robot.commands;

import org.usfirst.frc.team2152.robot.network.OdroidsCameraSettings;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SendPositionBR extends Command {
	public static final OdroidsCameraSettings cameras = new OdroidsCameraSettings();

    public SendPositionBR() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("Sending Position...");
    	cameras.sendGameData("Pos:BR");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
