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
	 double watchDogTime = 5;

	 PIDController contrL;
	 PIDController contrR;
	 PIDController pidHH;
	 Timer timer;

	 public MoveByEncoder(double leftDistance, double rightDistance, double speed, boolean clearBacklash, double watchDogTime) {
		 // Use requires() here to declare subsystem dependencies
		 // eg. requires(chassis);
		 requires(Robot.driveTrainSubsystem);
		 requires(Robot.navxSubsystem);
		 this.clearBacklash = clearBacklash;
		 motorSpeed = speed;
		 LeftDistance = leftDistance / DriveTrain.DISTANCE_PER_PULSE;
		 RightDistance = rightDistance / DriveTrain.DISTANCE_PER_PULSE;
		 this.watchDogTime = watchDogTime;

	 }
	 
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
		 timer = new Timer();
		 Robot.driveTrainSubsystem.setRampRate(PIDConstants.AUTO_DRIVE_RAMP_RATE,PIDConstants.AUTO_DRIVE_RAMP_TIMEOUT);
		 Robot.driveTrainSubsystem.resetEncoders(true,true);
		 pidHH = new PIDController(PIDConstants.HH_kP, PIDConstants.HH_kI, PIDConstants.HH_dD,
				 Robot.navxSubsystem.getAHRS(), this);
		 pidHH.disable();
		 pidHH.setInputRange(PIDConstants.HH_IN_MIN, PIDConstants.HH_IN_MAX);
		 pidHH.setOutputRange(PIDConstants.HH_OUT_MIN, PIDConstants.HH_OUT_MAX);
		 pidHH.setAbsoluteTolerance(PIDConstants.HH_TOLERANCE);
		 pidHH.setContinuous(true);

		 timer.reset();

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
				 Robot.driveTrainSubsystem.arcadeDrive(0.5, 0);
				 Timer.delay(0.1);
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
		 
		 timer.start();
	 }

	 // Called repeatedly when this Command is scheduled to run
	 protected void execute() {
	 }

	 // Make this return true when this Command no longer needs to run execute()
	 protected boolean isFinished() {
		 if (contrR.onTarget() && contrL.onTarget()) {
			 return true;
		 } else if (timer.get() >= watchDogTime) {
			 return true;
	     }else {
			 return false;
		 }
	 }

	 // Called once after isFinished returns true
	 protected void end() {
		 contrL.disable();
		 contrR.disable();
		 Robot.driveTrainSubsystem.setRightSpeed(0);
		 Robot.driveTrainSubsystem.setLeftSpeed(0);
		 Robot.driveTrainSubsystem.setRampRate(PIDConstants.CONTROLLER_DRIVE_RAMP_RATE, PIDConstants.CONTROLLER_DRIVE_RAMP_TIMEOUT);

	 }

	 // Called when another command which requires one or more of the same
	 // subsystems is scheduled to run
	 protected void interrupted() {
		 contrL.disable();
		 contrR.disable();
		 Robot.driveTrainSubsystem.setRightSpeed(0);
		 Robot.driveTrainSubsystem.setLeftSpeed(0);
		 Robot.driveTrainSubsystem.setRampRate(PIDConstants.CONTROLLER_DRIVE_RAMP_RATE, PIDConstants.CONTROLLER_DRIVE_RAMP_TIMEOUT);

	 }

	 @Override
	 public void pidWrite(double output) {


	 }

 }
