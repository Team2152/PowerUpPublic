package org.usfirst.frc.team2152.robot.auto;


import org.usfirst.frc.team2152.robot.commands.MoveByEncoder;
import org.usfirst.frc.team2152.robot.commands.PreCannedTurn;
import org.usfirst.frc.team2152.robot.utilities.PIDConstants;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class BaselineCenter extends CommandGroup {

    public BaselineCenter() {
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
    	Timer.delay(SmartDashboard.getNumber("Autonomous Delay", 0));
    	addSequential(new MoveByEncoder(40,40,PIDConstants.ENCODER_DRIVE_SPEED,false));
    	addSequential(new PreCannedTurn(45,false));
    	addSequential(new MoveByEncoder(69,69,PIDConstants.ENCODER_DRIVE_SPEED,false));
    	addSequential(new PreCannedTurn(45,false));
    	addSequential(new MoveByEncoder(80,80,PIDConstants.ENCODER_DRIVE_SPEED,false));
    	addSequential(new PreCannedTurn(-90,false));
    	addSequential(new MoveByEncoder(34,34,PIDConstants.ENCODER_DRIVE_SPEED,false));



    }
}
