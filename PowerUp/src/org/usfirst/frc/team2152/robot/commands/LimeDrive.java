package org.usfirst.frc.team2152.robot.commands;

import org.usfirst.frc.team2152.robot.ControllerMap;
import org.usfirst.frc.team2152.robot.Robot;
import org.usfirst.frc.team2152.robot.utilities.PIDConstants;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Takes input from the joystick and outputs it to the motors, PID turning and
 * heading hold available.
 */

// Needs to tune for new robot/carpet
public class LimeDrive extends Command {

	public LimeDrive() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveTrainSubsystem);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.driveTrainSubsystem.setRampRate(PIDConstants.CONTROLLER_DRIVE_RAMP_RATE,
				PIDConstants.CONTROLLER_DRIVE_RAMP_TIMEOUT);
		
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double outputThrottle = 0;
		double outputTurn = 0;
		double inputThrottle = ControllerMap.limeDriveThrottle.getRawAxis(1);
		double inputTurn = -ControllerMap.limeDriveTurn.getRawAxis(4);
		outputThrottle = Math.pow(Math.abs(inputThrottle), PIDConstants.DRIVETRAIN_THROTTLE_EXPONET);
		outputTurn = Math.pow(Math.abs(inputTurn), PIDConstants.DRIVETRAIN_TURN_EXPONET);
		if(inputThrottle < 0){
			outputThrottle *= -1;
		}
		
		if(inputTurn < 0){
			outputTurn *= -1;
		}
		
		if (Math.abs(outputThrottle) <= 0.1) {
			Robot.driveTrainSubsystem.arcadeDrive(0.0,
					(outputTurn * 0.45) + ControllerMap.limeDriveAssistTurn.getRawAxis(0) * 0.2);
		} else {
			Robot.driveTrainSubsystem.arcadeDrive((outputThrottle),
					-(outputThrottle * outputTurn));
		}
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

		Robot.driveTrainSubsystem.tankDrive(0, 0);
	}
}