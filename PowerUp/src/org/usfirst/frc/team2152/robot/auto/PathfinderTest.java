package org.usfirst.frc.team2152.robot.auto;

import org.usfirst.frc.team2152.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;

/**
 *
 */
public class PathfinderTest extends Command {
	Trajectory trajectory;
	TankModifier modifier;
	Trajectory left;
	Trajectory right;
	EncoderFollower leftEnc;
	EncoderFollower rightEnc;

	public PathfinderTest() {
		Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC,
				Trajectory.Config.SAMPLES_HIGH, 0.05, 1.7, 2.0, 60.0);
		Waypoint[] points = new Waypoint[] { new Waypoint(-4, -1, Pathfinder.d2r(-45)), new Waypoint(-2, -2, 0),
				new Waypoint(0, 0, 0) };

		trajectory = Pathfinder.generate(points, config);

		// Wheelbase Width = 0.5m
		modifier = new TankModifier(trajectory).modify(0.5);

		// Do something with the new Trajectories...
		left = modifier.getLeftTrajectory();
		right = modifier.getRightTrajectory();
		leftEnc = new EncoderFollower(modifier.getLeftTrajectory());
		rightEnc = new EncoderFollower(modifier.getRightTrajectory());

		requires(Robot.driveTrainSubsystem);

		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		// Encoder Position is the current, cumulative position of your encoder. If
		// you're using an SRX, this will be the
		// 'getEncPosition' function.
		// 1000 is the amount of encoder ticks per full revolution
		// Wheel Diameter is the diameter of your wheels (or pulley for a track system)
		// in meters
		leftEnc.configureEncoder(Robot.driveTrainSubsystem.getLSensorPosition(), 4069,
				12.566370614359172953850573533118);
		rightEnc.configureEncoder(Robot.driveTrainSubsystem.getRSensorPosition(), 4069,
				12.566370614359172953850573533118);

		// The first argument is the proportional gain. Usually this will be quite high
		// The second argument is the integral gain. This is unused for motion profiling
		// The third argument is the derivative gain. Tweak this if you are unhappy with
		// the tracking of the trajectory
		// The fourth argument is the velocity ratio. This is 1 over the maximum
		// velocity you provided in the
		// trajectory configuration (it translates m/s to a -1 to 1 scale that your
		// motors can read)
		// The fifth argument is your acceleration gain. Tweak this if you want to get
		// to a higher or lower speed quicker
		leftEnc.configurePIDVA(1.0, 0.0, 0.0, 1 / 0.45, 0);
		rightEnc.configurePIDVA(1.0, 0.0, 0.0, 1 / 0.45, 0);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double l = leftEnc.calculate(Robot.driveTrainSubsystem.getLSensorPosition());
		double r = rightEnc.calculate(Robot.driveTrainSubsystem.getLSensorPosition());

		double gyro_heading = Robot.navxSubsystem.getAngle(); // Assuming the gyro is giving a value in degrees
		double desired_heading = Pathfinder.r2d(leftEnc.getHeading()); // Should also be in degrees

		double angleDifference = Pathfinder.boundHalfDegrees(desired_heading - gyro_heading);
		double turn = 0.8 * (-1.0 / 80.0) * angleDifference;

		Robot.driveTrainSubsystem.tankDrive((l + turn), (r - turn));
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
