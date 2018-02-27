package org.usfirst.frc.team2152.robot.commands;

import org.usfirst.frc.team2152.robot.Robot;
import org.usfirst.frc.team2152.robot.utilities.PIDConstants;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PreCannedTurn extends Command implements PIDOutput {

	PIDController pidPCT;
	Timer timer;
	private double timeOut = 2;
	private double errorFromHeadingPCT = 0.0;
	private float setPointPCT = 0.0f;
	private boolean spinLeft;
	

	public PreCannedTurn(int setpoint, boolean spinLeft) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveTrainSubsystem);
		requires(Robot.navxSubsystem);
		this.setPointPCT = setpoint;
		this.spinLeft = spinLeft;
		timer = new Timer();
		

	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.driveTrainSubsystem.setRampRate(PIDConstants.AUTO_DRIVE_RAMP_RATE,PIDConstants.AUTO_DRIVE_RAMP_TIMEOUT);
		timer.reset();
		timer.start();
		Robot.navxSubsystem.resetAngle();
		Timer.delay(.25);
		pidPCT = new PIDController(PIDConstants.PCT_Kp, PIDConstants.PCT_Ki, PIDConstants.PCT_Kd,
				Robot.navxSubsystem.getAHRS(), this);
		pidPCT.disable();
		pidPCT.setOutputRange(-.75, .75);
		pidPCT.setAbsoluteTolerance(PIDConstants.PCT_TOLERANCE);
		pidPCT.setContinuous(false);
		
		pidPCT.setSetpoint(setPointPCT);

		pidPCT.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (spinLeft) {
			Robot.driveTrainSubsystem.tankDrive(-errorFromHeadingPCT, 0.1);
		} else {
			Robot.driveTrainSubsystem.tankDrive(-0.1, errorFromHeadingPCT);
		}
		Robot.driveTrainSubsystem.tankDrive(-errorFromHeadingPCT, errorFromHeadingPCT);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (pidPCT.getError() <= PIDConstants.PCT_TOLERANCE) {
			return true;
		} else {
			return false;
		}
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.driveTrainSubsystem.tankDrive(0, 0);
		pidPCT.disable();
		Robot.driveTrainSubsystem.resetRampRate();

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.driveTrainSubsystem.tankDrive(0, 0);
		pidPCT.disable();
		Robot.driveTrainSubsystem.resetRampRate();
	}

	@Override
	public void pidWrite(double output) {
		
		errorFromHeadingPCT = output;

	}
}
