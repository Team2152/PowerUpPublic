package org.usfirst.frc.team2152.robot.auto;

import org.usfirst.frc.team2152.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SwitchLeft extends CommandGroup {

    public SwitchLeft() {
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
    	String switchPosition = Robot.PLATE_ASSIGNMENT.substring(0,1);
    	if (switchPosition == "L"){
    		// Needs to be tuned for new robot/carpet
    	} else if (switchPosition == "R"){
    		// Needs to be tuned for new robot/carpet
    	} 
    }
}
