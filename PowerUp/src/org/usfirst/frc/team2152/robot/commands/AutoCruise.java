package org.usfirst.frc.team2152.robot.commands;

import org.usfirst.frc.team2152.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoCruise extends Command {

	private double power = 0;
	private double steering = 0;
	private double distance = 0;
	private double rightSpeed = 0;
	private double leftSpeed = 0;
	private double timeOut = 0;
	private Timer timer = new Timer();
	private boolean checkLeft = true;

	public AutoCruise(double power, double steering, double distance) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveTrainSubsystem);
		if(power > 1){
			power = 1;
		}else if(power < -1){
			power = -1;
		}
		this.power = power;
		if (steering > 1){
			steering = 1;
		}else if(steering < -1){
			steering = -1;
		}
		this.steering = steering;
		this.distance = distance;

	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.driveTrainSubsystem.resetEncoders(true, true);
		rightSpeed = power - (steering / 2);
		leftSpeed = power + (steering / 2);
		if (steering > 0) {
			checkLeft = true;
		} else if (steering < 0) {
			checkLeft = false;
		}
		timeOut = ((distance / 12) / power * 13.08) + 1;
		timer.reset();
		timer.start();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		Robot.driveTrainSubsystem.tankDrive(leftSpeed, rightSpeed);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {

		if (checkLeft == true) {
			if (Robot.driveTrainSubsystem.getLDistance() >= distance || timer.get() >= timeOut) {
				return true;
			} else {
				return false;
			}

		} else {
			if (Robot.driveTrainSubsystem.getRDistance() >= distance || timer.get() >= timeOut) {

				return true;
			} else {
				return false;
			}
		}

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
