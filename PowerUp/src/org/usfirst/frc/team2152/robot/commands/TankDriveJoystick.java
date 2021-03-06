package org.usfirst.frc.team2152.robot.commands;

import org.usfirst.frc.team2152.robot.OI;
import org.usfirst.frc.team2152.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TankDriveJoystick extends Command {

	public TankDriveJoystick() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires (Robot.driveTrainSubsystem);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.driveTrainSubsystem.tankDrive(Robot.driveTrainJoystickGain.applyGain(Robot.m_oi.driverXbox.getRawAxis(OI.XBOX_LEFT_YAXIS)),
				Robot.driveTrainJoystickGain.applyGain(Robot.m_oi.driverXbox.getRawAxis(OI.XBOX_RIGHT_YAXIS)));
		
		
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
		Robot.driveTrainSubsystem.tankDrive(0,0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.driveTrainSubsystem.tankDrive(0,0);
	}
}
