package org.usfirst.frc.team2152.robot.commands;

import org.usfirst.frc.team2152.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AcquireCubeLiDAR extends CommandGroup {

	public AcquireCubeLiDAR() {
		// Add Commands here:
		// e.g. addSequential(new Command1());
		// addSequential(new Command2());
		// these will run in order.

		// To run multiple commands at the same time,
		// use addParallel()
		// e.g. addParallel(new Command1());
		// addSequential(new Command2());
		// Command1 and Command2 will run in parallel.

		// A command group will require all of the subsystems that each member
		// would require.
		// e.g. if Command1 requires chassis, and Command2 requires arm,
		// a CommandGroup containing them would require both the chassis and the
		// arm.
		requires(Robot.cubeIntakeSubsystem);
		requires(Robot.cubeMoveSubsystem);
		
		addParallel(new CubeSetOpen());
		addSequential(new AutoCubeMoveLow());
		addSequential(new CubeIntakeSensorLiDAR(0.8));
		addSequential(new AutoCubeMoveHigh());
		addSequential(new CubeIntakeNudgeCheck(0.5));

		System.out.println("ACQ cube ended");
	}
}
