package org.usfirst.frc.team2152.robot.auto;

import org.usfirst.frc.team2152.robot.Robot;
import org.usfirst.frc.team2152.robot.commands.AutoRamp;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SwitchRight extends CommandGroup {

    public SwitchRight() {
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
    	
		//addSequential(new ClearDriveBackLash());

    	if (switchPosition.equals("Left")){
    		
    		//Only Cross Baseline
    	
			addSequential(new AutoRamp(.75, 0, 1, 120));

    		
//	    		addSequential(new MoveByEncoder(38,38,PIDConstants.ENCODER_DRIVE_SPEED,false));
//	        	addSequential(new PreCannedTurn(45,false));
//	        	addSequential(new MoveByEncoder(68,68,PIDConstants.ENCODER_DRIVE_SPEED,false));
//	        	addSequential(new PreCannedTurn(-45,false));
//	        	addSequential(new MoveByEncoder(33,33,PIDConstants.ENCODER_DRIVE_SPEED,false));
	        	
    	} else if (switchPosition.equals("Right")){
    		//Navigate to Switch   		
    		
    		addSequential(new AutoRamp(.75, .25, 1, 126));
			addSequential(new AutoRamp(0, .5, 1, 33));
    		
    		
//    			addSequential(new MoveByEncoder(38,38,PIDConstants.ENCODER_DRIVE_SPEED,true));
//    			addSequential(new PreCannedTurn(45,false));
//        	
//    			addSequential(new MoveByEncoder(45,45,PIDConstants.ENCODER_DRIVE_SPEED,false));
//        	
//    			addSequential(new PreCannedTurn(-45 - 5, true));
//    			addSequential(new Delay(0.5));
//    			addSequential(new MoveByEncoder(75,75,PIDConstants.ENCODER_DRIVE_SPEED,false));
//        	
//    			addSequential(new PreCannedTurn(-85,false));
//        	
//    			addSequential(new MoveByEncoder(35,35,PIDConstants.ENCODER_DRIVE_SPEED,false, 2));
    			
        	//Cube Delivery
    			
    			//addSequential(new CubeExpelSensor(1));


    	} else {
    		//Only cross baseline

    		
			addSequential(new AutoRamp(.75, 0, 1, 120));

    		
    		
//	    		addSequential(new MoveByEncoder(38,38,PIDConstants.ENCODER_DRIVE_SPEED,false));
//	        	addSequential(new PreCannedTurn(45,false));
//	        	addSequential(new MoveByEncoder(68,68,PIDConstants.ENCODER_DRIVE_SPEED,false));
//	        	addSequential(new PreCannedTurn(-45,false));
//	        	addSequential(new MoveByEncoder(33,33,PIDConstants.ENCODER_DRIVE_SPEED,false));
    	}
    }
}
