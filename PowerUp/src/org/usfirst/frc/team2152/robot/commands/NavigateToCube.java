package org.usfirst.frc.team2152.robot.commands;

import org.usfirst.frc.team2152.robot.Robot;
import org.usfirst.frc.team2152.robot.utilities.PIDConstants;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class NavigateToCube extends Command implements PIDOutput {
	PIDController cubeDistance;
	PIDController cubeHeading;
	Timer watchdogTimer;
	double headingOutput;
	double distanceOutput;
	double distanceTolerance;
	double headingTolerance;
	double maxForwardSpeed;
	double maxTurnSpeed;
	double toleranceWait;
	double theDistanceSetpoint;
	double timeOut;
	double startDistance;
	/**
	 * Navigate to cube uses the Vision processing on the ODroids to navigate to
	 * the cube, usable in both auto and teleOp
	 * 
	 * @param maxForwardSpeed
	 *            The maximum allowable speed for the distance PID.
	 * @param headingTolerance
	 *            The tolerance for the heading PID.
	 * @param distanceTolerance
	 *            The tolerance for the distance PID
	 * @param maxTurnSpeed
	 *            The maximum allowable speed for the heading PID
	 * @param waitTime
	 *            How long to wait for the watchdog timer.
	 */
	public NavigateToCube(double maxForwardSpeed, double headingTolerance, double distanceTolerance,
			double maxTurnSpeed, double waitTime, double timeOut) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveTrainSubsystem);
		this.headingTolerance = headingTolerance;
		this.distanceTolerance = distanceTolerance;
		this.maxForwardSpeed = maxForwardSpeed;
		this.maxTurnSpeed = maxTurnSpeed;
		toleranceWait = waitTime;
		this.timeOut = timeOut;

	}

	// Called just before this Command runs the first time
	protected void initialize() {
		theDistanceSetpoint = 10; //14.5
		Robot.driveTrainSubsystem.setRampRate(0, 100);
		Robot.driveTrainSubsystem.setRampRate(0.1, 10);
		Robot.driveTrainSubsystem.setBrakeMode(true);
		
		
		cubeDistance = new PIDController(PIDConstants.CAD_kP, PIDConstants.CAD_kI,
				PIDConstants.CAD_kD, Robot.driveTrainSubsystem.getClosestPID(PIDSourceType.kDisplacement),
				this);
		cubeDistance.setContinuous(false);
		cubeDistance.disable();
		cubeDistance.setAbsoluteTolerance(distanceTolerance);
		cubeDistance.setOutputRange(-maxForwardSpeed, maxForwardSpeed);

		cubeHeading = new PIDController(PIDConstants.CAT_Kp, PIDConstants.CAT_Ki,
				PIDConstants.CAT_Kd, Robot.driveTrainSubsystem.getNetXanglePID(PIDSourceType.kDisplacement),
				this);
		cubeHeading.setContinuous(false);
		cubeHeading.disable();

		cubeHeading.setAbsoluteTolerance(headingTolerance);
		cubeHeading.setOutputRange(-maxTurnSpeed, maxTurnSpeed);
		
		cubeDistance.setSetpoint(theDistanceSetpoint);
		cubeHeading.setSetpoint(0);
		
		startDistance = Robot.driveTrainSubsystem.getClosestPID(PIDSourceType.kDisplacement).pidGet();
		
		cubeHeading.enable();
		cubeDistance.enable();

		watchdogTimer = new Timer();
		watchdogTimer.reset();
		watchdogTimer.start();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {		
		Robot.driveTrainSubsystem.arcadeDrive(cubeDistance.get(), cubeHeading.get());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {

		if (Math.abs(Robot.driveTrainSubsystem.getClosestPID(PIDSourceType.kDisplacement).pidGet()) <= distanceTolerance) { 
			System.out.println("Navigate To Cube Exit On Displacement");
			return true;
		}  else if(cubeDistance.onTarget()){
			System.out.println("Navigate To Cube Exit On Target");
			return true;
		} else if (watchdogTimer.get() >= timeOut) {
			System.out.println("Navigate To Cube Exit TimeOut");
			return true; // Returns false for testing
		}else {
			return false;
		}
	}

	// Called once after isFinished returns true
	protected void end() {
		cubeHeading.disable();
		cubeDistance.disable();
		Robot.driveTrainSubsystem.tankDrive(0, 0);
		System.out.println("NavigateToCube End");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		cubeHeading.disable();
		cubeDistance.disable();
		Robot.driveTrainSubsystem.tankDrive(0, 0);
		Robot.driveTrainSubsystem.setRampRate(0, 0);
	}

	@Override
	public void pidWrite(double output) {

	}
}