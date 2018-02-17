package org.usfirst.frc.team2152.robot.commands;

import org.usfirst.frc.team2152.robot.Robot;
import org.usfirst.frc.team2152.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2152.robot.utilities.PIDConstants;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveByEncoder extends Command implements PIDOutput {
	double motorSpeed;
	double LeftDistance = 0;
	double RightDistance = 0;
	float setPointHH = 0.0f;
	private boolean clearBacklash = false;
	
	PIDController contrL;
	PIDController contrR;
	PIDController pidHH;

	public MoveByEncoder(double leftDistance, double rightDistance, double speed, boolean clearBacklash) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveTrainSubsystem);
		requires(Robot.navxSubsystem);
		this.clearBacklash = clearBacklash;
		motorSpeed = speed;
		LeftDistance = leftDistance / DriveTrain.DISTANCE_PER_PULSE;
		RightDistance = rightDistance / DriveTrain.DISTANCE_PER_PULSE;
		


	}

	// Called just before this Command runs the first time
	protected void initialize() {
		pidHH = new PIDController(PIDConstants.HH_kP, PIDConstants.HH_kI, PIDConstants.HH_dD,
				Robot.navxSubsystem.getAHRS(), this);
		pidHH.disable();
		pidHH.setInputRange(PIDConstants.HH_IN_MIN, PIDConstants.HH_IN_MAX);
		pidHH.setOutputRange(PIDConstants.HH_OUT_MIN, PIDConstants.HH_OUT_MAX);
		pidHH.setAbsoluteTolerance(PIDConstants.HH_TOLERANCE);
		pidHH.setContinuous(true);
		
		
		
		contrR = new PIDController(PIDConstants.ENCODER_DRIVE_kP, PIDConstants.ENCODER_DRIVE_kI,
				PIDConstants.ENCODER_DRIVE_kD, Robot.driveTrainSubsystem.getRTalonDistancePID(PIDSourceType.kDisplacement),
				e -> {
					Robot.driveTrainSubsystem.setRightSpeed(e - pidHH.get());
				});
		contrR.setAbsoluteTolerance(PIDConstants.ENCODER_DRIVE_kTolerance);

		
		contrL = new PIDController(PIDConstants.ENCODER_DRIVE_kP, PIDConstants.ENCODER_DRIVE_kI,
				PIDConstants.ENCODER_DRIVE_kD, Robot.driveTrainSubsystem.getLTalonDistancePID(PIDSourceType.kDisplacement),
				e -> {
					Robot.driveTrainSubsystem.setLeftSpeed(-e - pidHH.get());
				});
		contrL.setAbsoluteTolerance(PIDConstants.ENCODER_DRIVE_kTolerance);
		Robot.navxSubsystem.getAHRS().reset();
		setPointHH = Robot.navxSubsystem.getAHRS().getYaw();
		pidHH.enable();
		
		
		if (clearBacklash) {
			for (int time = 1; time <= 2; time++) {
				//Backlash stuff
			}
		}
		
		Robot.driveTrainSubsystem.arcadeDrive(0, 0);
			
			Robot.driveTrainSubsystem.resetEncoders(true,true);
			contrR.setSetpoint(RightDistance);
			contrL.setSetpoint(LeftDistance);
			contrL.setOutputRange(-motorSpeed, motorSpeed);
			contrR.setOutputRange(-motorSpeed, motorSpeed);
			contrR.enable();
			contrL.enable();
		}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if ((Math.abs(Robot.driveTrainSubsystem.getLSensorPosition()) >= Math.abs(LeftDistance))
				&& (Math.abs(Robot.driveTrainSubsystem.getRSensorPosition()) >= Math.abs(RightDistance))) {
			return true;
		} else {
			return false;
		}
	}

	// Called once after isFinished returns true
	protected void end() {
		contrL.disable();
		contrR.disable();
		Robot.driveTrainSubsystem.setRightSpeed(0);
		Robot.driveTrainSubsystem.setLeftSpeed(0);

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		contrL.disable();
		contrR.disable();
		Robot.driveTrainSubsystem.setRightSpeed(0);
		Robot.driveTrainSubsystem.setLeftSpeed(0);
	}

	@Override
	public void pidWrite(double output) {
		

	}

}
