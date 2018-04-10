package org.usfirst.frc.team2152.robot.commands;

import org.usfirst.frc.team2152.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorMoveHigh extends Command {

	double raiseSpeed = 0;
	Timer watchDog;
	double timeOut = 0;
	
    public ElevatorMoveHigh(double raiseSpeed, double timeOut) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.elevatorSubsystem);
    	this.raiseSpeed = raiseSpeed;
    	this.timeOut = timeOut;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	watchDog = new Timer();
    	watchDog.reset();
    	watchDog.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	Robot.elevatorSubsystem.setElevatorRaiseSpeed(raiseSpeed);
   }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
      
    	if(Robot.elevatorSubsystem.getElevatorMaxHeight() == true || watchDog.get() > timeOut){
    		return true;
    	} else {
    	return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	if(Robot.elevatorSubsystem.getElevatorMaxHeight() == true) {
    		Robot.elevatorSubsystem.setEncoder(86);
    	}
    	Robot.elevatorSubsystem.setElevatorStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.elevatorSubsystem.setElevatorStop();
    }
}
