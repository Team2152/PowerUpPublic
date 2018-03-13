package org.usfirst.frc.team2152.robot.commands;

import org.usfirst.frc.team2152.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CubeExpelSensor extends Command {

	private double speed = 0;
	private Timer  timer;
    public CubeExpelSensor(double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.cubeIntakeSubsystem);
    	this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.reset();
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.cubeIntakeSubsystem.cubeExpelMove(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
       if(Robot.cubeIntakeSubsystem.cubeDetectOutLeft() == false && Robot.cubeIntakeSubsystem.cubeDetectOutRight() == false && Robot.cubeIntakeSubsystem.cubeDetectIn() == false || timer.get() >= 2){
    	   return true;
       }else {
    	   return false;
       }
       
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
