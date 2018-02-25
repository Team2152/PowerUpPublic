package org.usfirst.frc.team2152.robot.auto;

import org.usfirst.frc.team2152.robot.Robot;
import org.usfirst.frc.team2152.robot.commands.MoveByEncoder;
import org.usfirst.frc.team2152.robot.commands.PreCannedTurn;
import org.usfirst.frc.team2152.robot.utilities.PIDConstants;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ScaleRight extends CommandGroup {

    public ScaleRight() {
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
    	String switchPosition = Robot.powerUpDashboard.getPlateAssignment("Switch Plates");
    	if (switchPosition == "Left"){
    		// Only Cross baseline
    		addSequential(new MoveByEncoder(38,38,PIDConstants.ENCODER_DRIVE_SPEED,false));
        	addSequential(new PreCannedTurn(45,false));
        	addSequential(new MoveByEncoder(68,68,PIDConstants.ENCODER_DRIVE_SPEED,false));
        	addSequential(new PreCannedTurn(-45,false));
        	addSequential(new MoveByEncoder(33,33,PIDConstants.ENCODER_DRIVE_SPEED,false));
    	} else if (switchPosition == "Right"){
    		// Navigate to right side scale plate
    		addSequential(new MoveByEncoder(38,38,PIDConstants.ENCODER_DRIVE_SPEED,false));
        	addSequential(new PreCannedTurn(45,false));
        	addSequential(new MoveByEncoder(68,68,PIDConstants.ENCODER_DRIVE_SPEED,false));
        	addSequential(new PreCannedTurn(-45,false));
        	addSequential(new MoveByEncoder(236,236,PIDConstants.ENCODER_DRIVE_SPEED,false));
        	addSequential(new PreCannedTurn(-90,false));
        	addSequential(new MoveByEncoder(63,63,PIDConstants.ENCODER_DRIVE_SPEED,false));
        	
        	// Cube delivery 

    	} else {
    		addSequential(new MoveByEncoder(38,38,PIDConstants.ENCODER_DRIVE_SPEED,false));
        	addSequential(new PreCannedTurn(45,false));
        	addSequential(new MoveByEncoder(68,68,PIDConstants.ENCODER_DRIVE_SPEED,false));
        	addSequential(new PreCannedTurn(-45,false));
        	addSequential(new MoveByEncoder(33,33,PIDConstants.ENCODER_DRIVE_SPEED,false));
    	}

    }
}