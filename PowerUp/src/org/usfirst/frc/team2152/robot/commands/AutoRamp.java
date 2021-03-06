package org.usfirst.frc.team2152.robot.commands;

import org.usfirst.frc.team2152.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoRamp extends Command {

	private double power = 0;
	private double steering = 0;
	private double rampRate = 0;
	private double distance = 0;
	private double timeOut = 0;
	private Timer timer;
	private boolean checkLeft = true;
	private double previousLeft = 0;
	private double previousRight = 0;
	private double previousAvg = 0;
	
	
	public AutoRamp(double power, double steering, double rampRate, double distance, double timeOut) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveTrainSubsystem);
		if (power > 1) {
			power = 1;
		} else if (power < -1) {
			power = -1;
		}
		this.power = power;
		// if (steering > 1){
		// steering = 1;
		// }else if(steering < -1){
		// steering = -1;
		// }
		this.timeOut = timeOut;
		this.steering = steering;
		this.rampRate = rampRate;
		this.distance = distance;

	}
	
	public AutoRamp(double power, double steering, double rampRate, double distance) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveTrainSubsystem);
		if (power > 1) {
			power = 1;
		} else if (power < -1) {
			power = -1;
		}
		this.power = power;
		// if (steering > 1){
		// steering = 1;
		// }else if(steering < -1){
		// steering = -1;
		// }
		timeOut = ((distance / 12) / (power * 13.08)) + 4;
		this.steering = steering;
		this.rampRate = rampRate;
		this.distance = distance;

	}

	// Called just before this Command runs the first time
	protected void initialize() {
		previousLeft = Robot.driveTrainSubsystem.getLDistance();
		previousRight = Robot.driveTrainSubsystem.getRDistance();
		previousAvg = (previousLeft + previousRight) / 2;
		timer = new Timer();
		Robot.driveTrainSubsystem.setRampRate(rampRate, 100);
		if (steering > 0) {
			checkLeft = false;
		} else if (steering < 0) {
			checkLeft = true;
		}
		timer.reset();
		timer.start();

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.driveTrainSubsystem.arcadeDrive(-power, steering);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		boolean timerPopped = (timer.get() >= timeOut) ? true : false;
		//System.out.println("TIME OUT " + timeOut + " TIME " + timer.get() + " POPPED " + timerPopped);
		if (steering != 0) {
			if (checkLeft == true) {
				if (Robot.driveTrainSubsystem.getLDistance() - previousLeft >= distance || timerPopped) {
					return true;
				} else {
					return false;
				}

			} else {
				if (Robot.driveTrainSubsystem.getRDistance() - previousRight >= distance || timerPopped) {

					return true;
				} else {
					return false;
				}
			}
		} else if (Robot.driveTrainSubsystem.getAverageDistance() - previousAvg >= distance || timerPopped) {
			return true;
		} else {
			return false;
		}
	}

	// Called once after isFinished returns true
	protected void end() {
		System.out.println("L Distance " + Robot.driveTrainSubsystem.getLDistance() + " R Distance "
				+ Robot.driveTrainSubsystem.getRDistance());
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.driveTrainSubsystem.tankDrive(0, 0);
	}
}
