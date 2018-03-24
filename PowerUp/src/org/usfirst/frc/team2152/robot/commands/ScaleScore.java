package org.usfirst.frc.team2152.robot.commands;

import org.usfirst.frc.team2152.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ScaleScore extends CommandGroup {

    public ScaleScore() {
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
    	requires(Robot.driveTrainSubsystem);
    	requires(Robot.elevatorSubsystem);
    	requires(Robot.cubeMoveSubsystem);
    	requires(Robot.cubeIntakeSubsystem);
    	
    	addSequential(new ElevatorMoveHigh(.65));
    	addSequential(new MoveByEncoder(5, 5, .25, false, 2));
    	addSequential(new CubeExpelSensor(1));
    	addSequential(new MoveByEncoder(-7, -7, .25, false, 2));
    	addSequential(new ElevatorMoveLow(.25));

    	
    }
}
