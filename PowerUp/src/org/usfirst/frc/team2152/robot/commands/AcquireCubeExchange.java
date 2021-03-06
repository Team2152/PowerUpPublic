package org.usfirst.frc.team2152.robot.commands;

import org.usfirst.frc.team2152.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AcquireCubeExchange extends CommandGroup {

    public AcquireCubeExchange() {
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
    	Robot.cubeIntakeSubsystem.cubeSolenoidOpen();
		addSequential(new AutoCubeMoveLow());
		//addSequential(new CubeIntakeSensorLiDAR(0.8));
		addSequential(new CubeIntakeSensor(0.8));
		//		if (Robot.cubeIntakeSubsystem.cubeDetectIn() || Robot.cubeIntakeSubsystem.cubeDetectOutLeft()
//				|| Robot.cubeIntakeSubsystem.cubeDetectOutRight()) {
//			addSequential(new CubeIntakeCheck(0.5));
//		}

		System.out.println("ACQE cube ended");
	}
    
}