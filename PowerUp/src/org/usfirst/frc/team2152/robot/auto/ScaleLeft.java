package org.usfirst.frc.team2152.robot.auto;

import org.usfirst.frc.team2152.robot.Robot;
import org.usfirst.frc.team2152.robot.commands.AutoRamp;
import org.usfirst.frc.team2152.robot.commands.ClearDriveBackLash;
import org.usfirst.frc.team2152.robot.commands.PreCannedTurn;
import org.usfirst.frc.team2152.robot.commands.ScaleScore;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ScaleLeft extends CommandGroup {

    public ScaleLeft() {
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
    	String scalePosition = Robot.powerUpDashboard.getPlateAssignment("Scale Plate");
    	
		addSequential(new ClearDriveBackLash());
		
    	if (scalePosition.equals("Left")){
    		// Navigate to left scale plate
    		
    		addSequential(new AutoRamp(.75, 0, 1, 325));
    		addSequential(new PreCannedTurn(90,false));
    		addSequential(new ScaleScore());
    		
    		
    		
    		
//    		addSequential(new MoveByEncoder(36,36,PIDConstants.ENCODER_DRIVE_SPEED,false));
//    		addSequential(new PreCannedTurn(-45,false));
//    		addSequential(new MoveByEncoder(48,48,PIDConstants.ENCODER_DRIVE_SPEED,false));
//    		addSequential(new PreCannedTurn(45,false));
//    		addSequential(new MoveByEncoder(252,252,PIDConstants.ENCODER_DRIVE_SPEED,false));
//    		addSequential(new PreCannedTurn(90,false));
//    		addSequential(new MoveByEncoder(20,20,PIDConstants.ENCODER_DRIVE_SPEED,false));
//    		
    		// Cube Delivery
    	} else if (scalePosition.equals("Right")){
    		addSequential(new AutoRamp(.75 ,0 , 1, 215));
    		addSequential(new AutoRamp(.75, .25, 1, 50));
    		addSequential(new AutoRamp(.75, 0, 1, 215));
    		addSequential(new AutoRamp(.75, -.25, 1, 100));
    		addSequential(new ScaleScore());
    		// Only cross baseline
//    		addSequential(new MoveByEncoder(36,36,PIDConstants.ENCODER_DRIVE_SPEED,false));
//        	addSequential(new PreCannedTurn(-45,false));
//        	addSequential(new MoveByEncoder(48,48,PIDConstants.ENCODER_DRIVE_SPEED,false));
//        	addSequential(new PreCannedTurn(45,false));
//        	addSequential(new MoveByEncoder(50,50,PIDConstants.ENCODER_DRIVE_SPEED,false));
    	} else {
    		addSequential(new AutoRamp(.75, 0, 1, 100));
    		
//    		addSequential(new MoveByEncoder(36,36,PIDConstants.ENCODER_DRIVE_SPEED,false));
//        	addSequential(new PreCannedTurn(-45,false));
//        	addSequential(new MoveByEncoder(48,48,PIDConstants.ENCODER_DRIVE_SPEED,false));
//        	addSequential(new PreCannedTurn(45,false));
//        	addSequential(new MoveByEncoder(50,50,PIDConstants.ENCODER_DRIVE_SPEED,false));
    	}






    }
}
