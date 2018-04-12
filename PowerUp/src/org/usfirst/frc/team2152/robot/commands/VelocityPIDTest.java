package org.usfirst.frc.team2152.robot.commands;

import org.usfirst.frc.team2152.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class VelocityPIDTest extends Command {

	public VelocityPIDTest() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveTrainSubsystem);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double leftYstick = Robot.m_oi.operatorXbox.getRawAxis(2);
//		Robot.driveTrainSubsystem.setRightSpeed(leftYstick);
//		System.out.println("Input: " + leftYstick + " Encoder Speed: " + Robot.driveTrainSubsystem.getRVelocity());
		 double targetVelocity_UnitsPer100ms = leftYstick * 3000;
		 //System.out.println(targetVelocity_UnitsPer100ms);
		 if (leftYstick > 0.1) {
		 int error = Robot.driveTrainSubsystem.getClosedLoopErrorL();
		 int target = Robot.driveTrainSubsystem.getClosedLoopTargetL();
		 System.out.println("Target: " + target + " Error: " + error);
		 Robot.driveTrainSubsystem.speedControl(targetVelocity_UnitsPer100ms, targetVelocity_UnitsPer100ms);
		 } else {
		 Robot.driveTrainSubsystem.speedControl(0, 0);
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
	}
}
