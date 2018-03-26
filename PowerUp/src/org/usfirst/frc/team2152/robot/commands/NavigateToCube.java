package org.usfirst.frc.team2152.robot.commands;

import org.usfirst.frc.team2152.robot.Robot;
import org.usfirst.frc.team2152.robot.network.Vars;
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
	double watchdogDistance;
	double toleranceWait;
	double theDistanceSetpoint = 14.5;
	private boolean reachedVisionLimit = false;
	private double visionLimit = 25;
	private double encoderAdjustment;

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
	 * @param watchdogDistance
	 *            The distance at which to start the watchdog timer.
	 * @param encoderAdjustment
	 *            How far to move the robot after the PID cutoff.
	 * @param visionLimit
	 *            When to cut off the PID.
	 */
	public NavigateToCube(double maxForwardSpeed, double headingTolerance, double distanceTolerance,
			double maxTurnSpeed, double waitTime, double watchdogDistance, double encoderAdjustment,
			double visionLimit) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveTrainSubsystem);
		requires(Robot.cubeIntakeSubsystem);
		requires(Robot.cubeMoveSubsystem);
		this.headingTolerance = headingTolerance;
		this.distanceTolerance = distanceTolerance;
		this.maxForwardSpeed = maxForwardSpeed;
		this.maxTurnSpeed = maxTurnSpeed;
		toleranceWait = waitTime;
		this.watchdogDistance = watchdogDistance;
		this.encoderAdjustment = encoderAdjustment;
		this.visionLimit = visionLimit;
		// System.out.println(" NavigateToGear Constructor Initialized");

		// LiveWindow.addActuator("Gear Distance", "Gear", gearDistance);
		// LiveWindow.addActuator("Gear Angle", "Gear", gearHeading);

	}

	// Called just before this Command runs the first time
	protected void initialize() {
		reachedVisionLimit = false;
		//SmartDashboard.putBoolean("Watchdog Exit Gear Auto", false);
		//Robot.steamworksDashboard.putWatchdogExitGearAuto(false);
		watchdogTimer = new Timer();
		watchdogTimer.reset();
		watchdogTimer.start();
		cubeDistance = new PIDController(PIDConstants.ENCODER_DRIVE_kP, PIDConstants.ENCODER_DRIVE_kI,
				PIDConstants.ENCODER_DRIVE_kD, Robot.driveTrainSubsystem.getNetDistancePID(PIDSourceType.kDisplacement),
				this);
		cubeDistance.setContinuous(false);
		cubeDistance.disable();
		cubeDistance.setAbsoluteTolerance(distanceTolerance);
		cubeDistance.setOutputRange(-maxForwardSpeed, maxForwardSpeed);

		cubeHeading = new PIDController(PIDConstants.HH_kP, PIDConstants.HH_kI,
				PIDConstants.HH_dD, Robot.driveTrainSubsystem.getNetXanglePID(PIDSourceType.kDisplacement),
				this);
		cubeHeading.setContinuous(false);
		cubeHeading.disable();

		cubeHeading.setAbsoluteTolerance(headingTolerance);
		cubeHeading.setOutputRange(-maxTurnSpeed, maxTurnSpeed);
		cubeDistance.setSetpoint(theDistanceSetpoint);
		cubeHeading.setSetpoint(0);
		cubeHeading.enable();
		cubeDistance.enable();

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (Robot.udp.getValue(Vars.Cube.Double.Distance) <= visionLimit) {
			if (reachedVisionLimit == false) {
				cubeDistance.disable();
				cubeHeading.disable();
				Robot.driveTrainSubsystem.resetEncoders(true, true);
				reachedVisionLimit = true;
			}
			// System.out.println(Robot.driveTrainSubsystem.getAverageDistance());
			Robot.driveTrainSubsystem.arcadeDrive(-0.35, 0);
		} else {
			Robot.driveTrainSubsystem.arcadeDrive(cubeDistance.get(), -cubeHeading.get());
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {

		boolean bWatchdogExit = (watchdogTimer.get() > toleranceWait);

		double averageDistance = Robot.driveTrainSubsystem.getAverageDistance();
		if (bWatchdogExit || (reachedVisionLimit && averageDistance >= encoderAdjustment)) {
			if (bWatchdogExit) {
			//	Robot.steamworksDashboard.putWatchdogExitGearAuto(true);
			}
			return true;
		} else {
			return false;
		}
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.driveTrainSubsystem.tankDrive(0, 0);
		cubeHeading.disable();
		cubeDistance.disable();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.driveTrainSubsystem.tankDrive(0, 0);
		cubeHeading.disable();
		cubeDistance.disable();
	}

	@Override
	public void pidWrite(double output) {

	}
}