package org.usfirst.frc.team2152.robot.auto;

import org.usfirst.frc.team2152.robot.Robot;
import org.usfirst.frc.team2152.robot.commands.AutoRamp;
import org.usfirst.frc.team2152.robot.commands.AutoRampHH;
import org.usfirst.frc.team2152.robot.commands.AutoStop;
import org.usfirst.frc.team2152.robot.commands.ClearDriveBackLash;
import org.usfirst.frc.team2152.robot.commands.CubeExpelTime;
import org.usfirst.frc.team2152.robot.commands.CubeSetClose;
import org.usfirst.frc.team2152.robot.commands.ElevatorMoveHigh;
import org.usfirst.frc.team2152.robot.commands.MoveByEncoder;
import org.usfirst.frc.team2152.robot.commands.PreCannedTurn;
import org.usfirst.frc.team2152.robot.commands.ResetEncoders;
import org.usfirst.frc.team2152.robot.commands.ScaleScore;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ScaleRightDirect extends CommandGroup {

	public ScaleRightDirect() {
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
		requires(Robot.driveTrainSubsystem);
		requires(Robot.cubeIntakeSubsystem);
		requires(Robot.elevatorSubsystem);
		Timer.delay(SmartDashboard.getNumber("Autonomous Delay", 0));

		String scalePosition = Robot.powerUpDashboard.getPlateAssignment("Scale Plate");

		//addSequential(new ClearDriveBackLash());
		addSequential(new ResetEncoders());
		addSequential(new CubeSetClose());
		if (scalePosition.equals("Left")) {
			System.out.println("LEFT");

			// Only Cross baseline
			addSequential(new AutoRamp(.5, 0, 1, 100));
        	addSequential(new AutoStop());

			// addSequential(new

		} else if (scalePosition.equals("Right")) {
			System.out.println("RIGHT");
			// Navigate to right side scale plate
			addSequential(new MoveByEncoder(195, 195, 0.4, false));
        	addSequential(new AutoStop());
			addSequential(new ElevatorMoveHigh(1, 3));
			addSequential(new AutoRamp(.3, 0.12, 1, 56));
        	addSequential(new AutoStop());
			addSequential(new CubeExpelTime(1, 0.85));
			//addSequential(new ScaleScore());

			// Cube delivery

		} else {
			addSequential(new AutoRamp(.5, 0, 1, 100));
        	addSequential(new AutoStop());


		}

	}
}
