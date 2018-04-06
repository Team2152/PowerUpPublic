package org.usfirst.frc.team2152.robot.motion_profiling;

import java.io.File;

import org.usfirst.frc.team2152.robot.Robot;
import org.usfirst.frc.team2152.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PathFollower extends Command {

	private DriveTrain driveTrain;
	private TrajectoryController trajectoryController;

	private double timestep = 0.005;

	public PathFollower(DriveTrain driveTrain, File csvLeft, File csvRight) {

		this.driveTrain = driveTrain;

		trajectoryController = new TrajectoryController(driveTrain, csvLeft, csvRight);

	}

	protected void initialize() {

		driveTrain.resetEncoders(true, true);
		Robot.navxSubsystem.resetAngle();
		trajectoryController.resetFollowers();
		trajectoryController.configureFollow();

		trajectoryController.trajectoryNotifier.startPeriodic(timestep);

	}

	protected void execute() {

	}

	protected boolean isFinished() {
		return trajectoryController.isTrajectoryDone();
	}

	// Called once after isFinished returns true
	protected void end() {

		driveTrain.tankDrive(0, 0);
		trajectoryController.trajectoryNotifier.stop();

		System.out.println("trajectory finished");

		trajectoryController.resetFollowers();
		driveTrain.resetEncoders(true, true);
		Robot.navxSubsystem.resetAngle();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {

		end();

	}
}
