package org.usfirst.frc.team2152.robot.commands;

import org.usfirst.frc.team2152.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LEDTest extends Command {

	Joystick js = null;
	int value = 0;
	
    public LEDTest(Joystick joystick, int value) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.ledSubsystem);
    	js = joystick;
    	this.value = value;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if (js.getRawButton(4) == true) {
    		Robot.ledSubsystem.setValue(32);
    	} else if (js.getRawButton(5) == true) {
    		Robot.ledSubsystem.setValue(224);
    	} else if (js.getRawButton(6) == true) { 
    		Robot.ledSubsystem.setValue(255);
    	} else
    		Robot.ledSubsystem.setValue(0);
    		
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//if (js.getRawButton(1) == false) {
    	//	System.out.println("Executing PWM: exiting");
    	//	return true;
    	//} else {
    	//	System.out.println("Executing PWM: active");
    	//	return false;
    	//}
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
