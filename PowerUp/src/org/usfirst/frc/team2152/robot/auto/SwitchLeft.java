package org.usfirst.frc.team2152.robot.auto;

import org.usfirst.frc.team2152.robot.Robot;
import org.usfirst.frc.team2152.robot.commands.Delay;
import org.usfirst.frc.team2152.robot.commands.MoveByEncoder;
import org.usfirst.frc.team2152.robot.commands.PreCannedTurn;
import org.usfirst.frc.team2152.robot.commands.SetCubeIntake;
import org.usfirst.frc.team2152.robot.utilities.PIDConstants;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
    
    	Timer.delay(SmartDashboard.getNumber("Autonomous Delay", 0));
    	String switchPosition = Robot.powerUpDashboard.getPlateAssignment("Switch Plates");
    	if (switchPosition.equals("Left")){
    		
    		//Navigate to Switch   		
    		
			addSequential(new MoveByEncoder(38,38,PIDConstants.ENCODER_DRIVE_SPEED,true));
			addSequential(new PreCannedTurn(45,false));
    	
			addSequential(new MoveByEncoder(45,45,PIDConstants.ENCODER_DRIVE_SPEED,false));
    	
			addSequential(new PreCannedTurn(-45 - 5, true));
			addSequential(new Delay(0.5));
			addSequential(new MoveByEncoder(75,75,PIDConstants.ENCODER_DRIVE_SPEED,false));
    	
			addSequential(new PreCannedTurn(-85,false));
    	
			addSequential(new MoveByEncoder(35,35,PIDConstants.ENCODER_DRIVE_SPEED,false, 2));
			
    	//Cube Delivery
			
			addSequential(new SetCubeIntake(1,1));
	        	
    	} else if (switchPosition.equals("Right")){
    		//Only Cross Baseline
    		
	    		addSequential(new MoveByEncoder(38,38,PIDConstants.ENCODER_DRIVE_SPEED,false));
	        	addSequential(new PreCannedTurn(-45,false));
	        	addSequential(new MoveByEncoder(68,68,PIDConstants.ENCODER_DRIVE_SPEED,false));
	        	addSequential(new PreCannedTurn(45,false));
	        	addSequential(new MoveByEncoder(33,33,PIDConstants.ENCODER_DRIVE_SPEED,false));
    		


    	} else {
    		//Only Cross Baseline
    		
	    		addSequential(new MoveByEncoder(38,38,PIDConstants.ENCODER_DRIVE_SPEED,false));
	        	addSequential(new PreCannedTurn(-45,false));
	        	addSequential(new MoveByEncoder(68,68,PIDConstants.ENCODER_DRIVE_SPEED,false));
	        	addSequential(new PreCannedTurn(45,false));
	        	addSequential(new MoveByEncoder(33,33,PIDConstants.ENCODER_DRIVE_SPEED,false));
    	}
    }
}
