package org.usfirst.frc.team2152.robot.commands;

import org.usfirst.frc.team2152.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCubeMoveHighWithCube extends CommandGroup {

    public AutoCubeMoveHighWithCube() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	requires(Robot.cubeMoveSubsystem);
		requires(Robot.cubeIntakeSubsystem);
		
    	System.out.println("USING CUBE IN GAINS");
		addParallel(new CubeIntakeDumb(.8));
		addSequential(new CubeMoveHighByTime(1, 0.2));
		addSequential(new CubeMoveHighByTime(.65, 0.2));
		addSequential(new CubeMoveHigh(.4));
		addParallel(new CubeIntakeDumb(0));

    }
}
