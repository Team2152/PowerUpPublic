package org.usfirst.frc.team2152.robot.commands;

import org.usfirst.frc.team2152.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MoveByPosition extends Command {
	private double leftDistance = 0;
	private double rightDistance = 0;
	Timer watchDog;
    public MoveByPosition(double leftDistance, double rightDistance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.leftDistance = leftDistance;
    	this.rightDistance = rightDistance;
    	watchDog = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrainSubsystem.setPID(SmartDashboard.getNumber("P", 
    			0),SmartDashboard.getNumber("I", 0),SmartDashboard.getNumber("D", 0));
    	Robot.driveTrainSubsystem.invertMotors(false, true, true, true);
    	watchDog.reset();
    	watchDog.start();

    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrainSubsystem.moveByPosition(leftDistance,rightDistance);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (watchDog.get() >= 20){
    		return true;
    	} else {
        return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	watchDog.stop();
    	Robot.driveTrainSubsystem.setLeftSpeed(0);
    	Robot.driveTrainSubsystem.setRightSpeed(0);
    	Robot.driveTrainSubsystem.invertMotors(true, true, false, true);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	watchDog.stop();
    	Robot.driveTrainSubsystem.setLeftSpeed(0);
    	Robot.driveTrainSubsystem.setRightSpeed(0);
    	Robot.driveTrainSubsystem.invertMotors(true, true, false, true);

    }
}
