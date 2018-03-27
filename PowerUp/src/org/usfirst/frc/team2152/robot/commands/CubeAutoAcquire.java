package org.usfirst.frc.team2152.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CubeAutoAcquire extends CommandGroup {

    public CubeAutoAcquire() {
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
    	addSequential(new ElevatorMoveLow(0.25, 10));
    	addSequential(new AutoCubeMoveLow());
    	addSequential(new NavigateToCube(0.75, 2, 2, 0.45, 4, 10, 9, 25, 5));
		addSequential(new CubeIntakeSensor(1));
		//addSequential(new MoveByEncoder(-20, -20, 0.75, false));

    }
}
