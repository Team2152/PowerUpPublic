package org.usfirst.frc.team2152.robot.commands;

import org.usfirst.frc.team2152.robot.Robot;
import org.usfirst.frc.team2152.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2152.robot.utilities.PIDConstants;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoRampHH extends Command implements PIDOutput {
	double motorSpeed;
	double distance = 0;
	float setPointHH = 0.0f;
	double watchDogTime = 5;
	double HHOutput;
	private double previousLeft = 0;
	private double previousRight = 0;
	private double previousAvg = 0;

	PIDController pidHH;
	Timer timer = new Timer();
	
	public AutoRampHH(double distance, double speed, double timeOut) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveTrainSubsystem);
		requires(Robot.navxSubsystem);
		motorSpeed = speed;
		distance = distance / DriveTrain.DISTANCE_PER_PULSE;
		watchDogTime = timeOut;

	}
	
	public AutoRampHH(double distance, double speed) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveTrainSubsystem);
		requires(Robot.navxSubsystem);
		motorSpeed = speed;
		distance = distance / DriveTrain.DISTANCE_PER_PULSE;
		watchDogTime = ((distance / 12) / (speed * 13.08)) + 5;

	}
	
	

	// Called just before this Command runs the first time
	protected void initialize() {
		previousLeft = Robot.driveTrainSubsystem.getLDistance();
		previousRight = Robot.driveTrainSubsystem.getRDistance();
		previousAvg = (previousLeft + previousRight) / 2;
		
		Robot.driveTrainSubsystem.setRampRate(PIDConstants.AUTO_DRIVE_RAMP_RATE, PIDConstants.AUTO_DRIVE_RAMP_TIMEOUT);
		pidHH = new PIDController(PIDConstants.HH_kP, PIDConstants.HH_kI, PIDConstants.HH_dD,
				Robot.navxSubsystem.getAHRS(), this);
		pidHH.disable();
		pidHH.setInputRange(PIDConstants.HH_IN_MIN, PIDConstants.HH_IN_MAX);
		pidHH.setOutputRange(PIDConstants.HH_OUT_MIN, PIDConstants.HH_OUT_MAX);
		pidHH.setAbsoluteTolerance(PIDConstants.HH_TOLERANCE);
		pidHH.setContinuous(true);
		timer.reset();

		Robot.navxSubsystem.getAHRS().reset();
		setPointHH = Robot.navxSubsystem.getAHRS().getYaw();
		pidHH.enable();

		Robot.driveTrainSubsystem.arcadeDrive(0, 0);

		Robot.driveTrainSubsystem.resetEncoders(true, true);

		timer.start();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.driveTrainSubsystem.arcadeDrive(-motorSpeed, HHOutput);
		

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		boolean timerPopped = (timer.get() >= watchDogTime) ? true : false;
		if (Robot.driveTrainSubsystem.getAverageDistance() - previousAvg >= distance || timerPopped) {
			return true;
		} else {
			return false;
		}
	}

	// Called once after isFinished returns true
	protected void end() {
		pidHH.disable();

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		pidHH.disable();
	}

	@Override
	public void pidWrite(double output) {
		HHOutput = output;

	}

}
