package org.usfirst.frc.team2152.robot.auto;

import org.usfirst.frc.team2152.robot.Robot;
import org.usfirst.frc.team2152.robot.commands.AutoRamp;
import org.usfirst.frc.team2152.robot.commands.ClearDriveBackLash;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class BaselineLeft extends CommandGroup {

	public BaselineLeft() {
		// Add Commands here:
		// e.g. addSequential(new Command1());
		// addSequential(new Command2());
		// these will run in order.
		requires(Robot.driveTrainSubsystem);
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
		Timer.delay(SmartDashboard.getNumber("Autonomous Delay", 0));
		// If starting from center of left alliance station
		// addSequential(new
		// MoveByEncoder(36,36,PIDConstants.ENCODER_DRIVE_SPEED,false));
		// addSequential(new PreCannedTurn(-45,false));
		// addSequential(new
		// MoveByEncoder(48,48,PIDConstants.ENCODER_DRIVE_SPEED,false));
		// addSequential(new PreCannedTurn(45,false));
		// addSequential(new
		// MoveByEncoder(50,50,PIDConstants.ENCODER_DRIVE_SPEED,false));

		// Direct movement
		// addSequential(new MoveByEncoder(120,120,
		// PIDConstants.ENCODER_DRIVE_SPEED, true, 3.5));

		//addSequential(new ClearDriveBackLash());
		addSequential(new AutoRamp(.4, 0, .5, 120));
	}
}
