package org.usfirst.frc.team2152.robot.commands;

import org.usfirst.frc.team2152.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CubeIntakeSensor extends Command {

	private double intakeSpeed = 0;
	private Timer timer = new Timer();
	private Timer watchdog = new Timer();
	private double watchdogTime = 1;
	private boolean bGotACube = false;

	public CubeIntakeSensor(double intakeSpeed) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.cubeIntakeSubsystem);
		this.intakeSpeed = intakeSpeed;
		
		
		
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		
		timer.reset();
		watchdog.reset();
		Robot.cubeIntakeSubsystem.cubeSolenoidOpen();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		
		//Robot.cubeIntakeSubsystem.cubeIntakeSensor(intakeSpeed);
		Robot.cubeIntakeSubsystem.cubeIntakeMove(intakeSpeed);
		//Robot.cubeIntakeSubsystem.cubeSolenoidSensor();
		if ((Robot.cubeIntakeSubsystem.cubeDetectOutLeft() == true || 
				Robot.cubeIntakeSubsystem.cubeDetectOutRight() == true)){
			watchdog.start();
			Robot.cubeIntakeSubsystem.cubeSolenoidClose();
			//System.out.println("Executing Close Solenoid");
		}

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (Robot.cubeIntakeSubsystem.cubeDetectIn() == true) {
		    if (bGotACube == false) {
			  timer.start();
			  bGotACube = true;
		    }
		    
		    if (bGotACube == true && timer.get() >= 0.5)
		    	return true;
		}
		
		//if (watchdog.get() > watchdogTime) {
		//	System.out.println("WATCHDOG TIMER POPPED");
		//	return true;
		//}
		
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.cubeIntakeSubsystem.cubeIntakeMove(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.cubeIntakeSubsystem.cubeIntakeMove(0);
	}
}
