package org.usfirst.frc.team2152.robot.commands;

import org.usfirst.frc.team2152.robot.Robot;

import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CubeIntakeSensorLiDAR extends Command {
	private double intakeSpeed = 0;
	private double watchdogTime = 1;
	private boolean bGotACube = false;
	private boolean isClamped = false;
	private boolean lidarSaw = false;
	private Timer timer;
	private Timer watchdog;
	private Timer lidar;

	public CubeIntakeSensorLiDAR(double intakeSpeed) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.cubeIntakeSubsystem);
		requires(Robot.cubeMoveSubsystem);
		this.intakeSpeed = intakeSpeed;

	}

	// Called just before this Command runs the first time
	protected void initialize() {
		timer = new Timer();
		watchdog = new Timer();
		lidar = new Timer();
		isClamped = false;
		Robot.cubeIntakeSubsystem.cubeSolenoidOpen();
		watchdog.stop();
		lidar.reset();
		lidarSaw = false;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {			 
		//System.out.println(lidar.get());
		//System.out.println(Robot.driveTrainSubsystem.getClosestPID(PIDSourceType.kDisplacement).pidGet());
		double lidarDistance = Robot.driveTrainSubsystem.getClosestPID(PIDSourceType.kDisplacement).pidGet();
		if (lidarDistance <= 10 ){
			if(lidarSaw == false){
				lidar.start();
				 lidarSaw = true;
			}
		} else {
			lidar.stop();
			lidar.reset();
			lidarSaw = false;
		}
		// Robot.cubeIntakeSubsystem.cubeIntakeSensor(intakeSpeed);
		Robot.cubeIntakeSubsystem.cubeIntakeMove(intakeSpeed);
		// Robot.cubeIntakeSubsystem.cubeSolenoidSensor();
		// Robot.cubeIntakeSubsystem.cubeSolenoidSensor();
		// if ((Robot.cubeIntakeSubsystem.cubeDetectOutLeft() == true
		// || Robot.cubeIntakeSubsystem.cubeDetectOutRight() == true)
		// || (lidarDistance <= 15 && (lidar.get() >= 1))) {
		if (lidarDistance <= 10 && (lidar.get() >= 0.25)) {
		 //System.out.println("CUBE CLAMP");
			Robot.cubeIntakeSubsystem.cubeSolenoidClose();
			if (isClamped == false) {
				isClamped = true;
				watchdog.reset();
				watchdog.start();
			}
		}

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (Robot.cubeIntakeSubsystem.cubeDetectIn() == true) {
			// System.out.println("Cube Detect In");
			if (bGotACube == false) {
				timer.stop();
				timer.reset();
				timer.start();
				bGotACube = true;
			}

			if (bGotACube == true && timer.get() >= 1) {
				return true;
			}
		}

		if (watchdog.get() >= watchdogTime) {
			// System.out.println("CUBE WATCH DOG");
			return true;
		}

		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		watchdog.stop();
		timer.stop();
		Robot.cubeIntakeSubsystem.cubeIntakeMove(0.0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		watchdog.stop();
		timer.stop();
		Robot.cubeIntakeSubsystem.cubeIntakeMove(0);
	}
}
