package org.usfirst.frc.team2152.robot.auto;

import org.usfirst.frc.team2152.robot.Robot;
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
    
    	Timer.delay(SmartDashboard.getNumber("Auto Delay", 0));
    	String switchPosition = Robot.powerUpDashboard.getPlateAssignment("Switch Plates");
    	if (switchPosition == "Left"){
    		//Navigate to left switch plate
    		
    		addSequential(new SetCubeIntake(.5));
    		Timer.delay(.25);
    		addSequential(new SetCubeIntake(0));
    		
        	addSequential(new MoveByEncoder(36,36,PIDConstants.ENCODER_DRIVE_SPEED,false));
        	addSequential(new PreCannedTurn(-45,false));
        	addSequential(new MoveByEncoder(48,48,PIDConstants.ENCODER_DRIVE_SPEED,false));
        	addSequential(new PreCannedTurn(45,false));
        	addSequential(new MoveByEncoder(93,93,PIDConstants.ENCODER_DRIVE_SPEED,false));
        	addSequential(new PreCannedTurn(90,false));
        	addSequential(new MoveByEncoder(27,27,PIDConstants.ENCODER_DRIVE_SPEED,false));

        	//Cube Delivery Commands
        	addSequential(new SetCubeIntake(-1));
    		Timer.delay(1);
    		addSequential(new SetCubeIntake(0));
    	} else if (switchPosition == "Right"){
    		//Only Cross Baseline
    		addSequential(new MoveByEncoder(36,36,PIDConstants.ENCODER_DRIVE_SPEED,false));
        	addSequential(new PreCannedTurn(-45,false));
        	addSequential(new MoveByEncoder(48,48,PIDConstants.ENCODER_DRIVE_SPEED,false));
        	addSequential(new PreCannedTurn(45,false));
        	addSequential(new MoveByEncoder(50,50,PIDConstants.ENCODER_DRIVE_SPEED,false));
    	} else {
    		addSequential(new MoveByEncoder(36,36,PIDConstants.ENCODER_DRIVE_SPEED,false));
        	addSequential(new PreCannedTurn(-45,false));
        	addSequential(new MoveByEncoder(48,48,PIDConstants.ENCODER_DRIVE_SPEED,false));
        	addSequential(new PreCannedTurn(45,false));
        	addSequential(new MoveByEncoder(50,50,PIDConstants.ENCODER_DRIVE_SPEED,false));
    	}
    }
}
