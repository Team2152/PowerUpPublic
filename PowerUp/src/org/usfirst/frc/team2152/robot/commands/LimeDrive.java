package org.usfirst.frc.team2152.robot.commands;

import org.usfirst.frc.team2152.robot.Robot;
import org.usfirst.frc.team2152.robot.utilities.PIDConstants;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
		Robot.driveTrainSubsystem.setRampRate(PIDConstants.CONTROLLER_DRIVE_RAMP_RATE, PIDConstants.CONTROLLER_DRIVE_RAMP_TIMEOUT);;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double outputThrottle = 0;
		double outputTurn = 0;
		outputThrottle = Robot.driveTrainJoystickGain.applyGain(Robot.m_oi.driverXbox.getRawAxis(1));
		outputTurn = -Robot.driveTrainJoystickGain.applyGain(Robot.m_oi.driverXbox.getRawAxis(4));
		

		if (Robot.m_oi.driverXbox.getRawButton(5) || (Math.abs(outputThrottle) <= 0.25)) {
			Robot.driveTrainSubsystem.arcadeDrive(0.0, (outputTurn * 0.66));
		} else {
			Robot.driveTrainSubsystem.arcadeDrive((outputThrottle), -(outputThrottle * outputTurn));
		}
		
		if(Robot.m_oi.driverXbox.getRawButton(1)){
			Robot.driveTrainSubsystem.resetEncoders(true, true);
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