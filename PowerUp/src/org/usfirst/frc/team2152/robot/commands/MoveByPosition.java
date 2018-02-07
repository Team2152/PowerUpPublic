package org.usfirst.frc.team2152.robot.commands;

import org.usfirst.frc.team2152.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveByPosition extends Command {
	private double leftDistance = 0;
	private double rightDistance = 0;
    public MoveByPosition(double leftDistance, double rightDistance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.leftDistance = leftDistance;
    	this.rightDistance = rightDistance;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrainSubsystem.moveByPosition(leftDistance,rightDistance);
    	
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
